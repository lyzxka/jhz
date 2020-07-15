DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(32) DEFAULT NULL COMMENT '登录姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(16) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(6) DEFAULT NULL COMMENT '加密盐',
  `enabled` tinyint(1) DEFAULT '1' comment '是否可用',
  `create_date` timestamp default now() comment '创建时间',
  `update_date` timestamp default now() comment '修改时间',
  `create_by` bigint(20) comment '创建人',
  `update_by` bigint(20) comment '修改人',
  `remark` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
)
  comment '系统用户表'
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_role`;
create table `sys_role`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) comment '角色名称',
  PRIMARY KEY (`id`)
)
  comment '角色表'
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_role`;
create table `sys_user_role`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色关系id',
  `role_id` bigint(20) comment '角色id',
  `user_id` bigint(20) comment '用户id',
  PRIMARY KEY (`id`)
)
  comment '用户角色关系表'
  ENGINE=InnoDB DEFAULT CHARSET=utf8;