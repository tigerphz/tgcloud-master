DROP TABLE IF EXISTS uac_role;
CREATE TABLE uac_role
(
  id             BIGINT PRIMARY KEY, #主键
  rolename       VARCHAR(50) NOT NULL, #名称
  status         INTEGER DEFAULT 0, #状态
  description    VARCHAR(200),
  deptid         BIGINT, #部门ID

  version int(11) DEFAULT '0' COMMENT '版本号',
  creator varchar(20) DEFAULT '' COMMENT '创建人',
  creator_id bigint(20) DEFAULT NULL COMMENT '创建人ID',
  created_time datetime DEFAULT NULL COMMENT '创建时间',
  update_operator varchar(20) DEFAULT NULL COMMENT '更新操作人',
  update_operator_id bigint(20) DEFAULT NULL COMMENT '更新操作人ID',
  update_time datetime DEFAULT NULL COMMENT '更新时间'
);
