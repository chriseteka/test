package me.chrisworks.domain.dtos;

import java.time.LocalDateTime;

public class ExceptionResponse {

  private String message;
  private String timeStamp;

  public ExceptionResponse() {}

  public ExceptionResponse(String message) {
    this.message = message;
    this.timeStamp = LocalDateTime.now().toString();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp.toString();
  }
}
