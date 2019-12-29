package com.garrykevin.payvice.controllers;

import com.garrykevin.payvice.expense_group.CreateExpenseGroupParam;
import com.garrykevin.payvice.expense_group.ExpenseGroupDto;
import com.garrykevin.payvice.expense_group.ExpenseGroupDtoService;
import com.garrykevin.payvice.expense_group.ExpenseGroupMemberParam;
import com.garrykevin.payvice.expense_group.mapper.ExpenseGroupMapper;
import com.garrykevin.payvice.request.expense_group.CreateExpenseGroupRequest;
import com.garrykevin.payvice.request.expense_group.ExpenseGroupMemberRequest;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/expense-group")
public class ExpenseGroupController {

  @Autowired
  ExpenseGroupDtoService expenseGroupDtoService;

  @Autowired
  ExpenseGroupMapper expenseGroupMapper;

  @PostMapping
  public ExpenseGroupDto createExpenseGroup(@RequestBody @Valid CreateExpenseGroupRequest createExpenseGroupRequest){
    CreateExpenseGroupParam createExpenseGroupParam = convertCreateExpenseGroupRequestToParam(createExpenseGroupRequest);
    return expenseGroupDtoService.create(createExpenseGroupParam);
  }

  private CreateExpenseGroupParam convertCreateExpenseGroupRequestToParam(CreateExpenseGroupRequest createExpenseGroupRequest){
    CreateExpenseGroupParam createExpenseGroupParam = new CreateExpenseGroupParam();
    createExpenseGroupParam.setName(createExpenseGroupRequest.getName());

    Function<ExpenseGroupMemberRequest, ExpenseGroupMemberParam> convertRequestToParam = (requestMember) -> {
      ExpenseGroupMemberParam expenseGroupMemberParam =  new ExpenseGroupMemberParam();
      // TODO: handle email and phone
      expenseGroupMemberParam.setId(requestMember.getUserId());
      return  expenseGroupMemberParam;
    };

    createExpenseGroupParam.setMembers(createExpenseGroupRequest.getExpenseGroupMember()
      .stream().map(convertRequestToParam).collect(Collectors.<ExpenseGroupMemberParam>toList()));
    return createExpenseGroupParam;
  }
}
