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
    
  mybatis启用二级缓存：  
  ` <setting name="cacheEnabled" value="true"/>`



+ Mybatis中 <![CDATA[ ]]> 的作用

<![CDATA[ ]]> 是xml 的语法，在CDATA的内部的内容会被解析器忽略。	



+ mybatis嵌套查询子查询column传多个参数描述|| 参数包含父查询没有的字段

```
<resultMap id="ProductDeviceStatisticsresultMap" type="DeviceActiveStatisticsPO">
        <result column="product_code" jdbcType="VARCHAR" property="productCode"/>
        <result column="name" jdbcType="VARCHAR" property="productName"/>
        <result column="deviceCount" jdbcType="VARCHAR" property="deviceCount"/>
        <collection property="deviceTrend"
                    column="{productCode=product_code,precisionFormat=precisionFormat,
                    startTime=startTime,endTime=endTime}"
                    select="listByProductCode"/>
    </resultMap>
   # 父查询中添加临时列，以便映射到子查询中 
    case when ('${precisionFormat}' != '') then '' else '${precisionFormat}' end as 		precisionFormat,
    case when ('${startTime}' != '') then '' else '${startTime}' end as startTime,
    case when ('${endTime}' != '') then '' else '${endTime}' end as endTime,
   # -----
   <select id="listProductDeviceStatistics" resultMap="ProductDeviceStatisticsresultMap">
    select
    case when ('${precisionFormat}' != '') then '' else '${precisionFormat}' end as 		precisionFormat,
    case when ('${startTime}' != '') then '' else '${startTime}' end as startTime,
    case when ('${endTime}' != '') then '' else '${endTime}' end as endTime,
    product.code product_code,product.`name`,COUNT(device.code) deviceCount
    from device
    left join product on product.code = device.product_code
    where device.creator_code = #{userCode,jdbcType=VARCHAR}
    group by product_code
  </select>
  # 子查询
    
```

+ mybatis中${}和#{}的区别

#{}和${}预处理是不一样的，#{}在预处理时，会把参数部分用一个？占位符代替，${}只是简单的字符串替换

