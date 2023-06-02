# language: pt

Funcionalidade:

    #Fazer uma requisição com sucesso - 201 (Created)
    #Fazer uma requisição com falha - 500 (Server error)
    #Fazer uma requisição com falha - 400 (Bad Request)
          #Fazer requisição com valores invalidos
          #Fazer requisição faltando valores

  Cenário: Registrar uma transação com sucesso
    Dado que seja feito uma requisição com os seguintes parâmetros
      | ip             | amount | tax  | accountId | description              | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 178.113.45.003 | 20.00  | 0.30 | 1         | Pagto*Gas Station seu Zé | 10021           | 1                 | 1               | 1         | 5432109-8          | 8485               | 341                  |
    Quando o serviço de registro de transações for chamado
    Então o status code registrado deve ser 200
    E a transação deve ser registrada na base de dados
      | idt_transaction | ip             | amount | tax  | accountId | description              | captureMethodId | captureMethodType | transactionType | operation | status | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 1               | 178.113.45.003 | 20.00  | 0.30 | 1         | Pagto*Gas Station seu Zé | 10021           | 1                 | 1               | 1         | 1      | 5432109-8          | 8485               | 341                  |

  Cenário: Registrar uma transação com falha utilizando valores inválidos
    Dado que seja feito uma requisição com os seguintes parâmetros
      | ip             | amount | tax  | accountId | description         | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 194.117.13.008 | 90.00  | 0.30 | 3         | Pagto*Loja da Maria | 20012           | 2                 | 2               | 4         | 4567890-1          | 0001               | 260                  |
    Quando o serviço de registro de transações for chamado
    Então o status code registrado deve ser 400

  Cenário: Registrar uma transação com falha faltando valores
    Dado que seja feito uma requisição com os seguintes parâmetros
      | ip             | amount  | tax  | accountId | description  | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency |
      | 192.151.79.004 | 1000.00 | 0.30 | 4         | Pagto*Vidros | 13125128        | 1                 | 1               | 1         | 5432109-8          | 0001               |
    Quando o serviço de registro de transações for chamado
    Então o status code registrado deve ser 400

  Cenário: Registrar uma transação com falha de servidor
    Dado que seja feito uma requisição com os seguintes parâmetros
      | ip             | amount | tax  | accountId | description        | captureMethodId | captureMethodType | transactionType | operation | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
      | 191.220.32.005 | 150.00 | 0.30 | 6         | Pagto*Bolos Renata | 30491239        | 2                 | 1               | 2         | 2345678-9          | 0001               | 290                  |
    Quando o serviço de registro de transações for chamado
    E ocorrer um erro do servidor
    Então o status code registrado deve ser 500