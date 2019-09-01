package com.emidio.restapiauthtest.security.config;

import com.emidio.restapiauthtest.security.AuthenticationEntryPointHandler;
import com.emidio.restapiauthtest.security.filters.CustomAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("admin")).roles("ADMIN").and()
                .withUser("user").password(encoder().encode("user")).roles("USER");
    }
    

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 
     * Obtem o meu filtro customizado para executar antes dos requests
     */
    @Bean
    protected CustomAuthenticationFilter getFilterBefore(){
        return new CustomAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointHandler()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/teste/**").permitAll().anyRequest().authenticated();

        http.addFilterBefore(getFilterBefore(), UsernamePasswordAuthenticationFilter.class);
     //  http.addFilterBefore(this.getFilterBefore(), BasicAuthenticationFilter.class);
        http.headers().cacheControl();

    }
}