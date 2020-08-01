查看系统内核：  
uname -r
默认版本是2.6,网上建议docker在内核3.1以上版本运行。在2.6下直接用yum install docker-io安装是不行的，提示没有包，于是：
直接用下载源安装：  
yum install https://get.docker.com/rpm/1.7.1/centos-6/RPMS/x86_64/docker-engine-1.7.1-1.el6.x86_64.rpm
查看docker启动状态：
service docker status
启动docker：
service docker start，再次查看状态提示：docker dead but pid file exists
说是因为device-mapper-libs版本过低导致的，于是进行升级：
yum update -y device-mapper-libs
很顺利，执行成功。再次启动docker并查看docker运行状态。docker此时已经在running中。
使用docker安装mysql：
查找mysql镜像：
docker search mysql
使用命令docker pull docker.io/mysql或者docker pull  mysql安装提示Could not reach any registry endpoint。失败原因大概是服务器没有成功访问到docker仓库，应该是docker版本太低了。
查看docker版本：
 docker --version
Docker version 1.7.1, build 786b29d
查资料的过程中说是要创建一个自己的镜像仓库，以前知道JFrog Artifactory是管理镜像仓库的工具。docker默认的是docker hub 镜像管理工具。