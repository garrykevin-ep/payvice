package com.garrykevin.payvice.groupexpense;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CreateGroupExpenseParam extends GroupExpenseParam {

  @JsonProperty("members")
  List<GroupExpenseMemberParam> members;

}
