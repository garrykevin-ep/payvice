package com.garrykevin.payvice.groupexpense.model;


import java.time.Instant;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String name;

  @Column
  private Double amount;

  @Column
  private Integer shareType;

  @Column
  private Instant expenditureDate;

  @ManyToOne
  GroupExpense groupExpense;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="expense_id")
  Set<ExpenseParticipant> expenseParticipants;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="expense_id")
  Set<ExpensePayer> expensePayer;
}
