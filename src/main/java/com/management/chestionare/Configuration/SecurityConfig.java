package com.management.chestionare.Configuration;

import com.management.chestionare.domain.Utilizator;
import com.management.chestionare.service.ServiceUtilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@SuppressWarnings({"SpellCheckingInspection", "rawtypes"})
@EnableWebSecurity
public class SecurityConfig {
    private final ServiceUtilizator serviceUtilizator;

    @Autowired
    public SecurityConfig(ServiceUtilizator serviceUtilizator) {
        this.serviceUtilizator = serviceUtilizator;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<Utilizator> utilizatorList = this.serviceUtilizator.findAll();
        if (!utilizatorList.isEmpty()) {
            UserDetailsManagerConfigurer.UserDetailsBuilder builder = auth
                    .inMemoryAuthentication()
                    .withUser(utilizatorList.get(0).getNumeDeUtilizator())
                    .password("{noop}" + utilizatorList.get(0).getParola())
                    .roles(utilizatorList.get(0).getRol().toString());
            for (int i = 1; i < utilizatorList.size(); i++) {
                Utilizator utilizator = utilizatorList.get(i);
                builder
                        .and()
                        .withUser(utilizator.getNumeDeUtilizator())
                        .password("{noop}" + utilizator.getParola())
                        .roles(utilizator.getRol().toString());
            }
        }
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http
                .logout();
    }
}