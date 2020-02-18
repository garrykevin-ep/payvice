package com.garrykevin.payvice.groupexpense.model;

import com.garrykevin.payvice.user.model.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class GroupExpensePayment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToOne
  User from;

  @OneToOne
  User to;

  Double amount;
}
