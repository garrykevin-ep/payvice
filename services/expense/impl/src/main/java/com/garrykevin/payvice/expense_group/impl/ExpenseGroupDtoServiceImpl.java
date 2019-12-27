package com.garrykevin.payvice.expense_group.impl;

import com.garrykevin.payvice.expense_group.CreateExpenseGroupParam;
import com.garrykevin.payvice.expense_group.ExpenseGroupDto;
import com.garrykevin.payvice.expense_group.ExpenseGroupDtoService;
import com.garrykevin.payvice.expense_group.ExpenseGroupMemberParam;
import com.garrykevin.payvice.expense_group.mapper.ExpenseGroupMapper;
import com.garrykevin.payvice.expense_group.model.ExpenseGroup;
import com.garrykevin.payvice.expense_group.repository.ExpenseGroupRepository;
import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.UserDtoService;
import com.garrykevin.payvice.user.mapper.UserMapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseGroupDtoServiceImpl implements ExpenseGroupDtoService {

  @Autowired
  UserDtoService userDtoService;

  @Autowired
  UserMapper userMapper;

  @Autowired
  ExpenseGroupRepository expenseGroupRepository;

  @Autowired
  ExpenseGroupMapper expenseGroupMapper;

  @Override
  public ExpenseGroupDto create(CreateExpenseGroupParam createExpenseGroupParam) {
    Set<Long> userIds = createExpenseGroupParam.getMembers()
      .stream()
      .map(ExpenseGroupMemberParam::getId)
      .collect(Collectors.toSet());
    Set<UserDto> userDtos = userDtoService.getByIds(userIds);
    ExpenseGroup expenseGroup = new ExpenseGroup();
    expenseGroup.setName(createExpenseGroupParam.getName());
    expenseGroup.setMembers(userMapper.dtosToModels(userDtos));
    expenseGroup = expenseGroupRepository.save(expenseGroup);
    return expenseGroupMapper.modelToDto(expenseGroup);
  }
}
