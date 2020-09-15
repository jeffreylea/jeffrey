#oauth_access_token

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(128) NOT NULL COMMENT '身份验证ID',
  `token_id` varchar(128) DEFAULT NULL COMMENT '令牌ID',
  `token` blob COMMENT '令牌',
  `user_name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `client_id` varchar(128) DEFAULT NULL COMMENT '客户端ID',
  `authentication` blob COMMENT '认证体',
  `refresh_token` varchar(256) DEFAULT NULL COMMENT '刷新令牌',
  PRIMARY KEY (`authentication_id`),
  KEY `PK_token_id` (`token_id`) USING BTREE,
  KEY `PK_refresh_token` (`refresh_token`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(128) DEFAULT NULL,
  `clientId` varchar(128) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `oauth_client`;
CREATE TABLE `oauth_client` (
  `id` varchar(128) NOT NULL COMMENT '客户端id',
  `name` varchar(256) DEFAULT NULL COMMENT '客户端名称',
  `disabled` bit(1) NOT NULL DEFAULT b'0',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端表';

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL COMMENT '客户端id',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '客户端所能访问的资源id集合',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端访问密匙',
  `scope` varchar(256) DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '客户端重定向URI',
  `authorities` varchar(256) DEFAULT NULL COMMENT '客户端权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token的有效时间（单位:秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token的有效时间（单位:秒）',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '预留字段，JSON格式',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动Approval操作',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端详情';

DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `authentication_id` varchar(128) NOT NULL COMMENT '身份验证ID',
  `token_id` varchar(128) DEFAULT NULL COMMENT '令牌ID',
  `token` blob COMMENT '令牌',
  `user_name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `client_id` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(128) DEFAULT NULL COMMENT '令牌ID',
  `token` blob COMMENT '令牌',
  `authentication` blob COMMENT '认证体',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=685 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `oauth_resource`;
CREATE TABLE `oauth_resource` (
  `id` varchar(128) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `alias` varchar(256) DEFAULT NULL,
  `describe` varchar(256) DEFAULT NULL,
  `disabled` bit(1) NOT NULL DEFAULT b'0',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';









