package com.learn.jeffrey.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/29 18:42
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${accessToken.validity.timeout:-1}")
    private int accessTokenValidity;

    @Value("${refreshToken.validity.timeout:-1}")
    private int refreshTokenValidity;

    private static final String DEMO_RESOURCE_ID = "order";
    // 数据库连接连接池对象

    @Autowired
    private DataSource dataSource;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    // 认证业务对象
    @Autowired
    private DBUserDetailsService userDetailsService;

    //授权模式专用对象
    @Autowired
    private AuthenticationManager authenticationManager;
    // 客户端信息来源
    @Bean
    public JdbcClientDetailsService jdbcclientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    //指定客户端信息数据库来源
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //password 方案一：明文存储，用于测试，不能用于生产
        String finalSecret = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        // String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");
        //配置两个客户端,一个用于password认证一个用于client认证
        /*clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret);*/

        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }


    // 授权信息保存策略
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    // 授权码模式数据来源
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //                   .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .tokenServices(tokenServices())
                //.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        if (jwtAccessTokenConverter !=null){

            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    //配置令牌端点(Token Endpoint)的安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }

    // 配置TokenServices参数
    @Primary
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setAccessTokenValiditySeconds(accessTokenValidity); // 默认不失效
        tokenServices.setRefreshTokenValiditySeconds(refreshTokenValidity);// 默认不失效
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    // token保存策略
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 使用非对称加密算法来对Token进行签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

}
