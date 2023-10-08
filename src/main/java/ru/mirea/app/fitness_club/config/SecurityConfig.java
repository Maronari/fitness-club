package ru.mirea.app.fitness_club.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    /*TODO - remove deprecated methods */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests() //deprecated
            .anyRequest().authenticated()
            .and() //deprecated
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/profile/" + "1", true) //TODO: make AuthenticationSuccessHandler
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
