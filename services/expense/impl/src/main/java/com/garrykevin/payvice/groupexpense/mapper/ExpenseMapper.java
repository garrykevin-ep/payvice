package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.expense.ExpenseDto;
import com.garrykevin.payvice.groupexpense.model.Expense;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExpenseParticipantMapper.class})
public interface ExpenseMapper {

  ExpenseDto modelToDto(final Expense expense);

  List<ExpenseDto> modelToDtos(final List<Expense> expenses);

}
