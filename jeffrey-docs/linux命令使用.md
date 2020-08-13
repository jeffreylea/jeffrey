netstat:

根据端口号查找进程号，查找程序路径
netstat -nlp | grep 端口号
得到PID后，执行命令：
ll /proc/进程号/cwd

给redis设置密码：
redis-cli->config set requirepass inspur123!@# 