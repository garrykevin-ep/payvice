package com.garrykevin.payvice.groupexpense.model;

import com.garrykevin.payvice.user.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ExpenseDebt {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  private User lender;

  @OneToOne
  private User borrower;

  @Column
  private Double amount;

}
