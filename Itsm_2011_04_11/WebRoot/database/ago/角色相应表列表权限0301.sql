

drop table  if exists role_table;

create table role_table(
	`id`  int(11) NOT NULL AUTO_INCREMENT ,
	`enname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '表英文名',
	`chname`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表中文名',
	`description`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
	`prop`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
	PRIMARY KEY (`id`)
);


drop table  if exists role_table_privilege;

create table role_table_privilege(
	`id`  int(11) NOT NULL AUTO_INCREMENT ,
	`roleID`  int(11) NOT NULL ,
	`tableID`  int(11) NOT NULL ,
	`privilege`  int(4) NOT NULL  COMMENT '0:个人;1:部门;2:所有',
	`prop`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段,可能会存选择部门时拼接相关信息',
	PRIMARY KEY(`id`)
);

insert into role_table(enname,chname,description) values('assets_base','资产列表','所有资产信息');