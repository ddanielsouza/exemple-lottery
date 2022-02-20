package br.com.dnsouza.exemplelottery.providers.lottery.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LotteryGameDTO implements Serializable{
  private static final long serialVersionUID = 2027263699075349562L;
  
  private List<Short> numbers = new ArrayList<>();
  private LocalDate date;
  
  public LotteryGameDTO() {
    super();
  }

  public LotteryGameDTO(List<Short> numbers, LocalDate date) {
    super();
    this.numbers = numbers;
    this.date = date;
  }


 
  public List<Short> getNumbers() {
    return numbers;
  }
  
  public void setNumbers(List<Short> numbers) {
    this.numbers = numbers;
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  public void setDate(LocalDate date) {
    this.date = date;
  }
}
