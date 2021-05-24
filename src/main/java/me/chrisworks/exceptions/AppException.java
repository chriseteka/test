package me.chrisworks.exceptions;

public class AppException extends RuntimeException {

  private final String message;

  public AppException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
