package br.com.dnsouza.exemplelottery.util;

import java.util.Arrays;

public enum ECountryAbbreviations {
  BRAZIL("BR"),
  UNITED_STATES("US");
  
  private  String abbreviations;
  
  ECountryAbbreviations(String abbreviations){
    this.abbreviations = abbreviations;
  }

  public String getAbbreviations() {
    return abbreviations;
  }

  public void setAbbreviations(String abbreviations) {
    this.abbreviations = abbreviations;
  }
  
  public static ECountryAbbreviations fromString(String abbreviations) {
   return  Arrays.asList(ECountryAbbreviations.values())
       .stream()
       .filter(country -> country.getAbbreviations().equalsIgnoreCase(abbreviations))
       .findFirst().orElse(null);
  }
  
}