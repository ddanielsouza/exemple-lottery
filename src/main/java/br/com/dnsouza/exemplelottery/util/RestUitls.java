package br.com.dnsouza.exemplelottery.util;

import java.util.HashMap;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import br.com.dnsouza.exemplelottery.application.exceptions.AppException;

public class RestUitls {
  
  public static <T> T request ( HashMap<String, String> paramentros, 
      HttpMethod methodo, String url, 
      ParameterizedTypeReference<T> responseType) {
    
    return RestUitls.request(paramentros, methodo, url, responseType, "application/json");
  }
  
  public static <T> T request ( HashMap<String, String> paramentros, 
      HttpMethod methodo, String url, 
      ParameterizedTypeReference<T> responseType, String contentType) {
    
    CloseableHttpClient httpClient = HttpClientBuilder
        .create()
        .build();
    
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    RestTemplate restTemplate = new RestTemplate(factory);
    
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", contentType);
    
    HttpEntity<?> httpEntity = new HttpEntity<Object>(header);
    
    try {
      ResponseEntity<T> response = restTemplate.exchange(url, methodo, httpEntity, responseType,  paramentros);
      
      return response.getBody();
    }catch (Exception e) {
     e.printStackTrace();
     throw new AppException(e);
    } 
  }
}
