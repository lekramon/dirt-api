# language: pt

Funcionalidade: Alterar status de uma transação

  Cenário: Alterar o status de uma transação com sucesso
    Dado que exista uma transação com os seguintes parâmetros no banco de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | PENDING | 5432109-8          | 8485               | 341                  |
    Quando for requisitada uma alteração de status para "SUCCESS" na transação de id 1
    Então o serviço de atualização deve retornar o status code 200 - "OK"
    E a transação deve ter o status alterado para "SUCCESS" na base de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | SUCCESS | 5432109-8          | 8485               | 341                  |

  Cenário: Alterar o status de uma transação com status inválido
    Dado que exista uma transação com os seguintes parâmetros no banco de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 2             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | DEBIT     | 2023-07-27 00:00:00.000000 | PENDING | 5432109-8          | 8485               | 341                  |
    Quando for requisitada uma alteração de status para "OK" na transação de id 2
    Então o serviço de atualização deve retornar o status code 400 - "Bad Request"

  Cenário: Alterar o status de uma transação de CANCELED para PENDING
    Dado que exista uma transação com os seguintes parâmetros no banco de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 3             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-28 00:00:00.000000 | CANCELED | 5432109-8          | 8485               | 341                  |
    Quando for requisitada uma alteração de status para "PENDING" na transação de id 3
    Então o serviço de atualização deve retornar o status code 406 - "Not Acceptable"

  Cenário: Alterar o status de uma transação que não existe no banco de dados
    Quando for requisitada uma alteração de status para "SUCCESS" na transação de id 8
    Então o serviço de atualização deve retornar o status code 404 - "Not Found"

  Cenário: Alterar o status de uma transação com serviço indisponível
    Dado que exista uma transação com os seguintes parâmetros no banco de dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 4             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-29 00:00:00.000000 | CANCELED | 5432109-8          | 8485               | 341                  |
    E que o serviço esteja indisponível
    Quando for requisitada uma alteração de status para "SUCCESS" na transação de id 4
    Então o serviço de atualização deve retornar o status code 500 - "Internal Server Error"