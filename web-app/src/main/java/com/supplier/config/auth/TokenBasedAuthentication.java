package com.supplier.config.auth;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.supplier.config.auth.token.TokenProvider;
import com.supplier.exception.WebAppException;

public class TokenBasedAuthentication extends GenericFilterBean {
    public static final String HEADER_SECURITY_TOKEN = "x-auth-token";

    private final UserDetailsService userDetailsService;

    private final TokenProvider tokenProvider;

    public TokenBasedAuthentication(UserDetailsService detailsService, TokenProvider tokenProvider) {
        this.userDetailsService = detailsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String authToken = httpServletRequest.getHeader(HEADER_SECURITY_TOKEN);
            if (StringUtils.hasText(authToken)) {
                String username = tokenProvider.getUsernameFromToken(authToken);
                UserDetails user = userDetailsService.loadUserByUsername(username);
                if (tokenProvider.validateToken(authToken, user)) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,
                            user.getPassword(), user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            throw new WebAppException(ex);
        }
    }

}
