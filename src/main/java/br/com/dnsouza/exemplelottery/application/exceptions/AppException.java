package br.com.dnsouza.exemplelottery.application.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{
  private static final long serialVersionUID = -2231180117351295207L;
  private HttpStatus statusCode = HttpStatus.BAD_REQUEST;
  
  public AppException() {
    super();
  }
  
  public AppException(String message, Throwable cause, HttpStatus statusCode) {
    super(message, cause);
    this.statusCode = statusCode;
  }
  
  public AppException(String message, Throwable cause) {
      super(message, cause);
  }
  
  public AppException(String message, HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode;
  }
  
  public AppException(String message) {
      super(message);
  }
  
  public AppException(Throwable cause, HttpStatus statusCode) {
    super(cause);
    this.statusCode = statusCode;
  }
  
  public AppException(Throwable cause) {
      super(cause);
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }
}
