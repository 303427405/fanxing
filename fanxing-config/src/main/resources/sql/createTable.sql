-- 权限表
DROP TABLE IF EXISTS `secu_permissions`;
CREATE TABLE `secu_permissions` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parentId` int(10) DEFAULT NULL COMMENT '父id',
  `name` varchar(50) DEFAULT NULL COMMENT '显示名称',
  `name_path` varchar(200) DEFAULT NULL COMMENT '名称路径',
  `icon` varchar(40) DEFAULT NULL COMMENT '图标',
  `bgColor` varchar(20) DEFAULT NULL COMMENT '快捷方式背景色',
  `webUrl` varchar(200) DEFAULT NULL COMMENT 'url地址',
  `btnFlg` varchar(50) DEFAULT NULL COMMENT '页面按钮显示标识',
  `leaf` int(1) DEFAULT NULL COMMENT '层级 1：一级 2：级 3：级....',
  `type` int(1) DEFAULT NULL COMMENT '权限类型 1:菜单，2:按钮',
  `enabled` int(1) DEFAULT NULL COMMENT '是否启用 1：是 0：不是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 快捷方式表
DROP TABLE IF EXISTS `secu_shortcut`;
CREATE TABLE `secu_shortcut` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `operator_id` int(10) DEFAULT NULL COMMENT '用户id',
  `permissions_id` int(10) DEFAULT NULL COMMENT '权限id',
  `sequence` int(1) DEFAULT NULL COMMENT '排序',
  `createUser` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` varchar(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 登录用户表
DROP TABLE IF EXISTS `secu_operator`;
CREATE TABLE `secu_operator` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `loginName` varchar(20) NOT NULL COMMENT '用户名',
  `realName` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` varchar(35) NOT NULL COMMENT '密码',
  `email` varchar(40) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(15) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `enabled` int(1) NOT NULL COMMENT '是否启用 1：启用 0：禁用',
  `isLockScreen` int(1) DEFAULT NULL COMMENT '锁屏状态 1： 锁定 0：未锁定',
  `accountNonExpired` int(1) DEFAULT '1' COMMENT '帐号是否已经过期 1:正常 0：过期',
  `imgPath` varchar(200) DEFAULT NULL COMMENT '头像路径',
  `orgCode` varchar(200) NOT NULL COMMENT '所属组织',
  `validDate` varchar(40) DEFAULT NULL  COMMENT '账号有效期',
  `createUser` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` varchar(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 角色表
DROP TABLE IF EXISTS `secu_role`;
CREATE TABLE `secu_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `roleName` varchar(10) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(30) DEFAULT NULL COMMENT '角色描述',
  `enabled`     int(1) DEFAULT NULL COMMENT '状态 1：启用 0：禁用',
  `createUser` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` varchar(20) DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 登录用户角户表
DROP TABLE IF EXISTS `secu_operator_role`;
CREATE TABLE `secu_operator_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator_id` int(10) DEFAULT NULL COMMENT '登录用户id',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 角户-权限表
DROP TABLE IF EXISTS `secu_role_permissions`;
CREATE TABLE `secu_role_permissions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `permissions_id` int(10) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 字典项表
DROP TABLE IF EXISTS `secu_dictionary`;
CREATE TABLE `secu_dictionary` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL COMMENT 'option 值',
  `parentId` int(10) DEFAULT NULL COMMENT '父id',
  `leaf` int(1) DEFAULT NULL COMMENT '层级 1：一级 2：级 ',
  `sequence` int(1) DEFAULT NULL COMMENT '排序',
  `enabled` int(1) DEFAULT NULL COMMENT '是否启用 1：是 0：不是',
  `createUser` varchar(20) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00' COMMENT '创建时间',
  `updateUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  `updateDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 部门表
DROP TABLE IF EXISTS `secu_department`;
CREATE TABLE `secu_department` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `code` varchar(30) DEFAULT NULL COMMENT '部门编码',
  `parentId` int(10) DEFAULT NULL COMMENT '父id',
  `enabled` smallint(1) DEFAULT NULL COMMENT '是否启用 1：是 0：不是',
  `createUser` varchar(20) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00' COMMENT '创建时间',
  `updateUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  `updateDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 区域表
DROP TABLE IF EXISTS `secu_area`;
CREATE TABLE `secu_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `full_name` varchar(200) DEFAULT NULL COMMENT '全称',
  `status` int(1) DEFAULT NULL COMMENT '是否启用：1启用，0禁用',
  `code` varchar(100) NOT NULL COMMENT '编码',
  `name_path` varchar(200) DEFAULT NULL COMMENT '名称路径',
  `code_path` varchar(200) DEFAULT NULL COMMENT 'id路径',
  `parentId` int(11) DEFAULT NULL COMMENT '父id',
  `parentCode` varchar(100) DEFAULT NULL COMMENT '父编码',
  `level` tinyint(1) NOT NULL COMMENT '层级',
  `leaf` int(1) DEFAULT NULL COMMENT '是否叶子结点 1：是 0：不是',
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `parentId` (`parentId`),
  KEY `parentCode` (`parentCode`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全国省市区街道';

-- 组织表
DROP TABLE IF EXISTS `secu_organization`;
CREATE TABLE `secu_organization` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(30) DEFAULT NULL COMMENT '编码',
  `area_code` varchar(30) DEFAULT NULL COMMENT '所属区域编码',
  `sequence` int(1) DEFAULT NULL COMMENT '排序',
  `state` int(1) DEFAULT NULL COMMENT '是否启用 1：是 0：不是',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `createUser` varchar(20) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00' COMMENT '创建时间',
  `updateUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;