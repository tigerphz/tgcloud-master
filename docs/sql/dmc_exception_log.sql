CREATE TABLE `dmc_exception_log` (
  `id` bigint(20) NOT NULL,
  `application_name` varchar(32) CHARACTER SET utf8 DEFAULT '' COMMENT '系统应用名',
  `exception_simple_name` varchar(200) CHARACTER SET utf8 DEFAULT '' COMMENT '异常类型',
  `exception_message` varchar(4500) CHARACTER SET utf8 DEFAULT '' COMMENT '异常信息(通过exception.getMessage()获取到的内容)',
  `exception_cause` varchar(500) CHARACTER SET utf8 DEFAULT '' COMMENT '异常原因(通过exception.getCause()获取到的内容)',
  `exception_stack` text CHARACTER SET utf8 COMMENT '异常堆栈信息',
  `creator` varchar(32) CHARACTER SET utf8 DEFAULT '' COMMENT '操作者姓名',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '操作者id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='全局异常记录';
