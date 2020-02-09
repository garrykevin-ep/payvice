package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.groupexpense.GroupExpenseDto;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.user.mapper.UserMapper;
import java.util.List;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class,ExpenseMapper.class})
//@DecoratedWith(GroupExpenseMapperDecorator.class)
public interface GroupExpenseMapper {

//  @Mapping(target = "me", source = "users")
  GroupExpenseDto modelToDto(final GroupExpense groupExpense);

  List<GroupExpenseDto> modelsToDtos(final List<GroupExpense> groupExpenses);

}
