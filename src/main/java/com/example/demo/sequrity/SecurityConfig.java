package com.example.demo.sequrity;

import com.example.demo.model.Sale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
                http.authorizeRequests()
                .antMatchers( "/sales/**","/request1","/request2","/request3","/request4").authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/")
                ;
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails user= User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$RdtpBtuWD1TizbQun3D2AOLY1U4udFwoveMYb0J/66z0Ib0eKllXi")
                .roles("User")
                .build();
        UserDetails admin= User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$E.KT/ZBznZ21EDxXOfI7fupXiqKWAKiJOca5sjRBNo1UbGB2eCz4O")
                .roles("Admin","User")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }



}