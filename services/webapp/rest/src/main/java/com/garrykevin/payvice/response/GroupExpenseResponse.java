package com.garrykevin.payvice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import java.util.List;
import lombok.Data;

@Data
public class GroupExpenseResponse {

  @JsonProperty("group_expenses")
  List<GroupExpenseDto> groupExpenseDtos;

}
