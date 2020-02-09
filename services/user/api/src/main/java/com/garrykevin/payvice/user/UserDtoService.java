package com.garrykevin.payvice.user;

import java.util.Optional;
import java.util.Set;

public interface UserDtoService {

  Optional<UserDto> getById(Long id);

  Optional<UserDto> getByEmail(String email);

  Set<UserDto> getByIds(Set<Long> ids);


  UserDto register(UserCreateParam user);

}
