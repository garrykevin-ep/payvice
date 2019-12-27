package com.garrykevin.payvice.request.expense_group;

import lombok.Data;

@Data
public class CreateExpenseGroupRequest extends ExpenseGroupRequest {

  public String name;

}
