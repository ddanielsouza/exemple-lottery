package br.com.dnsouza.exemplelottery.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import br.com.dnsouza.exemplelottery.providers.lottery.ILotteryProvider;
import br.com.dnsouza.exemplelottery.providers.lottery.implementations.LotteryBR;
import br.com.dnsouza.exemplelottery.providers.lottery.implementations.LotteryUS;
import br.com.dnsouza.exemplelottery.providers.messages.IMessagesProvider;
import br.com.dnsouza.exemplelottery.providers.messages.implementations.MessagesBR;
import br.com.dnsouza.exemplelottery.providers.messages.implementations.MessagesUS;
import br.com.dnsouza.exemplelottery.util.ECountryAbbreviations;

@Configuration
public class ProvidersConfig {
  @Autowired
  private HttpServletRequest request;
  
  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public IMessagesProvider messages() {
    final ECountryAbbreviations country = ECountryAbbreviations
        .fromString(request.getLocale().getCountry());
    
    switch (country) {
      case BRAZIL:
        return new MessagesBR();
      case UNITED_STATES:
        return new MessagesUS();
      default:
        return new MessagesUS();
    }
  }
  
//  @Bean
//  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//  public ILotteryProvider lotteryProvider() {
//    final ECountryAbbreviations country = ECountryAbbreviations
//        .fromString(request.getLocale().getCountry());
//    
//    switch (country) {
//      case BRAZIL:
//        return new LotteryBR();
//      case UNITED_STATES:
//        return new LotteryUS();
//      default:
//        return new LotteryUS();
//    }
//  }
  
}
