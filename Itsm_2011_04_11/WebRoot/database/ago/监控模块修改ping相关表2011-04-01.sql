
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
     
     

