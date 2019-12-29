package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.user.mapper.UserMapper;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
@DecoratedWith(GroupExpenseMapperDecorator.class)
public interface GroupExpenseMapper {

  GroupExpenseDto modelToDto(final GroupExpense groupExpense);


}
