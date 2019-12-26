package com.garrykevin.payvice.impl;

import com.garrykevin.payvice.UserDto;
import com.garrykevin.payvice.UserDtoService;
import com.garrykevin.payvice.mapper.UserMapper;
import com.garrykevin.payvice.model.User;
import com.garrykevin.payvice.repository.UserRepository;
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
    User user = userRepository.findById(id).orElseThrow();
    return Optional.of(userMapper.modelToDto(user));
  }

  @Override
  public Set<UserDto> getByIds(Set<Long> ids){
   Set<User> users = userRepository.findByIdIn(ids);
   return userMapper.modelToDtos(users.stream().collect(Collectors.toList()))
     .stream().collect(Collectors.toSet());
  }
}
