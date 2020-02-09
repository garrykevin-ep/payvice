package com.garrykevin.payvice.groupexpense;

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

  List<ExpenseDto> expenses;
}
