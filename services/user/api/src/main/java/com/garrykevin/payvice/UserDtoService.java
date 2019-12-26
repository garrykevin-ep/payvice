package com.garrykevin.payvice;

import com.garrykevin.payvice.UserDto;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDtoService {

  Optional<UserDto> getById(Long id);

  Set<UserDto> getByIds(Set<Long> ids);
}
