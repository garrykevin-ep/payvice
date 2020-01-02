package com.garrykevin.payvice.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  private int status;
  @JsonProperty("error")
  private String errorCode;
  private String message;

}
