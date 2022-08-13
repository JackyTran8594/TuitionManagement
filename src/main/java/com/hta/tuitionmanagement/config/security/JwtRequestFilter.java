package com.hta.tuitionmanagement.config.security;

import com.hta.tuitionmanagement.config.Profiles;
import com.hta.tuitionmanagement.service.impl.UserDetailsServiceImpl;
import com.hta.tuitionmanagement.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile({Profiles.JWT_AUTH_DEV, Profiles.JWT_AUTH_STAGING})
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestToken = request.getHeader("Authorization");
        String reqUri = request.getRequestURI();
        String method = request.getMethod();

        String username = null;
        String jwtToken = null;

        if (requestToken != null) {
            if (requestToken.startsWith("Bearer")) {
                jwtToken = requestToken.substring(7);
                username = jwtTokenProvider.getUsernameFromToken(jwtToken);

            } else {
                logger.warn("JWT Token doesn't begin with Bearer string");
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                logger.info("------SecurityContextHolder getPrincipal UserDetails:" + ((UserDetails) principal).getUsername());
            } else {
                logger.info("------SecurityContextHolder getPrincipal :" + principal);
            }
        }

        //once we get the token validate it
        if (username != null && (authentication == null || "anonymousUser".equals((String) authentication.getPrincipal()))) {
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

            // if token is valid configure Spring security to manually set authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                ;

                // after setting the authentication in the context, we specify that
                // the current user is authenticated. So it passes the spring security configuration successfully
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(request, responseWrapper);

        // copy to body response
        responseWrapper.copyBodyToResponse();
    }
}
