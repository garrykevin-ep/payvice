package com.garrykevin.payvice.exceptions;


public class BaseException extends RuntimeException {

  private ApplicationErrorCodes errorCode;

  public BaseException(ApplicationErrorCodes errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public BaseException(ApplicationErrorCodes errorCode, String message) {
    super(String.format("%s: %s", errorCode.getMessage(), message));
    this.errorCode = errorCode;
  }

  public BaseException(ApplicationErrorCodes errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
  }

  public ApplicationErrorCodes getErrorCode() {
    return errorCode;
  }
}
