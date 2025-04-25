package com.senior.project.filter;

import com.senior.project.config.JwtHelper;
import com.senior.project.domain.User;
import com.senior.project.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.senior.project.constant.SecurityConstant.TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            jwt = authHeader.substring(7);
            try {
                username = jwtHelper.getUsername(jwt);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userService.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            user, null, jwtHelper.getRoles(jwt).stream()
                            .map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                    );
                    SecurityContextHolder.getContext().setAuthentication(token);
                    log.debug("Authentication set for user: {}", username);
                }
            } catch (ExpiredJwtException e) {
                log.error("Token is expired");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has expired");
                return;
            } catch(SignatureException e) {
                log.error("Invalid token signature");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token signature");
                return;
            } catch (Exception e) {
                log.error("Authentication error: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Authentication failed");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
