package com.garrykevin.payvice.request.expense_group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExpenseGroupMemberRequest {

  @JsonProperty("user_id")
  public Long userId;

//  public String email;

}
