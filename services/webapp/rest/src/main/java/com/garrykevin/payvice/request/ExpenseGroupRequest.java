package com.garrykevin.payvice.request;

import java.util.Set;
import lombok.Data;

@Data
public class ExpenseGroupRequest {

  Long Id;

  String name;

  Set<Long> userIds;

}
