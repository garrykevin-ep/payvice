package com.garrykevin.payvice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class UserDto {
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String password;
}
