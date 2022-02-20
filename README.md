# exemple-lottery
Projeto de exemplo para os estagiários, com objetivo de demonstrar o funcionamento de encapsulamento. O projeto consulta o resultado dos jogos da mega-sena e Powerball (Jogo das loteria americanas, equivalente a mega-sena)  


## Exemplo de requisição <br>
```sh
curl --location --request POST 'http://localhost:8080/games/check?numbersBet=21 38 50 53 56 59' \
--data-raw ''
```

Obs: Para testar o Powerball use a header 'Accept-Language: en-US' assim não precisar de alguma VPN para simular o acesso nos EUA 
