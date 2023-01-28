package com.teachmeskills.security.config;

import com.teachmeskills.security.config.filter.JwtFilter;
import com.teachmeskills.security.session.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/login", "/registration").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }


//  @Bean
//  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
//    http
//            .httpBasic().disable()
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and().formLogin()
//            .and().authorizeRequests()
//            .antMatchers("/*", "/api/v1/auth", "/message").permitAll()
//            .and()
//            .authorizeRequests().antMatchers("/api/v1/users")
//            .authenticated()
//            .and()
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//            .logout(LogoutConfigurer::permitAll);
//    return http.build();
//  }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthContext authContext() {
        return new AuthContext();
    }
}
