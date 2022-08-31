package ai.ecma.codingbat.security;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    @Value("${jwt.access.key}")
    private String TOKEN_KEY;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        setSecurityContext(request);
        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer")) {

            authorization = authorization.substring(6).trim();

            String email = getEmailFromToken(authorization);
            if (!email.isEmpty()) {
                Optional<User> optionalUser = userRepository.findByEmail(email);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (user.isEnabled()
                            && user.isAccountNonExpired()
                            && user.isAccountNonLocked()
                            && user.isCredentialsNonExpired())
                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(
                                        new UsernamePasswordAuthenticationToken(
                                                user,
                                                null,
                                                user.getAuthorities()
                                        ));
                }
            }
        }
    }

    private String getEmailFromToken(String authorization) {
        String email = "";
        try {
            email = Jwts
                    .parser()
                    .setSigningKey(TOKEN_KEY)
                    .parseClaimsJws(authorization)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
        } finally {
            System.out.println("final");
        }
        return email;
    }

    private void setCorsConfig(HttpServletRequest request,
                               HttpServletResponse response,
                               FilterChain filterChain) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-auth-token");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

//        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.setHeader("Access-Control-Allowed-Methods", "POST, GET, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type,x-auth-token, " +
//                    "access-control-request-headers, access-control-request-method, accept, origin, authorization, x-requested-with");
//
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
    }
}
