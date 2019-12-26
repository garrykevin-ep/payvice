package com.garrykevin.payvice.user;

import java.util.Optional;
import java.util.Set;

public interface UserDtoService {

  Optional<UserDto> getById(Long id);

  Set<UserDto> getByIds(Set<Long> ids);
}
