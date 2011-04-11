update severity_typ set severitytype='极高' ,severityvalue=4 where id=15;
insert into severity_typ(id,severitytype,severityvalue,category)
values (8,'极高' ,4,2);


delete from privilege where mid in (
 select id  from menu where  name like '%基本设置%' or name like '%节假日管理%' or name like '%严重度与紧急度%' 
 or name like '%变更请求类别%' or name like '%问题类别%') ;# or name like '%菜单管理%') ;

delete from menu where  name like '%基本设置%' or name  like '%节假日管理%' or name  
like '%严重度与紧急度%'  or  name like '%变更请求类别%' or name  like '%问题类别%' ;# or name like '%菜单管理%' ;

delete  from privilege  where mid=84;
update menu set   parentid=0, name='个人资料配置(用户界面)' ,type =1,id=57,roletype=3  where id=84;

update menu set orderflag=17 where id=88;
update menu set orderflag=19 where id=86;

update menu set name="用户资料" where id=85;

update menu set name="角色权限配置" where id =86;