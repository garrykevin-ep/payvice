package com.garrykevin.payvice.security.impl;



import com.garrykevin.payvice.user.UserCreateParam;
import com.garrykevin.payvice.user.UserDto;

import com.garrykevin.payvice.user.UserDtoService;
import com.garrykevin.payvice.security.impl.model.CustomUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserDtoService userDtoService;


  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    UserDto userDto = userDtoService.getById(Long.parseLong(id)).orElseThrow();
    return new CustomUserPrincipal(userDto);
  }


}
