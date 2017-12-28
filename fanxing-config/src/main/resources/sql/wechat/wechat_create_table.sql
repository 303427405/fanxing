-- 微信公众账号
DROP TABLE IF EXISTS `w_tencentuser`;
CREATE TABLE `w_tencentuser` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) DEFAULT NULL COMMENT '公众账号',
  `weChatId` varchar(100) DEFAULT NULL COMMENT '微信公众账号Id',
  `appId` varchar(200) DEFAULT NULL COMMENT '第三方用户唯一凭证',
  `appSecret` varchar(200) DEFAULT NULL COMMENT '第三方用户唯一凭证密钥',
  `orgCode` varchar(200) NOT NULL COMMENT '所属组织',
  `status` int(1) DEFAULT NULL COMMENT '是否启用：1启用，0禁用',
  `title` varchar(200) DEFAULT NULL COMMENT '简介标题',
  `url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `content` varchar(200) DEFAULT NULL COMMENT '公众账号简介内容',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `createUser` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` varchar(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 消息列表
DROP TABLE IF EXISTS `w_inbox`;
CREATE TABLE `w_inbox` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wechatId` varchar(100) DEFAULT NULL COMMENT '微信号',
  `openId` varchar(100) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `msgType` varchar(50) DEFAULT NULL COMMENT '消息类型',
  `msgId` double(64,0) DEFAULT NULL COMMENT '消息id',
  `content` varchar(2000) DEFAULT NULL COMMENT '消息内容',
  `createTime` datetime NULL DEFAULT NULL COMMENT '消息接收时间',
  `replyCount` int(20) DEFAULT NULL COMMENT '回复消息数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 发送消息历史列表
DROP TABLE IF EXISTS `w_msg_history`;
CREATE TABLE `w_msg_history` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wechatId` varchar(100) DEFAULT NULL COMMENT '微信号',
  `openId` varchar(100) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `msgType` varchar(50) DEFAULT NULL COMMENT '消息类型',
  `content` varchar(2000) DEFAULT NULL COMMENT '消息内容',
  `createUser` varchar(20) NOT NULL  COMMENT '创建人',
  `createDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 工单列表
DROP TABLE IF EXISTS `w_work_order`;
CREATE TABLE `w_work_order` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) DEFAULT NULL COMMENT '名称',
  `wechatId` varchar(100) DEFAULT NULL COMMENT '微信号',
  `openId` varchar(100) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `content` varchar(2000) DEFAULT NULL COMMENT '事件内容',
  `status` int(1) NOT NULL COMMENT '状态：0：已受理，1：正在处理，2：已完成，3：已取消',
  `type` int(1) DEFAULT NULL COMMENT '类型：0主机，1显示器，2摄像头',
  `address` varchar(300) DEFAULT NULL COMMENT '详细地址',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `createUser` varchar(20) NOT NULL  COMMENT '创建人',
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00'  COMMENT '创建时间',
  `updateUser` varchar(20) DEFAULT NULL  COMMENT '修改人',
  `updateDate` datetime DEFAULT NULL  COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 粉丝表
DROP TABLE IF EXISTS `w_fan`;
CREATE TABLE `w_fan` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickName` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `openId` varchar(100) DEFAULT NULL COMMENT '粉丝标识id',
  `groupId` int(100) DEFAULT NULL COMMENT '粉丝所属群组id（微信方的）',
  `wechatId` varchar(100) DEFAULT NULL COMMENT '所属微信号',
  `sex` int(1) DEFAULT NULL COMMENT '粉丝性别',
  `city` varchar(100) DEFAULT NULL COMMENT '粉丝所在城市',
  `province` varchar(100) DEFAULT NULL COMMENT '粉丝所在省份',
  `country` varchar(100) DEFAULT NULL COMMENT '粉丝所在国家',
  `areaCodes` varchar(200) DEFAULT NULL COMMENT '所属区域',
  `address` varchar(300) DEFAULT NULL COMMENT '详细地址',
  `grade` int(1) DEFAULT NULL COMMENT '粉丝等级',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `createUser` varchar(100) NOT NULL,
  `createDate` datetime DEFAULT NULL COMMENT '关注时间',
  `updateUser` varchar(100) DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 微信群组表
DROP TABLE IF EXISTS `w_wechatgroup`;
CREATE TABLE `w_wechatgroup` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `groupId` int(10) DEFAULT NULL COMMENT '群组id(微信端)',
  `groupName` varchar(100) DEFAULT NULL COMMENT '群组名称',
  `wechatId` varchar(100) DEFAULT NULL COMMENT '微信号',
  `countFan` int(20) DEFAULT NULL COMMENT '粉丝数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `w_material`;
CREATE TABLE `w_material` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `photoUrl` varchar(200) DEFAULT  NULL COMMENT '图片地址',
  `newUrl` varchar(200) DEFAULT NULL COMMENT '图文地址',
  `content` varchar(200) DEFAULT  NULL COMMENT '内容',
  `orgCode` varchar(200) NOT NULL COMMENT '所属组织',
  `status` INT(1) DEFAULT NULL COMMENT '是否启用：1启用，0禁用',
  `type` int(1) DEFAULT NULL COMMENT '类型：0文本，1图片，2图文',
  `createUser` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` varchar(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `w_tencentuser_material`;
CREATE TABLE `w_tencentuser_material` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wechatId` varchar(100) DEFAULT NULL COMMENT '微信公众账号Id',
  `materialId` int(20)   DEFAULT NULL  COMMENT '素材Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



