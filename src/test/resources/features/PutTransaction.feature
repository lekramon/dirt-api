# language: pt

Funcionalidade: Alterar status de uma transação

  Cenário: Alterar o status de uma transação com sucesso
    Dado que exista uma transação com os seguintes parâmetros na base dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | PENDING | 5432109-8          | 8485               | 341                  |
    Quando for requisitada uma alteração dessa transação de id 1 para o status "SUCCESS"
    Então o serviço deve retornar o status code 200 - "OK"
    E a transação deve ser alterada para
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | SUCCESS | 5432109-8          | 8485               | 341                  |

  Cenário: Alterar o status de uma transação com falha
    Dado que exista uma transação com os seguintes parâmetros na base dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 2             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | CANCELED | 5432109-8          | 8485               | 341                  |
    Quando for requisitada uma alteração dessa transação de id 2 para o status "PENDING"
    Então o serviço deve retornar o status code 400 - "Bad Request"

  Cenário: Alterar o status de uma transação com serviço indisponível

    Dado que exista uma transação com os seguintes parâmetros na base dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 3             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | CANCELED | 5432109-8          | 8485               | 341                  |
    E que o serviço esteja indisponível
    Quando for requisitada uma alteração dessa transação de id 3 para o status "SUCCESS"
    Então o serviço deve retornar status code 500 - "Internal Server Error"