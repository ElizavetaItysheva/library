package com.skypro.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()

                .authorizeRequests()
                // Разрешение доступа к URL-адресу "/web" для всех пользователей
                .antMatchers("/web").permitAll()
                // Запрет доступа к любым другим URL-адресам, если пользователь не прошел аутентификацию
                .anyRequest().authenticated()
                // Настройка аутентификации через базовую HTTP-аутентификацию
                .and().httpBasic();

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        UserDetails user = userBuilder.username("user").password("password")
                .roles("USER").build();
        UserDetails admin = userBuilder.username("admin").password("password")
                .roles("USER","ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
