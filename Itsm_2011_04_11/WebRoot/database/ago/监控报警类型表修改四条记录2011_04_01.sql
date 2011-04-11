update  monitor_alert_smalltype   set NAME ='设备SNMP无反应',level=2 where  NAME='设备无反应';
update  monitor_alert_smalltype   set NAME ='服务器SNMP无反应',level=2  where  NAME='服务器无反应';
update  monitor_alert_smalltype   set NAME ='设备SNMP恢复',level=5  where  NAME='设备恢复';
update  monitor_alert_smalltype   set NAME ='服务器SNMP恢复',level=5  where  NAME='服务器恢复';
update  monitor_alert_smalltype   set level=4  where  NAME='Ping响应恢复';