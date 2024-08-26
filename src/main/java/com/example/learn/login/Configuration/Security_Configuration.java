package com.example.learn.login.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.learn.login.Implementation.User_Detail_service_IMlementation;

@Configuration
@EnableWebSecurity
public class Security_Configuration {

    @Autowired
    @Lazy
    private User_Detail_service_IMlementation detail_service_IMlementation;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detail_service_IMlementation);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login/**", "/home", "/resources/**", "/signup/**", "/api/patients/**",
                                "/about",
                                "/services", "/contact")
                        .permitAll() // Allow
                        .anyRequest().authenticated() // Require authentication for other requests
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page URL
                        .defaultSuccessUrl("/welcome", true)
                        .loginProcessingUrl("/login/v1")
                        // .failureForwardUrl("/login?error=true")

                        .permitAll())
                .logout(logout1 -> {
                    logout1.logoutUrl("/logout").logoutSuccessUrl("/home").invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                })
                .httpBasic(Customizer.withDefaults())
                .csrf(Customizer -> Customizer.disable());

        return http.build();
    }
}
