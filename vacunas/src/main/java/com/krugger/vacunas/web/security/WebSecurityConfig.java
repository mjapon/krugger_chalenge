package com.krugger.vacunas.web.security;


import com.krugger.vacunas.domain.service.CustomAuthenticationProvider;
import com.krugger.vacunas.web.security.filter.CorsFilterRequest;
import com.krugger.vacunas.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] allowed = {
            "/",
            "/**/api-docs/**",
            "/**/swagger.json",
            "/**/swagger-ui.html",
            "/**/swagger-resources/**",
            "/**/aut/authenticate",
            "/**/webjars/**"};

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Autowired
    private CorsFilterRequest corsFilterRequest;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(allowed).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(corsFilterRequest, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider);
    }


}
