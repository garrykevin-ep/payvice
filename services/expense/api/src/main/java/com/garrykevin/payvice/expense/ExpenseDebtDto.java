package com.garrykevin.payvice.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExpenseDebtDto {

  Long id;

  @JsonProperty("lender_id")
  Long lenderUserId;

  @JsonProperty("borrower_id")
  Long borrowerUserId;

  Double amount;
}
