package br.com.dnsouza.exemplelottery.providers.lottery;

import br.com.dnsouza.exemplelottery.providers.lottery.dto.LotteryGameDTO;

public interface ILotteryProvider {
  public LotteryGameDTO lastDraw();
}
