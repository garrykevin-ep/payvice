package com.garrykevin.payvice.expense_group.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ExpenseParticipants {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private Integer expenseId;

  @Column
  private Float oweAmount;

  @Column
  private Integer userId;

}
