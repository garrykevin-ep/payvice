package com.garrykevin.payvice.controllers;

import com.garrykevin.payvice.groupexpense.CreateGroupExpenseParam;
import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.GroupExpenseDtoService;
import com.garrykevin.payvice.groupexpense.GroupExpenseMemberParam;
import com.garrykevin.payvice.groupexpense.mapper.GroupExpenseMapper;
import com.garrykevin.payvice.request.groupexpense.CreateGroupExpenseRequest;
import com.garrykevin.payvice.request.groupexpense.GroupExpenseMemberRequest;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/group-expense")
public class GroupExpenseController {

  @Autowired
  GroupExpenseDtoService groupExpenseDtoService;

  @Autowired
  GroupExpenseMapper groupExpenseMapper;

  @PostMapping
  public GroupExpenseDto createExpenseGroup(@RequestBody @Valid CreateGroupExpenseRequest createGroupExpenseRequest){
    CreateGroupExpenseParam createGroupExpenseParam = convertCreateExpenseGroupRequestToParam(
      createGroupExpenseRequest);
    return groupExpenseDtoService.create(createGroupExpenseParam);
  }

  private CreateGroupExpenseParam convertCreateExpenseGroupRequestToParam(
    CreateGroupExpenseRequest createGroupExpenseRequest){
    CreateGroupExpenseParam createGroupExpenseParam = new CreateGroupExpenseParam();
    createGroupExpenseParam.setName(createGroupExpenseRequest.getName());

    Function<GroupExpenseMemberRequest, GroupExpenseMemberParam> convertRequestToParam = (requestMember) -> {
      GroupExpenseMemberParam groupExpenseMemberParam =  new GroupExpenseMemberParam();
      // TODO: handle email and phone
      groupExpenseMemberParam.setId(requestMember.getUserId());
      return groupExpenseMemberParam;
    };

    createGroupExpenseParam.setMembers(createGroupExpenseRequest.getExpenseGroupMember()
      .stream().map(convertRequestToParam).collect(Collectors.<GroupExpenseMemberParam>toList()));
    return createGroupExpenseParam;
  }
}
