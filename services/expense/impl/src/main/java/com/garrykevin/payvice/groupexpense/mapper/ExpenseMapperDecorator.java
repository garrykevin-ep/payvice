package com.garrykevin.payvice.groupexpense.mapper;

import com.garrykevin.payvice.expense.ExpenseDto;
import com.garrykevin.payvice.expense.ExpenseParticipantDto;
import com.garrykevin.payvice.groupexpense.model.Expense;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class ExpenseMapperDecorator implements ExpenseMapper {

  @Autowired
  @Qualifier("delegate")
  ExpenseMapper delegate;

//  @Autowired
//  ExpenseParticipantMapper expenseParticipantMapper;


  @Override
  public ExpenseDto modelToDto(Expense expense) {
    ExpenseDto expenseDto = delegate.modelToDto(expense);
    // set participants
//    List<ExpenseParticipantDto> expenseParticipantDtos =
//      expense.getExpenseParticipants().stream()
//      .map(expenseParticipant -> expenseParticipantMapper.modelToDto(expenseParticipant))
//      .collect(Collectors.toList());
//    expenseDto.setExpenseParticipants(expenseParticipantDtos);
    return expenseDto;
  }

  @Override
  public List<ExpenseDto> modelToDtos(List<Expense> expenses) {
    if (expenses == null) {
      return null;
    }
    return expenses
      .stream()
      .map(expense -> this.modelToDto(expense))
      .collect(Collectors.toList());
  }
}
