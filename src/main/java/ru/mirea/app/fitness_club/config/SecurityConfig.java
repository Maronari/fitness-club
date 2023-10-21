package ru.mirea.app.fitness_club.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LogManager.getLogger(SecurityConfig.class);

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/profile/**", "/css/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler())
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessHandler(myLogoutSuccessHandler())
                        .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Integer id = userDetailsService.getUserId(username);
            String role = userDetailsService.getUserRole(username);

            logger.info("LOGGING: " + username
                    + " ROLE: " + role
                    + " ID: " + id);

            response.sendRedirect("/profile/" + role + "/" + id);
        };
    }

    @Bean
    public LogoutSuccessHandler myLogoutSuccessHandler() {
        return (request, response, authentication) -> {

            logger.info("User logged out: " + authentication.getName());

            response.sendRedirect("/logout");
        };
    }

}
