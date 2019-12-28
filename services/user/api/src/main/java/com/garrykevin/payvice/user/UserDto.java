package com.garrykevin.payvice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;

  @JsonIgnore
  private String password;
}
