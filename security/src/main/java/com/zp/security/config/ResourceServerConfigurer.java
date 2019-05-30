package com.zp.security.config;

import com.zp.security.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.zp.security.authentication.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Description: im
 * Created by Zhu Peng on 2019/5/21
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    //短信认证
    @Autowired
    private SmsCodeAuthenticationSecurityConfig mSmsCodeAuthenticationSecurityConfig;
    //第三方认证
    private SpringSocialConfigurer mSpringSocialConfigurer;
    //OpenId认证
    private OpenIdAuthenticationSecurityConfig mOpenIdAuthenticationSecurityConfig;



    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.apply(mSmsCodeAuthenticationSecurityConfig)
//                .and()
//                .apply(mSpringSocialConfigurer)
//                .and()
//                .apply(mOpenIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();
    }
}
