package ai.ecma.codingbat.config;

import ai.ecma.codingbat.util.RestConstants;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.exceptions.MyEntryPointHandler;
import ai.ecma.codingbat.security.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final MyEntryPointHandler myEntryPointHandler;

    private final JWTFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .antMatchers(RestConstants.OPEN_PAGES)
                                        .permitAll()
                                        .antMatchers("/",
                                                "/favicon.ico",
                                                "//*.png",
                                                "//*.gif",
                                                "//*.svg",
                                                "//*.jpg",
                                                "//*.html",
                                                "//*.css",
                                                "//*.js",
                                                "/swagger-ui.html",
                                                "/swagger-resources/",
                                                "/v2/",
                                                "/csrf",
                                                "/webjars/",
                                                "/v2/api-docs",
                                                "/configuration/ui")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )

                .exceptionHandling()
                .authenticationEntryPoint(myEntryPointHandler)
                .and()
//                .rememberMe()
//                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}
