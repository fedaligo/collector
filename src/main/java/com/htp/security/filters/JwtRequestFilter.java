package com.htp.security.filters;

import com.htp.security.util.JwtUtil;
import com.htp.service.users.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String[] info = getTokenFromHeaderAndUserNameFromToken(authorizationHeader);
        String userName = info[1];
        String jwt = info[0];
        checkUserNameAndContext(userName, jwt, httpServletRequest);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String[] getTokenFromHeaderAndUserNameFromToken(String authorizationHeader){
        String[] info = new String[2];
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            info[0] = authorizationHeader.substring(15, authorizationHeader.length() - 2);
            info[1] = jwtUtil.extractUserName(info[0]);
        }
        return info;
    }

    private void checkTokenAndSetDetailsWithAuthentication(UserDetails userDetails, String jwt,
                                                           HttpServletRequest httpServletRequest){
        if (jwtUtil.validateToken(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().
                    buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private void checkUserNameAndContext(String userName, String jwt, HttpServletRequest httpServletRequest){
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
            checkTokenAndSetDetailsWithAuthentication(userDetails, jwt, httpServletRequest);
        }
    }


}
