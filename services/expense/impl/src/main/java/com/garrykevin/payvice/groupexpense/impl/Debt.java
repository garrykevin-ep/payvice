package com.garrykevin.payvice.groupexpense.impl;

import com.garrykevin.payvice.groupexpense.model.ExpenseDebt;
import com.garrykevin.payvice.groupexpense.model.ExpenseParticipant;
import com.garrykevin.payvice.groupexpense.model.ExpensePayer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;

@AllArgsConstructor
public class Debt {

  private Set<ExpensePayer> expensePayers;
  private Set<ExpenseParticipant> expenseParticipants;


  public void subractParticipantPaidAmount(){

    Iterator<ExpensePayer> expensePayerIterator = this.expensePayers.iterator();
    while(expensePayerIterator.hasNext()){
      ExpensePayer expensePayer = expensePayerIterator.next();
      // find if payer is also involved in the expense
      ExpenseParticipant expenseParticipant = this.expenseParticipants.stream()
        .filter(participant ->
          participant.getUser().getId() == expensePayer.getUser().getId() )
        .findAny().orElse(null);

      // then reduce amount what he paid
      if(expenseParticipant != null){

        // if true then overpaid && false for equal and underpayer
        Double AmountPaidForOthers = expensePayer.getAmountPaid() >
          expenseParticipant.getShareAmount() ?
          expensePayer.getAmountPaid() - expenseParticipant.getShareAmount() : 0;

        // subtract what he paid and setting amount he need to pay as debt
        // overpayer and equalpayer no amtToBePaid
        Double amountToBePaid = expensePayer.getAmountPaid() >=
          expenseParticipant.getShareAmount() ? 0 :
          expenseParticipant.getShareAmount() - expensePayer.getAmountPaid();

        expenseParticipant.setShareAmount(amountToBePaid);
        // rest amount that can use for debt assign
        expensePayer.setAmountPaid(AmountPaidForOthers);

        //remove participant as he has paid his share
        if (expenseParticipant.getShareAmount() == 0){
          this.expenseParticipants = this.expenseParticipants
            .stream()
            .filter(participant -> participant.getUser().getId() != expenseParticipant.getUser().getId())
            .collect(Collectors.toSet());
        }

        //remove underpayer/equalpayer to pay for others
        if (expensePayer.getAmountPaid() == 0){
          expensePayerIterator.remove();
        }
      }
    }
  }


  public Set<ExpenseDebt> calculateDebt(){

    this.subractParticipantPaidAmount();

    Set<ExpenseDebt> expenseDebts = new HashSet<ExpenseDebt>();

    for(ExpenseParticipant expenseParticipant: this.expenseParticipants){
      Iterator<ExpensePayer> expensePayerIterator = this.expensePayers.iterator();

      while( expensePayerIterator.hasNext() ){

        ExpensePayer expensePayer = expensePayerIterator.next();

        Double payerBalance = expensePayer.getAmountPaid() > expenseParticipant.getShareAmount()
          ? expensePayer.getAmountPaid() - expenseParticipant.getShareAmount()
          : 0;

        Double debtAmount = expensePayer.getAmountPaid() > expenseParticipant.getShareAmount()
        ? expenseParticipant.getShareAmount()
        : expensePayer.getAmountPaid();

        //subtract payer amount
        expensePayer.setAmountPaid(payerBalance);
        if(expensePayer.getAmountPaid() == 0){
          //remove payer
          expensePayerIterator.remove();
        }

        expenseParticipant.setShareAmount( expenseParticipant.getShareAmount() - debtAmount );
        ExpenseDebt expenseDebt = new ExpenseDebt();
        expenseDebt.setBorrower(expenseParticipant.getUser());
        expenseDebt.setLender(expensePayer.getUser());
        expenseDebt.setAmount(debtAmount);

        expenseDebts.add(expenseDebt);

        if( expenseParticipant.getShareAmount() == 0 ){
          break;
        }

      }
    }
    return expenseDebts;
  }



}
