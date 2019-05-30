package com.zp.security.authentication.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.UUID;

/**
 * Description: im
 * Created by Zhu Peng on 2019/5/21
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private SmsCodeAuthenticationFilter mSmsCodeAuthenticationFilter;

    @Autowired
    private AuthenticationSuccessHandler mAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler mAuthenticationFailureHandler;

    private UserDetailsService mUserDetailsService;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;
    @Autowired
    private DataSource mDataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        mSmsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mSmsCodeAuthenticationFilter.setAuthenticationSuccessHandler(mAuthenticationSuccessHandler);
        mSmsCodeAuthenticationFilter.setAuthenticationFailureHandler(mAuthenticationFailureHandler);
        String key = UUID.randomUUID().toString();
        mSmsCodeAuthenticationFilter.setRememberMeServices(new PersistentTokenBasedRememberMeServices(key, mUserDetailsService, persistentTokenRepository));

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(mUserDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(mSmsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(mDataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
