package br.com.dnsouza.exemplelottery.factory;

import br.com.dnsouza.exemplelottery.providers.postalcode.IPostalCodeProvider;
import br.com.dnsouza.exemplelottery.providers.postalcode.implementations.PostalCodeBR;
import br.com.dnsouza.exemplelottery.providers.postalcode.implementations.PostalCodeCA;

public class PostalCodeFactory {
  public static IPostalCodeProvider getPostalCode(String country) {
    switch (country) {
      case "BR":
        return new PostalCodeBR();
      case "CA":
        return new PostalCodeCA();
      default:
        break;
    }
    return null;
  }
}
