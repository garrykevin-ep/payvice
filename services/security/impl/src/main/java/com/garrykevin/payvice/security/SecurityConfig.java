package com.garrykevin.payvice.security;


import com.garrykevin.payvice.security.impl.OauthSuccessHandler;
import com.garrykevin.payvice.security.impl.UserDetailsServiceImpl;
import com.garrykevin.payvice.security.impl.jwt.JwtAuthenticationEntryPoint;
//import com.garrykevin.payvice.security.impl.jwt.JwtRequestFilter;
import com.garrykevin.payvice.security.impl.jwt.JwtRequestFilter;
import com.garrykevin.payvice.security.impl.jwt.JwtTokenUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
    private OauthSuccessHandler oauthSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetails);
//      auth.inMemoryAuthentication().withUser("ram").password("ram").roles("ADMIN");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http

        .csrf()
        .disable()
          .cors()
          .disable()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        //allow authenticated requests
        .anyRequest().authenticated()
        .and()
        .oauth2Login()
        .defaultSuccessUrl("/authenticate")
        .successHandler(oauthSuccessHandler)
        .permitAll()
        .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationHandler)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
;
      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigure() {
      return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("*").allowedOrigins("http://localhost:9200");
        }
      };
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
