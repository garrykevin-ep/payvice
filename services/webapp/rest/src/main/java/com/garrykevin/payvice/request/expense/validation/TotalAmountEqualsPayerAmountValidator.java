package com.garrykevin.payvice.request.expense.validation;

import com.garrykevin.payvice.request.expense.CreateExpenseRequest;
import com.garrykevin.payvice.request.expense.ExpensePayerRequest;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TotalAmountEqualsPayerAmountValidator  implements ConstraintValidator<TotalAmountEqualsPayerAmount, CreateExpenseRequest> {

  @Override
  public void initialize(TotalAmountEqualsPayerAmount constraintAnnotation) {

  }

  @Override
  public boolean isValid(CreateExpenseRequest request, ConstraintValidatorContext context) {
    Double payersTotal = request.getExpensePayers().stream()
      .map(ExpensePayerRequest::getAmountPaid)
      .collect(Collectors.summingDouble(Double::doubleValue));
    return request.getAmount().equals(payersTotal);
  }
}
