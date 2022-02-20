package br.com.dnsouza.exemplelottery.application.handlers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dnsouza.exemplelottery.application.exceptions.AppException;
import br.com.dnsouza.exemplelottery.dto.application.ExceptionDTO;
import br.com.dnsouza.exemplelottery.providers.messages.IMessagesProvider;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
  
  @Autowired
  private IMessagesProvider messages;
  
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
                                    Exception exception, 
                                    Object body, 
                                    HttpHeaders headers, 
                                    HttpStatus status, 
                                    WebRequest request) {
      
      final ExceptionDTO response = new ExceptionDTO(exception.getMessage());
      return new ResponseEntity<>(response, status);
  }
  @ExceptionHandler(value = { AppException.class })
  protected ResponseEntity<ExceptionDTO> appException(AppException ex) {
    final ExceptionDTO response = new ExceptionDTO(ex.getMessage());
    return new ResponseEntity<>(response, ex.getStatusCode());
  }
  
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ExceptionDTO> internalServerErro(Throwable ex) {
    final String msg = messages.internalServerError();
    final ExceptionDTO response = new ExceptionDTO(msg);
    
    boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
        getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
        
    if(isDebug) {
      ex.printStackTrace();
    }
        
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
