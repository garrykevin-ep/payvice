package com.garrykevin.payvice.groupexpense;

import com.garrykevin.payvice.user.UserDto;
import java.util.Set;
import lombok.Data;

@Data
public class GroupExpenseDto {

  public int id;

  public String name;

  Set<UserDto> users;
}
