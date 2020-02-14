### 进入pod查看mysql最大连接数
进入Pod内部执行相关操作: kubectl exec -it pod-name /bin/sh  
登陆mysql：mysql -uroot -pXXXXX
执行：show variables like "%max_connections%";
命令行修改最大连接数为1000： set global max_connections = 1000;