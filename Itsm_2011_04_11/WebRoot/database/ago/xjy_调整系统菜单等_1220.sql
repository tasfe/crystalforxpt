update severity_typ set severitytype='����' ,severityvalue=4 where id=15;
insert into severity_typ(id,severitytype,severityvalue,category)
values (8,'����' ,4,2);


delete from privilege where mid in (
 select id  from menu where  name like '%��������%' or name like '%�ڼ��չ���%' or name like '%���ض��������%' 
 or name like '%����������%' or name like '%�������%') ;# or name like '%�˵�����%') ;

delete from menu where  name like '%��������%' or name  like '%�ڼ��չ���%' or name  
like '%���ض��������%'  or  name like '%����������%' or name  like '%�������%' ;# or name like '%�˵�����%' ;

delete  from privilege  where mid=84;
update menu set   parentid=0, name='������������(�û�����)' ,type =1,id=57,roletype=3  where id=84;

update menu set orderflag=17 where id=88;
update menu set orderflag=19 where id=86;

update menu set name="�û�����" where id=85;

update menu set name="��ɫȨ������" where id =86;