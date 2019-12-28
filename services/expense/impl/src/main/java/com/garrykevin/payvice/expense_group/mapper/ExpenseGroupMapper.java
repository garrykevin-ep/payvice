package com.garrykevin.payvice.expense_group.mapper;

import com.garrykevin.payvice.expense_group.ExpenseGroupDto;
import com.garrykevin.payvice.expense_group.model.ExpenseGroup;
import com.garrykevin.payvice.user.mapper.UserMapper;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
@DecoratedWith(ExpenseGroupMapperDecorator.class)
public interface ExpenseGroupMapper {

  ExpenseGroupDto modelToDto(final ExpenseGroup expenseGroup);


}
