# language: pt

Funcionalidade: Registro de transações

  Cenário: Registrar uma transação com sucesso
    Dado que exista uma requisição com os seguintes parâmetros
      | ip             | amount | tax  | accountId | description              | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 178.113.45.003 | 20.00  | 0.30 | 1         | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 5432109-8          | 8485               | 341                  |
    Quando o serviço de registro de transações for chamado
    Então o serviço de registro deve retornar o status code 201 - "Created"
    E a transação deve ser registrada na base de dados com os seguintes dados
      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description              | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1             | 178.113.45.003 | 20.00             | 0.30           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Gas Station seu Zé | 10021           | WEB               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | PENDING | 5432109-8          | 8485               | 341                  |

  Cenário: Registrar uma transação com falha utilizando valores inválidos
    Dado que exista uma requisição com os seguintes parâmetros
      | iptv           | amount | tax  | accountId | description         | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 194.117.13.008 | 90.00  | 0.30 | 3         | Pagto*Loja da Maria | 20012           | WEB               | PIX             | DEBIT     | 4567890-1          | 0001               | 260                  |
    Quando o serviço de registro de transações for chamado
    Então o serviço de registro deve retornar o status code 400 - "Bad Request"

  Cenário: Registrar uma transação com falha faltando valores
    Dado que exista uma requisição com os seguintes parâmetros
      | ip             | amount  | tax  | accountId | description  | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency |
      | 192.151.79.004 | 1000.00 | 0.30 | 4         | Pagto*Vidros | 13125128        | APP               | PIX             | DEBIT     | 5432109-8          | 0001               |
    Quando o serviço de registro de transações for chamado
    Então o serviço de registro deve retornar o status code 400 - "Bad Request"

  Cenário: Registrar uma transação com o serviço indisponível
    Dado que exista uma requisição com os seguintes parâmetros
      | ip             | amount | tax  | accountId | description        | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 191.220.32.005 | 150.00 | 0.30 | 6         | Pagto*Bolos Renata | 30491239        | WEB               | PIX             | DEBIT     | 2345678-9          | 0001               | 290                  |
    E que o serviço esteja indisponível
    Quando o serviço de registro de transações for chamado
    Então o serviço de registro deve retornar o status code 500 - "Internal Server Error"