package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.expense.ExpenseParticipantDto;
import com.garrykevin.payvice.groupexpense.model.ExpenseParticipant;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
//@DecoratedWith(ExpenseParticipantMapperDecorator.class)
public interface ExpenseParticipantMapper {

  @Mapping(source = "user.id", target = "userId")
  ExpenseParticipantDto modelToDto(ExpenseParticipant expenseParticipant);
}
