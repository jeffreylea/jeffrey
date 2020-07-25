GitLab的安装:
1、配置yum源
vim /etc/yum.repos.d/gitlab-ce.repo
复制以下内容：

2、[gitlab-ce]
name=Gitlab CE Repository
baseurl=https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el$releasever/
gpgcheck=0
enabled=1

3、yum install gitlab-ce #自动安装最新版