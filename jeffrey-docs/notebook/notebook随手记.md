Visio图形模板
http://www.visiocafe.com/

# java开发中出现错误：Value '0000-00-00 00:00:00' can not be represented as java.sql.Timestamp  
数据库字段类型为datatime，为空时默认Value '0000-00-00 00:00:00',在mysql中作为一个特殊值存在。但是在java项目编译的时候会被视为不合法的值，被JVM认为格式不正确。有些地方说datatime的范围是1000-01-01 00:00:00 到 9999-12-31 23:59:59，但0000-00-00 00:00:01也是可以的
解决方案：
在jdbc连接中加上配置：zeroDateTimeBehavior=convertToNull

OAuth2学习：

jenkins学习：
https://jenkins.io/zh/doc/book/blueocean/

消息中间件学习：

appolo学习：
![](../basic/image/无标题.png)

Spring Security Oauth2中获取token接口：oauth/token，这个接口在TokenEndpoint这个类里，
这个类在`org.springframework.security.oauth2.provider.endpoint`这个包下面。
当输入错误的client_id和client_secret，接口返回invalid_client，并没有进入到这个接口的方法里面，所以在进入接口之前应该是首先验证了客户端id和secret的有效性。

# java中枚举的使用

#list.toArray的使用
userIdList.toArray(new String[] {})
userIdList.toArray(new String[0])


    
 ```
 public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
 
 public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
```

# 使用collection标签实现嵌套查询

```
<resultMap id="UserRolesResultMap" type="userShowSubRole">
       <result column="user_id" jdbcType="VARCHAR" property="userId"/>
       <collection property="roles" ofType="roleSimple" >
            <result column="role_id" jdbcType="VARCHAR" property="roleId"/>
            <result column="role_code" jdbcType="VARCHAR" property="roleCode"/>
            <result column="role_name" jdbcType="VARCHAR" property="roleName"/>
            <result column="tenant_id" jdbcType="VARCHAR" property="tenantId"/>
       </collection>    
  </resultMap>
  <select id="listRolesByUserIdsAndTenantId" resultMap = "UserRolesResultMap">
    select ur.user_id, ur.role_id, r.role_code, r.role_name, ur.tenant_id   
    from sys_user_role ur
    left join sys_role r on ur.role_id=r.role_id
    where ur.user_id in     
      <foreach collection="userIds" item="userId" open="(" close=")" separator=",">
        #{userId,jdbcType=VARCHAR}
      </foreach>     
      and r.status = '1'
      <if test="tenantId != null">
        and ur.tenant_id = #{tenantId,jdbcType=VARCHAR}
      </if>
  </select>
  
  <resultMap id="SimpleTenantResultMap" type="tenantSimple">
    <result column="tenant_id" jdbcType="VARCHAR" property="tenantId"/>
    <result column="tenant_name" jdbcType="VARCHAR" property="tenantName"/>
    <result column="status" jdbcType="VARCHAR" property="status"/>
  </resultMap>  
  <resultMap id="UserSimpleTenantsResultMap" type="UserShowSubTenant">
    <result column="user_id" jdbcType="VARCHAR" property="userId"/>
    <collection property="tenants" ofType="tenantSimple" resultMap="SimpleTenantResultMap"/>        
  </resultMap>
  
  <select id="listTenantsByUserIdPg" resultMap="SimpleTenantResultMap">
    select t.tenant_id, t.tenant_name, t.status
    from sys_tenant t
    left join sys_user_tenant ut on t.tenant_id = ut.tenant_id
    where ut.user_id = #{userId,jdbcType=VARCHAR}
    and t.status = 1
  </select>
  <!-- 查询用户所属租户信息 -->
  <select id="listTenantsByUserIdsAndTenantId" resultMap="UserSimpleTenantsResultMap">
    select ut.user_id, t.tenant_id, t.tenant_name, t.status
    from sys_user_tenant ut
    left join  sys_tenant t on t.tenant_id = ut.tenant_id
    where ut.user_id in     
     <foreach collection="userIds" item="userId" open="(" close=")" separator=",">
        #{userId,jdbcType=VARCHAR}
     </foreach>    
     <if test="tenantId != null">
        and ut.tenant_id = #{tenantId,jdbcType=VARCHAR}
      </if>
  </select>
  
传入数组ids  
  <foreach collection="ids" separator="," open="(" close=")" item="id">
         #{id, jdbcType=VARCHAR}
    </foreach>
 传入列表list      
    <foreach collection="list" item="i" separator=",">
    (#{i.relId,jdbcType=VARCHAR}, #{i.elementId,jdbcType=VARCHAR}, #{i.apiId,jdbcType=VARCHAR}, 
      #{i.modifierId,jdbcType=VARCHAR}, #{i.modifyTime,jdbcType=TIMESTAMP})
  </foreach>
```

Caused by: java.lang.ClassNotFoundException: org.springframework.boot.context.properties.ConfigurationPropertiesBean

springboot的版本和springcloud的版本不匹配导致。我的配置如下：删除org.springframework.cloud的配置项目就能正常启动了。

```
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
    </parent>
    
    
            <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-oauth2</artifactId>
            <version>2.2.0.RELEASE</version>
        </dependency>
```

