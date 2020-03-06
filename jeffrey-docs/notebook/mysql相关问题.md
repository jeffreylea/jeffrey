“Too many connections”引起MySQL崩溃并启动失败
https://blog.51cto.com/10950710/2160220

show variables like '%max_connections%'; 查看最大连接数
set global max_connections=1000;        重新设置最大连接数
