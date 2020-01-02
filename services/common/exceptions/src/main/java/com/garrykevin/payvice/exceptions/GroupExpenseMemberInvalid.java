package com.garrykevin.payvice.exceptions;

public class GroupExpenseMemberInvalid extends  BaseException {

  public GroupExpenseMemberInvalid(ApplicationErrorCodes errorCode) {
    super(errorCode);
  }

  public GroupExpenseMemberInvalid(ApplicationErrorCodes errorCode, String message) {
    super(errorCode, message);
  }

  public GroupExpenseMemberInvalid(ApplicationErrorCodes errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
