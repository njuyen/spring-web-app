package com.supplier.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.supplier.config.auth.AuthConfigAdapter;
import com.supplier.config.auth.TokenBasedAuthentication;
import com.supplier.config.auth.token.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final String SECRET_SIGN = "4b95309b6df87248328e1c3791e47793";
    private static final int EXPIRE_IN = 3600000;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProvider(SECRET_SIGN, EXPIRE_IN);
    }
    
    @Bean
    public TokenBasedAuthentication tokenBasedAuthentication() {
        return new TokenBasedAuthentication(userDetailsService, tokenProvider());
    }
    
    @Bean
    public  AuthConfigAdapter authConfigAdapter() {
        return new AuthConfigAdapter(userDetailsService, tokenProvider());
    }
    
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
            .and().apply(authConfigAdapter())
            .and().csrf().disable()
            .headers().frameOptions().disable()
            .and().authorizeRequests()
            .antMatchers("/","/users/*").permitAll()
            .anyRequest().authenticated()
            .and().logout().logoutSuccessUrl("/logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
}
