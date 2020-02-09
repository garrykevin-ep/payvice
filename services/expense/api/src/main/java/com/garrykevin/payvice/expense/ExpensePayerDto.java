package com.garrykevin.payvice.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExpensePayerDto {

  Long id;

  @JsonProperty("user_id")
  Long userId;

  @JsonProperty("amount_paid")
  Double amountPaid;

}
