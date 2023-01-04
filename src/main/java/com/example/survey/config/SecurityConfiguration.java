package com.example.survey.config;

import com.example.survey.filters.CustomAuthenticationFilter;
import com.example.survey.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//todo zmienic zeby nie korzystalo z WebSecurityConfigurerAdapter jako dobra praktyka od Security 5
    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //todo custom endpoint dla logowania, domyślnie "/login"
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**", "/users/refresh-token/**")
                .anonymous()
                .and()
                .formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/login/successful")
//                .loginProcessingUrl("/login")
//                .failureUrl("/login/failed")
                .permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/categories/**").hasAnyAuthority("registered_user", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/categories/**").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/categories/**").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/categories/**").hasAnyAuthority("admin");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/companies/**").hasAnyAuthority("registered_user", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/companies/**").hasAnyAuthority("registered_user", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/companies/{id}").hasAnyAuthority("registered_user", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/companies/{id}/verify").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/companies/**").hasAnyAuthority("registered_user", "admin");
        //todo reszta endpointow

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic();
    }

    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
