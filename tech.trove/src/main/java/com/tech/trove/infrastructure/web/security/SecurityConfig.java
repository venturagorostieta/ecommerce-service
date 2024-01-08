package com.tech.trove.infrastructure.web.security;

import com.tech.trove.infrastructure.common.constant.Role;
import com.tech.trove.infrastructure.web.filter.APIKeyAuthFilter;
import com.tech.trove.infrastructure.web.filter.ApiKeyAuthenticationManager;
import com.tech.trove.infrastructure.web.filter.PermissionFilter;
import com.tech.trove.infrastructure.web.security.handler.TechCustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

    @Value("${trove.security.api-key.client-api-key}")
    private String apiKey;

    @Value("${spring.security.oauth2.jwt.jwk-set-uri}")
    private String jwkSetUri;

    private final PermissionFilter permissionFilter;


    /**
     * The constant PERMITTED_PATHS.
     */
    public static final String[] PERMITTED_PATHS = {
            "/",
            "/actuator/health",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/health/**",
            "/login/**"
    };

    /**
     * The constant AUTHENTICATED_PATHS.
     */
    public static final String[] AUTHENTICATED_PATHS = {
            "/product/**",
            "/product/category/**",
            "/product/inventory/**",
            "/order/**",
            "/user/**"
    };

    /**
     * Custom access denied handler access denied handler.
     *
     * @return the access denied handler
     */
    @Bean(name = "customSpinAccessDeniedHandler")
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new TechCustomAccessDeniedHandler();
    }

    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final APIKeyAuthFilter apiKeyFilter = new APIKeyAuthFilter();
        apiKeyFilter.setAuthenticationManager(new ApiKeyAuthenticationManager()
                .addApikeyAuth(apiKey, Role.CLIENT));

        http
                .csrf().disable()
                .addFilter(apiKeyFilter)
                .authorizeHttpRequests(authorize ->
                        {
                            try {
                                authorize.antMatchers(PERMITTED_PATHS).permitAll()
                                        .antMatchers(AUTHENTICATED_PATHS).hasAnyRole(Role.USER.name())
                                        .anyRequest().authenticated().and()
                                        .addFilterBefore(permissionFilter, BasicAuthenticationFilter.class)
                                        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri(jwkSetUri)
                        )
                );
        return http.build();
    }

}
