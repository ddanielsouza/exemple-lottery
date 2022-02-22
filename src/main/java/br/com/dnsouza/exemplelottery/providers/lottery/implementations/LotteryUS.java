package br.com.dnsouza.exemplelottery.providers.lottery.implementations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.dnsouza.exemplelottery.application.exceptions.AppException;
import br.com.dnsouza.exemplelottery.providers.lottery.ILotteryProvider;
import br.com.dnsouza.exemplelottery.providers.lottery.dto.LotteryGameDTO;
import br.com.dnsouza.exemplelottery.providers.lottery.dto.us.PowerBallWinningNumbersDTO;
import br.com.dnsouza.exemplelottery.util.RestUitls;

@Component
public class LotteryUS implements ILotteryProvider {
  private final String urlBase = "https://www.powerball.com";
  private static final String MSG_NUMBERS_NOT_FOUND = "Erro on request powerball server";
  
  @Override
  public LotteryGameDTO lastDraw() {
    final String uri = urlBase.concat("/api/v1/numbers/powerball/recent?_format=json");
    
    ParameterizedTypeReference<List<PowerBallWinningNumbersDTO>> responseType = 
        new ParameterizedTypeReference<List<PowerBallWinningNumbersDTO>>() {};
        
    List<PowerBallWinningNumbersDTO> gamesWinnings = RestUitls.request(new HashMap<>(), HttpMethod.GET, uri, responseType);
    
    
    if(gamesWinnings.size() >= 1) {
      final LotteryGameDTO game = new LotteryGameDTO();
      final PowerBallWinningNumbersDTO gameWinning = gamesWinnings.get(0);
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
