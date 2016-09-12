package com.blogger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final String SECRET_SIGN = "4b95309b6df87248328e1c3791e47793";
    private static final int EXPIRE_IN = 3600000;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Ignore front-end resources
        web.ignoring()
            .antMatchers("/scripts/**/*.{js,html}")
            .antMatchers("/i18n/**")
            .antMatchers("/assets/**");
//            .antMatchers("/swagger-ui.html");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable()
            .headers().frameOptions().disable()
            .and().authorizeRequests()
            .antMatchers("/","/users/*").permitAll()
            .anyRequest().authenticated()
            .and().logout().logoutSuccessUrl("/logout");
    }

}
