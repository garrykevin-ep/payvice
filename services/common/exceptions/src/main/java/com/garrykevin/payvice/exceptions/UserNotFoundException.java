package com.garrykevin.payvice.exceptions;

public class UserNotFoundException extends  BaseException {

  public UserNotFoundException(ApplicationErrorCodes errorCode) {
    super(errorCode);
  }

  public UserNotFoundException(ApplicationErrorCodes errorCode, String message) {
    super(errorCode, message);
  }

  public UserNotFoundException(ApplicationErrorCodes errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
