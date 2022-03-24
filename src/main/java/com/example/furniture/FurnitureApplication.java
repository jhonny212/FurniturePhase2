package com.example.furniture;

import com.example.furniture.config.JWTAuthorizationFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class FurnitureApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FurnitureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/isAdminLoggedIn").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/isFabricatemanLoggedIn").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/isSalesmanLoggedIn").permitAll()
                    .anyRequest().authenticated();
        }
    }
}
