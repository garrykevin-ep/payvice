package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.user.mapper.UserMapper;
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

  @Override
  public GroupExpenseDto modelToDto(final GroupExpense groupExpense){
    GroupExpenseDto groupExpenseDto = delegate.modelToDto(groupExpense);
    groupExpenseDto.setUsers(userMapper.modelToDtos(groupExpense.getMembers()));
    return groupExpenseDto;
  }

}
