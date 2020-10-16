##下载安装地址
http://nginx.org/en/download.html
选择下载：nginx/Windows-1.12.2
直接解压启动
## 启动方式：
1、双击nginx.exe,有黑色弹框一闪而过
2、在命令行中执行.\nginx.exe即可启动
验证nginx是否启动成功：
查看网址：http://localhost:80
## 配置文件
位置：/conf/nginx.conf文件，默认监听端口是80，修改成800端口，不用重新启动,执行命令
nginx -s reload 即可让改动生效。
nginx配置多个端口
方式一（写多个listen）:
 ``` 
         listen       800;
		listen       900;
		listen       1000;
```
方式二（配置多个server）：
```$xslt

server {
        listen       8000;
        listen       somename:8080;
        server_name  somename  alias  another.alias;

        location / {
           root   html;
           index  index.html index.htm;
        }
    }
```

+ [正向代理和反向代理](https://blog.csdn.net/liuhenghui5201/article/details/90720442)    
正向代理：
为你而服务，代理的是你,例如你无法访问国外网站，我们可以通过代理服务器请求访问外国网站。
反向代理：
为服务器服务，代理的是服务器，例如你访问百度，你访问的是百度的代理服务器，代理服务器转发到百度的各个节点中。



