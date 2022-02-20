package br.com.dnsouza.exemplelottery.providers.messages;

public interface IMessagesProvider {
    public String internalServerError();

    public String numbersBetIsInvalid();

    public String isNotNull();

    public String searchNumberInLotteryFailed();
}
