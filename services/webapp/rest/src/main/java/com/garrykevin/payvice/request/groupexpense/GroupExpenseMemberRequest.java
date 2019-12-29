package com.garrykevin.payvice.request.groupexpense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupExpenseMemberRequest {

  @JsonProperty("user_id")
  public Long userId;

//  public String email;

}
