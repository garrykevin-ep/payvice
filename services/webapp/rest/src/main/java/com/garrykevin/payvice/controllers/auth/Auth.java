package com.garrykevin.payvice.controllers.auth;


import com.garrykevin.payvice.UserDto;
import com.garrykevin.payvice.UserDtoService;
import com.garrykevin.payvice.mapper.UserMapper;
import com.garrykevin.payvice.security.impl.jwt.JwtTokenUtil;
import com.garrykevin.payvice.security.impl.model.CustomUserPrincipal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Auth {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Autowired
  UserDtoService userDtoService;

  @Autowired
  UserMapper userMapper;

  @RequestMapping("auth/authenticate")
  public String dummmer(HttpServletRequest request){
    System.out.println("request from " + request.getRequestURI());
    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getHeader("username"),request.getHeader("pass")));
    }
    catch (Exception e){
      return e.getMessage();
    }
//    UserDto userDto = new UserDto(1,"garry","dto","garrydto@gmail.com","7272");
    UserDto userDto = new UserDto();
    CustomUserPrincipal customUserPrincipal = new CustomUserPrincipal(userDto,"");
    return jwtTokenUtil.generateToken(customUserPrincipal);
  }


  @RequestMapping("/secret")
  public String bull(){
    return "nice";
  }

  @RequestMapping("/user")
  public UserDto user(HttpServletRequest request){
    return userDtoService.getById(1l).orElseThrow();
  }

}
