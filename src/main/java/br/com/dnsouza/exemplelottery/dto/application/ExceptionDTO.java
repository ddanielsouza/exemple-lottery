package br.com.dnsouza.exemplelottery.dto.application;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ExceptionDTO implements Serializable{
  private static final long serialVersionUID = -3006640953918110661L;
  
  private String message;
  private LocalDateTime timestamp = LocalDateTime.now();
  
  public ExceptionDTO() {
  }
  
  public ExceptionDTO(String message) {
    super();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public LocalDateTime getTimestamp() {
    return timestamp;
  }
  
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
