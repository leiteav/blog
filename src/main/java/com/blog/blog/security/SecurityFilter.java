package com.blog.blog.security;

import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    final static String AUTHORIZATION = "Authorization";
    final static String BEARER_PREFIX = "Bearer ";
    private TokenManager tokenManager;
    private UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenManager tokenManager, UserRepository userRepository) {
        this.tokenManager = tokenManager;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = recuperarToken(request);

        if (Objects.nonNull(token)){
            final String username = tokenManager.getSubject(token);
            final User user = userRepository.findByUsername(username);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    protected static String recuperarToken(HttpServletRequest request){
        final String header = request.getHeader(AUTHORIZATION);
        if (Objects.isNull(header)){
            return null;
        }
        return header.replace(BEARER_PREFIX, "");
    }

}
