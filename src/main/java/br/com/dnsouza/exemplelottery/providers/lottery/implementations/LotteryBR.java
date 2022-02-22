package br.com.dnsouza.exemplelottery.providers.lottery.implementations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import br.com.dnsouza.exemplelottery.application.exceptions.AppException;
import br.com.dnsouza.exemplelottery.providers.lottery.ILotteryProvider;
import br.com.dnsouza.exemplelottery.providers.lottery.dto.LotteryGameDTO;
import br.com.dnsouza.exemplelottery.util.RegexUtil;
import br.com.dnsouza.exemplelottery.util.RestUitls;

@Component
public class LotteryBR implements ILotteryProvider {
  private final String urlBase = "http://loterias.caixa.gov.br";
  private static final String MSG_NUMBERS_NOT_FOUND = "Não foi possivel consultar os numeros da mega-sena";
  
  @Override
  public LotteryGameDTO lastDraw() {
    final String uri = urlBase.concat("/wps/portal/loterias");
    final String regexNumber = "<ul class=\"resultado-loteria mega-sena\">(?<content>.*?)<\\/ul>";
    final String regexDate = "class=\"logo-mega-sena\">.*?(?<date>\\d{2}\\/\\d{2}\\/\\d{4})";
    final LotteryGameDTO game = new LotteryGameDTO();
    
    ParameterizedTypeReference<String> responseType = 
        new ParameterizedTypeReference<String>() {};
    
    final String response = RestUitls.request(new  HashMap<>(), 
      HttpMethod.GET, uri, responseType, "text/html;charset=UTF-8");
    
    final String pageHTML = response.replaceAll("(\\n|\\r|\\t)*", "");
    
    final String contentNumbers = RegexUtil.findOne(pageHTML, regexNumber);
   
    if(contentNumbers == null || contentNumbers.trim().isEmpty())
      throw new AppException(MSG_NUMBERS_NOT_FOUND);
    
    List<Short> numbers = Arrays.asList(contentNumbers.split("<li>"))
        .stream()
        .filter(number -> number != null && !number.isEmpty())
        .map(number -> Short.valueOf(number.replaceAll("\\D", "")))
        .collect(Collectors.toList());
    
    game.setNumbers(numbers);
    
    String datePtBr =  RegexUtil.findOne(pageHTML, regexDate);
    
    if(datePtBr != null && datePtBr.trim().matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {
      LocalDate date = LocalDate.parse(datePtBr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      game.setDate(date);
    }
    
    return game;
  }
  
}
