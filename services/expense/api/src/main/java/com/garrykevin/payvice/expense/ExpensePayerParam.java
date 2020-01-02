package com.garrykevin.payvice.expense;

import lombok.Data;

@Data
public class ExpensePayerParam {

  private Long userId;

  private Double amountPaid;
}
