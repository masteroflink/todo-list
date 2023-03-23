package com.example.restservice.utils;

public class ErrorResponse {
  private String message;

  public ErrorResponse(String message) {
    super();

    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return this.message;
  }
}
