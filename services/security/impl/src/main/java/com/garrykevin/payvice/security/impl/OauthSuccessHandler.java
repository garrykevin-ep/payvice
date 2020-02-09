package com.garrykevin.payvice.security.impl;

import com.garrykevin.payvice.security.impl.jwt.JwtTokenUtil;
import com.garrykevin.payvice.security.impl.model.CustomUserPrincipal;
import com.garrykevin.payvice.user.UserCreateParam;
import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.UserDtoService;
import com.garrykevin.payvice.user.mapper.UserMapper;
import com.garrykevin.payvice.user.model.User;
import com.garrykevin.payvice.user.repository.UserRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OauthSuccessHandler implements AuthenticationSuccessHandler {

  @Value("${frontend.host}")
  private String frontEndUrl;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDtoService userDtoService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    Authentication authentication) throws IOException, ServletException {
    response.setStatus(HttpStatus.PERMANENT_REDIRECT.value());
    String token = authenticateUser(authentication);
    response.setHeader("Location",frontEndUrl + "auth-token?token=" + token);
  }

  /**
   * gets user details and generates token.
   * when does not exist creates
   * @param authentication
   * @return
   */
  private String authenticateUser(Authentication authentication){
    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    String email = oidcUser.getEmail();
    Optional<UserDto> optUserDto = userDtoService.getByEmail(email);
    if(optUserDto.isEmpty()){
      optUserDto = registerUser(authentication);
    }
    UserDto userDto = optUserDto.get();
    CustomUserPrincipal customUserPrincipal = new CustomUserPrincipal(userDto);
    return jwtTokenUtil.generateToken(customUserPrincipal);
  }

  /**
   * saves to user
   * @param authentication
   * @return
   */
  public Optional<UserDto> registerUser(Authentication authentication){
    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    String email = oidcUser.getEmail();
    String name = oidcUser.getFullName();
    UserCreateParam userCreateParam = new UserCreateParam();
    userCreateParam.setEmail(email);
    userCreateParam.setName(name);
    return Optional.of(userDtoService.register(userCreateParam));
  }


}
