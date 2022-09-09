package ru.sidorov.mcq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.sidorov.mcq.services.MyUserDetailService;


@EnableWebSecurity
public class SecurityConfiguration {
	
	AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailService myUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	
        http   
	        .csrf().disable()
	        .authorizeHttpRequests(
	                authz -> authz.anyRequest()
	                        .authenticated())
	        .httpBasic(Customizer.withDefaults())
	        .authenticationProvider(daoAuthenticationProvider());
       
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setHideUserNotFoundExceptions(false);
    	provider.setPasswordEncoder(passwordEncoder());
    	provider.setUserDetailsService(myUserDetailService);
    	return provider;
    }

}