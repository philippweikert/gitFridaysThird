package de.neuefische.todoapp.loginfeatures;

import de.neuefische.todoapp.loginfeatures.Service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String token = getAuthToken(request);

        if(token != null && !token.isBlank()){
            try{
                Claims claims = jwtService.parseClaims(token);
                setSecurityContext(claims);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalid token");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getAuthToken (HttpServletRequest request) {
        String authhorization = request.getHeader("Authorization");
        if (authhorization != null) {
            return authhorization.replace("Bearer", "").trim();
        }
        return null;
    }

    private void setSecurityContext (Claims claims) {
        List<SimpleGrantedAuthority> grantedAuthorities = ((List<String>) claims.get("roles"))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(claims.getSubject(), "", grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
