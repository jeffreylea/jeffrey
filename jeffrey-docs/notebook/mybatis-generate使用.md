pom中需添加依赖和插件：	

```POM
<!-- mybatis-generator使用 -->
 <groupId>tk.mybatis</groupId>
 <artifactId>mapper-spring-boot-starter</artifactId>
 <version>1.1.7</version>
 <build>
        <plugins>
            <!-- mybatis generator 自动生成代码插件 -->
			<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.3.7</version>
				<configuration>
					<configurationFile>mg.xml</configurationFile>
					<overwrite>true</overwrite>
					<verbose>true</verbose>
				</configuration>
			</plugin>
        </plugins>
    </build>
```
  代码