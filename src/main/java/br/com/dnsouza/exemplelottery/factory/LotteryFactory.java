package br.com.dnsouza.exemplelottery.factory;

import br.com.dnsouza.exemplelottery.providers.lottery.ILotteryProvider;
import br.com.dnsouza.exemplelottery.providers.lottery.implementations.LotteryBR;
import br.com.dnsouza.exemplelottery.providers.lottery.implementations.LotteryUS;
import br.com.dnsouza.exemplelottery.util.ECountryAbbreviations;

public class LotteryFactory {
  
  public static ILotteryProvider getInstance(String countryStr) {
    final ECountryAbbreviations country = ECountryAbbreviations
        .fromString(countryStr);
    return LotteryFactory.getInstance(country);
  }
  
  public static ILotteryProvider getInstance(ECountryAbbreviations country) {

    
    switch (country) {
      case BRAZIL:
        return new LotteryBR();
      case UNITED_STATES:
        return new LotteryUS();
      default:
        return new LotteryUS();
    }
  }
}
