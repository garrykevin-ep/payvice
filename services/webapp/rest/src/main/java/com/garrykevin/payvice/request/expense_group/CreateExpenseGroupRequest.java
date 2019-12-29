package com.garrykevin.payvice.request.expense_group;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateExpenseGroupRequest extends ExpenseGroupRequest {

  @JsonProperty("members")
  @Size(min=3,message = "group expense has to be created with minimum 3 members")
  List<ExpenseGroupMemberRequest> expenseGroupMember;

}
