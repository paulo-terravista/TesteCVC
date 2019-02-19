# Teste de admissão na CVC!
Segue a resposta dos testes propostos em # Welcome to StackEdit!

## Resposta do Teste 1
	A url disponibilizada é a http://localhost:8070/cvc/booking/option/city, são passados os parâmetros abaixo e todos são obrigatórios:
	
| Parametro      | Formato                    | Obrigatorio |
|----------------|----------------------------|-------------|
| id             | Número Inteiro             | Sim         |
| checkin        | Data no formato yyyy-mm-dd | Sim         |
| checkout       | Data no formato yyyy-mm-dd | Sim         |
| numberAdults   | Número Inteiro             | Sim         |
| numberChildren | Número Inteiro             | Sim         |


## Resposta do Teste 2
	A url disponibilizada é a http://localhost:8070/cvc/booking/option/hotel, são passados os parâmetros abaixo e apenas o id é obrigatório;:
	
| Parametro      | Formato                    | Obrigatorio | Default    |
|----------------|----------------------------|-------------|------------|
| id             | Número Inteiro             | Sim         |            |
| checkin        | Data no formato yyyy-mm-dd | Não         | Data atual |
| checkout       | Data no formato yyyy-mm-dd | Não         | Data atual |
| numberAdults   | Número Inteiro             | Não         | 1          |
| numberChildren | Número Inteiro             | Não         | 0          |


Apesar de não serem especificados na documentação, os valores default são utilizados quando um destes valores não são informados.

Em ambos serviços há validação da data e em caso de erro resulta o json :

```javascript
{
    "code": 400,
    "timestamp": "2019-02-19T20:09:15.997+0000",
    "message": "Data de checking maior que a data de checkout.",
    "details": "uri=/cvc/booking/option/city"
}
```

## Swagger
Foi disponibilizado link do swagger em: http://localhost:8070/cvc/swagger-ui.html
 