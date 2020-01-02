package com.garrykevin.payvice.response;


import com.garrykevin.payvice.exceptions.BaseException;
import com.garrykevin.payvice.exceptions.GroupExpenseMemberInvalid;
import com.garrykevin.payvice.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionAdvice class.
 * This is used to handle exceptions and send out proper error responses
 * and http codes.
 */
@RestControllerAdvice
public class ExceptionAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    GroupExpenseMemberInvalid.class
  })
  public ErrorResponse handleBadRequestExceptions(BaseException ex) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
      ex.getErrorCode().getMessage(), ex.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
//  @ExceptionHandler({})
  public ErrorResponse handleNotFoundExceptions(BaseException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getErrorCode().getMessage(),
      ex.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  @ExceptionHandler()
  public ErrorResponse handleInternalServerErrorExceptions(BaseException ex) {
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
      ex.getErrorCode().getMessage(), ex.getMessage());
  }

}
