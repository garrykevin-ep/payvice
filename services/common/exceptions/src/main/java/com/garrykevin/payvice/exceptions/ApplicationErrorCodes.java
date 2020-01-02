package com.garrykevin.payvice.exceptions;

public enum ApplicationErrorCodes {

  EXPENSE_NOT_FOUND("Expense Not Found"),

  USER_NOT_FOUND("User Not Found");

  private final String message;

  ApplicationErrorCodes(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
