package com.garrykevin.payvice.expense;

import java.util.Set;
import lombok.Data;

@Data
public class CreateExpenseParam extends ExpenseParam {

  private Long groupId;

  private Set<ExpenseParticipantParam> expenseParticipants;

  private Set<ExpensePayerParam> expensePayers;

}
