package com.garrykevin.payvice.groupexpense.impl;

import com.garrykevin.payvice.common.constants.PayviceConstants;
import com.garrykevin.payvice.exceptions.ApplicationErrorCodes;
import com.garrykevin.payvice.exceptions.UserNotFoundException;
import com.garrykevin.payvice.expense.CreateExpenseParam;
import com.garrykevin.payvice.expense.ExpenseDto;
import com.garrykevin.payvice.expense.ExpenseDtoService;
import com.garrykevin.payvice.expense.ExpenseParticipantParam;
import com.garrykevin.payvice.expense.ExpensePayerParam;
import com.garrykevin.payvice.groupexpense.model.Expense;
import com.garrykevin.payvice.groupexpense.model.ExpenseParticipant;
import com.garrykevin.payvice.groupexpense.model.ExpensePayer;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.groupexpense.repository.ExpenseRepository;
import com.garrykevin.payvice.groupexpense.repository.GroupExpenseRepository;
import com.garrykevin.payvice.user.model.User;
import com.garrykevin.payvice.user.repository.UserRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseDtoServiceImpl implements ExpenseDtoService {

  @Autowired
  ExpenseRepository expenseRepository;

  @Autowired
  GroupExpenseRepository groupExpenseRepository;


  @Autowired
  UserRepository userRepository;

  @Override
  public ExpenseDto create(CreateExpenseParam createExpenseParam) {
    Optional<GroupExpense> groupExpense = groupExpenseRepository.findById(createExpenseParam.getGroupId());
    Expense expense = new Expense();
    expense.setName(createExpenseParam.getName());
    expense.setAmount(createExpenseParam.getAmount());
    expense.setGroupExpense(groupExpense.get());

    // get expenseParticipants from repo
    Set<User> expenseParticipants = userRepository.findByIdIn(
      createExpenseParam.getExpenseParticipants().stream()
        .map(ExpenseParticipantParam::getUserId).collect(Collectors.toSet())
    );

    // creating expense participants
    expense.setExpenseParticipants(
      createExpenseParam.getExpenseParticipants()
        .stream()
        .map(param -> {
          ExpenseParticipant expenseParticipant = new ExpenseParticipant();
          expenseParticipant.setUser(
            // iterate users and create user
            expenseParticipants.stream()
              .filter(user -> param.getUserId() == user.getId())
              .findAny()
              .orElseThrow(() -> new UserNotFoundException(ApplicationErrorCodes.USER_NOT_FOUND))
          );

          // set participant share
          if ( createExpenseParam.getShareType() == PayviceConstants.EQUAL_SHARE ) {
            expenseParticipant.setShareAmount(createExpenseParam.getAmount() / (double) expenseParticipants.size() );

          }
          return expenseParticipant;
        })
        .collect(Collectors.toSet())
    );

    // get expense payers from repo
    Set<User> expenseUsersPayers = userRepository.findByIdIn(
      createExpenseParam.getExpensePayers()
        .stream()
        .map(ExpensePayerParam::getUserId)
        .collect(Collectors.toSet())
    );

    // creating expense payers
    Set<ExpensePayer> expensePayers = createExpenseParam.getExpensePayers()
      .stream()
      .map(param -> {
        ExpensePayer expensePayer = new ExpensePayer();
        expensePayer.setAmountPaid(param.getAmountPaid());
        expensePayer.setUser(
          expenseUsersPayers
          .stream()
          .filter(user -> user.getId() == param.getUserId())
          .findAny()
          .orElseThrow(()-> new UserNotFoundException(ApplicationErrorCodes.USER_NOT_FOUND))
        );
        return expensePayer;
      })
      .collect(Collectors.toSet());

    expense.setExpensePayer(expensePayers);

    expenseRepository.save(expense);
    return null;
  }
}
