package com.stackroute.helpdesk.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //String authorizationHeader = httpServletRequest.getHeader("Authorization");

        //if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")){
        //    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //}
        //else{
          //  String tokenPart = authorizationHeader.substring(7);
            //Claims claims = Jwts.parser().setSigningKey("SecretKey").parseClaimsJws(tokenPart).getBody();
            filterChain.doFilter(servletRequest, servletResponse);
        //}
    }
}
