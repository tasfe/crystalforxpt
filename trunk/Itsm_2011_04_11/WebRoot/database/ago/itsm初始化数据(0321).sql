SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `accessory`
-- ----------------------------
DROP TABLE IF EXISTS `accessory`;
CREATE TABLE `accessory` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL COMMENT '件附名称',
  `truename` varchar(50) NOT NULL COMMENT '上传时的名字',
  `upload_date` datetime NOT NULL COMMENT '上传日期',
  `table_id` int(11) NOT NULL COMMENT '关联表中的id',
  `type` varchar(20) default NULL COMMENT '附件类型',
  `table_name` varchar(50) NOT NULL COMMENT '关联表名',
  `upload_username` varchar(50) default NULL COMMENT '上传者的名字',
  `size` int(11) default NULL COMMENT '附件大小',
  `url` varchar(100) default NULL COMMENT '附件的url',
  `version` int(11) default NULL COMMENT '关联文档版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件';

-- ----------------------------
-- Records of accessory
-- ----------------------------

-- ----------------------------
-- Table structure for `addressbook_manage`
-- ----------------------------
DROP TABLE IF EXISTS `addressbook_manage`;
CREATE TABLE `addressbook_manage` (
  `id` bigint(50) NOT NULL auto_increment,
  `departmentID_` bigint(100) default NULL COMMENT '所属部门',
  `username_` varchar(200) default NULL COMMENT '姓名',
  `phone_` varchar(200) default NULL COMMENT '手机',
  `telephone_` varchar(200) default NULL COMMENT '固定电话',
  `phs_` varchar(200) default NULL COMMENT '小灵通',
  `homephone_` varchar(200) default NULL COMMENT '家庭电话',
  `fax_` varchar(400) default NULL COMMENT '传真',
  `email_` varchar(400) default NULL COMMENT '邮箱',
  `homepage_` varchar(400) default NULL COMMENT '主页',
  `qq_` varchar(100) default NULL COMMENT 'QQ',
  `msn_` varchar(400) default NULL COMMENT 'MSN',
  `address_` varchar(400) default NULL COMMENT '地址',
  `zipCode_` varchar(200) default NULL COMMENT '邮编',
  `remarks_` varchar(400) default NULL COMMENT '备注',
  `officePhone_` varchar(200) default NULL COMMENT '办公电话',
  `userId_` bigint(50) default NULL,
  `customertypeId_` bigint(50) default NULL COMMENT '关系',
  `companyName_` varchar(200) default NULL COMMENT '公司名称',
  `duties_` varchar(100) default NULL COMMENT '职务',
  `website_` varchar(200) default NULL COMMENT '公司网址',
  `personalWebsite_` varchar(200) default NULL COMMENT '个人网址',
  `initials_` varchar(200) default NULL COMMENT '首字母',
  `userContactId_` bigint(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of addressbook_manage
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_base`
-- ----------------------------
DROP TABLE IF EXISTS `assets_base`;
CREATE TABLE `assets_base` (
  `code` int(11) NOT NULL auto_increment,
  `support_id` int(11) default NULL,
  `produce_id` int(11) default NULL,
  `place_id` int(11) default NULL,
  `department_code` int(11) default NULL,
  `type_code` int(11) default NULL,
  `model` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `mac` varchar(255) default NULL,
  `devicename` varchar(255) default NULL,
  `in_date` datetime default NULL,
  `buy_date` datetime default NULL,
  `exitfacotry_Date` datetime default NULL,
  `users_id` int(11) default NULL,
  `buildlocation` int(11) default NULL,
  `itsmtype` int(11) default NULL,
  `ip` varchar(255) default NULL,
  `quality_time` varchar(255) default NULL,
  `system` varchar(255) default NULL,
  `des` int(11) default '0',
  `state` int(11) default NULL,
  `charge_id` int(11) default NULL,
  `maintainance_cost` int(11) default '0',
  `price` double default '0',
  `value_unit` int(11) default '0',
  `remark1` varchar(200) default NULL,
  `remark2` varchar(200) default NULL,
  `remark3` varchar(200) default NULL,
  `remark4` varchar(200) default NULL,
  `remark5` varchar(200) default NULL,
  `remark6` varchar(200) default NULL,
  `remark7` varchar(200) default NULL,
  `remark8` varchar(200) default NULL,
  `remark9` varchar(200) default NULL,
  `remark10` varchar(200) default NULL,
  `code_id` varchar(200) default NULL,
  `m_des` varchar(20) default NULL,
  `remark11` varchar(255) default NULL,
  `remark12` varchar(255) default NULL,
  `remark13` varchar(255) default NULL,
  `remark14` varchar(255) default NULL,
  `remark15` varchar(255) default NULL,
  `remark16` varchar(255) default NULL,
  `remark17` varchar(255) default NULL,
  `remark18` varchar(255) default NULL,
  `remark19` varchar(255) default NULL,
  `remark20` varchar(255) default NULL,
  `remark21` varchar(255) default NULL,
  `remark22` varchar(255) default NULL,
  `remark23` varchar(255) default NULL,
  `remark24` varchar(255) default NULL,
  `remark25` varchar(255) default NULL,
  `remark26` varchar(255) default NULL,
  `remark27` varchar(255) default NULL,
  `remark28` varchar(255) default NULL,
  `remark29` varchar(255) default NULL,
  `remark30` varchar(255) default NULL,
  PRIMARY KEY  (`code`),
  KEY `FK_assets_producer` (`produce_id`),
  KEY `FK_location` (`place_id`),
  KEY `FK_assets_type` (`type_code`),
  KEY `FK_department` (`department_code`),
  KEY `FK_assets_support` (`support_id`),
  KEY `FK_assets_state` (`state`),
  KEY `FK_assets_users` (`charge_id`),
  KEY `FK_assets_base` (`users_id`),
  KEY `FK_assets_base_buildlocation` (`buildlocation`),
  KEY `FK_assets_base_itsmtype` (`itsmtype`),
  CONSTRAINT `FK_assets_base` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_assets_base_buildlocation` FOREIGN KEY (`buildlocation`) REFERENCES `buildlocation` (`id`),
  CONSTRAINT `FK_assets_base_itsmtype` FOREIGN KEY (`itsmtype`) REFERENCES `itsm_type` (`id`),
  CONSTRAINT `FK_assets_producer` FOREIGN KEY (`produce_id`) REFERENCES `assets_producer` (`id`),
  CONSTRAINT `FK_assets_state` FOREIGN KEY (`state`) REFERENCES `assets_state` (`id`),
  CONSTRAINT `FK_assets_support` FOREIGN KEY (`support_id`) REFERENCES `assets_producer` (`id`),
  CONSTRAINT `FK_assets_type` FOREIGN KEY (`type_code`) REFERENCES `assets_type` (`id`),
  CONSTRAINT `FK_assets_users` FOREIGN KEY (`charge_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_department` FOREIGN KEY (`department_code`) REFERENCES `department` (`id`),
  CONSTRAINT `FK_location` FOREIGN KEY (`place_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_base
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_change`
-- ----------------------------
DROP TABLE IF EXISTS `assets_change`;
CREATE TABLE `assets_change` (
  `id` int(11) NOT NULL auto_increment,
  `change_type` int(11) default NULL,
  `receiverid` int(11) default NULL,
  `chargeid` int(11) default NULL,
  `managersid` int(11) default NULL,
  `userid` int(11) default NULL,
  `change_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `equipments` int(11) default NULL,
  `change_description` varchar(200) default NULL,
  `price` decimal(10,0) default NULL,
  `remark` varchar(200) default NULL,
  `assets_base_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_assets_change_chargeid` (`chargeid`),
  KEY `FK_assets_change_managers` (`managersid`),
  KEY `FK_assets_change_receiver` (`receiverid`),
  KEY `FK_assets_change_user` (`userid`),
  KEY `FK_assets_change_type` (`change_type`),
  KEY `FK_assets_change` (`assets_base_id`),
  CONSTRAINT `FK_assets_change` FOREIGN KEY (`assets_base_id`) REFERENCES `assets_base` (`code`),
  CONSTRAINT `FK_assets_change_chargeid` FOREIGN KEY (`chargeid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_assets_change_managers` FOREIGN KEY (`managersid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_assets_change_receiver` FOREIGN KEY (`receiverid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_assets_change_type` FOREIGN KEY (`change_type`) REFERENCES `assets_state` (`id`),
  CONSTRAINT `FK_assets_change_user` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_change
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_config`
-- ----------------------------
DROP TABLE IF EXISTS `assets_config`;
CREATE TABLE `assets_config` (
  `id` int(11) NOT NULL auto_increment,
  `assets_type_id` int(11) default NULL,
  `flag` int(11) default NULL,
  `config_name` varchar(50) default NULL,
  `config_column_name` varchar(50) default NULL,
  `config_stats` varchar(500) default NULL,
  `ischoose` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_assets_config` (`assets_type_id`),
  CONSTRAINT `FK_assets_config` FOREIGN KEY (`assets_type_id`) REFERENCES `assets_type` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_config
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_history_state`
-- ----------------------------
DROP TABLE IF EXISTS `assets_history_state`;
CREATE TABLE `assets_history_state` (
  `id` int(11) NOT NULL auto_increment,
  `code` varchar(255) default NULL,
  `state` int(11) NOT NULL COMMENT '1:²É¹º 2:Èë¿â 3:Î¬ÐÞ 4:½èµ÷ 5:ÁìÓÃ 6:ÕÛ¾É 7:±¨·Ï',
  `apply_date` datetime default NULL,
  `persion_id` int(11) default NULL,
  `back_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_history_state
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_info`
-- ----------------------------
DROP TABLE IF EXISTS `assets_info`;
CREATE TABLE `assets_info` (
  `id` int(11) NOT NULL auto_increment,
  `assets_base_id` int(11) default NULL,
  `remark1` varchar(200) default NULL,
  `remark2` varchar(200) default NULL,
  `remark3` varchar(200) default NULL,
  `remark4` varchar(200) default NULL,
  `remark5` varchar(200) default NULL,
  `remark6` varchar(200) default NULL,
  `remark7` varchar(200) default NULL,
  `remark8` varchar(200) default NULL,
  `remark9` varchar(200) default NULL,
  `remark10` varchar(200) default NULL,
  `remark11` varchar(200) default NULL,
  `remark12` varchar(200) default NULL,
  `remark13` varchar(200) default NULL,
  `remark14` varchar(200) default NULL,
  `remark15` varchar(200) default NULL,
  `remark16` varchar(200) default NULL,
  `remark17` varchar(200) default NULL,
  `remark18` varchar(200) default NULL,
  `remark19` varchar(200) default NULL,
  `remark20` varchar(200) default NULL,
  `remark21` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_assets_info` (`assets_base_id`),
  CONSTRAINT `FK_assets_info` FOREIGN KEY (`assets_base_id`) REFERENCES `assets_base` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_info
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_producer`
-- ----------------------------
DROP TABLE IF EXISTS `assets_producer`;
CREATE TABLE `assets_producer` (
  `id` int(11) NOT NULL auto_increment,
  `tel` varchar(255) default NULL COMMENT 'ÊÛºó·þÎñµç»°',
  `level` int(11) default NULL COMMENT 'ÐÇ¼¶¼¶±ð',
  `persion` varchar(255) default NULL COMMENT 'ÁªÏµÈË',
  `persion_tel` varchar(255) default NULL COMMENT 'ÁªÏµÈËµç»°',
  `address` varchar(255) default NULL,
  `description` varchar(255) default NULL COMMENT 'ËµÃ÷£¬±¸×¢',
  `type` int(11) default NULL COMMENT '1:Ìá¹©ÉÌ 2:Éú²úÉÌ',
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_producer
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_state`
-- ----------------------------
DROP TABLE IF EXISTS `assets_state`;
CREATE TABLE `assets_state` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `sequence` int(11) default NULL,
  `remark1` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_state
-- ----------------------------
INSERT INTO `assets_state` VALUES ('0', '已删除', '0', null);

-- ----------------------------
-- Table structure for `assets_type`
-- ----------------------------
DROP TABLE IF EXISTS `assets_type`;
CREATE TABLE `assets_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `flag` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `pid` int(11) default NULL,
  `code` varchar(255) default NULL,
  `countryname` varchar(255) default NULL,
  `countryCODE` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_type
-- ----------------------------

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `pid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for `authority_roleteam`
-- ----------------------------
DROP TABLE IF EXISTS `authority_roleteam`;
CREATE TABLE `authority_roleteam` (
  `roleteamID` int(11) NOT NULL,
  `authorityID` int(11) NOT NULL,
  PRIMARY KEY  (`roleteamID`,`authorityID`),
  KEY `FK416C3D4F6BEB4DC8` (`roleteamID`),
  KEY `FK416C3D4F12BDD5FA` (`authorityID`),
  CONSTRAINT `FK416C3D4F12BDD5FA` FOREIGN KEY (`authorityID`) REFERENCES `authority` (`id`),
  CONSTRAINT `FK416C3D4F6BEB4DC8` FOREIGN KEY (`roleteamID`) REFERENCES `roleteam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_roleteam
-- ----------------------------

-- ----------------------------
-- Table structure for `buildlocation`
-- ----------------------------
DROP TABLE IF EXISTS `buildlocation`;
CREATE TABLE `buildlocation` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `pid` int(11) default NULL,
  `code` varchar(100) default NULL,
  `allname` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buildlocation
-- ----------------------------
INSERT INTO `buildlocation` VALUES ('1', '存放位置', '0', '001', null);

-- ----------------------------
-- Table structure for `config_record`
-- ----------------------------
DROP TABLE IF EXISTS `config_record`;
CREATE TABLE `config_record` (
  `id` int(11) NOT NULL auto_increment,
  `record_name` varchar(50) default NULL,
  `record_description` varchar(100) default NULL,
  `assets_type_id` int(11) default NULL,
  `is_assetsinfo` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_config_record` (`assets_type_id`),
  CONSTRAINT `FK_config_record` FOREIGN KEY (`assets_type_id`) REFERENCES `assets_type` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config_record
-- ----------------------------

-- ----------------------------
-- Table structure for `customer_type`
-- ----------------------------
DROP TABLE IF EXISTS `customer_type`;
CREATE TABLE `customer_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `value` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_type
-- ----------------------------
INSERT INTO `customer_type` VALUES ('1', '好友', null);
INSERT INTO `customer_type` VALUES ('2', '朋友', null);
INSERT INTO `customer_type` VALUES ('3', '家人', null);
INSERT INTO `customer_type` VALUES ('4', '新同事', null);
INSERT INTO `customer_type` VALUES ('5', '陌生人', null);
INSERT INTO `customer_type` VALUES ('6', '黑名单', null);

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL auto_increment,
  `pid` int(11) default NULL COMMENT '父类id',
  `name` varchar(100) default NULL,
  `code` varchar(200) default NULL,
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '0', '总部', '001', '');

-- ----------------------------
-- Table structure for `document`
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `id` int(11) NOT NULL auto_increment,
  `summary` varchar(20) character set utf8 default NULL COMMENT '摘要',
  `context` text character set utf8 COMMENT '文档内容',
  `submit_time` datetime default NULL COMMENT '提交时间',
  `title` varchar(20) character set utf8 default NULL COMMENT '标题',
  `keyword` varchar(50) character set utf8 default NULL COMMENT '关键字以逗号隔开',
  `version` int(11) NOT NULL COMMENT '版本号',
  `user_id` int(11) default NULL COMMENT '用户id',
  `department_id` int(11) default NULL COMMENT '部门id',
  `user_name` varchar(20) character set utf8 default NULL COMMENT '用户名',
  `department_name` varchar(20) character set utf8 default NULL COMMENT '部门名称',
  `version_chain` varchar(200) character set utf8 default NULL COMMENT '版本链（版本号的组合）',
  `create_time` datetime default NULL COMMENT '创建时间',
  `cat_id` int(11) default NULL COMMENT '文档分类id',
  `num` varchar(20) character set utf8 NOT NULL COMMENT '文档编号',
  `cat_code` varchar(20) default NULL COMMENT '文档分类号',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `document_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `document_fk1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='文档';

-- ----------------------------
-- Records of document
-- ----------------------------

-- ----------------------------
-- Table structure for `document_authority`
-- ----------------------------
DROP TABLE IF EXISTS `document_authority`;
CREATE TABLE `document_authority` (
  `id` int(11) NOT NULL auto_increment,
  `did` int(11) default NULL COMMENT '文档库目录id',
  `tid` int(11) default NULL COMMENT '工程师分组id',
  `authority_sel` int(11) default NULL COMMENT '查看权限',
  `authority_add` int(11) default NULL COMMENT '添加权限',
  `authority_del` int(11) default NULL COMMENT '删除权限',
  `authority_upd` int(11) default NULL COMMENT '修改权限',
  PRIMARY KEY  (`id`),
  KEY `FK199F6D1FA94B7E13` (`did`),
  CONSTRAINT `FK199F6D1FA94B7E13` FOREIGN KEY (`did`) REFERENCES `document_cat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_authority
-- ----------------------------

-- ----------------------------
-- Table structure for `document_cat`
-- ----------------------------
DROP TABLE IF EXISTS `document_cat`;
CREATE TABLE `document_cat` (
  `id` int(11) NOT NULL auto_increment,
  `pid` int(11) default NULL COMMENT '父类id',
  `document` varchar(100) default NULL COMMENT '目录英文名称',
  `document_SC` varchar(100) default NULL COMMENT '目录中文名称',
  `code` varchar(200) default NULL COMMENT '本目录代码',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档类别';

-- ----------------------------
-- Records of document_cat
-- ----------------------------

-- ----------------------------
-- Table structure for `document_old`
-- ----------------------------
DROP TABLE IF EXISTS `document_old`;
CREATE TABLE `document_old` (
  `id` int(11) NOT NULL auto_increment,
  `summary` varchar(20) character set utf8 default NULL COMMENT '摘要',
  `context` text character set utf8 COMMENT '文档内容',
  `submit_time` datetime default NULL COMMENT '提交时间',
  `title` varchar(20) character set utf8 default NULL COMMENT '标题',
  `keyword` varchar(50) character set utf8 default NULL COMMENT '关键字以逗号隔开',
  `version` int(11) NOT NULL COMMENT '版本号',
  `user_id` int(11) default NULL COMMENT '用户id',
  `department_id` int(11) default NULL COMMENT '部门id',
  `user_name` varchar(20) character set utf8 default NULL COMMENT '用户名',
  `department_name` varchar(20) character set utf8 default NULL COMMENT '部门名称',
  `version_chain` varchar(200) character set utf8 default NULL COMMENT '版本链（版本号的组合）',
  `create_time` datetime default NULL COMMENT '创建时间',
  `cat_id` int(11) default NULL COMMENT '文档分类id',
  `num` varchar(20) character set utf8 NOT NULL COMMENT '文档编号',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `department_id` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='文档版本记录';

-- ----------------------------
-- Records of document_old
-- ----------------------------

-- ----------------------------
-- Table structure for `domain_register`
-- ----------------------------
DROP TABLE IF EXISTS `domain_register`;
CREATE TABLE `domain_register` (
  `id` int(11) NOT NULL auto_increment,
  `serial_number` varchar(20) default NULL COMMENT '序列号，编号',
  `domain` varchar(20) default NULL COMMENT '申请域名',
  `ip_address` text COMMENT '对应IP地址',
  `server_location` varchar(255) default NULL COMMENT '服务器存放位置',
  `units_full_name` varchar(255) default NULL COMMENT '申请单位全称',
  `units_address` varchar(255) default NULL COMMENT '单位所在校内地址',
  `technical_contact` varchar(20) default NULL COMMENT '技术联系人',
  `phone` varchar(50) default NULL COMMENT '联系电话',
  `email` varchar(50) default NULL COMMENT '电子邮箱',
  `foreign_domain` varchar(20) default NULL,
  `foreign_ip_address` text,
  `foreign_server_location` varchar(255) default NULL,
  `foreign_units_full_name` varchar(255) default NULL,
  `foreign_units_address` varchar(255) default NULL,
  `foreign_technical_contact` varchar(20) default NULL,
  `foreign_phone` varchar(50) default NULL,
  `foreign_email` varchar(50) default NULL,
  `remark` text COMMENT '备注',
  `units_leader` varchar(50) default NULL COMMENT '单位领导',
  `application` varchar(50) default NULL COMMENT '申请人',
  `application_date` date default NULL COMMENT '申请日期',
  `receiver` varchar(50) default NULL COMMENT '接收人',
  `receive_date` date default NULL COMMENT '接收日期',
  `completer` varchar(50) default NULL COMMENT '完成人',
  `complete_date` date default NULL COMMENT '完成日期',
  `state` int(11) default NULL COMMENT '审批状态',
  `approval_result` int(11) default NULL COMMENT '审批结果',
  `fail_reason` text COMMENT '审批不同意原因',
  `sumbit_user` int(11) default NULL COMMENT '申请提交人',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of domain_register
-- ----------------------------

-- ----------------------------
-- Table structure for `error_type`
-- ----------------------------
DROP TABLE IF EXISTS `error_type`;
CREATE TABLE `error_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL COMMENT '标签',
  `reason` longtext COMMENT '事故原因',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of error_type
-- ----------------------------

-- ----------------------------
-- Table structure for `formguide`
-- ----------------------------
DROP TABLE IF EXISTS `formguide`;
CREATE TABLE `formguide` (
  `id` int(11) NOT NULL auto_increment,
  `temp1` varchar(100) default NULL COMMENT '表单字段1名称及类型',
  `temp2` varchar(100) default NULL COMMENT '表单字段2名称及类型',
  `temp3` varchar(100) default NULL COMMENT '表单字段3名称及类型',
  `temp4` varchar(100) default NULL COMMENT '表单字段4名称及类型',
  `temp5` varchar(100) default NULL COMMENT '表单字段5名称及类型',
  `temp6` varchar(100) default NULL COMMENT '表单字段6名称及类型',
  `temp7` varchar(100) default NULL COMMENT '表单字段7名称及类型',
  `temp8` varchar(100) default NULL COMMENT '表单字段8名称及类型',
  `temp9` varchar(100) default NULL COMMENT '表单字段9名称及类型',
  `temp10` varchar(100) default NULL COMMENT '表单字段10名称及类型',
  `temp11` varchar(100) default NULL COMMENT '表单字段11名称及类型',
  `temp12` varchar(100) default NULL COMMENT '表单字段12名称及类型',
  `temp13` varchar(100) default NULL COMMENT '表单字段13名称及类型',
  `temp14` varchar(100) default NULL COMMENT '表单字段14名称及类型',
  `temp15` varchar(100) default NULL COMMENT '表单字段15名称及类型',
  `temp16` varchar(100) default NULL COMMENT '表单字段16名称及类型',
  `temp17` varchar(100) default NULL COMMENT '表单字段17名称及类型',
  `temp18` varchar(100) default NULL COMMENT '表单字段18名称及类型',
  `temp19` varchar(100) default NULL COMMENT '表单字段19名称及类型',
  `temp20` varchar(100) default NULL COMMENT '表单字段20名称及类型',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formguide
-- ----------------------------

-- ----------------------------
-- Table structure for `functioncolumn`
-- ----------------------------
DROP TABLE IF EXISTS `functioncolumn`;
CREATE TABLE `functioncolumn` (
  `ID` int(11) NOT NULL auto_increment,
  `ColumnName` varchar(64) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of functioncolumn
-- ----------------------------

-- ----------------------------
-- Table structure for `holiday`
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `holiday` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of holiday
-- ----------------------------

-- ----------------------------
-- Table structure for `interface_log`
-- ----------------------------
DROP TABLE IF EXISTS `interface_log`;
CREATE TABLE `interface_log` (
  `ID` int(11) NOT NULL,
  `IP` varchar(64) default NULL,
  `DESCRIPTION` varchar(250) default NULL,
  `NAME_CN` varchar(200) default NULL,
  `INTERFACE` varchar(64) default NULL,
  `INTERFACE_DESC` varchar(250) default NULL,
  `OPERATION` varchar(64) default NULL,
  `OPERATOR` varchar(200) default NULL,
  `REASON` varchar(500) default NULL,
  `STATUS` varchar(64) default NULL,
  `LOG_STATUS` int(11) default '0',
  `TIME` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `NOTE` varchar(250) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interface_log
-- ----------------------------

-- ----------------------------
-- Table structure for `ip_address`
-- ----------------------------
DROP TABLE IF EXISTS `ip_address`;
CREATE TABLE `ip_address` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID自动增长',
  `serial_number` varchar(255) default NULL COMMENT 'ip地址申请表编号',
  `apply_ipv4_count` int(11) default NULL COMMENT '申请ipv4地址的数量',
  `apply_ipv6_count` int(11) default NULL COMMENT '申请ipv6地址的数量',
  `apply_ip_purpose` varchar(255) default NULL COMMENT '申请ip地址的用途',
  `ip_use_room` varchar(255) default NULL COMMENT 'ip地址使用位置（房间号）',
  `units_full_name` varchar(255) default NULL COMMENT '申请单位全称',
  `units_address` varchar(255) default NULL COMMENT '单位所在校内地址',
  `units_extent_ip_count` int(11) default NULL COMMENT '单位现有ip地址数量',
  `units_extent_ip_purpose` varchar(255) default NULL COMMENT '单位现有ip地址用途',
  `technical_contact` varchar(20) default NULL COMMENT '技术联系人',
  `phone` varchar(50) default NULL COMMENT '联系电话',
  `email` varchar(50) default NULL COMMENT '电子邮箱',
  `apply_ip_cause` varchar(2000) default NULL COMMENT '申请ip地址的原因',
  `units_leader` varchar(20) default NULL COMMENT '单位负责人',
  `application` varchar(20) default NULL COMMENT 'ip申请人',
  `application_date` date default NULL COMMENT '申请日期',
  `receiver` varchar(20) default NULL COMMENT '接收人（网络中心填写）',
  `receive_date` date default NULL COMMENT '接收日期（网络中心填写）',
  `completer` varchar(20) default NULL COMMENT '完成人（网络中心填写）',
  `complete_date` date default NULL COMMENT '完成日期（网络中心填写）',
  `apply_ipv4_detail` text COMMENT '此次申请ipv4的地址',
  `apply_ipv6_detail` text COMMENT '此次申请ipv6的地址',
  `units_ipv4_detail` text COMMENT '单位现有的ipv4地址数据',
  `units_ipv6_detail` text COMMENT '此次现有ipv6的地址数据',
  `state` int(11) default NULL COMMENT '审批状态',
  `approval_result` int(11) default NULL COMMENT '审批结果',
  `fail_reason` varchar(255) default NULL COMMENT '未通过原因',
  `sumbit_user` int(11) default NULL COMMENT '提交者',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ip_address
-- ----------------------------

-- ----------------------------
-- Table structure for `ip_netcenter`
-- ----------------------------
DROP TABLE IF EXISTS `ip_netcenter`;
CREATE TABLE `ip_netcenter` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `type` int(11) default NULL,
  `phone` varchar(20) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ip_netcenter
-- ----------------------------

-- ----------------------------
-- Table structure for `itsm_type`
-- ----------------------------
DROP TABLE IF EXISTS `itsm_type`;
CREATE TABLE `itsm_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `pid` int(11) default NULL,
  `code` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itsm_type
-- ----------------------------
INSERT INTO `itsm_type` VALUES ('1', '运维类别', null, '0', '001');
INSERT INTO `itsm_type` VALUES ('5', '网络设备', null, '1', '001001');
INSERT INTO `itsm_type` VALUES ('6', '交换机', null, '5', '001001005');
INSERT INTO `itsm_type` VALUES ('7', '路由器', null, '5', '001001002');
INSERT INTO `itsm_type` VALUES ('8', '交换机+路由器', null, '5', '001001003');
INSERT INTO `itsm_type` VALUES ('9', 'SNMP主机', null, '5', '001001006');
INSERT INTO `itsm_type` VALUES ('10', '入网计算机', null, '1', '001002');

-- ----------------------------
-- Table structure for `jbpm4_deployment`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_deployment`;
CREATE TABLE `jbpm4_deployment` (
  `DBID_` bigint(20) NOT NULL,
  `NAME_` longtext,
  `TIMESTAMP_` bigint(20) default NULL,
  `STATE_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_deployment
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_deployprop`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_deployprop`;
CREATE TABLE `jbpm4_deployprop` (
  `DBID_` bigint(20) NOT NULL,
  `DEPLOYMENT_` bigint(20) default NULL,
  `OBJNAME_` varchar(255) default NULL,
  `KEY_` varchar(255) default NULL,
  `STRINGVAL_` varchar(255) default NULL,
  `LONGVAL_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  KEY `FK_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  CONSTRAINT `FK_DEPLPROP_DEPL` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_deployprop
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_execution`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_execution`;
CREATE TABLE `jbpm4_execution` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ACTIVITYNAME_` varchar(255) default NULL,
  `PROCDEFID_` varchar(255) default NULL,
  `HASVARS_` bit(1) default NULL,
  `NAME_` varchar(255) default NULL,
  `KEY_` varchar(255) default NULL,
  `ID_` varchar(255) default NULL,
  `STATE_` varchar(255) default NULL,
  `SUSPHISTSTATE_` varchar(255) default NULL,
  `PRIORITY_` int(11) default NULL,
  `HISACTINST_` bigint(20) default NULL,
  `PARENT_` bigint(20) default NULL,
  `INSTANCE_` bigint(20) default NULL,
  `SUPEREXEC_` bigint(20) default NULL,
  `SUBPROCINST_` bigint(20) default NULL,
  `PARENT_IDX_` int(11) default NULL,
  PRIMARY KEY  (`DBID_`),
  UNIQUE KEY `ID_` (`ID_`),
  KEY `IDX_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `IDX_EXEC_PARENT` (`PARENT_`),
  KEY `IDX_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `IDX_EXEC_INSTANCE` (`INSTANCE_`),
  KEY `FK_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `FK_EXEC_INSTANCE` (`INSTANCE_`),
  KEY `FK_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `FK_EXEC_PARENT` (`PARENT_`),
  CONSTRAINT `FK_EXEC_INSTANCE` FOREIGN KEY (`INSTANCE_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUBPI` FOREIGN KEY (`SUBPROCINST_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUPEREXEC` FOREIGN KEY (`SUPEREXEC_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_execution
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_actinst`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_actinst`;
CREATE TABLE `jbpm4_hist_actinst` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `HPROCI_` bigint(20) default NULL,
  `TYPE_` varchar(255) default NULL,
  `EXECUTION_` varchar(255) default NULL,
  `ACTIVITY_NAME_` varchar(255) default NULL,
  `START_` datetime default NULL,
  `END_` datetime default NULL,
  `DURATION_` bigint(20) default NULL,
  `TRANSITION_` varchar(255) default NULL,
  `NEXTIDX_` int(11) default NULL,
  `HTASK_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_HTI_HTASK` (`HTASK_`),
  KEY `IDX_HACTI_HPROCI` (`HPROCI_`),
  KEY `FK_HACTI_HPROCI` (`HPROCI_`),
  KEY `FK_HTI_HTASK` (`HTASK_`),
  CONSTRAINT `FK_HACTI_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HTI_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_hist_actinst
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_detail`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_detail`;
CREATE TABLE `jbpm4_hist_detail` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USERID_` varchar(255) default NULL,
  `TIME_` datetime default NULL,
  `HPROCI_` bigint(20) default NULL,
  `HPROCIIDX_` int(11) default NULL,
  `HACTI_` bigint(20) default NULL,
  `HACTIIDX_` int(11) default NULL,
  `HTASK_` bigint(20) default NULL,
  `HTASKIDX_` int(11) default NULL,
  `HVAR_` bigint(20) default NULL,
  `HVARIDX_` int(11) default NULL,
  `MESSAGE_` longtext,
  `OLD_STR_` varchar(255) default NULL,
  `NEW_STR_` varchar(255) default NULL,
  `OLD_INT_` int(11) default NULL,
  `NEW_INT_` int(11) default NULL,
  `OLD_TIME_` datetime default NULL,
  `NEW_TIME_` datetime default NULL,
  `PARENT_` bigint(20) default NULL,
  `PARENT_IDX_` int(11) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_HDET_HVAR` (`HVAR_`),
  KEY `IDX_HDET_HACTI` (`HACTI_`),
  KEY `IDX_HDET_HTASK` (`HTASK_`),
  KEY `IDX_HDET_HPROCI` (`HPROCI_`),
  KEY `FK_HDETAIL_HVAR` (`HVAR_`),
  KEY `FK_HDETAIL_HPROCI` (`HPROCI_`),
  KEY `FK_HDETAIL_HTASK` (`HTASK_`),
  KEY `FK_HDETAIL_HACTI` (`HACTI_`),
  CONSTRAINT `FK_HDETAIL_HACTI` FOREIGN KEY (`HACTI_`) REFERENCES `jbpm4_hist_actinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HVAR` FOREIGN KEY (`HVAR_`) REFERENCES `jbpm4_hist_var` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_hist_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_procinst`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_procinst`;
CREATE TABLE `jbpm4_hist_procinst` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) default NULL,
  `PROCDEFID_` varchar(255) default NULL,
  `KEY_` varchar(255) default NULL,
  `START_` datetime default NULL,
  `END_` datetime default NULL,
  `DURATION_` bigint(20) default NULL,
  `STATE_` varchar(255) default NULL,
  `ENDACTIVITY_` varchar(255) default NULL,
  `NEXTIDX_` int(11) default NULL,
  PRIMARY KEY  (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_hist_procinst
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_task`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_task`;
CREATE TABLE `jbpm4_hist_task` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `EXECUTION_` varchar(255) default NULL,
  `OUTCOME_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `PRIORITY_` int(11) default NULL,
  `STATE_` varchar(255) default NULL,
  `CREATE_` datetime default NULL,
  `END_` datetime default NULL,
  `DURATION_` bigint(20) default NULL,
  `NEXTIDX_` int(11) default NULL,
  `SUPERTASK_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_HSUPERT_SUB` (`SUPERTASK_`),
  KEY `FK_HSUPERT_SUB` (`SUPERTASK_`),
  CONSTRAINT `FK_HSUPERT_SUB` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_hist_task
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_var`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_var`;
CREATE TABLE `jbpm4_hist_var` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `PROCINSTID_` varchar(255) default NULL,
  `EXECUTIONID_` varchar(255) default NULL,
  `VARNAME_` varchar(255) default NULL,
  `VALUE_` varchar(255) default NULL,
  `HPROCI_` bigint(20) default NULL,
  `HTASK_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_HVAR_HTASK` (`HTASK_`),
  KEY `IDX_HVAR_HPROCI` (`HPROCI_`),
  KEY `FK_HVAR_HPROCI` (`HPROCI_`),
  KEY `FK_HVAR_HTASK` (`HTASK_`),
  CONSTRAINT `FK_HVAR_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HVAR_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_hist_var
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_id_group`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_id_group`;
CREATE TABLE `jbpm4_id_group` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) default NULL,
  `NAME_` varchar(255) default NULL,
  `TYPE_` varchar(255) default NULL,
  `PARENT_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_GROUP_PARENT` (`PARENT_`),
  KEY `FK_GROUP_PARENT` (`PARENT_`),
  CONSTRAINT `FK_GROUP_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_id_group` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_id_group
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_id_membership`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_id_membership`;
CREATE TABLE `jbpm4_id_membership` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USER_` bigint(20) default NULL,
  `GROUP_` bigint(20) default NULL,
  `NAME_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_MEM_GROUP` (`GROUP_`),
  KEY `IDX_MEM_USER` (`USER_`),
  KEY `FK_MEM_GROUP` (`GROUP_`),
  KEY `FK_MEM_USER` (`USER_`),
  CONSTRAINT `FK_MEM_GROUP` FOREIGN KEY (`GROUP_`) REFERENCES `jbpm4_id_group` (`DBID_`),
  CONSTRAINT `FK_MEM_USER` FOREIGN KEY (`USER_`) REFERENCES `jbpm4_id_user` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_id_membership
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_id_user`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_id_user`;
CREATE TABLE `jbpm4_id_user` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) default NULL,
  `PASSWORD_` varchar(255) default NULL,
  `GIVENNAME_` varchar(255) default NULL,
  `FAMILYNAME_` varchar(255) default NULL,
  `BUSINESSEMAIL_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_id_user
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_job`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_job`;
CREATE TABLE `jbpm4_job` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `DUEDATE_` datetime default NULL,
  `STATE_` varchar(255) default NULL,
  `ISEXCLUSIVE_` bit(1) default NULL,
  `LOCKOWNER_` varchar(255) default NULL,
  `LOCKEXPTIME_` datetime default NULL,
  `EXCEPTION_` longtext,
  `RETRIES_` int(11) default NULL,
  `PROCESSINSTANCE_` bigint(20) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  `CFG_` bigint(20) default NULL,
  `SIGNAL_` varchar(255) default NULL,
  `EVENT_` varchar(255) default NULL,
  `REPEAT_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_JOBRETRIES` (`RETRIES_`),
  KEY `IDX_JOBDUEDATE` (`DUEDATE_`),
  KEY `IDX_JOBLOCKEXP` (`LOCKEXPTIME_`),
  KEY `IDX_JOB_CFG` (`CFG_`),
  KEY `IDX_JOB_EXE` (`EXECUTION_`),
  KEY `IDX_JOB_PRINST` (`PROCESSINSTANCE_`),
  KEY `FK_JOB_CFG` (`CFG_`),
  CONSTRAINT `FK_JOB_CFG` FOREIGN KEY (`CFG_`) REFERENCES `jbpm4_lob` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_job
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_lob`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_lob`;
CREATE TABLE `jbpm4_lob` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `BLOB_VALUE_` longblob,
  `DEPLOYMENT_` bigint(20) default NULL,
  `NAME_` longtext,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  KEY `FK_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  CONSTRAINT `FK_LOB_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_lob
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_participation`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_participation`;
CREATE TABLE `jbpm4_participation` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `GROUPID_` varchar(255) default NULL,
  `USERID_` varchar(255) default NULL,
  `TYPE_` varchar(255) default NULL,
  `TASK_` bigint(20) default NULL,
  `SWIMLANE_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_PART_TASK` (`TASK_`),
  KEY `FK_PART_SWIMLANE` (`SWIMLANE_`),
  KEY `FK_PART_TASK` (`TASK_`),
  CONSTRAINT `FK_PART_SWIMLANE` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`),
  CONSTRAINT `FK_PART_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_participation
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_property`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_property`;
CREATE TABLE `jbpm4_property` (
  `KEY_` varchar(255) NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `VALUE_` varchar(255) default NULL,
  PRIMARY KEY  (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_property
-- ----------------------------
INSERT INTO `jbpm4_property` VALUES ('next.dbid', '0', '1');

-- ----------------------------
-- Table structure for `jbpm4_swimlane`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_swimlane`;
CREATE TABLE `jbpm4_swimlane` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_SWIMLANE_EXEC` (`EXECUTION_`),
  KEY `FK_SWIMLANE_EXEC` (`EXECUTION_`),
  CONSTRAINT `FK_SWIMLANE_EXEC` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_swimlane
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_task`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_task`;
CREATE TABLE `jbpm4_task` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `DESCR_` longtext,
  `STATE_` varchar(255) default NULL,
  `SUSPHISTSTATE_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `FORM_` varchar(255) default NULL,
  `PRIORITY_` int(11) default NULL,
  `CREATE_` datetime default NULL,
  `DUEDATE_` datetime default NULL,
  `PROGRESS_` int(11) default NULL,
  `SIGNALLING_` bit(1) default NULL,
  `EXECUTION_ID_` varchar(255) default NULL,
  `ACTIVITY_NAME_` varchar(255) default NULL,
  `HASVARS_` bit(1) default NULL,
  `SUPERTASK_` bigint(20) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  `PROCINST_` bigint(20) default NULL,
  `SWIMLANE_` bigint(20) default NULL,
  `TASKDEFNAME_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_TASK_SUPERTASK` (`SUPERTASK_`),
  KEY `FK_TASK_SWIML` (`SWIMLANE_`),
  KEY `FK_TASK_SUPERTASK` (`SUPERTASK_`),
  CONSTRAINT `FK_TASK_SUPERTASK` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_task` (`DBID_`),
  CONSTRAINT `FK_TASK_SWIML` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_task
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_variable`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_variable`;
CREATE TABLE `jbpm4_variable` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `KEY_` varchar(255) default NULL,
  `CONVERTER_` varchar(255) default NULL,
  `HIST_` bit(1) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  `TASK_` bigint(20) default NULL,
  `LOB_` bigint(20) default NULL,
  `DATE_VALUE_` datetime default NULL,
  `DOUBLE_VALUE_` double default NULL,
  `CLASSNAME_` varchar(255) default NULL,
  `LONG_VALUE_` bigint(20) default NULL,
  `STRING_VALUE_` varchar(255) default NULL,
  `TEXT_VALUE_` longtext,
  `EXESYS_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `IDX_VAR_EXESYS` (`EXESYS_`),
  KEY `IDX_VAR_TASK` (`TASK_`),
  KEY `IDX_VAR_EXECUTION` (`EXECUTION_`),
  KEY `IDX_VAR_LOB` (`LOB_`),
  KEY `FK_VAR_EXESYS` (`EXESYS_`),
  KEY `FK_VAR_LOB` (`LOB_`),
  KEY `FK_VAR_TASK` (`TASK_`),
  KEY `FK_VAR_EXECUTION` (`EXECUTION_`),
  CONSTRAINT `FK_VAR_EXECUTION` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_EXESYS` FOREIGN KEY (`EXESYS_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_LOB` FOREIGN KEY (`LOB_`) REFERENCES `jbpm4_lob` (`DBID_`),
  CONSTRAINT `FK_VAR_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbpm4_variable
-- ----------------------------

-- ----------------------------
-- Table structure for `knowledge_base`
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_base`;
CREATE TABLE `knowledge_base` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `category_id` int(11) default NULL,
  `symptom` varchar(255) default NULL,
  `indexcode` varchar(255) default NULL,
  `solution` text,
  `engineer_id` int(11) default NULL,
  `mode` char(2) default NULL,
  `currentTime` timestamp NULL default NULL,
  PRIMARY KEY  (`id`),
  KEY `NewIndex1` (`engineer_id`),
  CONSTRAINT `FK_knowledge_base` FOREIGN KEY (`engineer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge_base
-- ----------------------------

-- ----------------------------
-- Table structure for `ldapadapter`
-- ----------------------------
DROP TABLE IF EXISTS `ldapadapter`;
CREATE TABLE `ldapadapter` (
  `LDAP_ID` bigint(19) NOT NULL default '0',
  `DOMAINCONTROLLER` varchar(200) NOT NULL default 'ldap://192.168.4.83:389',
  `INITIAL_CONTEXT_FACTORY` varchar(200) NOT NULL default 'com.sun.jndi.ldap.LdapCtxFactory',
  `IS_SECURED` varchar(10) NOT NULL default 'SIMPLE',
  `USERNAME` varchar(100) default 'cn=Manager,o=adventnet,c=com',
  `PASSWORD` varchar(200) NOT NULL default '0',
  `BASE_DN` varchar(100) default 'o=adventnet,c=com',
  `SEARCH_FILTER` varchar(200) default 'mail=*',
  `LOGIN_ATTRIBUTE` varchar(200) default 'sAMAccountName',
  `MAIL_ATTRIBUTE` varchar(200) default 'mail',
  `DN_ATTRIBUTE` varchar(200) default 'distinguishedName',
  `LDAPSERVER_TYPE` varchar(200) default 'Microsoft Active Directory',
  `ISIMPORTED` tinyint(1) default '0',
  PRIMARY KEY  (`LDAP_ID`),
  UNIQUE KEY `LDAPAdapter_UK` (`DOMAINCONTROLLER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ldapadapter
-- ----------------------------

-- ----------------------------
-- Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL COMMENT 'åç§°',
  `content` longtext COMMENT 'æè¿°',
  `pid` int(11) default NULL COMMENT 'ç¶çº§id',
  `code` longtext COMMENT 'åå«ææç¶çº§çç¼å·',
  `location_sc` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('1', '所有区域', '', '0', '00', 'all');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `ID` bigint(20) NOT NULL,
  `roletype` varchar(50) default NULL,
  `ParentID` bigint(20) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Type` varchar(2) NOT NULL,
  `Module` int(2) default '0',
  `URL` varchar(200) default NULL,
  `Visiable` varchar(2) default NULL,
  `Icon` varchar(40) default NULL,
  `OrderFlag` bigint(20) NOT NULL,
  `PrivField` text,
  `Prop1` varchar(50) default NULL,
  `Prop2` varchar(50) default NULL,
  `Memo` varchar(50) default NULL,
  `AddTime` datetime NOT NULL,
  `AddUser` varchar(200) NOT NULL,
  `ModifyTime` datetime default NULL,
  `ModifyUser` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('3', '1', '0', '办公申请', '1', '2', null, 'Y', 'images/icons/icon039a1.gif', '7', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('4', '1:2', '0', 'IT资产管理', '1', '2', null, 'Y', 'images/icons/treeicon10.gif', '1', '', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('5', '1', '4', 'IT资产管理', '2', '2', 'assets/list.action', 'Y', 'images/icons/treeicon11.gif', '1', 'changes:批量变更,change:变更,changeselect:变更查询,export:导出,add:新建,update:修改,delete:删除,query:查看', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('6', '1', '4', '资产属性配置', '2', '2', 'assetsconfig/main.action', 'Y', 'images/icons/icon402a4.gif', '3', 'config:配置,update:修改,delete:删除', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('7', '1', '4', '资产类别管理', '2', '2', 'assetstype/main.action', 'Y', 'images/icons/icon047a12.gif', '4', 'add:新建,update:修改,delete:删除', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('8', '1', '4', '资产状态管理', '2', '2', 'assetsstate/list.action', 'Y', 'images/icons/icon041a15.gif', '5', 'add:新建,update:修改,delete:删除', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('9', '1', '4', '供应厂商管理', '2', '2', 'producer/list.action', 'Y', 'images/icons/icon041a6.gif', '6', 'add:新建,update:修改,delete:删除', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('10', '1:2', '4', '资产信息统计', '2', '2', 'assets/statistic.action', 'Y', 'images/icons/icon032a12.gif', '7', null, null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('11', '1:2', '4', '资产信息查看', '2', '2', 'showAssets/main.action', 'Y', 'images/icons/icon032a15.gif', '8', null, null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('12', '1', '4', '资产质保查询', '2', '2', 'assets/quality.action', 'Y', 'images/icons/icon031a9.gif', '9', null, null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('13', '1', '4', '资产批量导入', '2', '2', 'systemmanage/assets/importassets.jsp', 'Y', 'images/icons/icon026a2.gif', '10', 'import:导入,download:下载', null, null, null, '2010-11-11 09:41:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('15', '1:2', '0', '事件管理', '1', '2', null, 'Y', 'images/icons/icon012a5.gif', '1', null, null, null, null, '2010-11-12 08:52:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('16', '1', '15', '提交请求', '2', '2', 'engineerrequest/init.action', 'Y', 'images/icons/icon018a4.gif', '2', '', null, null, null, '2010-11-12 08:52:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('17', '1:2', '15', '跟踪请求处理情况', '2', '2', 'engineertrace/tracelist.action', 'Y', 'images/icons/icon018a8.gif', '3', 'select:查询,intervene:干预,distribute:指派任务', null, null, null, '2010-11-12 08:52:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('18', '1:2', '15', '服务请求历史', '2', '2', 'engineerrequesthistory/requestlist.action', 'Y', 'images/icons/icon022a1.gif', '4', '', null, null, null, '2010-11-12 08:52:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('20', '1:2', '0', '资源管理', '1', '2', null, 'Y', 'images/icons/icon038a4.gif', '1', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('21', '1', '20', '日志管理', '2', '2', 'worklog/myquery.action', 'Y', 'images/icons/icon038a4.gif', '2', 'add:新建,select:搜索,update:修改,delete:删除,query:查看', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('22', '1:2', '20', '日志查询', '2', '2', 'worklog/main.action', 'Y', 'images/icons/icon038a4.gif', '3', 'select:搜索,query:查看', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('23', '1:2', '20', '日志排行', '2', '2', 'worklog/hot.action', 'Y', 'images/icons/icon038a4.gif', '4', null, null, null, null, '2010-11-12 00:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('24', '1:2', '20', '知识库管理', '2', '2', 'knowledgebase/list.action', 'Y', 'images/icons/icon038a4.gif', '5', 'add:新建,select:搜索,update:修改,delete:删除,query:查看', null, null, null, '2010-11-11 00:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('25', '1', '20', '文档管理', '2', '2', 'document/main.action?pid=0', 'Y', 'images/icons/icon038a4.gif', '6', 'add:新建,select:搜索,update:修改,delete:删除,query:查看,history:历史', null, null, null, '2010-11-11 00:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('30', '1', '0', '通讯录管理', '1', '2', null, 'Y', 'images/icons/icon037a6.gif', '1', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('31', '1', '30', '公共通讯录', '2', '2', 'AddressBookManage/main.action?pid=0', 'Y', 'images/icons/icon037a6.gif', '2', 'add:新建,search:查询,update:修改,delete:删除,detail:查看,export:导出', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('32', '1', '30', '个人通讯录', '2', '2', 'AddressBookManage/mainContact.action', 'Y', 'images/icons/icon037a6.gif', '3', 'add:新建,search:查询,update:修改,delete:删除,detail:查看', null, null, null, '2010-11-15 11:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('33', '1', '30', '通讯录导入', '2', '2', 'AddressBookManage/addressImport.action', 'Y', 'images/icons/icon037a6.gif', '4', 'import:导入,downloads:模板下载', null, null, null, '2010-11-15 12:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('40', '1:2', '0', '计划任务管理', '1', '2', '', 'Y', 'images/icons/icon019a4.gif', '1', null, null, null, null, '2010-11-12 15:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('41', '1:2', '40', '添加新任务', '2', '2', 'schedualedtask/add.action', 'Y', 'images/icons/icon020a4.gif', '2', null, null, null, null, '2010-11-12 15:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('42', '1:2', '40', '查看全部任务', '2', '2', 'schedualedtask/list.action', 'Y', 'images/icons/icon005a15.gif', '3', 'update:修改,delete:删除', null, null, null, '2010-11-12 15:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('43', '1:2', '40', '工作日历', '2', '2', 'schedualedtask/calendar.action', 'Y', 'images/icons/icon010a12.gif', '4', null, null, null, null, '2010-11-12 15:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('44', '1:2', '40', '按周查看任务', '2', '2', 'schedualedtask/week.action', 'Y', 'images/icons/icon045a15.gif', '5', null, null, null, null, '2010-11-12 15:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('50', '3', '0', '提交新请求（用户界面）', '1', '2', 'userrequest/init.action', 'Y', 'images/icons/icon031a9.gif', '1', null, null, null, null, '2010-11-15 10:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('51', '3', '0', '跟踪服务进度（用户界面）', '1', '2', 'usertrace/tracelist.action', 'Y', 'images/icons/icon003a5.gif', '2', null, null, null, null, '2010-11-12 00:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('52', '3', '0', '查看历史记录（用户界面）', '1', '2', 'userrequesthistory/tracelist.action?state=5', 'Y', 'images/icons/icon012a6.gif', '3', null, null, null, null, '2010-11-12 00:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('53', '3', '0', '知识库检索（用户界面）', '1', '2', 'knowledgebase/user.action', 'Y', 'images/icons/icon048a6.gif', '4', 'select:搜索', null, null, null, '2010-11-12 00:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('54', '3', '0', '论坛交流（用户界面）', '1', '2', 'bbs/index.jsp', 'Y', 'images/icons/icon038a4.gif', '5', null, null, null, null, '2010-11-12 00:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('55', '3', '0', '系统公告（用户界面）', '1', '2', 'SystemNotice/findNowNotices.action', 'Y', 'images/icons/icon020a4.gif', '6', null, null, null, null, '2010-11-12 00:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('56', '3', '0', '退出系统（用户界面）', '1', '2', 'SystemNotice/findNowNotices.action', 'Y', 'images/icons/icon037a6.gif', '7', null, null, null, null, '2010-11-12 00:00:00', 'yhm', null, null);
INSERT INTO `menu` VALUES ('57', '3', '0', '个人资料配置(用户界面)', '1', '2', 'user/personaldata.action', 'Y', 'images/icons/icon038a4.gif', '15', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('60', '1', '0', '监控管理', '1', '2', null, 'Y', 'images/icons/treeicon10.gif', '1', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('61', '1', '60', '设备管理', '2', '2', 'monitorDevice/deviceList.action', 'Y', 'images/icons/icon008a1.gif', '1', 'add:添加,search:搜索,delete:删除,query:查看,update:修改,port:设备端口, statistics:统计', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('62', '1', '60', '分区管理', '2', '2', 'monitor/subnet/subnetList.jsp', 'Y', 'images/icons/icon042a1.gif', '2', 'add:新建,update:修改,delete:删除,topology:拓扑', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('63', '1', '60', '入网计算机', '2', '2', 'monitorComputer/computerList.action', 'Y', 'images/icons/icon2.gif', '3', 'scan:扫描', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('64', '1', '60', 'Top-N', '2', '2', 'monitorTopN/listAll.action', 'Y', 'images/icons/icon031a9.gif', '4', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('65', '1', '60', '报警', '2', '2', 'monitorAlert/listAll.action', 'Y', 'images/icons/icon003a5.gif', '5', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('67', '1', '60', '管理', '2', '2', 'monitor/manage/settings.jsp', 'Y', 'images/icons/icon038a4.gif', '7', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('70', '4', '0', '系统管理', '1', '2', '', 'Y', 'images/icons/icon012a6.gif', '1', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('72', '4', '70', '区域管理', '2', '2', 'location/main.action?pid=1', 'Y', 'images/icons/icon038a4.gif', '3', 'add:新建,delete:删除,update:修改', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('74', '4', '70', '部门管理', '2', '2', 'department/main.action?pid=1', 'Y', 'images/icons/icon026a19.gif', '5', 'add:新建,search:查询,update:修改,delete:删除,detail:查看,export:导出', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('75', '0', '3', '日程管理', '2', '2', null, 'Y', 'images/icons/icon020a4.gif', '6', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('76', '1', '3', 'IP申请管理', '2', '2', '', 'Y', 'images/icons/icon047a1.gif', '7', '', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('77', '1', '3', '域名申请管理', '2', '2', '', 'Y', 'images/icons/icon046a17.gif', '8', '', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('78', '4', '70', '用户管理', '2', '2', null, 'Y', 'images/icons/icon025a11.gif', '9', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('80', '4', '70', '类别管理', '2', '2', null, 'Y', 'images/icons/icon400a19.gif', '11', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('81', '1', '75', '预约登记', '3', '2', 'schedule/add.action', 'Y', 'images/icons/icon020a4.gif', '12', null, null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('82', '1', '75', '预约查询', '3', '2', 'schedule/list.action', 'Y ', 'images/icons/icon020a4.gif', '13', 'add:新建,search:搜索,update:修改,delete:删除,detail:查看,reset:重置', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('83', '1', '75', '我的日程', '3', '2', 'schedule/my.action', 'Y', 'images/icons/icon020a4.gif', '14', 'add:新建,search:搜索,update:修改,delete:删除,detail:查看,reset:重置', null, null, null, '2010-11-12 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('85', '4', '78', '用户资料', '3', '2', 'user/list.action', 'Y ', 'images/icons/icon025a11.gif', '16', 'add:新建,search:搜索,update:修改,delete:删除,detail:查看,reset:重置', null, null, null, '2010-11-14 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('86', '4', '78', '角色权限配置', '3', '2', 'role/main.action', 'Y', 'images/icons/icon025a1.gif', '19', '', null, null, null, '2010-11-14 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('88', '4', '78', '用户角色', '3', '2', 'role/list.action', 'Y', 'images/icons/icon025a8.gif', '17', 'add:新建,search:搜索,update:修改,delete:删除,detail:查看,reset:重置', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('89', '4', '78', '工程师分组', '3', '2', 'engineer/list.action', 'Y', 'images/icons/icon021a12.gif', '20', 'add:新建,search:搜索,update:修改,delete:删除,detail:查看,reset:重置', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('90', '4', '80', '事件及服务类别', '3', '2', 'serviceCategory/main.action?type=1', 'Y ', 'images/icons/icon400a14.gif', '21', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('91', '4', '80', '计划任务类别', '3', '2', 'serviceCategory/main.action?type=2', 'Y', 'images/icons/icon400a14.gif', '22', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('95', '4', '80', '项目类别', '3', '2', 'serviceCategory/main.action?type=4', 'Y', 'images/icons/icon400a14.gif', '26', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('96', '4', '80', '项目任务类别', '3', '2', 'serviceCategory/list.action?type=5', 'Y ', 'images/icons/icon400a14.gif', '27', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('97', '4', '70', '系统公告', '2', '2', 'SystemNotice/listSystemNotice.action', 'Y', 'images/icons/icon_board.gif', '28', 'add:新建新公告,search:搜索,update:修改,delete:删除,detail:查看,reset:重置,noticecheck:通知查看,addpersonal:个人通知发布', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('98', '4', '80', '文档库目录', '3', '2', 'documentCat/main.action?type=4', 'Y', 'images/icons/icon_magazaine.gif', '25', 'add:新建,update:修改,delete:删除', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('99', '0', '76', 'IP列表查询', '3', '2', 'ipaddress/list.action', 'Y', 'images/icons/icon007a12.gif', '29', 'add:新建,update:修改,delete:删除,approve:待审批,reset:重置,search:查询,detail:查看,print:打印,export:导出', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('100', '0', '76', 'IP用户申请', '3', '2', 'ipaddress/user_saveInput.action', 'Y', 'images/icons/icon007a12.gif', '30', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('101', '0', '76', 'IP用户查询', '3', '2', 'ipaddress/user_list.action', 'Y ', 'images/icons/icon007a12.gif', '31', 'add:新建,update:修改,delete:删除,search:搜索,reset:重置,detail:查看', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('102', '1', '77', '域名列表查询', '3', '2', 'domainregister/list.action', 'Y', 'images/icons/icon039a1.gif', '32', 'add:新建,update:修改,delete:删除,approve:待审批,reset:重置,search:查询,detail:查看,print:打印,export:导出', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('103', '1', '77', '域名用户申请', '3', '2', 'domainregister/user_saveInput.action', 'Y', 'images/icons/icon039a1.gif', '33', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('104', '1', '77', '域名用户查询', '3', '2', 'domainregister/user_list.action', 'Y', 'images/icons/icon039a1.gif', '34', 'add:新建,update:修改,delete:删除,search:搜索,reset:重置,detail:查看', null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('105', '1', '4', '资产批量入库', '2', '2', 'assets/batch.action', 'Y', 'images/icons/icon047a2.gif', '2', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('106', '4', '70', '任务分配机制', '2', '0', 'taskallocation/list.action', 'Y', 'images/icons/icon007a12.gif', '5', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('107', '1', '4', '资产位置管理', '2', '2', 'buildlocation/main.action', 'Y', 'images/icons/icon007a12.gif', '11', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('108', '1', '4', '运维类别管理', '2', '2', 'itsmtype/main.action', 'Y', 'images/icons/icon007a12.gif', '12', null, null, null, null, '2010-11-15 10:00:00', 'admin', null, null);
INSERT INTO `menu` VALUES ('109', '4', '78', '角色组别管理', '3', '2', 'roleGroup/main.action', 'Y', null, '23', 'add:新建,update:修改,delete:删除,search:搜索,reset:重置,detail:查看', null, null, null, '2010-02-25 10:00:00', 'admin', null, null);

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '名称',
  `url` varchar(255) default NULL COMMENT 'URL',
  `img` varchar(255) default NULL COMMENT '图片地址',
  `orderNo` int(11) default NULL COMMENT '排序号',
  `pid` int(11) default NULL COMMENT '父级id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_access_log`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_access_log`;
CREATE TABLE `monitor_access_log` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `MAC` varchar(64) default NULL,
  `UP_DEVICE_IP` varchar(64) default NULL,
  `INTERFACE_DESCRIPTION` varchar(500) default NULL,
  `FIRST_TIME` datetime default NULL,
  `LAST_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_access_log
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_alert`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_alert`;
CREATE TABLE `monitor_alert` (
  `ID` int(11) NOT NULL auto_increment,
  `TYPE_CODE` int(11) default NULL,
  `STYPE_CODE` int(11) default NULL,
  `FILE` int(11) unsigned default NULL,
  `OLD` varchar(64) default NULL,
  `VALUE` varchar(16) default NULL,
  `LIMEN` varchar(16) default NULL,
  `IP` varchar(64) default NULL,
  `MAC` varchar(64) default NULL,
  `UPLINK` varchar(64) default NULL,
  `PORT` varchar(64) default NULL,
  `INTERFACE` varchar(64) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `HOST_NAME` varchar(64) default NULL,
  `COMP_NAME` varchar(64) default NULL,
  `DOMAIN` varchar(64) default NULL,
  `LOGIN_NAME` varchar(64) default NULL,
  `FIRST_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `LAST_TIME` timestamp NOT NULL default '0000-00-00 00:00:00',
  `COUNT` int(11) default '1',
  `OBJECT_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_ALERT_ALERTSTYPE` (`STYPE_CODE`),
  KEY `FK_ALERT_ALERTTYPE` (`TYPE_CODE`),
  CONSTRAINT `FK_ALERT_ALERTSTYPE` FOREIGN KEY (`STYPE_CODE`) REFERENCES `monitor_alert_smalltype` (`CODE`),
  CONSTRAINT `FK_ALERT_ALERTTYPE` FOREIGN KEY (`TYPE_CODE`) REFERENCES `monitor_alert_type` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_alert
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_alert_policy`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_alert_policy`;
CREATE TABLE `monitor_alert_policy` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(64) default NULL,
  `MODIFY_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `NOTE` text,
  `CHECK_GAP` int(11) default NULL,
  `ERROR_GAP` int(11) default NULL,
  `ERROR_RETRY` int(11) default NULL,
  `LIMEN_RETRY` int(11) default NULL,
  `TEMP_RETRY` int(11) default NULL,
  `DAY_OF_WEEK` varchar(30) default NULL,
  `DAILY_START` int(11) default NULL,
  `DAILY_END` int(11) default NULL,
  `MOBILE_SWITCH` tinyint(1) default NULL,
  `EMAIL_SWITCH` tinyint(1) default NULL,
  `SOUND_SWITCH` tinyint(1) default NULL,
  `ALERT_RECEIVERS` varchar(250) default NULL,
  `ALERT_TYPES` varchar(250) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_alert_policy
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_alert_smalltype`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_alert_smalltype`;
CREATE TABLE `monitor_alert_smalltype` (
  `CODE` int(11) NOT NULL,
  `NAME` varchar(200) default NULL,
  `PCODE` int(11) default NULL,
  `ISUSE` int(11) default NULL,
  `LEVEL` int(11) default NULL,
  `DESCRIPTION` varchar(500) default NULL,
  PRIMARY KEY  (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_alert_smalltype
-- ----------------------------
INSERT INTO `monitor_alert_smalltype` VALUES ('1', '设备无反应', '1', '1', '1', '报警说明：通过SNMP协议向目标设备发送请求，没有得到回应.<br/>产生原因：<br/>1．可能是SNMP通讯字（Community）配置不正确；<br/>2．可能是本机的网络设置出现问题导致不能连接到网络；<br/>3．可能是本机与目标设备之间的物理链路不通；<br/>4．可能是目标设备故障、断电或是没有启动SNMP服务.<br/>解决办法：针对以上4种原因逐个排查。');
INSERT INTO `monitor_alert_smalltype` VALUES ('2', '服务器无反应', '1', '1', '1', '报警说明：通过SNMP协议向目标服务器发送请求，没有得到回应。<br/>产生原因：1．可能是SNMP通讯字（Community）配置不正确；<br/>2．可能是本机的网络设置出现问题导致不能连接到网络；<br/>3．可能是本机与目标服务器之间的物理链路不通；<br/>4．可能是目标服务器故障、断电或是没有启动SNMP服务。<br/>解决办法：针对以上4种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('3', '服务无反应', '1', '1', '1', '报警说明：通过TCP协议向目标服务的端口发送连接请求，没有得到回应。<br/>产生原因：<br/>1．可能是本机的网络设置出现问题导致不能连接到网络；<br/>2．可能是本机与目标服务所在的服务器之间的物理链路不通；<br/>3．可能是目标服务器故障或者断电；<br/>4．可能是目标服务没有正常运行或已被关闭，或是已经转到了其它的端口。<br/>解决办法：针对以上4种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('4', '新计算机', '2', '1', '3', '报警说明：扫描网络内计算机后，对比计算机档案表，出现了新增的MAC记录。<br/>产生原因：<br/>1．有新计算机连接到网络中；<br/>2．原有计算机更换或新增了网卡。 \r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('5', 'IP地址改变', '2', '1', '3', '报警说明：扫描网络内计算机后，发现存在某个MAC更换了原有的IP地址。<br/>产生原因：<br/>1．网络使用DHCP协议动态分配IP，某个台主机本次登录后被重新分配了新的IP地址；<br/>2．用户自己修改了原来的静态IP地址。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('6', '上连设备改变', '2', '1', '3', '报警说明：扫描网络内计算机后，发现存在某台主机更换了网络位置，通过另外一台交互设备来接入网络.<br/>\r\n产生原因：<br/>1．目标主机可能是便携设备，可以轻易的通过不同交换设备接入到网络;<br/>2．网络内更换了新的交换设备.');
INSERT INTO `monitor_alert_smalltype` VALUES ('7', '计算机名改变', '2', '1', '3', '报警说明：扫描网络内计算机后，发现存在某台主机修改了计算机名。');
INSERT INTO `monitor_alert_smalltype` VALUES ('8', '域或组改变', '2', '1', '3', '报警说明：扫描网络内计算机后，发现存在某台主机修改了域或组。');
INSERT INTO `monitor_alert_smalltype` VALUES ('9', '登录名改变', '2', '1', '3', '报警说明：扫描网络内计算机后，发现存在某台主机更换了登录账号。');
INSERT INTO `monitor_alert_smalltype` VALUES ('10', 'CPU占用率超过阈值', '3', '1', '2', '报警说明：当设备/服务器的CPU连续多次监测结果超过了用户指定的CPU阈值，产生报警。<br/>产生原因：网络线路负载高，或者网络中存在广播风暴可能造成设备的CPU过高<br/>解决办法：可查询设备/服务器的CPU占用率超过阈值记录。');
INSERT INTO `monitor_alert_smalltype` VALUES ('11', '接口流量超过阈值', '3', '1', '2', '报警说明：当设备接口的流量/服务器网卡流量连续多次监测结果超过了用户指定的流量阈值，产生报警。<br/>产生原因：P2P下载、广播风暴、在线视频可能造成流量超过阈值。<br/>解决办法：可查询设备接口的流量/服务器网卡流量超过阈值的记录。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('12', '温度超过阈值', '3', '1', '2', '报警说明：当温度监控设备连续多次监测结果超过了用户指定的温度阈值，产生报警。<br/>产生原因：<br/>解决办法：可查询温度监控设备温度超过阈值的记录。');
INSERT INTO `monitor_alert_smalltype` VALUES ('13', '冷启动', '4', '1', '3', '报警说明：当设备/服务器配置了SNMP TRAP并制定网管机接收TRAP，设备/服务器进行冷启动操作时会产生报警。<br/>产生原因：<br/>解决办法：可查询设备/服务器的冷启动的记录\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('14', '热启动', '4', '1', '3', '报警说明：当设备/服务器配置了SNMP TRAP并制定网管机接收TRAP，设备/服务器进行热启动操作时会产生报警。<br/>产生原因：<br/>解决办法：可查询设备/服务器的热启动的记录。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('15', '接口上线', '4', '1', '3', '报警说明：当设备/服务器配置了SNMP TRAP并制定网管机接收TRAP，设备/服务器的接口状态由下线变为上线时会产生报警。<br/>产生原因：<br/>解决办法：可查询设备/服务器的接口上线的记录。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('16', '接口下线', '4', '1', '3', '报警说明：当设备/服务器配置了SNMP TRAP并制定网管机接收TRAP，设备/服务器的接口状态由上线变为下线时会产生报警。<br/>产生原因：<br/>解决办法：可查询设备/服务器的接口下线的记录。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('17', 'ARP报警', '5', '1', '3', '报警说明：扫描交换机的ARP表进行分析，出现一个MAC对应多个IP时，从入网计算机表查找此MAC，如果能找到则产生报警。<br/>产生原因：产生报警的入网计算机中病毒，或者安装了某些软件导致其不停申请和抢占IP，致使同网段其它计算机无法获取IP。<br/>解决办法：根据报警的入网计算机对应的上连设备和接口，定位计算机所在位置，断开其网网络连接，并责令计算机负责人进行问题排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('18', 'SYSLOG', '5', '1', '3', null);
INSERT INTO `monitor_alert_smalltype` VALUES ('19', '接口改变', '2', '1', '3', '报警说明：扫描网络内计算机后，发现存在某台主机更换了网络位置，通过同一台交互设备的另一个接口来接入网络。');
INSERT INTO `monitor_alert_smalltype` VALUES ('20', '设备恢复', '1', '1', '4', '报警说明：从“设备无反应”故障中恢复到正常状态，表明故障已经排除。');
INSERT INTO `monitor_alert_smalltype` VALUES ('21', '服务器恢复', '1', '1', '4', '报警说明：从“服务器无反应”故障中恢复到正常状态，表明故障已经排除。');
INSERT INTO `monitor_alert_smalltype` VALUES ('22', '服务恢复', '1', '1', '4', '报警说明：从“服务无反应”故障中恢复到正常状态，表明本机又能够与该服务建立起TCP连接。');
INSERT INTO `monitor_alert_smalltype` VALUES ('23', 'CPU占用率恢复正常', '3', '1', '5', '报警说明：通过SNMP协议向目标服务器发送请求，没有得到回应。<br/>产生原因：<br/>1．可能是SNMP通讯字（Community）配置不正确；<br/>2．可能是本机的网络设置出现问题导致不能连接到网络；<br/>3．可能是本机与目标服务器之间的物理链路不通；<br/>4．可能是目标服务器故障、断电或是没有启动SNMP服务。<br/>解决办法：针对以上4种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('24', '接口流量恢复正常', '3', '1', '5', '报警说明：当设备接口的流量/服务器网卡流量由超过指定流量阈值转变为低于指定流量阈值，产生报警。<br/>产生原因：<br/>解决办法：可查询设备接口的流量/服务器网卡流量恢复正常的记录。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('25', '温度恢复正常', '3', '1', '5', '报警说明：当温度监控设备由超过指定温度阈值转变为低于指定温度阈值，产生报警。<br/>产生原因：<br/>解决办法：可查询温度监控设备温度恢复正常的记录\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('26', '进程不存在', '1', '1', '1', '报警说明：通过SNMP协议向目标进程所在的服务器发送请求，没有得到回应或者得到回应但回应内容表明该进程已不存在。<br/>产生原因：<br/>1．可能是SNMP通讯字（Community）配置不正确；<br/>2．可能是本机的网络设置出现问题导致不能连接到网络；<br/>3．可能是本机与目标进程所在的服务器之间的物理链路不通；<br/>4．可能是目标服务器故障、断电或是没有启动SNMP服务；<br/>5．可能是目标进程已被关闭或自行退出。<br/>解决办法：针对以上5种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('27', '进程恢复', '1', '1', '4', '报警说明：从“进程不存在”故障中恢复到正常状态，表明本机又能够检测到该进程了。');
INSERT INTO `monitor_alert_smalltype` VALUES ('28', '关键接口异常', '1', '1', '1', '报警说明：通过SNMP协议向目标接口所在的设备发送请求，没有得到回应或者得到回应但回应内容表明该接口已下线或被关闭。<br/>产生原因：<br/>1．可能是SNMP通讯字（Community）配置不正确；<br/>2．可能是本机的网络设置出现问题导致不能连接到网络；<br/>3．可能是本机与目接口所在设备之间的物理链路不通；<br/>4．可能是目标设备故障、断电或是没有启动SNMP服务；<br/>5．可能是目标接口已被关闭或下线。<br/>解决办法：针对以上5种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('29', '关键接口恢复', '4', '1', '4', '报警说明：从“关键接口异常”故障中恢复到正常状态，表明本机又能够检测到该接口处于上线状态了。');
INSERT INTO `monitor_alert_smalltype` VALUES ('30', 'URL监测异常', '1', '1', '1', '报警说明：通过HTTP协议或HTTPS向目标URL的指定端口发送连接请求，请求超时或返回结果异常。<br/>产生原因：<br/>1．可能是本机的网络设置出现问题导致不能连接到网络；<br/>2．可能是本机与目标服务所在的服务器之间的物理链路不通；<br/>3．可能是目标服务器故障或者断电；<br/>4．可能是目标服务没有正常运行或已被关闭，或是已经转到了其它的端口；<br/>5．可能是网络繁忙导致页面响应迟缓；<br/>6．可能是目标页面中包含了不应该有的关键词，或者是没有包含应该存在的关键词。<br/>解决办法：针对以上6种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('31', 'URL监测恢复正常', '1', '1', '1', '报警说明：从“URL监测异常”故障中恢复到正常状态，表明本机又能够正常连接到目标页面了。');
INSERT INTO `monitor_alert_smalltype` VALUES ('32', 'Ping操作无响应', '1', '1', '1', '报警说明：通过Ping操作向目标设备发送请求，没有得到回应。<br/>产生原因：<br/>1．可能是本机与目标设备之间的物理链路不通；<br/>2．可能是目标机器设置了防火墙，禁止了对Ping的响应；<br/>3．可能是目标机器没有开机、断电或出现故障。<br/>解决办法：针对以上3种原因逐个排查。\r\n');
INSERT INTO `monitor_alert_smalltype` VALUES ('33', 'Ping响应恢复', '4', '1', '4', '报警说明：通过Ping操作向目标设备发送请求，响应恢复正常。\n');

-- ----------------------------
-- Table structure for `monitor_alert_type`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_alert_type`;
CREATE TABLE `monitor_alert_type` (
  `CODE` int(11) NOT NULL,
  `NAME` varchar(200) default NULL,
  `ISUSE` int(11) default NULL,
  PRIMARY KEY  (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_alert_type
-- ----------------------------
INSERT INTO `monitor_alert_type` VALUES ('1', '故障报警', '1');
INSERT INTO `monitor_alert_type` VALUES ('2', '接入报警', '1');
INSERT INTO `monitor_alert_type` VALUES ('3', '阈值超过', '1');
INSERT INTO `monitor_alert_type` VALUES ('4', 'SNMPTRAP', '1');
INSERT INTO `monitor_alert_type` VALUES ('5', '其它报警', '1');

-- ----------------------------
-- Table structure for `monitor_arp_alert_white_mac`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_arp_alert_white_mac`;
CREATE TABLE `monitor_arp_alert_white_mac` (
  `ID` int(11) NOT NULL auto_increment,
  `MAC` varchar(64) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_arp_alert_white_mac
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_arp_log`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_arp_log`;
CREATE TABLE `monitor_arp_log` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `MAC` varchar(64) default NULL,
  `TYPE` varchar(64) default NULL,
  `FIRST_TIME` datetime default NULL,
  `LAST_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_arp_log
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_building`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_building`;
CREATE TABLE `monitor_building` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(64) default NULL,
  `NOTE` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_building
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_building_zone`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_building_zone`;
CREATE TABLE `monitor_building_zone` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(64) default NULL,
  `BUILDING_ID` int(11) default NULL,
  `NOTE` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_BUILDINGZONEINFO_BUILDINGINFO` (`BUILDING_ID`),
  CONSTRAINT `FK_BUILDINGZONEINFO_BUILDINGINFO` FOREIGN KEY (`BUILDING_ID`) REFERENCES `monitor_building` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_building_zone
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_computer`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_computer`;
CREATE TABLE `monitor_computer` (
  `ID` int(11) NOT NULL auto_increment,
  `DEVICE_ID` int(11) NOT NULL,
  `IP` varchar(64) default NULL,
  `MAC` varchar(32) default NULL,
  `DEVICE_PORT` varchar(10) default NULL,
  `DEVICE_INTERFACE` varchar(10) default NULL,
  `INTERFACE_DESCRIPTION` varchar(500) default NULL,
  `HOST_NAME` varchar(500) default NULL,
  `COMPUTER_NAME` varchar(500) default NULL,
  `DOMAIN` varchar(250) default NULL,
  `LOGIN_NAME` varchar(250) default NULL,
  `SEGMENT` varchar(100) default NULL,
  `DISCOVERY_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `USER` varchar(200) default NULL,
  `PLACE` varchar(500) default NULL,
  `DEPARTMENT` varchar(200) default NULL,
  `REGISTERED` tinyint(1) NOT NULL default '0',
  `SNAPSHOT` tinyint(1) default '0',
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_COMPUTE_DEVICE` (`DEVICE_ID`),
  CONSTRAINT `FK_COMPUTE_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_computer
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_cpu_data`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_cpu_data`;
CREATE TABLE `monitor_cpu_data` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `CPU` float(5,2) default NULL,
  `GATHER_TIME` datetime default NULL,
  `DATA_INDEX` int(11) default '1',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_cpu_data
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_cpu_data_day`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_cpu_data_day`;
CREATE TABLE `monitor_cpu_data_day` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `CPU` float(5,2) default NULL,
  `MAX_CPU` float(5,2) default NULL,
  `MIN_CPU` float(5,2) default NULL,
  `GATHER_TIME` datetime default NULL,
  `DATA_INDEX` int(11) default '1',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_cpu_data_day
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_cpu_data_hour`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_cpu_data_hour`;
CREATE TABLE `monitor_cpu_data_hour` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `CPU` float(5,2) default NULL,
  `MAX_CPU` float(5,2) default NULL,
  `MIN_CPU` float(5,2) default NULL,
  `GATHER_TIME` datetime default NULL,
  `DATA_INDEX` int(11) default '1',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_cpu_data_hour
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_department`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_department`;
CREATE TABLE `monitor_department` (
  `ID` int(11) NOT NULL auto_increment,
  `DEPARTMENT_ID` int(11) default NULL,
  `NAME` varchar(64) default NULL,
  `LEADER` varchar(64) default NULL,
  `TEL` varchar(64) default NULL,
  `EMAIL` varchar(128) default NULL,
  `URL` char(10) default NULL,
  `NOTE` varchar(256) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_department
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_device`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_device`;
CREATE TABLE `monitor_device` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `MAC` varchar(64) default NULL,
  `CPU_OID` varchar(64) default NULL,
  `READ_COMMUNITY` varchar(64) default NULL,
  `WRITE_COMMUNITY` varchar(64) default NULL,
  `DESCRIPTION` varchar(250) default NULL,
  `NAME` varchar(200) default NULL,
  `NAME_CN` varchar(200) default NULL,
  `CUSTOM_TITLE` varchar(100) default NULL,
  `TYPE_CODE` int(11) default NULL,
  `MODEL_CODE` int(11) default NULL,
  `PASSWORD_LOGIN` varchar(64) default NULL,
  `PASSWORD_ENABLE` varchar(64) default NULL,
  `MFR_ID` int(11) default NULL,
  `POLICY_ID` int(11) default NULL,
  `BUILDING_ID` int(11) default NULL,
  `ZONE_ID` int(11) default NULL,
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  `ARCHIVE_USER_NUM` int(11) unsigned NOT NULL default '0',
  `SNAP_USER_NUM` int(11) unsigned NOT NULL default '0',
  `V3_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_DEVICE_BUILDINGZONE` (`BUILDING_ID`),
  KEY `FK_DEVICE_DEVICETYPE` (`TYPE_CODE`),
  KEY `FK_DEVICE_POLICY` (`POLICY_ID`),
  KEY `FK_DEVICE_SWITCHMODEL` (`MODEL_CODE`),
  KEY `FK_VENDORMAC_DEVICE` (`MFR_ID`),
  KEY `FK_DEVICE_BUILDING` (`ZONE_ID`),
  KEY `FK_Device_V3` (`V3_ID`),
  CONSTRAINT `FK_DEVICE_BUILDING` FOREIGN KEY (`ZONE_ID`) REFERENCES `monitor_building` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_DEVICE_BUILDINGZONE` FOREIGN KEY (`BUILDING_ID`) REFERENCES `monitor_building_zone` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `FK_DEVICE_DEVICETYPE` FOREIGN KEY (`TYPE_CODE`) REFERENCES `monitor_device_type` (`CODE`),
  CONSTRAINT `FK_DEVICE_POLICY` FOREIGN KEY (`POLICY_ID`) REFERENCES `monitor_alert_policy` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `FK_DEVICE_SWITCHMODEL` FOREIGN KEY (`MODEL_CODE`) REFERENCES `monitor_switch_model` (`CODE`) ON DELETE SET NULL,
  CONSTRAINT `FK_Device_V3` FOREIGN KEY (`V3_ID`) REFERENCES `monitor_v3_params` (`ID`),
  CONSTRAINT `FK_VENDORMAC_DEVICE` FOREIGN KEY (`MFR_ID`) REFERENCES `monitor_vendor_mac` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_device
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_device_type`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_device_type`;
CREATE TABLE `monitor_device_type` (
  `CODE` int(11) NOT NULL,
  `NAME` varchar(200) default NULL,
  `ISUSE` int(11) default NULL,
  `ICON` varchar(64) default NULL COMMENT '默认图标名称',
  PRIMARY KEY  (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_device_type
-- ----------------------------
INSERT INTO `monitor_device_type` VALUES ('1', '交换', '1', 'switch');
INSERT INTO `monitor_device_type` VALUES ('2', '路由', '1', 'router');
INSERT INTO `monitor_device_type` VALUES ('3', '交换+路由', '1', 'router');
INSERT INTO `monitor_device_type` VALUES ('4', 'SNMP主机', '1', 'server');
INSERT INTO `monitor_device_type` VALUES ('5', 'AP控制器', '1', 'controller');
INSERT INTO `monitor_device_type` VALUES ('6', '温湿度监控设备', '1', 'akcp');
INSERT INTO `monitor_device_type` VALUES ('20', '虚拟设备', '1', 'virtual ');

-- ----------------------------
-- Table structure for `monitor_interface_cache`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_interface_cache`;
CREATE TABLE `monitor_interface_cache` (
  `ID` int(11) NOT NULL auto_increment,
  `DEVICE_ID` int(11) default NULL,
  `PORT` varchar(64) default NULL,
  `INTERFACE` varchar(64) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `NOTE` varchar(250) default NULL,
  `lLOCATE` varchar(250) default NULL,
  `USER` varchar(250) default NULL,
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_INTERFACECACHE_DEVICE` (`DEVICE_ID`),
  CONSTRAINT `FK_INTERFACECACHE_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_interface_cache
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_key_interface`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_key_interface`;
CREATE TABLE `monitor_key_interface` (
  `ID` int(11) NOT NULL auto_increment,
  `DEVICE_ID` int(11) default NULL,
  `INTERFACE_NUM` varchar(11) default NULL,
  `IS_MONITOR` tinyint(1) default NULL,
  `INTERFACE_DESC` varchar(255) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_KEY_INTERFACE_DEVICE` (`DEVICE_ID`),
  CONSTRAINT `FK_KEY_INTERFACE_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_key_interface
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_linkport`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_linkport`;
CREATE TABLE `monitor_linkport` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `SUBNET_ID` int(11) default NULL,
  `PORT` varchar(64) default NULL,
  `INTERFACE` varchar(200) default NULL,
  `DESCRIPTION` varchar(500) default NULL,
  `DOWNLINK_IP` varchar(64) default NULL,
  `DOWNLINK_PORT` varchar(64) default NULL,
  `DOWNLINK_INTERFACE` varchar(200) default NULL,
  `DOWNLINK_DESC` varchar(500) default NULL,
  `INODE` int(11) default NULL,
  `EDGE` int(11) default NULL,
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `AK_Key_Link_Port` (`ID`),
  KEY `FK_LINKPORT_SUBNET` (`SUBNET_ID`),
  CONSTRAINT `FK_LINKPORT_SUBNET` FOREIGN KEY (`SUBNET_ID`) REFERENCES `monitor_subnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_linkport
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_mac_info`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_mac_info`;
CREATE TABLE `monitor_mac_info` (
  `ID` int(11) NOT NULL,
  `MAC` varchar(32) default NULL,
  `IP` varchar(64) default NULL,
  `SWITCH_ID` int(11) default NULL,
  `DEP_ID` int(11) default NULL,
  `BUILDING_ID` int(11) default NULL,
  `ZONE_ID` int(11) default NULL,
  `ROOM` varchar(64) default NULL,
  `MOVABLE` tinyint(1) default NULL,
  `USER_NAME` varchar(64) default NULL,
  `PHONE_NUM` varchar(32) default NULL,
  `EMAIL` varchar(255) default NULL,
  `USER_NUM` varchar(32) default NULL,
  `MODIFY_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `NOTE1` varchar(255) default NULL,
  `NOTE2` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_MACINFO_BUILDINGINFO` (`BUILDING_ID`),
  KEY `FK_MACINFO_BUILDINGZONEINFO` (`ZONE_ID`),
  KEY `FK_MACINFO_DEPARTMENT` (`DEP_ID`),
  KEY `FK_MACINFO_DEVICE` (`SWITCH_ID`),
  CONSTRAINT `FK_MACINFO_BUILDINGINFO` FOREIGN KEY (`BUILDING_ID`) REFERENCES `monitor_building` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `FK_MACINFO_BUILDINGZONEINFO` FOREIGN KEY (`ZONE_ID`) REFERENCES `monitor_building_zone` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `FK_MACINFO_DEPARTMENT` FOREIGN KEY (`DEP_ID`) REFERENCES `monitor_department` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `FK_MACINFO_DEVICE` FOREIGN KEY (`SWITCH_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_mac_info
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_old_device`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_old_device`;
CREATE TABLE `monitor_old_device` (
  `ID` int(11) NOT NULL auto_increment,
  `ip` varchar(64) default NULL,
  `Subnet` int(11) NOT NULL,
  `Read_Community` varchar(64) default NULL,
  `Write_Community` varchar(64) default NULL,
  `Description` varchar(500) default NULL,
  `Title` varchar(256) default NULL,
  `Type` int(11) default NULL,
  `mfr_mac` int(11) default NULL,
  `Note1` varchar(250) default NULL,
  `Note2` varchar(250) default NULL,
  `policy_id` int(11) default NULL,
  `Note3` varchar(250) default NULL,
  `Note4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_old_device
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_old_linkport`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_old_linkport`;
CREATE TABLE `monitor_old_linkport` (
  `ID` int(11) NOT NULL auto_increment,
  `Link_IP` varchar(64) default NULL,
  `Subnet` int(11) NOT NULL,
  `Link_Port` varchar(64) default NULL,
  `Link_Interface` varchar(200) default NULL,
  `Link_Description` varchar(500) default NULL,
  `DownLink_IP` varchar(64) default NULL,
  `DownLink_Port` varchar(64) default NULL,
  `DownLink_Interface` varchar(200) default NULL,
  `DownLink_Description` varchar(500) default NULL,
  `Link_Inode` int(11) default NULL,
  `Link_Edge` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_old_linkport
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_ping_dest`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_ping_dest`;
CREATE TABLE `monitor_ping_dest` (
  `ID` int(11) NOT NULL auto_increment,
  `TYPE_ID` int(11) default NULL,
  `DEST_IP_URL` varchar(255) default NULL,
  `HOST_NAME` varchar(255) default NULL,
  `NOTE` varchar(500) default NULL,
  `IS_RUN` tinyint(1) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_PINTDEST_PINTTYPE` (`TYPE_ID`),
  CONSTRAINT `FK_PINTDEST_PINTTYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `monitor_ping_type` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_ping_dest
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_ping_response_record`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_ping_response_record`;
CREATE TABLE `monitor_ping_response_record` (
  `ID` int(11) NOT NULL auto_increment,
  `PING_DEST_ID` int(11) default NULL,
  `EXCUTE_COUNT` int(11) default NULL,
  `START_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `END_TIME` timestamp NOT NULL default '0000-00-00 00:00:00',
  `RESPONSE_STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_RESPONSERECORD_DEST` (`PING_DEST_ID`),
  CONSTRAINT `FK_RESPONSERECORD_DEST` FOREIGN KEY (`PING_DEST_ID`) REFERENCES `monitor_ping_dest` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_ping_response_record
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_ping_result`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_ping_result`;
CREATE TABLE `monitor_ping_result` (
  `ID` int(11) NOT NULL auto_increment,
  `PING_DEST_ID` int(11) default NULL,
  `SUCCESS_COUNT` int(11) default NULL,
  `SEND_COUNT` int(11) default NULL,
  `MIN_REPLY_TIME` int(11) default NULL,
  `MAX_REPLY_TIME` int(11) default NULL,
  `SUM_REPLY_TIME` int(11) default NULL,
  `SUM_TTL` int(11) default NULL,
  `LOOP_GAP` int(11) NOT NULL default '0',
  `COMPLETED_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`),
  KEY `FK_RESULT_DEST` (`PING_DEST_ID`),
  CONSTRAINT `FK_RESULT_DEST` FOREIGN KEY (`PING_DEST_ID`) REFERENCES `monitor_ping_dest` (`ID`) ON DELETE CASCADE ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_ping_result
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_ping_result_day`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_ping_result_day`;
CREATE TABLE `monitor_ping_result_day` (
  `ID` int(11) NOT NULL auto_increment,
  `PING_DEST_ID` int(11) default NULL,
  `SUCCESS_COUNT` int(11) default NULL,
  `SEND_COUNT` int(11) default NULL,
  `MIN_REPLY_TIME` int(11) default NULL,
  `MAX_REPLY_TIME` int(11) default NULL,
  `SUM_REPLY_TIME` int(11) default NULL,
  `SUM_TTL` int(11) default NULL,
  `RESPONSE_TIME` int(11) default NULL,
  `NO_RESPONSE_TIME` int(11) default NULL,
  `COMPLETED_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`),
  KEY `FK_RESULTDAY_DEST` (`PING_DEST_ID`),
  CONSTRAINT `FK_RESULTDAY_DEST` FOREIGN KEY (`PING_DEST_ID`) REFERENCES `monitor_ping_dest` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_ping_result_day
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_ping_time_point`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_ping_time_point`;
CREATE TABLE `monitor_ping_time_point` (
  `ID` int(11) NOT NULL auto_increment,
  `TIME_POINT` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_ping_time_point
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_ping_type`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_ping_type`;
CREATE TABLE `monitor_ping_type` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(64) default NULL,
  `NOTE` varchar(500) default NULL,
  `POLICY_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_PINTTYPE_ALERTPOLICY` (`POLICY_ID`),
  CONSTRAINT `FK_PINTTYPE_ALERTPOLICY` FOREIGN KEY (`POLICY_ID`) REFERENCES `monitor_alert_policy` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_ping_type
-- ----------------------------
INSERT INTO `monitor_ping_type` VALUES ('1', 'combanc', null, null);

-- ----------------------------
-- Table structure for `monitor_realtime_cpu`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_realtime_cpu`;
CREATE TABLE `monitor_realtime_cpu` (
  `ID` int(11) NOT NULL auto_increment,
  `DATA` float default NULL,
  `IP` varchar(64) default NULL,
  `NAME` varchar(200) default NULL,
  `TYPE` varchar(200) default NULL,
  `GATHER_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定期故障检查结束后，统计所有分区中设备，';

-- ----------------------------
-- Records of monitor_realtime_cpu
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_realtime_delay`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_realtime_delay`;
CREATE TABLE `monitor_realtime_delay` (
  `ID` int(11) NOT NULL auto_increment,
  `DATA` bigint(20) default NULL,
  `IP` varchar(64) default NULL,
  `NAME` varchar(200) default NULL,
  `TYPE` varchar(200) default NULL,
  `GATHER_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定期故障检查结束后，统计所有分区中设备，';

-- ----------------------------
-- Records of monitor_realtime_delay
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_realtime_fault`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_realtime_fault`;
CREATE TABLE `monitor_realtime_fault` (
  `ID` int(11) NOT NULL auto_increment,
  `DATA` varchar(64) default NULL,
  `IP` varchar(64) default NULL,
  `NAME` varchar(200) default NULL,
  `TYPE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定期故障检查结束后，统计所有分区中无反应';

-- ----------------------------
-- Records of monitor_realtime_fault
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_realtime_portflow`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_realtime_portflow`;
CREATE TABLE `monitor_realtime_portflow` (
  `ID` int(11) NOT NULL auto_increment,
  `DEVICE_IP` varchar(64) default NULL,
  `DEVICE_NAME` varchar(200) default NULL,
  `DEVICE_TYPE` varchar(200) default NULL,
  `INTERFACE` varchar(64) default NULL,
  `INTERFACE_DESCIPTION` varchar(200) default NULL,
  `IS_LINKPORT` int(11) default NULL,
  `BILATERAL_TRAFFIC` bigint(20) default NULL,
  `TX_BYTE` bigint(20) default NULL,
  `RX_BYTE` bigint(20) default NULL,
  `DELIVERY_TRAFFIC` bigint(20) default NULL,
  `RECEIVE_TRAFFIC` bigint(20) default NULL,
  `GATHER_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_realtime_portflow
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_realtime_use`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_realtime_use`;
CREATE TABLE `monitor_realtime_use` (
  `ID` int(11) NOT NULL auto_increment,
  `TOTAL_NUM` float default NULL,
  `EFFECTIVE_NUM` float default NULL,
  `IP` varchar(64) default NULL,
  `NAME` varchar(200) default NULL,
  `TYPE` varchar(200) default NULL,
  `EXIST` tinyint(1) default NULL,
  `RATE` float default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定期故障检查结束后，统计所有分区中设备，';

-- ----------------------------
-- Records of monitor_realtime_use
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_regular_data`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_regular_data`;
CREATE TABLE `monitor_regular_data` (
  `ID` int(11) unsigned NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `INTERFACE` varchar(200) default NULL,
  `TX_BYTE` bigint(20) default NULL,
  `RX_BYTE` bigint(20) default NULL,
  `BI_TRAFFIC` bigint(20) default NULL,
  `DELIVERY_TRAFFIC` bigint(20) default NULL,
  `RECEIVE_TRAFFIC` bigint(20) default NULL,
  `GATHER_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `AK_Key_DayData` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_regular_data
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_regular_data_day`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_regular_data_day`;
CREATE TABLE `monitor_regular_data_day` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `INTERFACE` varchar(200) default NULL,
  `TX_BYTE` bigint(20) default NULL,
  `RX_BYTE` bigint(20) default NULL,
  `BI_TRAFFIC` bigint(20) default NULL,
  `MAX_BI_TRAFFIC` bigint(20) default NULL,
  `MIN_BI_TRAFFIC` bigint(20) default NULL,
  `DELIVERY_TRAFFIC` bigint(20) default NULL,
  `MAX_DELIVERY_TRAFFIC` bigint(20) default NULL,
  `MIN_DELIVERY_TRAFFIC` bigint(20) default NULL,
  `RECEIVE_TRAFFIC` bigint(20) default NULL,
  `MAX_RECEIVE_TRAFFIC` bigint(20) default NULL,
  `MIN_RECEIVE_TRAFFIC` bigint(20) default NULL,
  `GATHER_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `AK_Key_DayData` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_regular_data_day
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_regular_data_hour`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_regular_data_hour`;
CREATE TABLE `monitor_regular_data_hour` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `INTERFACE` varchar(200) default NULL,
  `TX_BYTE` bigint(20) default NULL,
  `RX_BYTE` bigint(20) default NULL,
  `BI_TRAFFIC` bigint(20) default NULL,
  `MAX_BI_TRAFFIC` bigint(20) default NULL,
  `MIN_BI_TRAFFIC` bigint(20) default NULL,
  `DELIVERY_TRAFFIC` bigint(20) default NULL,
  `MAX_DELIVERY_TRAFFIC` bigint(20) default NULL,
  `MIN_DELIVERY_TRAFFIC` bigint(20) default NULL,
  `RECEIVE_TRAFFIC` bigint(20) default NULL,
  `MAX_RECEIVE_TRAFFIC` bigint(20) default NULL,
  `MIN_RECEIVE_TRAFFIC` bigint(20) default NULL,
  `GATHER_TIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `AK_Key_DayData` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_regular_data_hour
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_report_task`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_report_task`;
CREATE TABLE `monitor_report_task` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(100) default NULL,
  `INTRO` varchar(255) default NULL,
  `URL` varchar(255) default NULL,
  `CONFIG_XML` mediumtext,
  `ADD_TIME` datetime default NULL,
  `PROP1` varchar(50) default NULL,
  `PROP2` varchar(50) default NULL,
  `PROP3` varchar(50) default NULL,
  `PROP4` varchar(50) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_report_task
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_segment`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_segment`;
CREATE TABLE `monitor_segment` (
  `ID` int(11) NOT NULL auto_increment,
  `SUBNET_ID` int(11) default NULL,
  `NETWORK_SEGMENT` varchar(64) default NULL,
  `MASK` varchar(64) default NULL,
  `CONNECTED_DEVICE` varchar(64) default NULL,
  `DEVICE_PORT` varchar(10) default NULL,
  `DEVICE_INTERFACE` varchar(10) default NULL,
  `INTERFACE_DESCRIPTION` varchar(500) default NULL,
  `NEXT_HOP` varchar(64) default NULL,
  `TYPE` varchar(100) default NULL,
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_SEGMENT_SUBNET` (`SUBNET_ID`),
  CONSTRAINT `FK_SEGMENT_SUBNET` FOREIGN KEY (`SUBNET_ID`) REFERENCES `monitor_subnet` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_segment
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_server_process`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_server_process`;
CREATE TABLE `monitor_server_process` (
  `ID` int(11) NOT NULL auto_increment,
  `DEVICE_ID` int(11) default NULL,
  `NAME` varchar(200) default NULL,
  `START` tinyint(1) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_PROCESS_DEVICE` (`DEVICE_ID`),
  CONSTRAINT `FK_PROCESS_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_server_process
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_snapshot`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_snapshot`;
CREATE TABLE `monitor_snapshot` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `MAC` varchar(32) default NULL,
  `DEVICE_ID` int(11) NOT NULL,
  `DEVICE_PORT` varchar(10) default NULL,
  `DEVICE_INTERFACE` varchar(10) default NULL,
  `INTERFACE_DESCRIPTION` varchar(500) default NULL,
  `HOST_NAME` varchar(500) default NULL,
  `COMPUTER_NAME` varchar(500) default NULL,
  `DOMAIN` varchar(250) default NULL,
  `LOGIN_NAME` varchar(250) default NULL,
  `DISCOVERY_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `USER` varchar(200) default NULL,
  `PLACE` varchar(500) default NULL,
  `DEPARTMENT` varchar(200) default NULL,
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_SNAPSHOT_DEVICE` (`DEVICE_ID`),
  CONSTRAINT `FK_SNAPSHOT_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_subnet`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_subnet`;
CREATE TABLE `monitor_subnet` (
  `ID` int(11) NOT NULL auto_increment,
  `PARENT_ID` int(11) default NULL,
  `NAME` varchar(200) default NULL,
  `TYPE_CODE` int(11) default NULL,
  `SUBNET_ORDER` int(11) default NULL,
  `SCAN` int(11) default '0',
  `START` int(11) default '0',
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  `CENTER_IP` varchar(64) default NULL COMMENT '中心设备',
  `EDGE_RENDERER` varchar(64) default NULL COMMENT '连线样式：直线，折线，弧线',
  `NODE_TEXT_TYPE` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_SUBNET_SUBNETTYPE` (`TYPE_CODE`),
  KEY `FK_SUBNET_SUBNET` (`PARENT_ID`),
  CONSTRAINT `FK_SUBNET_SUBNET` FOREIGN KEY (`PARENT_ID`) REFERENCES `monitor_subnet` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_SUBNET_SUBNETTYPE` FOREIGN KEY (`TYPE_CODE`) REFERENCES `monitor_subnet_type` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_subnet
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_subnet_device`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_subnet_device`;
CREATE TABLE `monitor_subnet_device` (
  `ID` int(11) NOT NULL auto_increment,
  `SUBNET_ID` int(11) NOT NULL,
  `DEVICE_ID` int(11) NOT NULL,
  `LOCATION_X` int(11) default NULL,
  `LOCATION_Y` int(11) default NULL,
  `ICON` varchar(256) default NULL,
  `SELECTED` int(11) default '0' COMMENT '是否选择0：否',
  `MONITORED` int(11) default '0' COMMENT '是否监控0：否',
  `IS_LINK` int(11) NOT NULL default '0',
  PRIMARY KEY  (`ID`),
  KEY `FK_SUBNETDEVICE_DEVICE` (`DEVICE_ID`),
  KEY `FK_SUBNETDEVICE_SUBNET` (`SUBNET_ID`),
  CONSTRAINT `FK_SUBNETDEVICE_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_SUBNETDEVICE_SUBNET` FOREIGN KEY (`SUBNET_ID`) REFERENCES `monitor_subnet` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_subnet_device
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_subnet_type`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_subnet_type`;
CREATE TABLE `monitor_subnet_type` (
  `CODE` int(11) NOT NULL,
  `NAME` varchar(200) default NULL,
  `ISUSE` int(11) default NULL,
  PRIMARY KEY  (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_subnet_type
-- ----------------------------
INSERT INTO `monitor_subnet_type` VALUES ('1', '交换', '1');
INSERT INTO `monitor_subnet_type` VALUES ('2', '路由', '1');
INSERT INTO `monitor_subnet_type` VALUES ('3', '服务器', '1');
INSERT INTO `monitor_subnet_type` VALUES ('4', '业务', '1');

-- ----------------------------
-- Table structure for `monitor_switch_model`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_switch_model`;
CREATE TABLE `monitor_switch_model` (
  `CODE` int(11) NOT NULL auto_increment,
  `TYPE` varchar(64) default NULL,
  `NOTE` text,
  `FORMAT` text,
  `MODIFIABLE` tinyint(1) default NULL,
  `LOGIN` text,
  `BACKUP` text,
  `IP_MAC` text,
  `ARP_DISBAND` text,
  `INTERFACE_OPEN` text,
  `INTERFACE_SHUTDOWN` text,
  `SNMP_CONFIG` text,
  `IOS_BACKUP` text,
  `OTHER` text,
  `SAVE` text,
  `LOGOUT` text,
  `ISUSE` int(11) default NULL,
  PRIMARY KEY  (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_switch_model
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_system_param`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_system_param`;
CREATE TABLE `monitor_system_param` (
  `CODE` varchar(100) NOT NULL,
  `PARAM` varchar(200) default NULL,
  `VALUE` varchar(200) default NULL,
  `HIGH_VALUE` varchar(200) default NULL,
  `NOTE` varchar(255) default NULL,
  `TYPE` varchar(64) default NULL,
  `ISUSE` int(11) default NULL,
  PRIMARY KEY  (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警参数类别，isUse暂时不用';

-- ----------------------------
-- Records of monitor_system_param
-- ----------------------------
INSERT INTO `monitor_system_param` VALUES ('AdminPwd', '系统管理员密码', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('AlertDataKeep', '报警保存记录数', '30000', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('AntiAliased', '消除锯齿', '0', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('ArpLimen', 'Arp警示阈值（个）', '5', '10', '', 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('ArpNbt', 'Netbios扫描', '0', null, '默认为0，不进行Netbios扫描', 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('BackColor', '背景颜色', '白', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('BackPicPath', '拓扑图默认背景图片路径', 'images/monitor/defaultBackPic.jpg', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('BackupType', '备份、恢复配置包含历史数据', '0', null, '默认不备份历史数据，暂时未使用', 'history', '0');
INSERT INTO `monitor_system_param` VALUES ('BandHighLimen', '带宽严重警示阈值（%）', '30', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('BandLimen', '带宽警示阈值（%）', '1', '10', '', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('BroadcastLimen', '广播包警示阈值（个/ 秒）', '50', '100', '', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('ChangeAlert', '计算机信息改变后报警', '1', null, '计算机类报警', 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('ChangeRefresh', '报警后进行归档', '1', null, '（计算机类报警）报警后自动归档', 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('CheckGap', '故障检查间隔', '5', '0 0/5 * * * ?', '单位为秒', 'time', '1');
INSERT INTO `monitor_system_param` VALUES ('ComputerNameChangedAlert', '计算机名改变', '1', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('CpuHighLimen', 'CPU严重警示阈值（%）', '60', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('CpuLimen', 'CPU警示阈值（%）', '1', '60', '', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('DeleteGap', '计算机表过期删除时间', '三个月', null, '', 'time', '1');
INSERT INTO `monitor_system_param` VALUES ('DeviceBgPath', '设备拓扑背景图', '', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('DiskRate', '磁盘使用率（%）', '90', '95', '', 'server', '1');
INSERT INTO `monitor_system_param` VALUES ('DomainChangedAlert', '域或组改变', '1', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('EmailSwitch', '邮件报警', '1', null, '邮件报警开关，默认0，关', 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('ErrorGap', '异常后的监测时间间隔', '1', '0 0/1 * * * ?', '单位为秒', 'time', '1');
INSERT INTO `monitor_system_param` VALUES ('ExportFile', '输出图像/数据到文件', '1', null, '默认为真', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('FluxScanGapTime', '流量及CPU采集间隔', '5', '0 0/5 * * * ?', null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('FluxSwitch', '启用流量、CPU采集(5分钟)', '1', null, '默认启用流量采集', 'history', '1');
INSERT INTO `monitor_system_param` VALUES ('FluxType', '流量采集类型', '1', null, null, 'history', '1');
INSERT INTO `monitor_system_param` VALUES ('HostBgPath', '服务器拓扑背景图', '', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('HourDataKeep', '小时历史数据保存时长', '90', null, null, 'history', '1');
INSERT INTO `monitor_system_param` VALUES ('IgnoreError', '忽略MAC转发异常', '1', null, '默认为1，忽略MAC转发异常', 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('IPChangedAlert', 'IP地址改变', '1', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('L23Scan', '扫描MAC转发表&ARP表', '0', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('LimitToAddLevel', '报警升级门限', '100', null, null, null, null);
INSERT INTO `monitor_system_param` VALUES ('LinkChangedAlert', '交换机接口改变', '1', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('MailSender', '发送Email帐号', '', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('MailSenderPwd', '发送Email帐号密码', '', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('MaxBroadcast', '是否在拓扑图中标识出向心广播包最多的节点', '1', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('MaxFlow', '是否在拓扑图中标识出向心广播包最多的节点', '1', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('MemRate', '内存使用率（%）', '90', '95', '', 'server', '1');
INSERT INTO `monitor_system_param` VALUES ('MinuteDataKeep', '分钟历史数据保存时长', '7', null, null, 'history', '1');
INSERT INTO `monitor_system_param` VALUES ('MobileSwitch', '短信报警', '1', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('NewHost', '新计算机', '1', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('PingAutoExcuteMode', ' ping的自动执行模式', '0', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingDayInWeek', 'ping时间（星期几）', '02,04,01', null, null, null, null);
INSERT INTO `monitor_system_param` VALUES ('PingDetailLife', 'ping细节表保存时间', '2', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingEndTimeOfDay', 'Ping结束时间', '23', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('PingExcuteMode', 'ping执行模式', '1', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingIsDefineHours', '在下列时间段才监测', '1', null, null, null, null);
INSERT INTO `monitor_system_param` VALUES ('PingLoopGap', 'ping扫描间隔时间', '5', '0 0/20 21-23 ? * 02,04,01', null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingNum', 'ping次数', '4', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingReportLife', 'ping统计表保存时间', '180', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingScan', 'Ping预扫描', '0', null, '', 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('PingSize', 'ping包长度', '32', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PingStartTimeOfDay', 'Ping开始时间', '4', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('PingTimeout', 'ping超时时间', '4000', null, null, null, '1');
INSERT INTO `monitor_system_param` VALUES ('PollGap', '数据刷新间隔', '5', '0 0/5 * * * ?', '拓扑图轮询间隔', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('ProcNum', '进程数', '50', '80', '', 'server', '1');
INSERT INTO `monitor_system_param` VALUES ('ScanGapTime', '扫描间隔(分钟)', '30', '0 0/30 * * * ?', null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('ScanHour0', '定时扫描时间1', '10', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('ScanHour1', '定时扫描时间2', '16', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('ScanHour2', '定时扫描时间3', '20', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('ScrollTime', '幻灯显示间隔（秒）', '10', null, null, 'time', '1');
INSERT INTO `monitor_system_param` VALUES ('SmsSn', '短信猫序列号', '', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('SmtpServer', '发送SMTP服务器', '', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('SnmpRetry', 'SNMP重试次数', '1', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('SnmpTimeOut', 'SNMP 网络超时时间', '5', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('SoundSwitch', '声音报警', '1', null, null, 'alert', '1');
INSERT INTO `monitor_system_param` VALUES ('SyncHour', '自动同步时间', '不定时同步', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('SyncServer', '数据同步网管站IP', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('TcpConn', 'TCP连接数', '100', '150', '', 'server', '1');
INSERT INTO `monitor_system_param` VALUES ('TempHighLimen', '温度严重警示阈值（度）', '40', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('TempLimen', '温度警示阈值（度）', '25', '40', '', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('ToolIp1', '带IP参数', '1', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('ToolIp2', '带IP参数', '0', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('ToolName1', '工具一名称', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('ToolName2', '工具二名称', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('ToolPath1', '工具一路径', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('ToolPath2', '工具二路径', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('TopN', 'TopN', '10', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('UnicastLimen', '单播包警示阈值（个/ 秒）', '2000', '2000', '', 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('UserBgPath', '用户拓扑背景图', '', null, null, 'topo', '1');
INSERT INTO `monitor_system_param` VALUES ('UserChangedAlert', '登录名改变', '1', null, null, 'scan', '1');
INSERT INTO `monitor_system_param` VALUES ('UserPwd', '普通用户密码', '', null, null, 'other', '1');
INSERT INTO `monitor_system_param` VALUES ('VmemRate', '虚存使用率（%）', '90', '95', '', 'server', '1');

-- ----------------------------
-- Table structure for `monitor_tcp_port`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_tcp_port`;
CREATE TABLE `monitor_tcp_port` (
  `PORT_NUMBER` varchar(64) NOT NULL,
  `PORT_NAME` varchar(64) default NULL,
  `PORT_TYPE` varchar(64) default NULL,
  PRIMARY KEY  (`PORT_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_tcp_port
-- ----------------------------
INSERT INTO `monitor_tcp_port` VALUES ('110', 'POP3', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('1433', 'MS-SQL', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('1521', 'ORACLE', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('21', 'FTP', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('25', 'SMTP', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('3306', 'MYSQL', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('389', 'LDAP', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('443', 'HTTPS', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('80', 'HTTP', 'TCP');
INSERT INTO `monitor_tcp_port` VALUES ('8080', 'WWWCache', 'TCP');

-- ----------------------------
-- Table structure for `monitor_topology_edge`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_topology_edge`;
CREATE TABLE `monitor_topology_edge` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(64) default NULL,
  `INTERFACE` varchar(200) default NULL,
  `DOWNLINK_IP` varchar(64) default NULL,
  `DOWNLINK_INTERFACE` varchar(200) default NULL,
  `DATA_SOURCE` varchar(32) default NULL,
  `SEND_BYTE` bigint(20) default '0',
  `RECEIVE_BYTE` bigint(20) default '0',
  `SEND_UNICAST_PACKET` bigint(20) default '0',
  `RECEIVE_UNICAST_PACKET` bigint(20) default '0',
  `SEND_BROADCAST_PACKET` bigint(20) default '0',
  `RECEIVE_BROADCAST_PACKET` bigint(20) default '0',
  `SEND_WRONG_PACKET` bigint(20) default '0',
  `RECEIVE_WRONG_PACKET` bigint(20) default '0',
  `SEND_LOST_PACKET` bigint(20) default '0',
  `RECEIVE_LOST_PACKET` bigint(20) default '0',
  `DIFF_SEND_BYTE` bigint(20) default '0',
  `DIFF_RECEIVE_BYTE` bigint(20) default '0',
  `DIFF_SEND_UNICAST_PACKET` bigint(20) default '0',
  `DIFF_RECEIVE_UNICAST_PACKET` bigint(20) default '0',
  `DIFF_SEND_BROADCAST_PACKET` bigint(20) default '0',
  `DIFF_RECEIVE_BROADCAST_PACKET` bigint(20) default '0',
  `DIFF_SEND_WRONG_PACKET` bigint(20) default '0',
  `DIFF_RECEIVE_WRONG_PACKET` bigint(20) default '0',
  `DIFF_SEND_LOST_PACKET` bigint(20) default '0',
  `DIFF_RECEIVE_LOST_PACKET` bigint(20) default '0',
  `TIME` bigint(20) default '0',
  `TIME_GAP` bigint(20) default '0',
  `INTERFACE_RATE` bigint(20) default '0',
  `BILATERAL_PEAK` bigint(20) default '0',
  `BILATERAL_PEAK_TIME` bigint(20) default '0',
  `SEND_PEAK` bigint(20) default '0',
  `SEND_PEAK_TIME` bigint(20) default '0',
  `RECEIVE_PEAK` bigint(20) default '0',
  `RECEIVE_PEAK_TIME` bigint(20) default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_topology_edge
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_topology_node`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_topology_node`;
CREATE TABLE `monitor_topology_node` (
  `ID` int(11) NOT NULL auto_increment,
  `DEVICE_ID` int(11) default NULL,
  `CPU_OID` varchar(64) default NULL,
  `CPU` varchar(64) default NULL,
  `CPU_AVERAGE` varchar(64) default NULL,
  `CPU_PEAK` varchar(64) default NULL,
  `PEAK_TIME` varchar(64) default NULL,
  `REPLY_TIME` varchar(64) default NULL,
  `RUN_TIME` varchar(64) default NULL,
  `STATUS` varchar(64) default NULL,
  `GRAND_TOTAL` bigint(20) default NULL,
  `COUNT` bigint(20) default NULL,
  `TIME` varchar(64) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_monitor_topology_node_1` (`DEVICE_ID`),
  CONSTRAINT `FK_monitor_topology_node_1` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_topology_node
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_url_response`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_url_response`;
CREATE TABLE `monitor_url_response` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(100) default NULL,
  `ADDRESS` varchar(500) default NULL,
  `PORT` varchar(10) default NULL,
  `DOMAIN` varchar(200) default NULL,
  `USERNAME` varchar(200) default NULL,
  `PASSWORD` varchar(200) default NULL,
  `REALM` varchar(200) default NULL,
  `CONTAIN` varchar(500) default NULL,
  `NOCONTAIN` varchar(500) default NULL,
  `SERVER_IP` varchar(100) default NULL,
  `REPLY_TIME` int(11) default NULL,
  `SCAN` int(11) default NULL,
  `ADD_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `FLUSH_TIME` timestamp NOT NULL default '0000-00-00 00:00:00',
  `STATE_CODE` int(11) default NULL,
  `TYPE` int(11) default NULL,
  `SUBNET` int(11) default NULL,
  `POLICY_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_URL_ALERTPOLICY` (`POLICY_ID`),
  CONSTRAINT `FK_URL_ALERTPOLICY` FOREIGN KEY (`POLICY_ID`) REFERENCES `monitor_alert_policy` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_url_response
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_url_service`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_url_service`;
CREATE TABLE `monitor_url_service` (
  `ID` int(11) NOT NULL,
  `DEVICE_ID` int(11) default NULL,
  `URL_ID` int(11) default NULL,
  `PARAMETER` varchar(2000) default NULL,
  `DESCRIPTION` varchar(2000) default NULL,
  `LASTRUNTIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `MONITORINTERVAL` int(11) default NULL,
  `DAYOFWEEK` varchar(7) default NULL,
  `DAILY_START` int(11) default NULL,
  `DAILY_END` int(11) default NULL,
  `SEVERITY` int(11) default NULL,
  `ENABLED` int(11) default NULL,
  `STATE` int(11) default NULL,
  `LASTMSG` varchar(4000) default NULL,
  `DESC_TYPE` int(11) default NULL,
  `WONTEDDESC` varchar(4000) default NULL,
  `ERRATUM_INTERVAL` int(11) default NULL,
  `ERRATUM_COUNT` int(11) default NULL,
  `SRV_NAME` varchar(255) default NULL,
  `STAMP` varchar(127) default NULL,
  `LAST_STATE` int(11) default NULL,
  `TYPE_ID` varchar(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_SERVICE_DEVICE` (`DEVICE_ID`),
  KEY `FK_SERVICE_TYPE` (`TYPE_ID`),
  KEY `FK_SERVICE_URL` (`URL_ID`),
  CONSTRAINT `FK_SERVICE_DEVICE` FOREIGN KEY (`DEVICE_ID`) REFERENCES `monitor_device` (`ID`),
  CONSTRAINT `FK_SERVICE_TYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `monitor_url_type` (`ID`),
  CONSTRAINT `FK_SERVICE_URL` FOREIGN KEY (`URL_ID`) REFERENCES `monitor_url_response` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_url_service
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_url_type`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_url_type`;
CREATE TABLE `monitor_url_type` (
  `ID` varchar(20) NOT NULL,
  `NAME` varchar(255) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `CONFIGER` varchar(255) default NULL,
  `MONITOR` varchar(255) default NULL,
  `EXTEND_OID` varchar(20) default NULL,
  `SORT` int(11) default NULL,
  `AUTO_DISCOVER` int(11) default NULL,
  `PORTS` varchar(127) default NULL,
  `TIMEOUT` int(11) default NULL,
  `MONITOR_INTERVAL` int(11) default NULL,
  `ERRATUM_INTERVAL` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_url_type
-- ----------------------------
INSERT INTO `monitor_url_type` VALUES ('HTTP', 'HTTP服务', '使用HTTP协议监测Web服务器，可以监测Web服务器运行状态是否正常。', 'netmon.monitor.http.HTTPParamConfiger', 'netmon.monitor.http.HTTPMonitor', '1', '3', '1', '80', '5000', '600', '300');
INSERT INTO `monitor_url_type` VALUES ('HTTPS', 'HTTPS服务', '使用HTTPS协议监测Web服务器，可以监测Web服务器运行状态是否正常。', 'netmon.monitor.https.HTTPSParamConfiger', 'netmon.monitor.https.HTTPSMonitor', '2', '3', '1', '443', '5000', '600', '300');

-- ----------------------------
-- Table structure for `monitor_user`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_user`;
CREATE TABLE `monitor_user` (
  `ID` int(11) NOT NULL auto_increment,
  `UserName` varchar(64) default NULL,
  `FullName` varchar(128) default NULL,
  `Password` varchar(20) default NULL,
  `Email` varchar(64) default NULL,
  `Phone` varchar(20) default NULL,
  `Mobile` varchar(20) default NULL,
  `Status` int(11) default NULL,
  `DepartmentID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_Reference_41` (`DepartmentID`),
  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`DepartmentID`) REFERENCES `monitor_department` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_user
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_v3_auth`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_v3_auth`;
CREATE TABLE `monitor_v3_auth` (
  `ID` int(11) NOT NULL,
  `PROTOCOL` int(11) default NULL,
  `NAME` varchar(128) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_v3_auth
-- ----------------------------
INSERT INTO `monitor_v3_auth` VALUES ('1', '21', 'MD5', 'MD5_AUTH');
INSERT INTO `monitor_v3_auth` VALUES ('2', '22', 'SHA', 'SHA_AUTH');

-- ----------------------------
-- Table structure for `monitor_v3_params`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_v3_params`;
CREATE TABLE `monitor_v3_params` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(200) default NULL,
  `MODIFY_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `NOTE` text,
  `CONTEXT_NAME` varchar(250) default NULL,
  `CONTEXT_ID` varchar(250) default NULL,
  `USER_NAME` varchar(128) default NULL,
  `SECURITY_LEVEL` int(11) default NULL,
  `AUTH_PROTOCOL` int(11) default NULL,
  `AUTH_PASSWORD` varchar(250) default NULL,
  `PRIV_PROTOCOL` int(11) default NULL,
  `PRIV_PASSWORD` varchar(250) default NULL,
  `ENGINE_ID` varchar(250) default NULL,
  `NOTE1` varchar(250) default NULL,
  `NOTE2` varchar(250) default NULL,
  `NOTE3` varchar(250) default NULL,
  `NOTE4` varchar(250) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_Reference_V3_Auth` (`AUTH_PROTOCOL`),
  KEY `FK_Reference_V3_Priv` (`PRIV_PROTOCOL`),
  KEY `FK_Reference_V3_SecLvl` (`SECURITY_LEVEL`),
  CONSTRAINT `FK_Reference_V3_Auth` FOREIGN KEY (`AUTH_PROTOCOL`) REFERENCES `monitor_v3_auth` (`ID`),
  CONSTRAINT `FK_Reference_V3_Priv` FOREIGN KEY (`PRIV_PROTOCOL`) REFERENCES `monitor_v3_priv` (`ID`),
  CONSTRAINT `FK_Reference_V3_SecLvl` FOREIGN KEY (`SECURITY_LEVEL`) REFERENCES `monitor_v3_security_level` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_v3_params
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_v3_priv`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_v3_priv`;
CREATE TABLE `monitor_v3_priv` (
  `ID` int(11) NOT NULL,
  `PROTOCOL` int(11) default NULL,
  `NAME` varchar(128) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_v3_priv
-- ----------------------------
INSERT INTO `monitor_v3_priv` VALUES ('1', '46', 'CBC-3DES', 'CBC-3DES');
INSERT INTO `monitor_v3_priv` VALUES ('2', '50', 'CBC-DES', 'CBC-DES');
INSERT INTO `monitor_v3_priv` VALUES ('3', '49', 'CFB-AES-128', 'CFB-AES-128');
INSERT INTO `monitor_v3_priv` VALUES ('4', '48', 'CFB-AES-192', 'CFB-AES-192');
INSERT INTO `monitor_v3_priv` VALUES ('5', '47', 'CFB-AES-256', 'CFB-AES-256');

-- ----------------------------
-- Table structure for `monitor_v3_security_level`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_v3_security_level`;
CREATE TABLE `monitor_v3_security_level` (
  `ID` int(11) NOT NULL,
  `LEVEL` int(11) default NULL,
  `NAME` varchar(128) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_v3_security_level
-- ----------------------------
INSERT INTO `monitor_v3_security_level` VALUES ('1', '0', 'NoAuth,NoPriv', 'NO_AUTH_NO_PRIV');
INSERT INTO `monitor_v3_security_level` VALUES ('2', '1', 'Auth,NoPriv', 'AUTH_NO_PRIV');
INSERT INTO `monitor_v3_security_level` VALUES ('3', '2', 'Auth,Priv', 'AUTH_PRIV');

-- ----------------------------
-- Table structure for `monitor_vendor`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_vendor`;
CREATE TABLE `monitor_vendor` (
  `VENDOR` varchar(64) NOT NULL,
  `CPU_OID` varchar(64) default NULL,
  `TEMPERATURE` varchar(64) default NULL,
  `NOTE` varchar(255) default NULL,
  PRIMARY KEY  (`VENDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_vendor
-- ----------------------------
INSERT INTO `monitor_vendor` VALUES ('11', '.1.3.6.1.4.1.11.2.14.11.5.1.9.6.1.0', null, 'Hp');
INSERT INTO `monitor_vendor` VALUES ('1916', '.1.3.6.1.4.1.1916.1.1.1.28.0', '.1.3.6.1.4.1.1916.1.1.1.8.0', 'Extreme');
INSERT INTO `monitor_vendor` VALUES ('1991', '.1.3.6.1.4.1.1991.1.1.2.1.51.0', null, 'Foundry');
INSERT INTO `monitor_vendor` VALUES ('2011', '.1.3.6.1.4.1.2011.2.23.1.18.1.3.0', null, 'Huawei');
INSERT INTO `monitor_vendor` VALUES ('2272', '.1.3.6.1.4.1.2272.1.1.20.0', null, 'BAY');
INSERT INTO `monitor_vendor` VALUES ('25506', '.1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7', null, 'H3C');
INSERT INTO `monitor_vendor` VALUES ('2636', '.1.3.6.1.4.1.2636.3.1.13.1.8.9.1.0.0', null, 'Juniper');
INSERT INTO `monitor_vendor` VALUES ('311', '.1.3.6.1.2.1.25.3.3.1.2', null, 'MS');
INSERT INTO `monitor_vendor` VALUES ('3854', '.1.3.6.1.4.1.3854.1.2.2.1.16.1.3.0', null, 'AKCP');
INSERT INTO `monitor_vendor` VALUES ('3902', '.1.3.6.1.4.1.3902.3.3.1.1.5', null, 'ZX');
INSERT INTO `monitor_vendor` VALUES ('4881', '.1.3.6.1.4.1.4881.1.1.10.2.36.1.1.1.0', null, 'Ruijie');
INSERT INTO `monitor_vendor` VALUES ('6339', '.1.3.6.1.4.1.6339.100.1.11.0', null, 'DCS');
INSERT INTO `monitor_vendor` VALUES ('6486', '.1.3.6.1.4.1.6486.800.1.2.1.16.1.1.1.13.0', null, 'Alcatel');
INSERT INTO `monitor_vendor` VALUES ('8212', '.1.3.6.1.4.1.8212.1.1.4.1.1.4.1', null, 'Harbour');
INSERT INTO `monitor_vendor` VALUES ('9', '.1.3.6.1.4.1.9.2.1.56.0', '.1.3.6.1.4.1.9.9.13.1.3.1.3.1', 'Cisco');

-- ----------------------------
-- Table structure for `monitor_vendor_mac`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_vendor_mac`;
CREATE TABLE `monitor_vendor_mac` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(200) default NULL,
  `MAC` varchar(200) default NULL,
  `ISUSE` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_vendor_mac
-- ----------------------------
INSERT INTO `monitor_vendor_mac` VALUES ('1', '思科/3COM', 'm', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('2', '华3/神码', 'p', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('3', '锐捷.1924F+', 'RG.1924F+', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('4', '锐捷.1926F+', 'RG.1926F+', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('5', '锐捷.1926G+', 'RG.1926G+', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('6', '锐捷.2024E', 'RG.2024E', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('7', '锐捷.2024M', 'RG.2024M', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('8', '锐捷.2126G/2150G', 'RG.2150G', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('9', '锐捷.MAC绑定', 'RG.BD', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('10', '港湾.5610/2948', 'GW.1', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('11', '港湾.5010/2824/3550', 'GW.2', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('12', '港湾.U2/U4', 'GW.3', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('13', '华为', 'HW.6506', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('14', '中兴', 'ZX.2826', '1');
INSERT INTO `monitor_vendor_mac` VALUES ('15', '思科无线AP', 'CISCO.AP', '1');

-- ----------------------------
-- Table structure for `monitor_vlan`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_vlan`;
CREATE TABLE `monitor_vlan` (
  `ID` int(11) NOT NULL,
  `SEGMENT` varchar(64) default NULL,
  `MASK` varchar(64) default NULL,
  `DESCRIPTION` varchar(64) default NULL,
  `SWITCH_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_VLAN_DEVICE` (`SWITCH_ID`),
  CONSTRAINT `FK_VLAN_DEVICE` FOREIGN KEY (`SWITCH_ID`) REFERENCES `monitor_device` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_vlan
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_zone_vlan`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_zone_vlan`;
CREATE TABLE `monitor_zone_vlan` (
  `ID` int(11) NOT NULL auto_increment,
  `ZONE_ID` int(11) default NULL,
  `VLAN_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_ZONEVLAN_BUILDINGZONEINFO` (`ZONE_ID`),
  KEY `FK_ZONEVLAN_VLAN` (`VLAN_ID`),
  CONSTRAINT `FK_ZONEVLAN_BUILDINGZONEINFO` FOREIGN KEY (`ZONE_ID`) REFERENCES `monitor_building_zone` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_ZONEVLAN_VLAN` FOREIGN KEY (`VLAN_ID`) REFERENCES `monitor_vlan` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_zone_vlan
-- ----------------------------

-- ----------------------------
-- Table structure for `personal_notice`
-- ----------------------------
DROP TABLE IF EXISTS `personal_notice`;
CREATE TABLE `personal_notice` (
  `id` bigint(50) NOT NULL,
  `userId_` bigint(50) default NULL,
  `title_` varchar(500) default NULL,
  `date_` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `content_` varchar(500) default NULL,
  `from_` varchar(100) default NULL,
  `to_` varchar(100) default NULL,
  `cc_` varchar(400) default NULL,
  `read_` tinyint(4) default NULL,
  `usertype_` int(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `privilege`
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `OWNERTYPE` char(1) NOT NULL,
  `OWENR` int(11) NOT NULL,
  `PRIVTYPE` varchar(60) NOT NULL,
  `MID` varchar(200) NOT NULL,
  `CODE` varchar(60) NOT NULL,
  `Value` char(2) NOT NULL,
  `ID` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege(系统预置管理员，默认用户，默认工程师，默认IT经理)
-- ----------------------------
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '109', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '70', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '72', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '72', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '72', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '72', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'search', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'detail', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '74', 'export', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '78', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'search', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'detail', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '85', 'reset', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '86', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '87', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'search', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'detail', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '88', 'reset', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'search', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'detail', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '89', 'reset', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '80', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '90', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '91', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '95', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '96', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '98', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '98', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '98', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '98', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'search', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'detail', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'reset', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'noticecheck', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '97', 'addpersonal', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '106', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '50', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '51', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '52', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '53', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '53', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '54', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '55', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '56', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 2, 'menu', '57', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '4', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '10', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '11', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '15', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '17', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '17', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '17', 'intervene', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '17', 'distribute', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '18', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '20', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '22', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '22', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '22', 'query', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '23', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '24', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '24', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '24', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '24', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '24', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '24', 'query', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '40', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '41', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '42', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '42', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '42', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '43', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 4, 'menu', '44', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '4', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'changes', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'change', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'changeselect', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'export', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '5', 'query', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '6', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '6', 'config', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '6', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '6', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '7', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '7', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '7', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '7', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '8', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '8', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '8', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '8', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '9', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '9', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '9', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '9', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '12', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '13', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '13', 'import', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '13', 'download', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '107', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '108', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '15', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '16', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '17', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '17', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '18', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '20', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '21', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '21', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '21', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '21', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '21', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '21', 'query', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '23', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '24', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '24', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '24', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '24', 'query', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '25', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '25', 'add', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '25', 'select', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '25', 'update', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '25', 'delete', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '25', 'history', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '40', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '41', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '42', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '43', 'browse', '1');
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 3, 'menu', '44', 'browse', '1');


-- ----------------------------
-- Table structure for `pro_definition`
-- ----------------------------
DROP TABLE IF EXISTS `pro_definition`;
CREATE TABLE `pro_definition` (
  `defId` int(11) NOT NULL,
  `typeId` int(11) default NULL,
  `name` varchar(256) default NULL,
  `description` varchar(1024) default NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `deployId` varchar(64) default NULL,
  `defXml` text,
  `drawDefXml` text,
  `isDefault` char(10) default NULL COMMENT '是否缺省\n            1=是\n            0=否',
  PRIMARY KEY  (`defId`),
  KEY `FK_FK_PD_R_PT` (`typeId`),
  CONSTRAINT `FK_FK_PD_R_PT` FOREIGN KEY (`typeId`) REFERENCES `pro_type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pro_definition
-- ----------------------------

-- ----------------------------
-- Table structure for `pro_type`
-- ----------------------------
DROP TABLE IF EXISTS `pro_type`;
CREATE TABLE `pro_type` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(128) default NULL,
  PRIMARY KEY  (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pro_type
-- ----------------------------

-- ----------------------------
-- Table structure for `pro_user_assign`
-- ----------------------------
DROP TABLE IF EXISTS `pro_user_assign`;
CREATE TABLE `pro_user_assign` (
  `assignId` int(11) NOT NULL,
  `deployId` varchar(128) default NULL,
  `activityName` varchar(128) default NULL,
  `roleId` varchar(128) default NULL,
  `roleName` varchar(128) default NULL,
  `userId` varchar(128) default NULL,
  `username` varchar(128) default NULL,
  `isSigned` int(11) default NULL COMMENT '1=是会签任务\n            0=非会签任务            \n            若为会签任务，则需要为该会签添加会签的决策方式的设置',
  PRIMARY KEY  (`assignId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pro_user_assign
-- ----------------------------

-- ----------------------------
-- Table structure for `problem_type`
-- ----------------------------
DROP TABLE IF EXISTS `problem_type`;
CREATE TABLE `problem_type` (
  `id` int(11) NOT NULL auto_increment,
  `problem_cate` varchar(100) default NULL COMMENT '类别英文名称',
  `problem_cate_SC` varchar(100) default NULL COMMENT '类别中文名称',
  `code` varchar(200) default NULL COMMENT '目录结构代码',
  `pid` int(11) default NULL COMMENT '类别代码',
  `add_table1` int(11) default NULL COMMENT '增补描述表单',
  `add_table2` int(11) default NULL COMMENT '增补解决方案表单',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of problem_type
-- ----------------------------

-- ----------------------------
-- Table structure for `process_run`
-- ----------------------------
DROP TABLE IF EXISTS `process_run`;
CREATE TABLE `process_run` (
  `runId` int(11) NOT NULL,
  `subject` varchar(256) default NULL COMMENT '标题：一般为流程名称＋格式化的时间',
  `creator` varchar(128) default NULL,
  `userId` int(11) default NULL,
  `defId` int(11) default NULL,
  `piId` varchar(64) default NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `runStatus` int(11) default NULL COMMENT '0=尚未启动\n            1=已经启动流程\n            2=运行结束',
  PRIMARY KEY  (`runId`),
  KEY `FK_PR_R_USER` (`userId`),
  KEY `FK_PR_R_PD` (`defId`),
  CONSTRAINT `FK_FK_PR_R_PD` FOREIGN KEY (`defId`) REFERENCES `pro_definition` (`defId`),
  CONSTRAINT `FK_PR_R_PD` FOREIGN KEY (`defId`) REFERENCES `pro_definition` (`defId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PR_R_USER` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process_run
-- ----------------------------

-- ----------------------------
-- Table structure for `project_manage`
-- ----------------------------
DROP TABLE IF EXISTS `project_manage`;
CREATE TABLE `project_manage` (
  `id` bigint(50) NOT NULL auto_increment,
  `projectName_` varchar(200) default NULL COMMENT '项目名称',
  `projectManager_` varchar(100) default NULL COMMENT '项目主管',
  `projectLocation_` varchar(100) default NULL COMMENT '项目所属地区',
  `projectType_` int(11) default NULL COMMENT '项目类别',
  `beginTime_` timestamp NULL default NULL COMMENT '项目提交的起始时间',
  `endTime_` timestamp NULL default NULL COMMENT '项目完成的结束时间',
  `projectContent_` varchar(500) default NULL COMMENT '项目的内容',
  `file` varchar(500) default NULL COMMENT '附加文档',
  `userId_` bigint(50) default NULL COMMENT '项目属于哪个主管',
  `userName_` varchar(200) default NULL COMMENT '主管的名字',
  `projectNo_` bigint(50) default NULL COMMENT '项目编号',
  `projectCreater_` varchar(100) default NULL COMMENT '项目创建者',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of project_manage
-- ----------------------------

-- ----------------------------
-- Table structure for `report_mana`
-- ----------------------------
DROP TABLE IF EXISTS `report_mana`;
CREATE TABLE `report_mana` (
  `id` int(11) NOT NULL auto_increment,
  `report_title` varchar(100) default NULL,
  `report_value` longtext,
  `creator` varchar(50) default NULL,
  `linkto` int(11) default NULL,
  `linkitem` int(11) default NULL,
  `createtime` datetime default NULL,
  `requdept` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_mana
-- ----------------------------

-- ----------------------------
-- Table structure for `role_group`
-- ----------------------------
DROP TABLE IF EXISTS `role_group`;
CREATE TABLE `role_group` (
  `id` int(11) NOT NULL auto_increment,
  `pid` int(11) NOT NULL default '-1' COMMENT '父类id',
  `name` varchar(100) default NULL,
  `code` varchar(200) default NULL,
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_group
-- ----------------------------

-- ----------------------------
-- Table structure for `role_table`
-- ----------------------------
DROP TABLE IF EXISTS `role_table`;
CREATE TABLE `role_table` (
  `id` int(11) NOT NULL auto_increment,
  `enname` varchar(100) NOT NULL COMMENT '表英文名',
  `chname` varchar(200) default NULL COMMENT '表中文名',
  `description` varchar(200) default NULL COMMENT '描述',
  `prop` varchar(200) default NULL COMMENT '预留字段',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_table
-- ----------------------------
INSERT INTO `role_table` VALUES ('1', 'assets_base', '资产列表', '所有资产信息', null);

-- ----------------------------
-- Table structure for `role_table_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `role_table_privilege`;
CREATE TABLE `role_table_privilege` (
  `id` int(11) NOT NULL auto_increment,
  `roleID` int(11) NOT NULL,
  `tableID` int(11) NOT NULL,
  `privilege` int(4) NOT NULL COMMENT '0:个人;1:部门;2:所有',
  `prop` varchar(200) default NULL COMMENT '预留字段,可能会存选择部门时拼接相关信息',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_table_privilege
-- ----------------------------
INSERT INTO `role_table_privilege` VALUES ('1', '3', '1', '0', null);
INSERT INTO `role_table_privilege` VALUES ('2', '4', '1', '2', null);
-- ----------------------------
-- Table structure for `roleteam`
-- ----------------------------
DROP TABLE IF EXISTS `roleteam`;
CREATE TABLE `roleteam` (
  `id` int(11) NOT NULL auto_increment,
  `locationID` int(11) default NULL COMMENT '地理位置ID',
  `name` varchar(100) default NULL COMMENT '组分名称或者角色名称',
  `type` varchar(1) default NULL COMMENT '1-工程师分组，0-角色',
  `description` varchar(100) default NULL COMMENT '备用字段',
  `orderNo` int(11) default NULL,
  `teamleader` int(11) default NULL COMMENT '组长负责人',
  `useFor` int(11) default NULL COMMENT '固定，浮动,全局',
  `roletype` int(11) NOT NULL default '3' COMMENT '色角类型 0：admin 1：工程师 2：it经理 3：用户 4:管理员',
  `roletypename` varchar(20) default NULL COMMENT '色角类型名称',
  `rolegroup` int(10) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `FKF02A97D3B912ED0C` (`locationID`),
  CONSTRAINT `FKF02A97D3B912ED0C` FOREIGN KEY (`locationID`) REFERENCES `location` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleteam(系统预置管理员，默认用户，默认工程师，默认IT经理)
-- ----------------------------
INSERT INTO `roleteam` VALUES ('1', null, '管理员', '0', null, null, null, '3', '4', '管理员', '0');
INSERT INTO `roleteam` VALUES ('2', null, '用户(系统预置)', '0', null, null, null, '1', '3', '用户', '0');
INSERT INTO `roleteam` VALUES ('3', null, '工程师(系统预置)', '0', null, null, null, '1', '1', '工程师', '0');
INSERT INTO `roleteam` VALUES ('4', null, 'IT经理(系统预置)', '0', null, null, null, '1', '2', '经理', '0');
-- ----------------------------
-- Table structure for `schedualed_task_detail`
-- ----------------------------
DROP TABLE IF EXISTS `schedualed_task_detail`;
CREATE TABLE `schedualed_task_detail` (
  `ID` int(11) NOT NULL auto_increment,
  `ScheTaskID` int(11) default NULL COMMENT '计划任务管理ID',
  `Engineer` varchar(2000) default NULL COMMENT '工程师',
  `TaskCode` varchar(50) default NULL COMMENT '任务编号',
  `State` varchar(10) default NULL COMMENT '状态',
  `SchedualedTime` datetime default NULL COMMENT '计划时间',
  `FinishedTime` datetime default NULL COMMENT '完成时间',
  `Solution` text COMMENT '详细解决方案',
  `Advice` text COMMENT '意见及建议',
  `ReviewTime` datetime default NULL COMMENT '复审时间',
  `ServiceLvl` int(11) default NULL COMMENT '服务水平',
  `SarLvl` int(11) default NULL COMMENT '满意程度',
  `FiniEngineer` varchar(20) default NULL COMMENT '完成任务工程师',
  `Cause` varchar(1000) default NULL COMMENT '删除原因',
  `ReviewEngi` varchar(20) default NULL COMMENT '复审工程师',
  `CronExpress` varchar(50) default NULL COMMENT 'Cron表达式',
  PRIMARY KEY  (`ID`),
  KEY `FK_schedualed_task_detail` (`ScheTaskID`),
  CONSTRAINT `FK_schedualed_task_detail` FOREIGN KEY (`ScheTaskID`) REFERENCES `schedualed_tasks` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedualed_task_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `schedualed_task_diary`
-- ----------------------------
DROP TABLE IF EXISTS `schedualed_task_diary`;
CREATE TABLE `schedualed_task_diary` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `Title` varchar(50) default NULL COMMENT '日志标题',
  `Content` text COMMENT '日志内容',
  `SubmitTime` datetime default NULL COMMENT '提交时间',
  `Progress` int(11) default NULL COMMENT '任务进度',
  `UserId` int(11) default NULL COMMENT '提交用户ID',
  `TaskDetailId` int(11) default NULL COMMENT '任务ID',
  PRIMARY KEY  (`ID`),
  KEY `FK_schedualed_task_diary` (`UserId`),
  CONSTRAINT `FK_schedualed_task_diary` FOREIGN KEY (`UserId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedualed_task_diary
-- ----------------------------

-- ----------------------------
-- Table structure for `schedualed_task_user`
-- ----------------------------
DROP TABLE IF EXISTS `schedualed_task_user`;
CREATE TABLE `schedualed_task_user` (
  `ID` int(11) NOT NULL auto_increment,
  `TaskID` int(11) default NULL COMMENT '任务ID',
  `UserID` int(11) default NULL COMMENT '用户ID',
  `FinishTime` datetime default NULL COMMENT '完成时间',
  `FinishResult` varchar(2000) default NULL COMMENT '完成结果',
  `Flag` int(11) default NULL COMMENT '0:未完成；1:完成',
  `OutOfDay` int(11) default NULL COMMENT '超期天数',
  PRIMARY KEY  (`ID`),
  KEY `FK_schedualed_task_user` (`TaskID`),
  KEY `FK_schedualed_task_user_user` (`UserID`),
  CONSTRAINT `FK_schedualed_task_user` FOREIGN KEY (`TaskID`) REFERENCES `schedualed_task_detail` (`ID`),
  CONSTRAINT `FK_schedualed_task_user_user` FOREIGN KEY (`UserID`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedualed_task_user
-- ----------------------------

-- ----------------------------
-- Table structure for `schedualed_tasks`
-- ----------------------------
DROP TABLE IF EXISTS `schedualed_tasks`;
CREATE TABLE `schedualed_tasks` (
  `ID` int(11) NOT NULL auto_increment,
  `Impact` int(11) default NULL COMMENT '影响度',
  `Urgency` int(11) default NULL COMMENT '紧急度',
  `TaskCate` int(11) default NULL COMMENT '任务类别',
  `ProNO` varchar(50) default NULL COMMENT '任务编码',
  `KeyWord` varchar(200) default NULL COMMENT '摘要',
  `Principal` int(11) default NULL COMMENT '负责人',
  `ProObj` varchar(40) default NULL COMMENT '类别变更号',
  `Submit` varchar(50) default NULL COMMENT '提交者',
  `Serverity` varchar(20) default NULL COMMENT '重要程度',
  `Detail` varchar(5000) default NULL COMMENT '任务描述',
  `UpFile` varchar(3000) default NULL COMMENT '上传文件',
  `Application` varchar(50) default NULL COMMENT '创建人',
  `SubmitAt` datetime default NULL COMMENT '制定时间',
  `ApproveAt` datetime default NULL COMMENT '截止时间',
  `Rate` varchar(40) default NULL COMMENT '任务执行频率',
  `Configure` varchar(600) default NULL COMMENT '涉及配置',
  `Tmp1` varchar(200) default NULL COMMENT '备用1',
  `Tmp2` varchar(200) default NULL COMMENT '备用2',
  `Tmp3` varchar(200) default NULL COMMENT '备用3',
  `Tmp4` varchar(200) default NULL COMMENT '备用4',
  `Tmp5` varchar(200) default NULL COMMENT '备用5',
  PRIMARY KEY  (`ID`),
  KEY `FK_schedualed_tasks` (`TaskCate`),
  KEY `FK_schedualed_tasks_serverity` (`Serverity`),
  KEY `FK_schedualed_tasks_user` (`Principal`),
  CONSTRAINT `FK_schedualed_tasks` FOREIGN KEY (`TaskCate`) REFERENCES `service_category` (`id`),
  CONSTRAINT `FK_schedualed_tasks_user` FOREIGN KEY (`Principal`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedualed_tasks
-- ----------------------------

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL auto_increment,
  `executor` int(11) default NULL,
  `content` varchar(500) default NULL,
  `adate` timestamp NULL default CURRENT_TIMESTAMP,
  `atime` timestamp NULL default '0000-00-00 00:00:00',
  `hour` varchar(2) default NULL,
  `minute` varchar(2) default NULL,
  `level` int(11) default NULL,
  `assigner` int(11) default NULL,
  `status` int(11) default NULL,
  `title` varchar(50) default NULL,
  `detail` varchar(500) default NULL,
  `sign` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------

-- ----------------------------
-- Table structure for `service_category`
-- ----------------------------
DROP TABLE IF EXISTS `service_category`;
CREATE TABLE `service_category` (
  `id` int(11) NOT NULL auto_increment,
  `formguide_id` int(11) default NULL COMMENT '自定义表单id',
  `item` varchar(200) default NULL COMMENT '名称',
  `type` int(11) default NULL COMMENT '1-事件及服务类别 2-计划任务类别 3-变更请求类别 4-项目类别 5-项目任务类别',
  `code` varchar(200) default NULL COMMENT '服务编号(包括自己和所有的父编号)',
  `item_zh` varchar(200) default NULL COMMENT '服务名称的中文',
  `explainer` varchar(255) default NULL COMMENT '说明',
  `ico_url` varchar(100) default NULL COMMENT '图片或自定义页面',
  `open_object` varchar(100) default NULL COMMENT '公开对象或类型',
  `self_service` varchar(100) default NULL COMMENT '是否需要自动服务，0-不显示，直接提交服务请求；1-以向导方式帮助用户解决问题',
  `description` int(11) default NULL COMMENT '是否填写描述：0-允许填写 1-允许填写及选择关联配置 2-不允许填写',
  `isFormguide` int(11) default NULL COMMENT '是否增补自定义表单，0-是，1-不是',
  `defEss` int(11) default NULL COMMENT '影响度',
  `defEme` int(11) default NULL COMMENT '紧急度',
  `isDef` int(11) default NULL COMMENT '是否强制执行默认服务水平协议，0-不是，1-不是',
  `pid` int(11) default NULL COMMENT '父类id',
  `workflow` varchar(50) default NULL COMMENT '备用字段1',
  `approver` varchar(100) default NULL COMMENT '备用字段2',
  `charset` varchar(200) default NULL COMMENT '备用字段3',
  `sla` varchar(255) default NULL COMMENT '备用字段4',
  PRIMARY KEY  (`id`),
  KEY `FK72EFE348378295D3` (`formguide_id`),
  CONSTRAINT `FK72EFE348378295D3` FOREIGN KEY (`formguide_id`) REFERENCES `formguide` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务类别';

-- ----------------------------
-- Records of service_category
-- ----------------------------
INSERT INTO `service_category` VALUES ('1', null, '', '1', '|1$', '用户问题', '用户问题', '', null, '0', '0', null, '2', '2', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for `service_level`
-- ----------------------------
DROP TABLE IF EXISTS `service_level`;
CREATE TABLE `service_level` (
  `id` int(11) NOT NULL,
  `depart_id` int(11) default NULL,
  `title` varchar(20) default NULL,
  `request_no` int(11) NOT NULL,
  `engineer_id` int(11) default NULL,
  `case` varchar(100) default NULL,
  `start_time` date default NULL,
  `end_time` date default NULL,
  `delay_time` int(11) default NULL,
  `totle_time` int(11) default NULL,
  `plan` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `request_no` (`request_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务水平';

-- ----------------------------
-- Records of service_level
-- ----------------------------

-- ----------------------------
-- Table structure for `service_request`
-- ----------------------------
DROP TABLE IF EXISTS `service_request`;
CREATE TABLE `service_request` (
  `id` int(11) NOT NULL auto_increment,
  `request_dept` int(11) default NULL COMMENT '申请人部门id',
  `source_dept` int(11) default NULL COMMENT '问题来源部门id',
  `categroyID` int(11) default NULL COMMENT '事件或变更类别id',
  `operatorID` int(11) default NULL COMMENT '操作人或实施计人id',
  `severity` int(11) default NULL COMMENT '严重程度',
  `emergency` int(11) default NULL COMMENT '紧急度',
  `request_user` int(11) default NULL COMMENT '申请人id',
  `essential` int(11) default NULL COMMENT '影响度',
  `engineerID` int(11) default NULL COMMENT '事件处理工程师或变更实施工程师id',
  `request_no` varchar(60) NOT NULL COMMENT '事件工单号或变更工单号',
  `score` int(11) default NULL COMMENT '分数',
  `times` int(11) default NULL COMMENT '次数',
  `source` varchar(20) default NULL COMMENT '消息来源',
  `message` varchar(200) default NULL COMMENT '消息内容',
  `relation_events` varchar(255) default NULL COMMENT '相关联的事件工单',
  `submint_time` datetime default NULL COMMENT '请求提交时间',
  `expect_time` datetime default NULL COMMENT '希望完成时间',
  `assign_time` datetime default NULL COMMENT '任务指派时间',
  `description` text COMMENT '内容描述及上传的附件信息',
  `IP` varchar(40) default NULL COMMENT '监控系统提交的工单中，该事件或服务请求发生的IP',
  `cause` varchar(255) default NULL COMMENT '事件拒绝原因或强制指派任务意见或建议',
  `is_finished` int(11) default NULL COMMENT '完成为1,未完为0',
  `priority_lvl` int(11) default NULL COMMENT '优先级',
  `return_cause` varchar(200) default NULL COMMENT '退回原因',
  `pause_cause` varchar(200) default NULL COMMENT '挂起原因',
  `transmit_cause` varchar(200) default NULL COMMENT '转交原因',
  `plan` varchar(255) default NULL COMMENT '事件解决计划',
  `solution` text COMMENT '事件处理解决方案或变更实施解决方案',
  `error_cause` varchar(200) default NULL COMMENT '事故原因',
  `summary` varchar(200) default NULL COMMENT '摘要',
  `CI` varchar(255) default NULL COMMENT '所涉及配置项代码',
  `begin_time` datetime default NULL COMMENT '事件开始处理时间或变更流程错误重新执行时间',
  `finish_time` datetime default NULL COMMENT '事件处理结束时间或变更完成时间',
  `feedback` varchar(255) default NULL COMMENT '事件用户反馈或变更复审意见及建议',
  `promise_time` datetime default NULL COMMENT '事件或变更请求承诺完成时间',
  `originator` int(11) default NULL COMMENT '事件提交者或变更发起人',
  `state` int(11) default NULL COMMENT '事件或变更状态,  0:待派单\r\n1:等受理\r\n2:处理中\r\n3:流程进行中\r\n4:已拒绝\r\n5：已完成，等待用户反馈\r\n6：已关闭',
  `add_to_knowledge` int(11) default NULL COMMENT '是否添加到解决方案,0:不添加,1:添加',
  `response_time` datetime default NULL COMMENT '事件或变更请求自动响应时间',
  `transmit_time` datetime default NULL COMMENT '事件或变更请求自动转交时间',
  `accept_engineers` varchar(255) default NULL COMMENT '等待受理工程师IDs',
  `process_step` int(11) default NULL COMMENT '流程进行所到的步骤',
  `service_lvl` int(11) default NULL COMMENT '1.非常满意\r\n2.满意\r\n3.不满意',
  `deal_engineers` varchar(255) default NULL COMMENT '等待处理工程师',
  `table_input` varchar(255) default NULL COMMENT '流程中需要填写的字段名、字段宽度及内容',
  `temp1` int(11) default NULL COMMENT '备用字段1',
  `temp2` varchar(200) default NULL COMMENT '备用字段2',
  `temp3` varchar(255) default NULL COMMENT '备用字段3',
  `temp4` varchar(255) default NULL COMMENT '备用字段4',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `request_no_2` (`request_no`),
  KEY `FK135EDB059510CF42` (`source_dept`),
  KEY `FK135EDB05E7CAAD4E` (`request_dept`),
  KEY `FK135EDB05DB032D6B` (`engineerID`),
  KEY `FK135EDB0585691E60` (`operatorID`),
  KEY `FK135EDB0557E8D72B` (`categroyID`),
  KEY `FK135EDB05D2D5751C` (`request_user`),
  KEY `request_no` (`request_no`),
  CONSTRAINT `FK135EDB0557E8D72B` FOREIGN KEY (`categroyID`) REFERENCES `service_category` (`id`),
  CONSTRAINT `FK135EDB0585691E60` FOREIGN KEY (`operatorID`) REFERENCES `users` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK135EDB059510CF42` FOREIGN KEY (`source_dept`) REFERENCES `department` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK135EDB05D2D5751C` FOREIGN KEY (`request_user`) REFERENCES `users` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK135EDB05DB032D6B` FOREIGN KEY (`engineerID`) REFERENCES `users` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK135EDB05E7CAAD4E` FOREIGN KEY (`request_dept`) REFERENCES `department` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单';

-- ----------------------------
-- Records of service_request
-- ----------------------------

-- ----------------------------
-- Table structure for `service_request_assets`
-- ----------------------------
DROP TABLE IF EXISTS `service_request_assets`;
CREATE TABLE `service_request_assets` (
  `service_id` int(11) NOT NULL,
  `asset_id` int(11) NOT NULL,
  PRIMARY KEY  (`service_id`,`asset_id`),
  KEY `asset_id` (`asset_id`),
  CONSTRAINT `service_request_assets_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `service_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `service_request_assets_ibfk_2` FOREIGN KEY (`asset_id`) REFERENCES `assets_base` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_request_assets
-- ----------------------------

-- ----------------------------
-- Table structure for `service_tran`
-- ----------------------------
DROP TABLE IF EXISTS `service_tran`;
CREATE TABLE `service_tran` (
  `id` int(11) NOT NULL auto_increment,
  `categroyID` int(11) default NULL COMMENT '服务类别ID',
  `service_from` int(11) default NULL COMMENT '任务指派者操作者变更者',
  `service_to` int(11) default NULL COMMENT '任务接收人',
  `requ_no` varchar(40) NOT NULL COMMENT '事件或服务请求工单号',
  `auto_or_end_time` varchar(40) default NULL COMMENT '事件自动转交及承诺完成时间',
  `service_tilte` varchar(200) default NULL COMMENT '活动标题',
  `note` varchar(255) default NULL COMMENT '意见建议、变更内容',
  `type` varchar(40) default NULL COMMENT '类型',
  `operator_time` datetime default NULL COMMENT '事件工程师处理时间',
  `minutes` int(11) default NULL COMMENT '分钟数',
  `other_note` varchar(255) default NULL COMMENT '已签阅通知人及时间',
  `users` varchar(255) default NULL COMMENT '未签阅通知人',
  PRIMARY KEY  (`id`),
  KEY `FK15764E751E58B5E6` (`service_to`),
  KEY `FK15764E7557E8D72B` (`categroyID`),
  KEY `FK15764E759A967495` (`service_from`),
  KEY `requ_no` (`requ_no`),
  CONSTRAINT `FK15764E751E58B5E6` FOREIGN KEY (`service_to`) REFERENCES `users` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK15764E7557E8D72B` FOREIGN KEY (`categroyID`) REFERENCES `service_category` (`id`),
  CONSTRAINT `FK15764E759A967495` FOREIGN KEY (`service_from`) REFERENCES `users` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务流程记录';

-- ----------------------------
-- Records of service_tran
-- ----------------------------

-- ----------------------------
-- Table structure for `severity_typ`
-- ----------------------------
DROP TABLE IF EXISTS `severity_typ`;
CREATE TABLE `severity_typ` (
  `id` int(11) NOT NULL auto_increment,
  `severityType` varchar(100) default NULL COMMENT '程度',
  `severityValue` int(11) default NULL COMMENT '赋值',
  `category` int(11) default NULL COMMENT '大类',
  `type` varchar(10) default NULL COMMENT '备用字段',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of severity_typ
-- ----------------------------
INSERT INTO `severity_typ` VALUES ('1', '高', '3', '1', null);
INSERT INTO `severity_typ` VALUES ('2', '高', '3', '3', null);
INSERT INTO `severity_typ` VALUES ('3', '中', '2', '2', null);
INSERT INTO `severity_typ` VALUES ('4', '低', '1', '2', null);
INSERT INTO `severity_typ` VALUES ('5', '中', '2', '1', null);
INSERT INTO `severity_typ` VALUES ('6', '低', '1', '1', null);
INSERT INTO `severity_typ` VALUES ('7', '中', '2', '3', null);
INSERT INTO `severity_typ` VALUES ('8', '极高', '4', '2', null);
INSERT INTO `severity_typ` VALUES ('9', '高', '3', '2', null);
INSERT INTO `severity_typ` VALUES ('10', '低', '1', '3', null);
INSERT INTO `severity_typ` VALUES ('12', '极高', '4', '3', null);
INSERT INTO `severity_typ` VALUES ('15', '极高', '4', '1', null);

-- ----------------------------
-- Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `id` decimal(19,0) NOT NULL,
  `enfield` varchar(50) default NULL COMMENT '英文名称',
  `chfield` varchar(50) default NULL COMMENT '中文名称',
  `value` varchar(50) default NULL COMMENT '值',
  `property1` varchar(50) default NULL COMMENT '扩展属性1',
  `value1` varchar(50) default NULL COMMENT '扩展属性1的值',
  `property2` varchar(50) default NULL COMMENT '扩展属性2',
  `value2` varchar(50) default NULL COMMENT '扩展属性2的值',
  `property3` varchar(50) default NULL COMMENT '扩展属性3',
  `value3` varchar(50) default NULL COMMENT '扩展属性3的值',
  `property4` varchar(50) default NULL COMMENT '扩展属性4',
  `value4` varchar(50) default NULL COMMENT '扩展属性4的值',
  `createuser` int(10) default NULL,
  `createtime` datetime default NULL,
  `lastmodifyuser` int(10) default NULL,
  `lastmodifytime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('1', 'isLdap', '是否开启LDAP', 'false', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `system_log`
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `LogID` bigint(20) NOT NULL auto_increment,
  `UserID` int(11) NOT NULL,
  `UserName` varchar(64) NOT NULL,
  `LoginIP` varchar(20) default NULL,
  `Operation` longtext,
  `CreateDate` datetime NOT NULL,
  PRIMARY KEY  (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------

-- ----------------------------
-- Table structure for `system_notice`
-- ----------------------------
DROP TABLE IF EXISTS `system_notice`;
CREATE TABLE `system_notice` (
  `id` bigint(50) NOT NULL auto_increment,
  `authorName_` varchar(100) default NULL,
  `title_` varchar(100) default NULL,
  `date_` datetime default NULL,
  `content_` varchar(1000) default NULL,
  `fileName_` varchar(100) default NULL,
  `randomName_` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `task_cat`
-- ----------------------------
DROP TABLE IF EXISTS `task_cat`;
CREATE TABLE `task_cat` (
  `taskID` int(11) NOT NULL,
  `catID` int(11) NOT NULL,
  PRIMARY KEY  (`taskID`,`catID`),
  KEY `taskID` (`taskID`),
  KEY `catID` (`catID`),
  CONSTRAINT `task_cat_fk` FOREIGN KEY (`taskID`) REFERENCES `taskallocation` (`id`),
  CONSTRAINT `task_cat_fk1` FOREIGN KEY (`catID`) REFERENCES `service_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_cat
-- ----------------------------

-- ----------------------------
-- Table structure for `task_dep`
-- ----------------------------
DROP TABLE IF EXISTS `task_dep`;
CREATE TABLE `task_dep` (
  `departmentID` int(11) NOT NULL,
  `taskID` int(11) NOT NULL,
  PRIMARY KEY  (`departmentID`,`taskID`),
  KEY `departmentID` (`departmentID`),
  KEY `taskID` (`taskID`),
  CONSTRAINT `task_dep_fk` FOREIGN KEY (`departmentID`) REFERENCES `department` (`id`),
  CONSTRAINT `task_dep_fk1` FOREIGN KEY (`taskID`) REFERENCES `taskallocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_dep
-- ----------------------------

-- ----------------------------
-- Table structure for `task_team`
-- ----------------------------
DROP TABLE IF EXISTS `task_team`;
CREATE TABLE `task_team` (
  `taskID` int(11) NOT NULL,
  `teamID` int(11) NOT NULL,
  PRIMARY KEY  (`taskID`,`teamID`),
  KEY `taskID` (`taskID`),
  KEY `teamID` (`teamID`),
  CONSTRAINT `task_team_fk` FOREIGN KEY (`taskID`) REFERENCES `taskallocation` (`id`),
  CONSTRAINT `task_team_fk1` FOREIGN KEY (`teamID`) REFERENCES `roleteam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_team
-- ----------------------------

-- ----------------------------
-- Table structure for `taskallocation`
-- ----------------------------
DROP TABLE IF EXISTS `taskallocation`;
CREATE TABLE `taskallocation` (
  `id` int(11) NOT NULL auto_increment,
  `department` int(11) default NULL COMMENT '关联部门Id',
  `roleteam` int(11) default NULL,
  `taskAllocationID` int(11) default NULL,
  `displayname` varchar(150) default NULL,
  `category` int(11) default NULL,
  `code` varchar(200) default NULL,
  `content` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF68B2425CCB1EAAB` (`department`),
  KEY `FKF68B2425CC31E96D` (`roleteam`),
  KEY `FKF68B24255C5A0D9A` (`taskAllocationID`),
  KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自动分配';

-- ----------------------------
-- Records of taskallocation
-- ----------------------------

-- ----------------------------
-- Table structure for `user_roleteam`
-- ----------------------------
DROP TABLE IF EXISTS `user_roleteam`;
CREATE TABLE `user_roleteam` (
  `roleID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY  (`userID`,`roleID`),
  KEY `FKF32DF1C7A4DE99AB` (`roleID`),
  KEY `FKF32DF1C75351B3C7` (`userID`),
  CONSTRAINT `FKF32DF1C75351B3C7` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKF32DF1C7A4DE99AB` FOREIGN KEY (`roleID`) REFERENCES `roleteam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roleteam
-- ----------------------------
INSERT INTO `user_roleteam` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL auto_increment,
  `departmentID` int(11) default NULL COMMENT '部门ID',
  `locationID` int(11) default NULL COMMENT '地理位置ID',
  `managerID` int(11) default NULL COMMENT '主管ID',
  `username` varchar(100) default NULL COMMENT '登录名',
  `password` varchar(100) default NULL,
  `truename` varchar(100) default NULL COMMENT '真实姓名',
  `usertype` varchar(100) default NULL COMMENT '用户类型',
  `phone` varchar(50) default NULL COMMENT '固定电话',
  `cellphone` varchar(50) default NULL COMMENT '移动电话',
  `address` varchar(200) default NULL COMMENT '住址-备用字段',
  `email` varchar(100) default NULL COMMENT '电子邮件',
  `last_AccessTime` datetime default NULL COMMENT '上次访问时间',
  `last_AccessIP` varchar(100) default NULL COMMENT '上次访问IP',
  `status` int(11) default NULL COMMENT '状态-备用字段',
  `online` int(11) default NULL COMMENT '是否登陆过系统：1 表示登陆过',
  `description` varchar(255) default NULL COMMENT '描述简介',
  `sex` varchar(2) default NULL COMMENT '性别-备用字段',
  `birthday` datetime default NULL COMMENT '出生日期-备用字段',
  `add1` int(11) default NULL COMMENT '备用字段',
  `add2` varchar(100) default NULL COMMENT '备用字段',
  `add3` varchar(100) default NULL COMMENT '备用字段',
  `LDAP_ID` bigint(20) default NULL,
  `USER_DN` text,
  `num_worklog` int(11) default NULL COMMENT '日志数量',
  PRIMARY KEY  (`id`),
  KEY `FK6A68E08B912ED0C` (`locationID`),
  KEY `FK6A68E08622E6B46` (`departmentID`),
  CONSTRAINT `FK6A68E08622E6B46` FOREIGN KEY (`departmentID`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `FK6A68E08B912ED0C` FOREIGN KEY (`locationID`) REFERENCES `location` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', '1', '1', 'admin', 'admin', 'admin', 'system', '010-82550123', '13265489877', '康邦公司', 'admin@combanc.com.cn', null, null, '1', null, null, null, null, '32', null, null, null, null, null);

-- ----------------------------
-- Table structure for `util_keytable`
-- ----------------------------
DROP TABLE IF EXISTS `util_keytable`;
CREATE TABLE `util_keytable` (
  `id` int(1) NOT NULL,
  `sysversion` bigint(10) NOT NULL,
  `nextavailable` decimal(19,0) NOT NULL,
  `cachesize` decimal(19,0) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of util_keytable
-- ----------------------------

-- ----------------------------
-- Table structure for `work_log`
-- ----------------------------
DROP TABLE IF EXISTS `work_log`;
CREATE TABLE `work_log` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '关联用户Id',
  `content` varchar(2000) default NULL COMMENT '志日内容',
  `time` date NOT NULL COMMENT '日期',
  `title` varchar(100) default NULL COMMENT '标题',
  `type` int(11) default NULL COMMENT '1,个人2，工作3，公开',
  `department_id` int(11) default NULL COMMENT '联关部门id',
  `location_id` int(11) default NULL COMMENT '关联地理位置',
  `note` varchar(100) default NULL COMMENT '摘要',
  `time_sumbit` datetime default NULL COMMENT '提交时间',
  `wordpress` int(11) default NULL COMMENT '点击数',
  `user_name` varchar(20) default NULL COMMENT '日志用户名称',
  `department_name` int(11) default NULL COMMENT '关联部门名',
  `location_name` int(11) default NULL COMMENT '地理位置名称',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `department_id` (`department_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `work_log_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `work_log_fk1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `work_log_fk2` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作日志';

-- ----------------------------
-- Records of work_log
-- ----------------------------
-- 修改表记录--------------------
ALTER TABLE `monitor_subnet` ADD COLUMN `LINK_DIRECTION` INT(11) AFTER `NODE_TEXT_TYPE`;
ALTER TABLE `monitor_subnet` ADD COLUMN `LAYOUT` INT(11) AFTER `LINK_DIRECTION`;
INSERT INTO  monitor_subnet_type value(9,'区域',1);
INSERT INTO `menu` VALUES ('110', '4', '70', '系统信息', '2', '2', 'license/getInfo.action', 'Y', null, '29', null, null, null, null, '2010-02-25 10:00:00', 'admin', null, null);
INSERT INTO `privilege`(`OWNERTYPE`,`OWENR`,`PRIVTYPE`,`MID`,`CODE`,`Value`) VALUES ('R', 1, 'menu', '110', 'browse', '1');



drop table if exists MONITOR_PING_RESULT;
drop table if exists MONITOR_PING_RESULT_DAY;
drop table if exists MONITOR_PING_RESPONSE_RECORD;
drop table if exists MONITOR_PING_DEST;
drop table if exists MONITOR_PING_TYPE;
drop table if exists MONITOR_PING_DEST_TYPE;
drop table if exists MONITOR_PING_REGION;
drop table if exists MONITOR_PING_REGION_GROUP;

create table MONITOR_PING_DEST_TYPE
(
   ID                   int(11) not null auto_increment,
   NAME                 varchar(32),
   ICON_CONN            varchar(128) not null,
   ICON_DISCONN         varchar(128) not null,
   ICON_FORBID          varchar(128) not null,
   NOTE                 varchar(255),
   primary key (ID)
);

insert into MONITOR_PING_DEST_TYPE
values(1,'服务器','dest_server_conn.png','dest_server_disconn.png','dest_server_forbid.png','');
insert into MONITOR_PING_DEST_TYPE
values(2,'工作站','dest_workstation_conn.png','dest_workstation_disconn.png','dest_workstation_forbid.png','');
insert into MONITOR_PING_DEST_TYPE
values(3,'普通PC','dest_pc_conn.png','dest_pc_disconn.png','dest_pc_forbid.png','');
insert into MONITOR_PING_DEST_TYPE
values(4,'网络设备','dest_device_conn.png','dest_device_disconn.png','dest_device_forbid.png','');
insert into MONITOR_PING_DEST_TYPE
values(5,'其它','dest_others_conn.png','dest_others_disconn.png','dest_others_forbid.png','');


create table MONITOR_PING_REGION_GROUP
(
   ID                   int(11) not null  auto_increment,
   NAME                 varchar(64),
   NOTE                 varchar(200),
   SUBNET_ID            int(11),
   primary key (ID)
);

 alter table MONITOR_PING_REGION_GROUP add constraint FK_PING_GROUP_SUBNET foreign key (SUBNET_ID)
      references MONITOR_SUBNET (ID) on delete restrict on update restrict;




create table MONITOR_PING_REGION
(
   ID                   integer(11) not null auto_increment,
   NAME                 varchar(64),
   NOTE                 varchar(500),
   POLICY_ID            int(11),
   GROUP_ID             int(11),
   primary key (ID)
);

alter table MONITOR_PING_REGION add constraint FK_PINGTYPE_ALERTPOLICY foreign key (POLICY_ID)
      references MONITOR_ALERT_POLICY (ID) on delete set null on update cascade;

alter table MONITOR_PING_REGION add constraint FK_PING_REGION_GROUP foreign key (GROUP_ID)
      references MONITOR_PING_REGION_GROUP (ID) on delete restrict on update restrict;




create table MONITOR_PING_DEST
(
   ID                   int(11) not null auto_increment,
   DEST_IP_URL          varchar(255),
   HOST_NAME            varchar(255),
   HOST_TYPE            int(11),
   REGION               int(11),
   LOCATION             varchar(255),
   MINISTRY             varchar(64),
   DEPARTMENT           varchar(64),
   USER                 varchar(64),
   PHONE                varchar(32),
   MAC                  varchar(32),
   DESCRIPTION          varchar(255),
   APPLICATION          varchar(255),
   ASSETS_NO            varchar(32),
   DISK_NO              varchar(128),
   NOTE                 varchar(500),
   IS_RUN               boolean,
   FLUSH_TIME           timestamp,
   primary key (ID)
);

alter table MONITOR_PING_DEST add constraint FK_PINGDEST_REGION foreign key (REGION)
      references MONITOR_PING_REGION (ID) on delete set null on update cascade;

alter table MONITOR_PING_DEST add constraint FK_PING_DEST_TYPE foreign key (HOST_TYPE)
      references MONITOR_PING_DEST_TYPE (ID) on delete restrict on update restrict;




create table MONITOR_PING_RESULT
(
   ID                   int(11) not null auto_increment,
   PING_DEST_ID         int(11),
   SUCCESS_COUNT        int,
   SEND_COUNT           int,
   MIN_REPLY_TIME       int,
   MAX_REPLY_TIME       int,
   SUM_REPLY_TIME       int,
   SUM_TTL              int,
   LOOP_GAP             int(11) not null default 0,
   COMPLETED_TIME       timestamp,
   primary key (ID)
);

alter table MONITOR_PING_RESULT add constraint FK_RESULT_DEST foreign key (PING_DEST_ID)
      references MONITOR_PING_DEST (ID) on delete cascade on update restrict;
      
 
create table MONITOR_PING_RESULT_DAY
(
   ID                   int(11) not null auto_increment,
   PING_DEST_ID         int(11),
   SUCCESS_COUNT        int,
   SEND_COUNT           int,
   MIN_REPLY_TIME       int,
   MAX_REPLY_TIME       int,
   SUM_REPLY_TIME       int,
   SUM_TTL              int,
   RESPONSE_TIME        int(11),
   NO_RESPONSE_TIME     int(11),
   COMPLETED_TIME       timestamp,
   primary key (ID)
);

alter table MONITOR_PING_RESULT_DAY add constraint FK_RESULTDAY_DEST foreign key (PING_DEST_ID)
      references MONITOR_PING_DEST (ID) on delete cascade on update restrict;
      

create table MONITOR_PING_RESPONSE_RECORD
(
   ID                   int(11) not null auto_increment,
   PING_DEST_ID         int(11),
   EXCUTE_COUNT         int,
   START_TIME           timestamp,
   END_TIME             timestamp,
   RESPONSE_STATUS      int,
   primary key (ID)
);

alter table MONITOR_PING_RESPONSE_RECORD add constraint FK_RESPONSERECORD_DEST foreign key (PING_DEST_ID)
      references MONITOR_PING_DEST (ID) on delete cascade on update restrict;
      
      
update  monitor_alert_smalltype   set NAME ='设备SNMP无反应',level=2 where  NAME='设备无反应';
update  monitor_alert_smalltype   set NAME ='服务器SNMP无反应',level=2  where  NAME='服务器无反应';
update  monitor_alert_smalltype   set NAME ='设备SNMP恢复',level=5  where  NAME='设备恢复';
update  monitor_alert_smalltype   set NAME ='服务器SNMP恢复',level=5  where  NAME='服务器恢复';
update  monitor_alert_smalltype   set level=4  where  NAME='Ping响应恢复';
     
     



