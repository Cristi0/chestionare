package com.management.chestionare.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SuppressWarnings("SpellCheckingInspection")
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("UTILIZATOR_OBISNUIT")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMINISTRATOR");
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http
                .logout();
    }
}