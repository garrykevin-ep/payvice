package com.garrykevin.payvice.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExpenseParticipantDto {

  Long id;

  @JsonProperty("share_amount")
  Double shareAmount;

  @JsonProperty("user_id")
  Long userId;
}
