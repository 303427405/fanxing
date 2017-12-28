-- 用戶表
INSERT INTO secu_operator VALUES ('1', 'admin', '我是超管', '21232f297a57a5a743894a0e4a801fc3', null, '1234567', '','1','0', '1',null,'00_20160503111332523',  null,'admin', '2014-08-15 17:13:30', null, null);
INSERT INTO secu_operator VALUES ('2', 'test', '测试号', '098f6bcd4621d373cade4e832627b4f6', null, '1234567', '','1', '0','1', null,'11_20160503141732696', null,'admin', '2014-08-15 17:13:30', null, null);

-- 角色表
INSERT INTO secu_role VALUES ('1', '超级管理员', '拥有系统所有权限', '1','admin', '2014-08-15 17:14:40', null, null);


-- 用戶-角色表
INSERT INTO secu_operator_role VALUES ('1', '1', '1');
-- 角色-权限表
delete from secu_role_permissions;

-- 组织表
insert into `secu_organization` (`name`, `code`, `area_code`, `sequence`, `state`, `remark`, `createUser`, `createDate`, `updateUser`, `updateDate`) values('中国','00_20160503111332523','00',NULL,'1',NULL,'admin','2016-05-03 11:13:32','admin','2016-05-03 11:13:32');
insert into `secu_organization` (`name`, `code`, `area_code`, `sequence`, `state`, `remark`, `createUser`, `createDate`, `updateUser`, `updateDate`) values('北京','11_20160503141732696','11',NULL,'1',NULL,'admin','2016-05-03 14:17:32','admin','2016-05-03 14:17:32');