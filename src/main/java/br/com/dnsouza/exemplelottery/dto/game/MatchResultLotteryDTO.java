package br.com.dnsouza.exemplelottery.dto.game;

import java.io.Serializable;
import java.util.List;

import br.com.dnsouza.exemplelottery.providers.lottery.dto.LotteryGameDTO;

public class MatchResultLotteryDTO implements Serializable{
  private static final long serialVersionUID = -4584415470316314646L;
  
  private boolean isWinner;
  private List<Short> guessedNumbers;
  private LotteryGameDTO game;
  
  public MatchResultLotteryDTO() {
    super();
  }

  public MatchResultLotteryDTO(boolean isWinner, List<Short> guessedNumbers, LotteryGameDTO game) {
    super();
    this.isWinner = isWinner;
    this.guessedNumbers = guessedNumbers;
    this.game = game;
  }

  public boolean isWinner() {
    return isWinner;
  }
  
  public void setWinner(boolean isWinner) {
    this.isWinner = isWinner;
  }
  
  public List<Short> getGuessedNumbers() {
    return guessedNumbers;
  }
  
  public void setGuessedNumbers(List<Short> guessedNumbers) {
    this.guessedNumbers = guessedNumbers;
  }
  
  public LotteryGameDTO getGame() {
    return game;
  }
  
  public void setGame(LotteryGameDTO game) {
    this.game = game;
  }
}
