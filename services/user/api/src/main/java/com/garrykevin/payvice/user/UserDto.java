package com.garrykevin.payvice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {
  private long id;
  private String name;
//  @JsonProperty("first_name")
//  private String firstName;
//  @JsonProperty("last_name")
//  private String lastName;
  private String email;
//  private String phone;
//  @JsonIgnore
//  private String password;
}
