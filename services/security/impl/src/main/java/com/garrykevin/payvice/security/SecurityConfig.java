package com.garrykevin.payvice.security;


import com.garrykevin.payvice.security.impl.UserDetailsServiceImpl;
import com.garrykevin.payvice.security.impl.jwt.JwtAuthenticationEntryPoint;
//import com.garrykevin.payvice.security.impl.jwt.JwtRequestFilter;
import com.garrykevin.payvice.security.impl.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig {

  @Configuration
  @Order(1)
  public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationHandler;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsServiceImpl userDetails;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetails);
//      auth.inMemoryAuthentication().withUser("ram").password("ram").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/**").permitAll()
        .antMatchers("/user").permitAll()
        .antMatchers("/auth/**")
        .permitAll()
        .anyRequest().authenticated()
        .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationHandler)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new PlainTextPasswordEncoder();
    }


    class PlainTextPasswordEncoder implements PasswordEncoder{

      @Override
      public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
      }
    }


  }
}
