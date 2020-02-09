package com.garrykevin.expense_group;

import com.garrykevin.payvice.groupexpense.impl.Debt;
import com.garrykevin.payvice.groupexpense.model.ExpenseDebt;
import com.garrykevin.payvice.groupexpense.model.ExpenseParticipant;
import com.garrykevin.payvice.groupexpense.model.ExpensePayer;
import com.garrykevin.payvice.user.model.User;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DebtCalTest {



  @Test
  /**
   * one user.payer and participant are same.
   * user 1 pays for user 1
   */
  public void samePayerAndParticipant(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    ExpensePayer expensePayer = createPayer(10d,1);
    expensePayers.add(expensePayer);
    ExpenseParticipant expenseParticipant = createParticipant(10d,1);
    expenseParticipants.add(expenseParticipant);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("0 debts",0,expenseDebts.size());
  }

  @Test
  /**
   * two users. payer and participant are different.
   * user 1 pays for user2
   */
  public void OnePayerAndOneParticipant_PayerNotInvolved(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    ExpensePayer expensePayer = createPayer(10d,1);
    expensePayers.add(expensePayer);
    ExpenseParticipant expenseParticipant = createParticipant(10d,2);
    expenseParticipants.add(expenseParticipant);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has only one debt",1, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream().collect(Collectors.toList());
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(0).getAmount());
  }

  //two user

  @Test
  /**
   *Two users. payer involved.
   * user 1 paid 20
   * user 1,2 has 10 as share amt
   * so user 2 has to give user 1 10
   */
  public void SamePayerAndParticipant_PayerInvolved(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer = createPayer(20d,1);
    expensePayers.add(expensePayer);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,1);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,2);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has only one debt",1, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream().collect(Collectors.toList());
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(0).getAmount());
  }


  @Test
  /**
   * Two users. both are payer.
   * expense amt is 20
   * user 1 paid 15 overpayer
   * user 2 paid 5 underpayer
   * user 1,2 has 10 as share amt
   * so user 2 has to give user 1 5units.
   */
  public void SamePayerAndParticipant(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(15d,1);
    ExpensePayer expensePayer2 = createPayer(5d,2);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,1);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,2);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has one debt",1, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream().collect(Collectors.toList());
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(5d), expenseDebtList.get(0).getAmount());
  }


  @Test
  /**
   * Two users. both are payer.
   * expense amt is 20
   * user 1 paid 10 share payer.
   * user 2 paid 10 share payer
   * user 1,2 has 10 as share amt
   * no debt
   */
  public void TwoUser_MultiplePayer_AllSharePayer(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(10d,1);
    ExpensePayer expensePayer2 = createPayer(10d,2);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,1);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,2);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has no debt",0, expenseDebts.size());
  }


  //three user tests

  @Test
  /**
   * user 1 paid 20
   * user 2,3 has to give user1 10unit.
   */
  public void ThreeUser_PayerNotInvolved(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer = createPayer(20d,1);
    expensePayers.add(expensePayer);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,2);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,3);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has two debt",2, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());
    // assert for user2 debt
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(0).getAmount());
    // assert for user3 debt
    Assert.assertEquals("Debt is from participant",3, expenseDebtList.get(1).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(1).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(1).getAmount());
  }



  @Test
  /**
   * user 1 paid 30 and is also involved.
   * user 2,3 has to give user1 10unit.
   */
  public void ThreeUser_PayerInvolved_OnePayer(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer = createPayer(30d,1);
    expensePayers.add(expensePayer);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,2);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,3);
    ExpenseParticipant expenseParticipant3 = createParticipant(10d,1);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    expenseParticipants.add(expenseParticipant3);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has two debt",2, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());
    // assert for user2 debt
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(0).getAmount());
    // assert for user3 debt
    Assert.assertEquals("Debt is from participant",3, expenseDebtList.get(1).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(1).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(1).getAmount());
  }


  @Test
  /**
   * user 1 paid 10. share payer
   * user 2 paid 20. over payer
   * user 3 has to give user 2 10units
   */
  public void ThreeUser_MultiplePayer_ShareAndOverPayer(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(10d,1);
    ExpensePayer expensePayer2 = createPayer(20d,2);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,2);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,3);
    ExpenseParticipant expenseParticipant3 = createParticipant(10d,1);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    expenseParticipants.add(expenseParticipant3);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has one debt",1, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());
    // assert for user3 debt
    Assert.assertEquals("Debt is from participant",3, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",2, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(10d), expenseDebtList.get(0).getAmount());
  }

  @Test
  /**
   * user 1 paid 10. share payer
   * user 2 paid 7. under payer
   * user 3 paid 13. over payer
   * user 2 has to give user 3 3units
   */
  public void ThreeUser_MultiplePayer_ShareAndOverAndUnderPayer(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(10d,1);
    ExpensePayer expensePayer2 = createPayer(7d,2);
    ExpensePayer expensePayer3 = createPayer(13d,3);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    expensePayers.add(expensePayer3);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,2);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,3);
    ExpenseParticipant expenseParticipant3 = createParticipant(10d,1);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    expenseParticipants.add(expenseParticipant3);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has one debt",1, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());
    // assert for user3 debt
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",3, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(3d), expenseDebtList.get(0).getAmount());
  }

  @Test
  /**
   * user 1 paid 5. under payer
   * user 2 paid 5. under payer
   * user 3 paid 20. over payer
   * user 1,2 has to give user 3 5units.
   */
  public void ThreeUser_MultiplePayer_2UnderAnd1Over(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(5d,1);
    ExpensePayer expensePayer2 = createPayer(5d,2);
    ExpensePayer expensePayer3 = createPayer(20d,3);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    expensePayers.add(expensePayer3);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,2);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,3);
    ExpenseParticipant expenseParticipant3 = createParticipant(10d,1);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    expenseParticipants.add(expenseParticipant3);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has two debt",2, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());
    // assert for user1 debt
    Assert.assertEquals("Debt is from participant",1, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",3, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(5d), expenseDebtList.get(0).getAmount());
    // assert for user2 debt
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(1).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",3, expenseDebtList.get(1).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(5d), expenseDebtList.get(1).getAmount());
  }

  @Test
  /**
   * tests multiple debts for same user
   * user 1 paid 15. over payer
   * user 2 paid 5. under payer
   * user 3 paid 15. over payer
   * user 2 has to give user 1,3 5rs.
   */
  public void ThreeUser_MultiplePayer_2OverAndUnderPayer(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(15d,1);
    ExpensePayer expensePayer2 = createPayer(5d,2);
    ExpensePayer expensePayer3 = createPayer(15d,3);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    expensePayers.add(expensePayer3);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,1);
    ExpenseParticipant expenseParticipant2 = createParticipant(15d,2);
    ExpenseParticipant expenseParticipant3 = createParticipant(10d,3);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    expenseParticipants.add(expenseParticipant3);
    //start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    //assert
    Assert.assertEquals("Has two debt",2, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());
    // assert to user1 debt
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(0).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",1, expenseDebtList.get(0).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(5d), expenseDebtList.get(0).getAmount());
    // assert to user3 debt
    Assert.assertEquals("Debt is from participant",2, expenseDebtList.get(1).getBorrower().getId());
    Assert.assertEquals("Debt has to be paid to payer",3, expenseDebtList.get(1).getLender().getId());
    Assert.assertEquals("Debt amount what the payer paid",Double.valueOf(5d), expenseDebtList.get(1).getAmount());
  }

  @Test
  /**
   * one user will get 2 debts another will get 1debt
   * user 1 12 over payer
   * user 2 28 over payer
   * user 3 0 paid under payer
   * user 4 0 paid under payer
   */
  public void FourUser(){
    // setup
    Set<ExpenseParticipant> expenseParticipants = new HashSet<ExpenseParticipant>();
    Set<ExpensePayer> expensePayers = new HashSet<ExpensePayer>();
    // setup payer
    ExpensePayer expensePayer1 = createPayer(12d,1);
    ExpensePayer expensePayer2 = createPayer(28d,2);
    expensePayers.add(expensePayer1);
    expensePayers.add(expensePayer2);
    // setup participant
    ExpenseParticipant expenseParticipant1 = createParticipant(10d,1);
    ExpenseParticipant expenseParticipant2 = createParticipant(10d,2);
    ExpenseParticipant expenseParticipant3 = createParticipant(10d,3);
    ExpenseParticipant expenseParticipant4 = createParticipant(10d,4);
    expenseParticipants.add(expenseParticipant1);
    expenseParticipants.add(expenseParticipant2);
    expenseParticipants.add(expenseParticipant3);
    expenseParticipants.add(expenseParticipant4);
    // start test
    Debt debt = new Debt(expensePayers,expenseParticipants);
    Set<ExpenseDebt> expenseDebts = debt.calculateDebt();
    // assert
    Assert.assertEquals("Has two debt",3, expenseDebts.size());
    List<ExpenseDebt> expenseDebtList = expenseDebts.stream()
      .sorted((e1,e2)->  e1.getBorrower().getId() > e2.getBorrower().getId() ? 1 : -1)
      .collect(Collectors.toList());

    // find user with 2 debts
    Long userWith2Debt = expenseDebtList
      .stream()
      .map(expenseDebt -> expenseDebt.getBorrower().getId())
      .collect(Collectors.groupingBy(Function.identity(),
        Collectors.counting()))
      .entrySet()
      .stream()
      .filter( map -> map.getValue() == 2 )
      .findFirst().get().getKey();

    Double sumUserWith2Debt = expenseDebtList
      .stream()
      .filter(expenseDebt -> expenseDebt.getBorrower().getId() == userWith2Debt )
      .map(ExpenseDebt::getAmount)
      .collect(Collectors.summingDouble(f -> f));

    Assert.assertEquals("debt of the user has to be equal to 10",Double.valueOf(10d),
      sumUserWith2Debt);

  }

  private ExpenseParticipant createParticipant(Double amountToBePaid,long userId){
    ExpenseParticipant expenseParticipant = new ExpenseParticipant();
    expenseParticipant.setShareAmount(amountToBePaid);
    User user = new User();
    user.setId(userId);
    expenseParticipant.setUser(user);
    return expenseParticipant;
  }

  private ExpensePayer createPayer(Double amountPaid,long userId){
    ExpensePayer expensePayer = new ExpensePayer();
    User user = new User();
    user.setId(userId);
    expensePayer.setUser(user);
    expensePayer.setAmountPaid(amountPaid);
    return expensePayer;
  }

}
