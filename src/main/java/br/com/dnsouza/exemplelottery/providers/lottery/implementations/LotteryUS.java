package br.com.dnsouza.exemplelottery.providers.lottery.implementations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.dnsouza.exemplelottery.application.exceptions.AppException;
import br.com.dnsouza.exemplelottery.providers.lottery.ILotteryProvider;
import br.com.dnsouza.exemplelottery.providers.lottery.dto.LotteryGameDTO;
import br.com.dnsouza.exemplelottery.providers.lottery.dto.us.PowerBallWinningNumbersDTO;

@Component
public class LotteryUS implements ILotteryProvider {
  private final String urlBase = "https://www.powerball.com";
  private static final String MSG_NUMBERS_NOT_FOUND = "Erro on request powerball server";
  
  @Autowired
  private RestTemplate restTemplate;
  
  @Override
  public LotteryGameDTO lastDraw() {
    final String uri = urlBase.concat("/api/v1/numbers/powerball/recent?_format=json");
    ResponseEntity<PowerBallWinningNumbersDTO[]> response = this.restTemplate
        .getForEntity(uri, PowerBallWinningNumbersDTO[].class);
    
    if(response.getStatusCode() != HttpStatus.OK) {
      throw new AppException(MSG_NUMBERS_NOT_FOUND);
    }
    
    final PowerBallWinningNumbersDTO[] gamesWinnings = response.getBody();
    
    if(gamesWinnings.length >= 1) {
      final LotteryGameDTO game = new LotteryGameDTO();
      final PowerBallWinningNumbersDTO gameWinning = gamesWinnings[0];
      List<Short> numbers = Arrays.asList(gameWinning.getFieldWinningNumbers().split(","))
          .stream()
          .filter(number -> number != null && !number.isEmpty())
          .map(number -> Short.valueOf(number.replaceAll("\\D", "")))
          .collect(Collectors.toList());
      
      game.setNumbers(numbers);
      game.setDate(gameWinning.getFieldDrawDate());
      
      return game;
    }
    
    return null;
  }
}
