SET FOREIGN_KEY_CHECKS=0;

ALTER TABLE `menu` ADD COLUMN `RoleType`  int(11) NOT NULL DEFAULT 0 COMMENT '�˵������Ľ�ɫ���ͣ����ɫ�������Ӧ' AFTER `ID`;

ALTER TABLE `menu` MODIFY COLUMN `ParentID`  bigint(20) NOT NULL AFTER `RoleType`;

ALTER TABLE `roleteam` ADD COLUMN `roletype`  int(11) NOT NULL DEFAULT 3 COMMENT 'ɫ������ 0��admin 1������ʦ 2��it���� 3���û� 4:����Ա' AFTER `useFor`;

ALTER TABLE `roleteam` ADD COLUMN `roletypename`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ɫ����������' AFTER `roletype`;

update menu set URL='department/main.action?pid=1' where id=74;
update menu set URL='department/main.action?pid=1' where id=72;

