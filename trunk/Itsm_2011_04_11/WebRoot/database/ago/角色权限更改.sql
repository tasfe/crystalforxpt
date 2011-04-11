SET FOREIGN_KEY_CHECKS=0;

ALTER TABLE `menu` ADD COLUMN `RoleType`  int(11) NOT NULL DEFAULT 0 COMMENT '菜单所属的角色类型，与角色类型相对应' AFTER `ID`;

ALTER TABLE `menu` MODIFY COLUMN `ParentID`  bigint(20) NOT NULL AFTER `RoleType`;

ALTER TABLE `roleteam` ADD COLUMN `roletype`  int(11) NOT NULL DEFAULT 3 COMMENT '色角类型 0：admin 1：工程师 2：it经理 3：用户 4:管理员' AFTER `useFor`;

ALTER TABLE `roleteam` ADD COLUMN `roletypename`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '色角类型名称' AFTER `roletype`;

update menu set URL='department/main.action?pid=1' where id=74;
update menu set URL='department/main.action?pid=1' where id=72;

