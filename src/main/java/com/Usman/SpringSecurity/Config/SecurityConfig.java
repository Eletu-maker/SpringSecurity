package com.Usman.SpringSecurity.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

       //lamda
       //Disable the csrf
      http.csrf(Customizer->  Customizer.disable());
       // authorize access ie no one cant access this page without authorization
     http.authorizeHttpRequests(request -> request.anyRequest().authenticated() );
        // enable the form so u can give information for authorization
       http.formLogin(Customizer.withDefaults());
        // make the postman to work
        http.httpBasic(Customizer.withDefaults());
        // how to make http stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /*
        what happening under lamda
        Customizer<CsrfConfigurer<HttpSecurity>> configurerCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
                httpSecurityCsrfConfigurer.disable();
            }
        };

        http.csrf(configurerCustomizer);


         */
        return http.build();
    }



    // Spring boot user from database
    // this is how to customize or have different user but yet not a good idea because it not coming from data base
    @Bean
    public UserDetailsService userDetailsService(){

        // do not use withDefaultPasswordEncoder() in real world project
         UserDetails user1 = User
                 .withDefaultPasswordEncoder()
                 .username("eletu")
                 .password("el1234")
                 .roles("USER")
                 .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("olaseni")
                .password("ol1234")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user1,user2);
    }
}
