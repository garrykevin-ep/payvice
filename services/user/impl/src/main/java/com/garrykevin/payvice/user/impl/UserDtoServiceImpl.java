package com.garrykevin.payvice.user.impl;

import com.garrykevin.payvice.user.UserCreateParam;
import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.UserDtoService;
import com.garrykevin.payvice.user.mapper.UserMapper;
import com.garrykevin.payvice.user.model.User;
import com.garrykevin.payvice.user.repository.UserRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Override
  public Optional<UserDto> getById(Long id) {
    User user = userRepository.findById(id).get();
    if (user == null){
      return Optional.empty();
    }
    return Optional.of(userMapper.modelToDto(user));
  }

  @Override
  public Optional<UserDto> getByEmail(String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if(user == null){
      return Optional.empty();
    }
    return Optional.of(userMapper.modelToDto(user));
  }

  @Override
  public Set<UserDto> getByIds(Set<Long> ids){
   Set<User> users = userRepository.findByIdIn(ids);
   return userMapper.modelToDtos(users.stream().collect(Collectors.toList()))
     .stream().collect(Collectors.toSet());
  }

  @Override
  /**
   *  validates and creates user
   */
  public UserDto register(UserCreateParam userCreateParam) {
    //todo validate for existing user
    User user = new User();
    user.setName(userCreateParam.getName());
    user.setEmail(userCreateParam.getEmail());
    user = userRepository.save(user);
    return userMapper.modelToDto(user);
  }


}
