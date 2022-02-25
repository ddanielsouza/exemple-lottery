package br.com.dnsouza.exemplelottery.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dnsouza.exemplelottery.dto.game.MatchResultLotteryDTO;
import br.com.dnsouza.exemplelottery.service.GameService;
import br.com.dnsouza.exemplelottery.util.ECountryAbbreviations;

@RestController
@RequestMapping("games")
public class GameController {
  @Autowired 
  private GameService gameService;
  
  /**
   * Verifica o resultado de uma partida da mega-sena ou Powerball (Jogo de loteria dos EUA equivalente a mega-sena)
   * @return
   * @throws Exception 
   */
  @RequestMapping(value = "check")
  public ResponseEntity<MatchResultLotteryDTO> checkMatch(
      @RequestParam("numbersBet") String numbersBet,
      @RequestParam(value = "country", defaultValue = "BR") String country){
    final MatchResultLotteryDTO response = this.gameService.checkMatch(numbersBet, country);
    return new ResponseEntity<>(response, HttpStatus.OK); 
  }
}
