package com.garrykevin.payvice.controllers;

import com.garrykevin.payvice.groupexpense.CreateGroupExpenseParam;
import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.GroupExpenseDtoService;
import com.garrykevin.payvice.groupexpense.GroupExpenseMemberParam;
import com.garrykevin.payvice.groupexpense.mapper.GroupExpenseMapper;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.request.groupexpense.CreateGroupExpenseRequest;
import com.garrykevin.payvice.request.groupexpense.GroupExpenseMemberRequest;
import com.garrykevin.payvice.security.impl.model.CustomUserPrincipal;
import com.garrykevin.payvice.user.UserDto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/group-expense")
public class GroupExpenseController {

  @Autowired
  GroupExpenseDtoService groupExpenseDtoService;

  @PostMapping
  public GroupExpenseDto createExpenseGroup(@RequestBody @Valid CreateGroupExpenseRequest createGroupExpenseRequest){
    CreateGroupExpenseParam createGroupExpenseParam = convertCreateExpenseGroupRequestToParam(
      createGroupExpenseRequest);
    return groupExpenseDtoService.create(createGroupExpenseParam);
  }

  @GetMapping
  public List<GroupExpenseDto> getGroupExpenses(Authentication authentication){
    CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
    UserDto userDto = customUserPrincipal.getUserDto();
    return groupExpenseDtoService.getAll(userDto.getId());
  }

  @GetMapping("{groupExpenseId}")
  public GroupExpenseDto getGroupExpense(Authentication authentication,
    @PathVariable Long groupExpenseId){
    return groupExpenseDtoService.getById(groupExpenseId);
  }

  private CreateGroupExpenseParam convertCreateExpenseGroupRequestToParam(
    CreateGroupExpenseRequest createGroupExpenseRequest){
    CreateGroupExpenseParam createGroupExpenseParam = new CreateGroupExpenseParam();
    createGroupExpenseParam.setName(createGroupExpenseRequest.getName());

    Function<GroupExpenseMemberRequest, GroupExpenseMemberParam> convertRequestToParam = (requestMember) -> {
      GroupExpenseMemberParam groupExpenseMemberParam =  new GroupExpenseMemberParam();
      // TODO: handle email and phone for new users
      groupExpenseMemberParam.setId(requestMember.getUserId());
      return groupExpenseMemberParam;
    };

    createGroupExpenseParam.setMembers(createGroupExpenseRequest.getExpenseGroupMember()
      .stream().map(convertRequestToParam).collect(Collectors.<GroupExpenseMemberParam>toList()));
    return createGroupExpenseParam;
  }
}
