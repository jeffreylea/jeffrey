1、	git查看分支：
git branch ：查看本地分支，并在当前分支用*标记。
Git branch –a: 查看所有分支，包括本地分支和远程分支。
Git bramch –r:查看远程分支。

2、	切换分支
Git branch XXX :创建新的分支。如果分支已经存在会提示分支已经存在。
Git checkout XXX：切换到XXX分支。
git checkout -b XXX: 创建分支并切换到新的分支。这个命令是git branch和git checkout命令的组合，这也是和git branch XXX命令的区别。 
Git checkout –b XXX origin/XXX:基于远程分支创建本地分支，并切换到新的分支。
git checkout –b XXX develop：基于本地develop分支创建新的并切换到新的分支。
3、	更新代码
操作之前要看清分支，清楚操作的是哪个分支，可以使用git branch确定在哪个分支上，使用git pull 更新代码到本地。
Unpacking objects:更新代码时一直处于这个状态
4、	查看远程地址：
Git remote –v  :可以查看远程地址的详细情况
Git remote ：可以查看当前目录下远程地址的引用名称。

5、     clone代码时出现权限错误( Access denied Authentication failed)

在clone代码时突然出现这个错误。用`git config --list`命令查了下,显示的用户名和密码和我实际要下载的代码不一致。

```
user.name=XXX
user.email=XXX
```
这是我另一个git的用户名密码，修改下用户名密码是不是就可以了呢。于是执行以下命令修改用户名密码:

```
#全局级配置，如果没有仓库级别的特殊配置，默认读取这个配置
git config --global user.name "name"
git config --global user.email "email"

#仓库级配置，一般一个项目配置一次
git config user.name "name"
git config user.email "email"
```
再次使用`git config --list`命令查下，这时已经修改过来了。
再次clone时还是出现权限错误。
在eclipse中更新下载是正常的，于是就有了以下操作：

先在所要clone代码位置创建文件夹(项目名称)，使用`git init`初始化，使用eclipse打开此文件夹。
然后使用eclipse中的git进行pull,在操作的时候需要填写配置远程地址，用户名和密码。这样顺利clone了项目。

对git不太熟悉才这样操作，还需要熟悉下git命令。

另一种解决的方式是卸载git重新安装，再次git clone或 git pull操作时会弹出安全中心输入用户名密码的窗口，填入正确的用户名密码就可以正常的操作了。
使用gitbash无权限但eclipse中git操作正常的原因可能是，两者使用的不是同一个git客户端，使用的密码位置也不一样，如果再次遇到这种情况，先不用重新安装，可以试着去控制面板\用户帐户\凭据管理器中修改下对应的凭据。

本地代码与远程代码关联，添加远程地址：
git remote add origin xxxurlXX
