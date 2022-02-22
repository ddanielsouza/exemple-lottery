package br.com.dnsouza.exemplelottery.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.dnsouza.exemplelottery.application.exceptions.AppException;
import br.com.dnsouza.exemplelottery.dto.game.MatchResultLotteryDTO;
import br.com.dnsouza.exemplelottery.factory.PostalCodeFactory;
import br.com.dnsouza.exemplelottery.providers.lottery.ILotteryProvider;
import br.com.dnsouza.exemplelottery.providers.lottery.dto.LotteryGameDTO;
import br.com.dnsouza.exemplelottery.providers.messages.IMessagesProvider;
import br.com.dnsouza.exemplelottery.providers.postalcode.IPostalCodeProvider;

@Service
public class GameService {
  private static final Short MIN_BET_NUMBER = 1;
  private static final Short MAX_BET_NUMBER = 60;
  private static final Short MIN_BET_NUMBER_AMOUNT = 6;
  
  @Autowired
  private IMessagesProvider messages;
  
  @Autowired
  private ILotteryProvider lotteryProvider;
  
  public void teste() {
    final IPostalCodeProvider postalcode = PostalCodeFactory.getPostalCode("BR");
    postalcode.searchCity( );
  }
  
  public MatchResultLotteryDTO checkMatch(String strNumbersBet) {
    
    if(strNumbersBet == null) {
      throw new AppException( String.format(this.messages.isNotNull(), "numbersBet") );
    }
    
    List<Short> numbersBet = this.getNumbersFromString(strNumbersBet);
    
    if(numbersBet.size() < MIN_BET_NUMBER_AMOUNT) {
      throw new AppException( this.messages.numbersBetIsInvalid() );
    }
    
    final LotteryGameDTO game = lotteryProvider.lastDraw();
    
    if(game == null
        ||game.getNumbers() == null 
        || game.getNumbers().size() < MIN_BET_NUMBER_AMOUNT) {
      
      throw new AppException( this.messages.searchNumberInLotteryFailed(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    final List<Short> guessedNumbers = numbersBet.stream()
        .filter((numberBet) -> {
          final boolean hasNumber = game.getNumbers()
              .stream()
              .filter(number -> numberBet == number)
              .findFirst()
              .isPresent();
          
          return hasNumber;
        })
        .collect(Collectors.toList());
    
    boolean isWinner = guessedNumbers.size() == MIN_BET_NUMBER_AMOUNT;
    
    return new MatchResultLotteryDTO(isWinner, guessedNumbers, game);
  }
  
  public List<Short> getNumbersFromString(String strNumbersBet) {
    List<Short> numbersBet =  Arrays.asList(strNumbersBet.trim().split(" "))
        .stream()
        .filter(strNumber -> {
          if(!strNumber.matches("\\d{1,2}")) {
            return false;
          }
          
          final int number = Integer.valueOf(strNumber);
          return number >= MIN_BET_NUMBER && number <= MAX_BET_NUMBER;
        })
        .map(number -> Short.valueOf(number))
        .collect(Collectors.toList());
    
    return new ArrayList<Short>(new HashSet<Short>(numbersBet));
  }
}
