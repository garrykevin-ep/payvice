package com.garrykevin.payvice.expense_group;

import com.garrykevin.payvice.user.UserDto;
import java.util.Set;
import lombok.Data;

@Data
public class ExpenseGroupDto {

  public int id;

  public String name;

  Set<UserDto> users;
}
