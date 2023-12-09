package nvv.dev.programingcourses.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final List<String> CORS_LIST_URL = Arrays.asList(
            "https://v3d8.vercel.app/",
            "http://localhost:8080/",
            "http://localhost:3000/"
    );
    private final List<String> CORS_LIST_METHODS = Arrays.asList(
            "GET",
            "POST",
            "PUT",
            "DELETE"
    );
    private final List<String> CORS_LIST_HEADERS = Arrays.asList(
            "content-type",
            "authorization",
            "access-control-allow-origin",
            "x-requested-with"
    );
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(CORS_LIST_URL);
                        config.setAllowedMethods(CORS_LIST_METHODS);
                        config.setAllowedHeaders(CORS_LIST_HEADERS);
                        return config;
                    });
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/docs/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/api/v1/docs/**")
                                .authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/docs/**")
                                .authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/docs/**")
                                .authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/docs/**")
                                .authenticated()
                                .requestMatchers("/api/v1/users/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll()
                )
                .sessionManagement(ses ->
                        ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}

