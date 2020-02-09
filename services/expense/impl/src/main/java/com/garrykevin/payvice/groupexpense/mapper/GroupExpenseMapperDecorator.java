package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.user.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class GroupExpenseMapperDecorator implements GroupExpenseMapper {

  @Autowired
  @Qualifier("delegate")
  private GroupExpenseMapper delegate;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ExpenseMapper expenseMapper;

  @Override
  public GroupExpenseDto modelToDto(final GroupExpense groupExpense){
    GroupExpenseDto groupExpenseDto = delegate.modelToDto(groupExpense);
    groupExpenseDto.setMembers(userMapper.modelToDtos(groupExpense.getMembers()));
    groupExpenseDto.setExpenses(expenseMapper.modelToDtos(groupExpense.getExpenses()));
    return groupExpenseDto;
  }


  @Override
  public List<GroupExpenseDto> modelsToDtos(final List<GroupExpense> groupExpenses){
    List<GroupExpenseDto> groupExpenseDtos = groupExpenses.stream()
      .map(groupExpense -> this.modelToDto(groupExpense)).collect(Collectors.toList());
    return groupExpenseDtos;
  }

}
