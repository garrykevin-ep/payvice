package com.garrykevin.payvice.mapper;


import com.garrykevin.payvice.UserDto;
import com.garrykevin.payvice.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto modelToDto(final User entity);

  List<UserDto> modelToDtos(final List<User> users);
}
