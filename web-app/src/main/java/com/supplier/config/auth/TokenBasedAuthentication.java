package com.supplier.config.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class TokenBasedAuthentication extends AbstractAuthenticationProcessingFilter {
    private static final Logger LOG = LoggerFactory.getLogger(TokenBasedAuthentication.class);
    public static final String HEADER_SECURITY_TOKEN = "X-Auth-Token"; 
    
    public TokenBasedAuthentication(String defaultFilterUrl) {
        super(defaultFilterUrl);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterUrl));
        setAuthenticationManager(new TokenAuthenticationManager());
//        setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());
    } 
 
 
    /**
     * Attempt to authenticate request - basically just pass over to another method to authenticate request headers 
     */
    @Override 
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
            throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(HEADER_SECURITY_TOKEN);
        LOG.info("X-Auth-Token = " + token);
        AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
        if ( userAuthenticationToken == null) 
            throw new AuthenticationServiceException(String.format("Authentication failed: %s", "Bad Token"));
        return userAuthenticationToken;
    }
 
    /**
     * authenticate the user based on token
     * @return
     */
    private AbstractAuthenticationToken authUserByToken(String token) {
        if(token == null) {
            return null;
        }
        AbstractAuthenticationToken authToken = null;
        try {
            return authToken;
        } catch (Exception e) {
            LOG.error("Authenticate user by token error: ", e);
        }
        return authToken;
    }
 
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }

}
