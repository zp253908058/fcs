package com.zp.security.config;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Description: im
 * Created by Zhu Peng on 2019/5/21
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService mUserDetailsService;
    private final TokenStore mTokenStore;
    private final AuthenticationManager mAuthenticationManager;

    @Autowired
    public AuthorizationServerConfigurer(UserDetailsService userDetailsService, @Qualifier("redis") TokenStore tokenStore, AuthenticationManager authenticationManager) {
        this.mUserDetailsService = userDetailsService;
        this.mTokenStore = tokenStore;
        this.mAuthenticationManager = authenticationManager;
    }

    /**
     * tokenKey的访问权限表达式配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        builder.withClient("fun_chat")
                .secret("fun_chat_secret")
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(24*60*60)
                .refreshTokenValiditySeconds(30*24*60*60)
                .scopes("all");
    }

    /**
     * 认证及token配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(mTokenStore)
                .authenticationManager(mAuthenticationManager)
                .userDetailsService(mUserDetailsService);
    }
}
