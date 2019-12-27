package com.garrykevin.payvice.expense_group;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CreateExpenseGroupParam extends ExpenseGroupParam {

  @JsonProperty("members")
  List<ExpenseGroupMemberParam> members;

}
