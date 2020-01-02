package com.garrykevin.payvice.groupexpense.model;

import com.garrykevin.payvice.user.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ExpenseParticipant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

//  @ManyToOne
//  private Expense Expense;

  @Column
  private Double shareAmount;

  @OneToOne
  private User user;

}
