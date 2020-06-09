一、新建脚本文件。
Bat命令
```
@echo off
set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%"
"C:\Program Files\MySQL\MySQL Server 5.5\bin\mysqldump" --opt -u root --password=admin inspurceshi > F:\db_backup\inspurceshi_%Ymd%.sql
@echo on
```

```
Bat命令

@echo off

set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%"

D:\你的安装路径\MySQL\bin\mysqldump --opt -u 账号 --password=密码  数据库名 > D:\db_backup\保存的文件名_%Ymd%.sql
@echo on
```

二、添加windows任务计划，相当于定时任务。
右击“计算机”>>“管理”>>“工具”>>“任务计划程序”后，点击创建任务：（如果是win10系统，右击“计算机”>>“管理”即可
+ “常规“选项下：先给定时任务起个名字

+ ”触发器“ 选项下：定义执行时间

+ “操作” 选项下：选择刚创建的备份脚本



