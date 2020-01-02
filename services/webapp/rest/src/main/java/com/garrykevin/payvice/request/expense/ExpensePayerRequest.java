package com.garrykevin.payvice.request.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpensePayerRequest {

  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("amount_paid")
  @NotNull
  private Double amountPaid;

}
