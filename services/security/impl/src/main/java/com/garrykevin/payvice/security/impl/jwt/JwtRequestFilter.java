package com.garrykevin.payvice.security.impl.jwt;

import com.garrykevin.payvice.security.impl.UserDetailsServiceImpl;
import com.garrykevin.payvice.security.impl.model.CustomUserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  AuthenticationManager authenticationManager;


  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain chain) throws ServletException, IOException {


    final String requestTokenHeader = request.getHeader("Authorization");
    long userId = 0;
    String jwtToken = null;

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        userId = jwtTokenUtil.getUserId(jwtToken);
      } catch (IllegalArgumentException e) {
        System.out.println("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        System.out.println("JWT Token has expired");
      }
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }


    if(userId != 0 && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails  = this.userDetailsService.loadUserByUsername(String.valueOf(userId));
      CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) userDetails;

      if (jwtTokenUtil.validateToken(jwtToken, customUserPrincipal.getUserDto().getId())) {


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());

        usernamePasswordAuthenticationToken
          .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    chain.doFilter(request,response);
  }

  private UserDetails dummyUser(HttpServletRequest request){
    return new UserDetails() {
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
      }

      @Override
      public String getPassword() {
        return "summa";
      }

      @Override
      public String getUsername() {
        return request.getHeader("Name");
      }

      @Override
      public boolean isAccountNonExpired() {
        return false;
      }

      @Override
      public boolean isAccountNonLocked() {
        return false;
      }

      @Override
      public boolean isCredentialsNonExpired() {
        return false;
      }

      @Override
      public boolean isEnabled() {
        return true;
      }

    };
  }
}
