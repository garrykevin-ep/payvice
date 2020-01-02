package com.garrykevin.payvice.request.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.Data;

@Data
public class ExpenseRequest {

  private String name;

  private Double amount;

  @JsonProperty("share_type")
  private Integer shareType;

  @JsonProperty("expenditure_date")
  private Instant expenditureDate;

}
