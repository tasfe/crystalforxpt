drop table  if exists role_group;

create table role_group(
	`id`  int(11) NOT NULL AUTO_INCREMENT ,
	`pid`  int(11) NOT NULL DEFAULT -1 COMMENT '����id' ,
	`name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
	`code`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
	`description`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
	PRIMARY KEY (`id`)
);

alter table roleteam add (rolegroup int(10) not null default 0);

insert into menu(id,roletype,parentid,name,type,module,url,visiable,privfield,addtime,adduser,orderflag)
values(109,4,78,'角色分组设置',3,2,'roleGroup/main.action','Y','add:新建,update:修改,delete:删除,search:搜索,reset:重置,detail:查看','2010-02-25 10:00:00','admin',23);

INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'browse', '1');
INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'add', '1');
INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'search', '1');
INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'update', '1');
INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'delete', '1');
INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'detail', '1');
INSERT INTO `privilege` (`OWNERTYPE`, `OWENR`, `PRIVTYPE`, `MID`, `CODE`, `Value`) VALUES ('R', 1, 'menu', '109', 'reset', '1');
