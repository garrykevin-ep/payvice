package com.garrykevin.payvice.user.mapper;


import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.model.User;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto modelToDto(final User entity);

  List<UserDto> modelToDtos(final List<User> users);

  Set<UserDto> modelToDtos(final Set<User> users);

  User dtoToModel(final UserDto userDto);

  List<User> dtosToModels(final List<UserDto> userDtos);


  Set<User> dtosToModels(final Set<UserDto> userDtos);

}
