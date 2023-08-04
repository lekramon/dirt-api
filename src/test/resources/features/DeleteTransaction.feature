# language: pt

Funcionalidade: Apagar transações

  Cenário: Apagar uma transação com sucesso
    Dado que haja uma transação com os seguintes parâmetros no banco de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | PENDING | 5432109-8          | 8485               | 341                  |
    Quando for requisitada que a transação de id 1 seja apagada
    Então o serviço de apagar deve retornar o status code 200 - "OK"
    E a transação deve ter sido apagada no banco de dados

  Cenário: Apagar uma transação que não existe no banco de dados
    Quando for requisitada que a transação de id 6 seja apagada
    Então o serviço de apagar deve retornar o status code 404 - "Not Found"

  Cenário: Apagar uma transação com o serviço indisponível
    Dado que haja uma transação com os seguintes parâmetros no banco de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 3             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | CANCELED | 5432109-8          | 8485               | 341                  |
    E que o serviço esteja indisponível
    Quando for requisitada que a transação de id 3 seja apagada
    Então o serviço de apagar deve retornar o status code 500 - "Internal Server Error"