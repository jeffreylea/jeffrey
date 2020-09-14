
pom.xml引入zk依赖
``` 
<dependency>
     <groupId>org.apache.zookeeper</groupId>
     <artifactId>zookeeper</artifactId>
     <version>3.5.5</version>
 </dependency>
```
application.yml配置
```
zookeeper:
  address: 127.0.0.1:2181
  timeout: 4000
```

