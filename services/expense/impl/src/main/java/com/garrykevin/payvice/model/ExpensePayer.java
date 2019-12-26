package com.garrykevin.payvice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ExpensePayer {

  @Id
  private Integer id;

  @Column
  private Integer expenseId;

  @Column
  private Integer userId;

  @Column
  private Integer shareAmount;

}
