package com.tiger.tgcloud.dmc.api.model.domain;

import com.tiger.tgcloud.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 15:02
 * @version: V1.0
 * @modified by:
 */
@Alias("MicroService")
@Table(name = "dmc_micro_service")
@Data
@NoArgsConstructor
public class MicroServiceInfo extends BaseEntity {

    private static final long serialVersionUID = 2143185833384433300L;

    /**
     * 服务名称
     */
    @Column(name = "service_name")
    public String serviceName;

    /**
     * 服务应用名
     */
    @Column(name = "service_code")
    public String serviceCode;

    /**
     * 路由别名
     */
    @Column(name = "route_name")
    public String routeName;

    /**
     * 路由前缀
     */
    @Column(name = "route_prefix")
    public String routePrefix;

    /**
     * 服务ID
     */
    @Column(name = "client_id")
    public String clientId;

    /**
     * 服务密钥
     */
    @Column(name = "client_secret")
    public String clientSecret;

    /**
     * 服务类型 自系统/异系统
     */
    public Integer type;

    /**
     * 服务基础地址
     */
    @Column(name = "base_url")
    public String baseUrl;

    /**
     * 状态是否启用
     */
    public Integer status;

    /**
     * 是否进行认证
     */
    @Column(name = "is_auth")
    public Boolean isAuth;

    /**
     * 是否进行路由
     */
    @Column(name = "is_route")
    public Boolean isRoute;

    /**
     * 小图标
     */
    public String icon;

    /**
     * 大图标
     */
    public String picture;

    public Integer sort;

    /**
     * 联系人
     */
    public String contacter;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 描述
     */
    private String remark;
}
