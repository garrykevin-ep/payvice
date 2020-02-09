package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.expense.ExpensePayerDto;
import com.garrykevin.payvice.groupexpense.model.ExpensePayer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpensePayerMapper {

  @Mapping(source = "user.id", target = "userId")
  ExpensePayerDto modelToDto(ExpensePayer expensePayer);

}
