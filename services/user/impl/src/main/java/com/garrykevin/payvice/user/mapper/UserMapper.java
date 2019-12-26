package com.garrykevin.payvice.user.mapper;


import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto modelToDto(final User entity);

  List<UserDto> modelToDtos(final List<User> users);
}
