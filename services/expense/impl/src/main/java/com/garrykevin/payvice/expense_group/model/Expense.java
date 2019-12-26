package com.garrykevin.payvice.expense_group.model;


import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Expense {

  @Id
  private Long id;

  @Column
  private String name;

  @Column
  private Integer amount;

  @Column
  private Integer shareType;

  @Column
  private Instant spendDate;
}
