-- 商品分类
DROP TABLE IF EXISTS `p_product_category`;
CREATE TABLE `p_product_category` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) NOT NULL COMMENT '名称',
  `code` varchar(200) NOT NULL COMMENT '编码',
  `wechatId` varchar(100) NOT NULL COMMENT '微信公众账号Id',
  `orgCode` varchar(200) NOT NULL COMMENT '所属组织',
  `leaf` int(1) DEFAULT NULL COMMENT '层级 1：一级 2：级 ',
  `parentId` int(20) DEFAULT NULL COMMENT '父id',
  `status` int(1) NOT NULL COMMENT '是否启用：1启用，0禁用',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `createUser` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` varchar(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 商品
DROP TABLE IF EXISTS `p_product`;
CREATE TABLE `p_product` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(40) NOT NULL COMMENT '名称',
  `code` VARCHAR(200) NOT NULL COMMENT '编码',
  `salePrice` DECIMAL(21,6) NOT NULL DEFAULT '0.000000' COMMENT '销售价',
  `unit` INT(1) DEFAULT NULL COMMENT '商品单位',
  `photoMaxUrl` VARCHAR(200) DEFAULT NULL COMMENT '大图片地址',
  `wechatId` VARCHAR(100) NOT NULL COMMENT '微信公众账号Id',
  `orgCode` VARCHAR(200) NOT NULL COMMENT '所属组织',
  `status` INT(1) NOT NULL COMMENT '是否启用：1启用，0禁用',
  `content` VARCHAR(200) NOT NULL COMMENT '内容',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  
  `createUser` VARCHAR(20) NOT NULL,
  `createDate` DATETIME NOT NULL DEFAULT '2016-04-13 00:00:00',
  `updateUser` VARCHAR(20) DEFAULT NULL,
  `updateDate` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

--市场



