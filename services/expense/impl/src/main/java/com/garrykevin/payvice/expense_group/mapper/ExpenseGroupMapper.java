package com.garrykevin.payvice.expense_group.mapper;

import com.garrykevin.payvice.expense_group.ExpenseGroupDto;
import com.garrykevin.payvice.expense_group.model.ExpenseGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseGroupMapper {

  ExpenseGroupDto modelToDto(final ExpenseGroup expenseGroup);

}
