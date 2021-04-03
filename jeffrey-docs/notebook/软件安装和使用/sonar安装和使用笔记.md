window下安装sonarqube7.0

```
下载：
下载地址：https://www.sonarqube.org/downloads/，最新版本需要jdk11环境，现在大部分开发环境使用JDK8，并且最新版本也不支持mysql，所以选择了7.0版本。
安装：
先在数据库中创建数据库，名为sonar，配置数据库：
/conf/sonar.properties,
修改内容如下：
----------------
sonar.jdbc.username=root
sonar.jdbc.password=inspur123!@#
sonar.jdbc.url=jdbc:mysql://10.110.25.74:31862/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false
# TCP port for incoming HTTP connections. Default value is 9000.
sonar.web.port=9002
-----------------
启动后访问：localhost:9002，默认用户名密码：admin/admin

代码分析客户端的使用：
基于maven的sonarrunner:
编辑maven的配置文件，setttings.xml,添加内容如下：
---------------------------------
<settings>
    <pluginGroups>
        <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
    </pluginGroups>
    <profiles>
        <profile>
            <id>sonar</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <!-- Optional URL to server. Default value is http://localhost:9000 -->
                <sonar.host.url>
                  http://myserver:9000
                </sonar.host.url>
            </properties>
        </profile>
     </profiles>
</settings>
---------------------------------
sonar.host.url的地址为上面安装sonarqube的服务器地址。

执行命令：mvn sonar:sonar -DskipTest=true,再次登录,就可以看到分析的项目。

使用sonarrunner镜像执行：

声明式流水线内容：
-----------------
containerTemplate(name: 'sonarqubesacanner', image: 'retailcloud-docker-local.repo.inspur.com/gitlabci/sonar-scanner-cli:latest', ttyEnabled: true, command: 'cat', args: '', resourceLimitCpu: '1', resourceLimitMemory: '2048Mi', resourceRequestCpu: '1', resourceRequestMemory: '2048Mi')
-----------------
stage("sonarqube"){
                    container('sonarqubesacanner') {
                        sh 'pwd'
                        sh 'ls -l'
                        sh ' sonar-scanner -Dsonar.projectKey=paascloud-applet -Dsonar.sourceEncoding=UTF-8 -Dsonar.sources=.  -Dsonar.host.url=http://sonarqube.10.110.25.75.xip.io:80 -Dsonar.login=64b81bae1d8fb689573605f9a3fcdd731837f627'
                      }
                }
-----------------
报错Please provide compiled classes of your project with sonar.java.binaries property
原因：最新版本的sonarQube6.5以上的版本只能扫描class文件
解决：新版本的需要在项目根目录下的sonar-project.properties文件新增属性sonar.java.binaries
指定class编译路径

添加规则

```

