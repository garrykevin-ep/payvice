package com.garrykevin.payvice.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ExpenseDto {

  private Long id;

  String name;

  Double amount;

  @JsonProperty("share_type")
  Integer shareType;

  @JsonProperty("expense_participants")
  List<ExpenseParticipantDto> expenseParticipants;

  @JsonProperty("expense_payers")
  List<ExpensePayerDto> expensePayer;

  @JsonProperty("expense_debts")
  List<ExpenseDebtDto> expenseDebts;

//  List<Integer> expnse

}
