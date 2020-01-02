package com.garrykevin.payvice.request.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.garrykevin.payvice.request.expense.validation.TotalAmountEqualsPayerAmount;
import java.util.Set;
import javax.validation.Valid;
import lombok.Data;

@TotalAmountEqualsPayerAmount
@Data
public class CreateExpenseRequest extends ExpenseRequest {

  @JsonProperty("group_id")
  private Long groupId;

  @JsonProperty("expense_participants")
  private Set<ExpenseParticipantRequest> expenseParticipants;

  @JsonProperty("expense_payers")
  @Valid
  private Set<ExpensePayerRequest> expensePayers;

}
