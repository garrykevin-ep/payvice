package com.garrykevin.payvice.controllers;

import com.garrykevin.payvice.expense.CreateExpenseParam;
import com.garrykevin.payvice.expense.ExpenseDto;
import com.garrykevin.payvice.expense.ExpenseDtoService;
import com.garrykevin.payvice.expense.ExpenseParticipantParam;
import com.garrykevin.payvice.expense.ExpensePayerParam;
import com.garrykevin.payvice.groupexpense.model.Expense;
import com.garrykevin.payvice.groupexpense.model.ExpensePayer;
import com.garrykevin.payvice.groupexpense.repository.ExpenseRepository;
import com.garrykevin.payvice.request.expense.CreateExpenseRequest;
import com.garrykevin.payvice.request.expense.ExpenseParticipantRequest;
import com.garrykevin.payvice.request.expense.ExpensePayerRequest;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

  @Autowired
  ExpenseDtoService expenseDtoService;

  @PostMapping
  public ExpenseDto createExpense(@Valid @RequestBody  CreateExpenseRequest createExpenseRequest){
//    convertCreateExpenseRequestToParam(createExpenseRequest);
//    expenseDtoService.create(convertCreateExpenseRequestToParam(createExpenseRequest));
    return null;

  }

  private CreateExpenseParam convertCreateExpenseRequestToParam(CreateExpenseRequest createExpenseRequest){
    CreateExpenseParam createExpenseParam = new CreateExpenseParam();
    createExpenseParam.setGroupId(createExpenseRequest.getGroupId());
    createExpenseParam.setName(createExpenseRequest.getName());
    createExpenseParam.setAmount(createExpenseRequest.getAmount());
    createExpenseParam.setShareType(createExpenseRequest.getShareType());
//    createExpenseParam.setExpenditureDate(createExpenseRequest.getExpenditureDate());
    Set<ExpenseParticipantParam> expenseParticipantParams = createExpenseRequest.getExpenseParticipants().stream().map(request -> {
      ExpenseParticipantParam param = new ExpenseParticipantParam();
      param.setUserId(request.getUserId());
      return param;
    }).collect(Collectors.toSet());
    createExpenseParam.setExpenseParticipants(expenseParticipantParams);


    Set<ExpensePayerParam> expensePayers = createExpenseRequest.getExpensePayers().stream()
    .map(request -> {
      ExpensePayerParam param = new ExpensePayerParam();
      param.setUserId(request.getUserId());
      param.setAmountPaid(request.getAmountPaid());
      return param;
    }).collect(Collectors.toSet());
    createExpenseParam.setExpensePayers(expensePayers);
    return createExpenseParam;
  }
}
