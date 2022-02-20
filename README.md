# exemple-lottery
Projeto de exemplo para os estagiários, com objetivo de demonstrar o funcionamento de encapsulamento. O projeto consulta o resultado dos jogos da mega-sena e Powerball (Jogo das loteria americanas, equivalente a mega-sena)  


#Exemplo de requisição <br>
curl --location --request POST 'http://localhost:8080/games/check?numbersBet=21 38 50 53 56 59' \
--header 'Accept-Language: en-US' \
--data-raw ''
