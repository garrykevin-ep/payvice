package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.expense.ExpenseDebtDto;
import com.garrykevin.payvice.groupexpense.model.ExpenseDebt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseDebtMapper {

  @Mapping(source = "lender.id",target = "lenderUserId")
  @Mapping(source = "borrower.id",target = "borrowerUserId")
  ExpenseDebtDto modelToDto(ExpenseDebt expenseDebt);
}
