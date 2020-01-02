package com.garrykevin.payvice.request.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExpenseParticipantRequest {

  @JsonProperty("user_id")
  private Long userId;
}
