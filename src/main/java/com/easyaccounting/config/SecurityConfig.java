package com.easyaccounting.config;

import com.easyaccounting.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeRequests()
                .antMatchers("/company/**").hasAnyAuthority("Root", "Admin", "Manager", "Employee")
                .antMatchers("/user/**").hasAnyAuthority("Root", "Admin", "Manager", "Employee")
                .antMatchers("/client-vendor/**").hasAnyAuthority("Admin", "Manager", "Employee")
                .antMatchers("/product/**").hasAnyAuthority("Admin", "Manager", "Manager")
                .antMatchers("/category/**").hasAnyAuthority("Admin", "Manager", "Employee")
                .antMatchers("/purchase-invoice/**").hasAnyAuthority("Admin", "Manager", "Employee")
                .antMatchers("/sales-invoice/**").hasAnyAuthority("Admin", "Manager", "Employee")
                .antMatchers("/report/**").hasAnyAuthority("Admin", "Manager", "Employee")
                .antMatchers("/payment/**").hasAnyAuthority("Admin", "Manager", "Employee")
                .antMatchers("/dashboard").authenticated()
                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authSuccessHandler)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .tokenValiditySeconds(120)
                .key("accounting")
                .userDetailsService(securityService)
                .and().build();

    }
}
