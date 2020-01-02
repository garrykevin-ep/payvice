package com.garrykevin.payvice.expense;

import java.time.Instant;
import lombok.Data;

@Data
public class ExpenseParam {

  private String name;

  private Double amount;

  private Integer shareType;

  private Instant expenditureDate;
}
