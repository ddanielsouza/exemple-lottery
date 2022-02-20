package br.com.dnsouza.exemplelottery.providers.messages.implementations;

import br.com.dnsouza.exemplelottery.providers.messages.IMessagesProvider;

public class MessagesBR implements IMessagesProvider{
  @Override
  public String internalServerError() {
    return "Erro interno no servidor";
  }

  @Override
  public String numbersBetIsInvalid() {
    return "Numeros da mega-sena está invalido";
  }

  @Override
  public String isNotNull() {
    return "Campo %s não pode ser nulo";
  }

  @Override
  public String searchNumberInLotteryFailed() {
    return "Falha ao buscar numeros da mega-sena no site da caixa";
  }
}
