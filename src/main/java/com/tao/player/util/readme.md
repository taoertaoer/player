```sql
host is not allowed to connect to this mysql server

--enable remote access
--update mysql.user t set t.host='%' where t.user='root'; 
select t.user,t.host from mysql.user t;

--authorize
grant all privileges on *.* to 'root'@'%' identified by '' with grant option;
flush privileges;
```



在Find对应的选择条件中，粘贴正则表达式：/\*{1,2}[\s\S]*?\*/。

