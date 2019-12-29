package com.garrykevin.payvice.expense_group.mapper;

import com.garrykevin.payvice.expense_group.ExpenseGroupDto;
import com.garrykevin.payvice.expense_group.model.ExpenseGroup;
import com.garrykevin.payvice.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class ExpenseGroupMapperDecorator implements ExpenseGroupMapper {

  @Autowired
  @Qualifier("delegate")
  private ExpenseGroupMapper delegate;

  @Autowired
  private UserMapper userMapper;

  @Override
  public ExpenseGroupDto modelToDto(final ExpenseGroup expenseGroup){
    ExpenseGroupDto expenseGroupDto = delegate.modelToDto(expenseGroup);
    expenseGroupDto.setUsers(userMapper.modelToDtos(expenseGroup.getMembers()));
    return expenseGroupDto;
  }

}
