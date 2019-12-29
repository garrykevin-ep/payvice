package com.garrykevin.payvice.request.groupexpense;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGroupExpenseRequest extends GroupExpenseRequest {

  @JsonProperty("members")
  @Size(min=3,message = "group expense has to be created with minimum 3 members")
  List<GroupExpenseMemberRequest> expenseGroupMember;

}
