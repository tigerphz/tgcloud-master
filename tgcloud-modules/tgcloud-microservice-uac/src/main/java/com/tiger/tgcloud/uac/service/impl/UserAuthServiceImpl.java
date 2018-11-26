package com.tiger.tgcloud.uac.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tiger.tgcloud.base.constant.MqTopicConstants;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import com.tiger.tgcloud.dmc.api.service.MqMessageFeignApi;
import com.tiger.tgcloud.uac.api.exceptions.UacBizException;
import com.tiger.tgcloud.uac.api.model.dto.UserRegisterDto;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.model.enums.EmailTemplateEnum;
import com.tiger.tgcloud.uac.model.enums.UserSourceEnum;
import com.tiger.tgcloud.uac.model.enums.UserStatusEnum;
import com.tiger.tgcloud.uac.mq.EmailProducer;
import com.tiger.tgcloud.uac.repository.UserRepository;
import com.tiger.tgcloud.uac.service.RedisService;
import com.tiger.tgcloud.uac.service.UserAuthService;
import com.tiger.tgcloud.uac.utils.Md5Util;
import com.tiger.tgcloud.utils.PublicUtil;
import com.tiger.tgcloud.utils.RedisKeyUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:15
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAuthServiceImpl extends BaseService implements UserAuthService {

    @Resource
    private RedisService redisService;

    @Autowired
    private EmailProducer emailProducer;

    @Value("${tgcloud.auth.active-user-url}")
    private String activeUserUrl;

    @Autowired
    private MqMessageFeignApi mqMessageFeignApi;

    @Autowired
    private UserRepository userRepository;

    /**
     * 注册用户.
     *
     * @param registerDto the register dto
     */
    @Override
    public void register(UserRegisterDto registerDto) {
        // 校验注册信息
        validateRegisterInfo(registerDto);

        String mobileNo = registerDto.getMobileNo();
        String email = registerDto.getEmail();
        Date now = new Date();

        // 封装注册信息
        long id = generateId();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registerDto.getUserName());
        userInfo.setPasswordhash(Md5Util.encrypt(registerDto.getPassword()));
        userInfo.setMobile(mobileNo);
        userInfo.setStatus(UserStatusEnum.ENABLE.getKey());
        userInfo.setSource(UserSourceEnum.REGISTER.getKey());
        userInfo.setEmail(email);
        userInfo.setId(id);

        userInfo.setCreatorId(id);
        userInfo.setCreator(registerDto.getUserName());
        userInfo.setCreatedTime(now);

        userInfo.setUpdateOperatorId(id);
        userInfo.setUpdateOperator(registerDto.getUserName());
        userInfo.setUpdateTime(now);

        userRepository.save(userInfo);

        // 发送激活邮件
        String activeToken = PublicUtil.uuid() + super.generateId();
        redisService.setKey(RedisKeyUtil.getActiveUserKey(activeToken), email, 1, TimeUnit.DAYS);

        Map<String, Object> param = Maps.newHashMap();
        param.put("loginName", registerDto.getUserName());
        param.put("email", registerDto.getEmail());
        param.put("activeUserUrl", activeUserUrl + activeToken);
        param.put("dateTime", DateUtil.formatDateTime(new Date()));

        Set<String> to = Sets.newHashSet();
        to.add(registerDto.getEmail());

        MqMessageData mqMessageData = emailProducer.sendEmailMq(to, EmailTemplateEnum.ACTIVE_USER, MqTopicConstants.MqTagEnum.ACTIVE_USER, param);
        mqMessageData.setCreatorId(id);
        mqMessageData.setCreator(registerDto.getUserName());
        mqMessageData.setCreatedTime(now);

        mqMessageData.setUpdateOperatorId(id);
        mqMessageData.setUpdateOperator(registerDto.getUserName());
        mqMessageData.setUpdateTime(now);

        mqMessageFeignApi.saveAndSendMqMessage(mqMessageData);
    }

    private void validateRegisterInfo(UserRegisterDto registerDto) {
        Preconditions.checkArgument(!StringUtils.isEmpty(registerDto.getPassword()), ErrorCodeEnum.UAC10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(registerDto.getConfirmPwd()), ErrorCodeEnum.UAC10011009.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(registerDto.getRegisterSource()), "验证类型错误");
        Preconditions.checkArgument(registerDto.getPassword().equals(registerDto.getConfirmPwd()), "两次密码不一致");

        UserInfo uacUser = new UserInfo();
        uacUser.setUsername(registerDto.getUserName());
        int count = userRepository.selectCount(uacUser);
        if (count > 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10011012);
        }

        uacUser = new UserInfo();
        uacUser.setMobile(registerDto.getMobileNo());
        count = userRepository.selectCount(uacUser);
        if (count > 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10011013);
        }

        uacUser = new UserInfo();
        uacUser.setEmail(registerDto.getEmail());
        count = userRepository.selectCount(uacUser);
        if (count > 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10011019);
        }
    }

    @Override
    public void activeUser(String activeUserToken) {
        Preconditions.checkArgument(!StringUtils.isEmpty(activeUserToken), "激活用户失败");

        String activeUserKey = RedisKeyUtil.getActiveUserKey(activeUserToken);

        String email = redisService.getKey(activeUserKey);

        if (StringUtils.isEmpty(email)) {
            throw new UacBizException(ErrorCodeEnum.UAC10011030);
        }
        // 修改用户状态, 绑定访客角色
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);

        userInfo = userRepository.selectOne(userInfo);
        if (userInfo == null) {
            logger.error("找不到用户信息. email={}", email);
            throw new UacBizException(ErrorCodeEnum.UAC10011004, email);
        }

        UserInfo update = new UserInfo();
        update.setId(userInfo.getId());
        update.setStatus(UserStatusEnum.ENABLE.getKey());

        //更新保存用户状态
        boolean result = userRepository.updateByPrimaryKeySelective(update);
        update.setUpdateOperatorId(update.getId());
        update.setUpdateOperator(update.getUsername());
        update.setUpdateTime(new Date());
        if (!result) {
            throw new UacBizException(ErrorCodeEnum.UAC10011038, update.getId());
        }

        Map<String, Object> param = Maps.newHashMap();
        param.put("loginName", userInfo.getUsername());
        param.put("dateTime", DateUtil.formatDateTime(new Date()));

        Set<String> to = Sets.newHashSet();
        to.add(userInfo.getEmail());

        MqMessageData mqMessageData = emailProducer.sendEmailMq(to, EmailTemplateEnum.ACTIVE_USER_SUCCESS, MqTopicConstants.MqTagEnum.ACTIVE_USER_SUCCESS, param);
        mqMessageFeignApi.saveAndSendMqMessage(mqMessageData);

        // 删除 activeUserToken
        redisService.deleteKey(activeUserKey);
    }

    /**
     * 检测邮箱是否可用
     *
     * @param email
     * @return
     */
    @Override
    public Boolean checkEmailActive(String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus(UserStatusEnum.ENABLE.getKey());
        userInfo.setEmail(email);
        return userRepository.selectCount(userInfo) > 0;
    }
}
