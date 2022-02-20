package br.com.dnsouza.exemplelottery.providers.lottery.dto.us;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PowerBallWinningNumbersDTO implements Serializable{
  private static final long serialVersionUID = 8009623336865970903L;
  @JsonProperty("field_winning_numbers")
  private String fieldWinningNumbers;
  @JsonProperty("field_multiplier")
  private String fieldMultiplier;
  @JsonProperty("field_draw_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  private LocalDate fieldDrawDate;
  
  public String getFieldWinningNumbers() {
    return fieldWinningNumbers;
  }
  
  public void setFieldWinningNumbers(String fieldWinningNumbers) {
    this.fieldWinningNumbers = fieldWinningNumbers;
  }
  
  public String getFieldMultiplier() {
    return fieldMultiplier;
  }
  
  public void setFieldMultiplier(String fieldMultiplier) {
    this.fieldMultiplier = fieldMultiplier;
  }
  
  public LocalDate getFieldDrawDate() {
    return fieldDrawDate;
  }
  
  public void setFieldDrawDate(LocalDate fieldDrawDate) {
    this.fieldDrawDate = fieldDrawDate;
  }
  
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
