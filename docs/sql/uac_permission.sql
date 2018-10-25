DROP TABLE IF EXISTS uac_permission;
CREATE TABLE uac_permission
(
  id             BIGINT PRIMARY KEY, #主键
  permname       VARCHAR(50) NOT NULL, #名称
  status         INTEGER              DEFAULT 0, #状态
  description    VARCHAR(200),
  type           INTEGER     NOT NULL DEFAULT 0, # 0菜单分类 1页面 2按钮
  icon           VARCHAR(50), #图标
  sort           INTEGER, #顺序
  parentid       BIGINT, #父节点
  url            VARCHAR(200), #地址
  code           VARCHAR(200), #权限标示 如 view create update delete 基本的增删该查

  version int(11) DEFAULT '0' COMMENT '版本号',
  creator varchar(20) DEFAULT '' COMMENT '创建人',
  creator_id bigint(20) DEFAULT NULL COMMENT '创建人ID',
  created_time datetime DEFAULT NULL COMMENT '创建时间',
  update_operator varchar(20) DEFAULT NULL COMMENT '更新操作人',
  update_operator_id bigint(20) DEFAULT NULL COMMENT '更新操作人ID',
  update_time datetime DEFAULT NULL COMMENT '更新时间'
);