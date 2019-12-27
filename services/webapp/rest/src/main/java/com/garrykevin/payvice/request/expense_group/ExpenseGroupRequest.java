package com.garrykevin.payvice.request.expense_group;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ExpenseGroupRequest {

  @JsonProperty("members")
  List<ExpenseGroupMemberRequest> expenseGroupMember;

}
