package com.rest.API.security.config;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rest.API.exception.UnauthorizedException;
import com.rest.API.model.AppUserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;
import com.rest.API.security.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    private static final Logger LOGGER= Logger.getLogger(JwtRequestFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, AuthenticationException {
        final String requestTokenHeader = request.getHeader("Authorization");
        LOGGER.info("requestTokenHeader " + requestTokenHeader);
        String username = null;
        String jwtToken = null;
// JWT Token is in the form "Bearer token". Remove Bearer word and get
// only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                LOGGER.info("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                LOGGER.info("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
// Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            LOGGER.info("in JwtRequestFilter: calling loadUserByUsername");
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
// if token is valid configure Spring Security to manually set
// authentication
            logger.info(("userDetails: " + userDetails.toString()));
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                LOGGER.info("in jwt request filter with userDetails: " + userDetails.toString());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
// After setting the Authentication in the context, we specify
// that the current user is authenticated. So it passes the
// Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    public void validateAuthorizationUserRole(String authorizationHeader, List<AppUserRoleEnum> role) throws Exception{
        AppUserRoleEnum pp;
        String token = authorizationHeader.substring(7);
        String tokenRole = jwtTokenUtil.getRoleFromtoken(token);
        if(!role.stream().anyMatch(element -> tokenRole.equals(element.name()))){
            throw new UnauthorizedException(tokenRole);
        }

    }
}