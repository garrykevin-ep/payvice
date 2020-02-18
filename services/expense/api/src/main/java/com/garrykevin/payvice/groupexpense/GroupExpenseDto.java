package com.garrykevin.payvice.groupexpense;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.garrykevin.payvice.expense.ExpenseDto;
import com.garrykevin.payvice.user.UserDto;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class GroupExpenseDto {

  public int id;

  public String name;

  Set<UserDto> members;

  @JsonInclude(Include.NON_NULL)
  List<ExpenseDto> expenses;
}
