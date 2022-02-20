package br.com.dnsouza.exemplelottery.providers.messages.implementations;

import br.com.dnsouza.exemplelottery.providers.messages.IMessagesProvider;

public class MessagesUS implements IMessagesProvider{
  @Override
  public String internalServerError() {
    return "Internal server error";
  }

  @Override
  public String numbersBetIsInvalid() {
    return "PowerBall numbers are invalid";
  }
  
  @Override
  public String isNotNull() {
    return "Field %s is required";
  }

  @Override
  public String searchNumberInLotteryFailed() {
    return "Request powerball server is failed";
  }
}
