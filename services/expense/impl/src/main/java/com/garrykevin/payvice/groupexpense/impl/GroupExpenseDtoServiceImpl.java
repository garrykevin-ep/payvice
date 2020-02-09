package com.garrykevin.payvice.groupexpense.impl;

import com.garrykevin.payvice.groupexpense.CreateGroupExpenseParam;
import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.GroupExpenseDtoService;
import com.garrykevin.payvice.groupexpense.GroupExpenseMemberParam;
import com.garrykevin.payvice.groupexpense.mapper.GroupExpenseMapper;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.groupexpense.repository.GroupExpenseRepository;
import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.UserDtoService;
import com.garrykevin.payvice.user.mapper.UserMapper;
import com.garrykevin.payvice.user.model.User;
import com.garrykevin.payvice.user.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupExpenseDtoServiceImpl implements GroupExpenseDtoService {

  @Autowired
  UserDtoService userDtoService;

  @Autowired
  UserMapper userMapper;

  @Autowired
  GroupExpenseRepository groupExpenseRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  GroupExpenseMapper groupExpenseMapper;

  @Override
  public GroupExpenseDto create(CreateGroupExpenseParam createGroupExpenseParam) {
    Set<Long> userIds = createGroupExpenseParam.getMembers()
      .stream()
      .map(GroupExpenseMemberParam::getId)
      .collect(Collectors.toSet());
    Set<UserDto> userDtos = userDtoService.getByIds(userIds);
    //TODO: return does not exist
    GroupExpense groupExpense = new GroupExpense();
    groupExpense.setName(createGroupExpenseParam.getName());
    groupExpense.setMembers(userMapper.dtosToModels(userDtos));
    groupExpense = groupExpenseRepository.save(groupExpense);
    return groupExpenseMapper.modelToDto(groupExpense);
  }

  @Override
  public List<GroupExpenseDto> getAll(Long userId) {
    User user = userRepository.findById(userId).get();
    List<GroupExpense> groupExpenses = groupExpenseRepository.findByMembers(user);
    return groupExpenseMapper.modelsToDtos(groupExpenses);
  }

  @Override
  public GroupExpenseDto getById(Long groupExpenseId) {
    //TODO:(security) is member then return
    GroupExpense groupExpense = groupExpenseRepository.findById(groupExpenseId).orElse(null);
//    List<Expense> groupExpenseExpenses = groupExpense.getExpenses();
    return groupExpenseMapper.modelToDto(groupExpense);
  }
}
