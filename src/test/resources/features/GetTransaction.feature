## language: pt
#
#Funcionalidade: Listar transações
#
#    ##Cenários com sucesso
#  Contexto:
#    Dado que existam as seguintes transações no banco de dados
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName                            | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description            | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 1             | 178.113.45.003 | 20.00             | 0.00           | 1         | Han Solo                               | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Sabre Luz        | 10033           | APP               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | PENDING  | 5432109-8          | 8485               | 341                  |
#      | 2             | 192.142.20.123 | 2000.00           | 0.00           | 2         | Darth Vader                            | 09566900090     | 9617324-8  | 0001             | 260            | F           | Pagto*Sabre Luz        | 10033           | APP               | PIX             | DEBIT     | 2023-08-03 00:00:00.000000 | SUCCESS  | 5840231-7          | 0001               | 290                  |
#      | 3             | 198.222.20.009 | 999.00            | 0.45           | 3         | Obi-Wan Kenobi                         | 77683477060     | 2836175-0  | 0001             | 290            | F           | Pagto*Sabre Luz        | 10044           | ATM               | TED             | DEBIT     | 2023-08-04 00:00:00.000000 | PENDING  | 6781549-2          | 0001               | 290                  |
#      | 4             | 199.221.36.020 | 800.00            | 0.30           | 4         | Yoda                                   | 12101738082     | 8496723-0  | 0001             | 290            | F           | Pagto*Ganja            | 10066           | WEB               | DOC             | CREDIT    | 2023-08-05 00:00:00.000000 | SUCCESS  | 8496723-0          | 0001               | 290                  |
#      | 5             | 178.142.69.666 | 210.00            | 0.30           | 5         | Padmé Amidala                          | 21498650090     | 1359764-2  | 0001             | 290            | F           | Pagto*Boticário        | 10066           | WEB               | DOC             | DEBIT     | 2023-08-06 00:00:00.000000 | CANCELED | 1359764-2          | 0001               | 290                  |
#      | 6             | 161.342.32.013 | 45.00             | 0.00           | 6         | Galactic Republic Ltda.                | 85561160000162  | 2465739-8  | 0001             | 290            | J           | Pagto*Manutenção naves | 10033           | APP               | PIX             | CREDIT    | 2023-08-07 00:00:00.000000 | SUCCESS  | 9617324-8          | 0001               | 260                  |
#      | 7             | 190.231.34.654 | 421.00            | 0.00           | 7         | Jabba the Hutt Enterprises 72513767098 | 03349624000107  | 8915627-3  | 0001             | 290            | J           | Pagto*Saxofone         | 10033           | APP               | DOC             | CREDIT    | 2023-08-08 00:00:00.000000 | SUCCESS  | 5432109-8          | 8485               | 341                  |
#      | 8             | 199.221.36.020 | 800.00            | 0.30           | 4         | Yoda                                   | 12101738082     | 8496723-0  | 0001             | 290            | F           | Pagto*Ganja            | 10066           | WEB               | DOC             | CREDIT    | 2023-08-09 00:00:00.000000 | SUCCESS  | 8496723-0          | 0001               | 290                  |
#      | 9             | 178.142.69.666 | 210.00            | 0.30           | 5         | Padmé Amidala                          | 21498650090     | 1359764-2  | 0001             | 290            | F           | Pagto*Boticário        | 10066           | WEB               | DOC             | DEBIT     | 2023-08-10 00:00:00.000000 | CANCELED | 1359764-2          | 0001               | 290                  |
#      | 10            | 161.342.32.013 | 45.00             | 0.00           | 6         | Galactic Republic Ltda.                | 85561160000162  | 2465739-8  | 0001             | 290            | J           | Pagto*Manutenção naves | 10033           | APP               | PIX             | CREDIT    | 2023-08-11 00:00:00.000000 | SUCCESS  | 9617324-8          | 0001               | 260                  |
#      | 11            | 190.231.34.654 | 421.00            | 0.00           | 7         | Jabba the Hutt Enterprises 72513767098 | 03349624000107  | 8915627-3  | 0001             | 290            | J           | Pagto*Saxofone         | 10033           | WEB               | DOC             | CREDIT    | 2023-08-12 00:00:00.000000 | SUCCESS  | 5432109-8          | 8485               | 341                  |
#
#  Cenário: Realizar uma consulta páginada
#    Quando uma requisição de consulta de transações seja realizada
#    Então o serviço de listagem deve retornar o status code 200 - "OK"
#    E as seguintes transações devem ser retornadas para a página 1
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName                            | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description            | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status   | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 11            | 190.231.34.654 | 421.00            | 0.00           | 7         | Jabba the Hutt Enterprises 72513767098 | 03349624000107  | 8915627-3  | 0001             | 290            | J           | Pagto*Saxofone         | 10033           | APP               | DOC             | CREDIT    | 2023-08-12 00:00:00.000000 | SUCCESS  | 5432109-8          | 8485               | 341                  |
#      | 10            | 161.342.32.013 | 45.00             | 0.00           | 6         | Galactic Republic Ltda.                | 85561160000162  | 2465739-8  | 0001             | 290            | J           | Pagto*Manutenção naves | 10033           | APP               | PIX             | CREDIT    | 2023-08-11 00:00:00.000000 | SUCCESS  | 9617324-8          | 0001               | 260                  |
#      | 9             | 178.142.69.666 | 210.00            | 0.30           | 5         | Padmé Amidala                          | 21498650090     | 1359764-2  | 0001             | 290            | F           | Pagto*Boticário        | 10066           | WEB               | DOC             | DEBIT     | 2023-08-10 00:00:00.000000 | CANCELED | 1359764-2          | 0001               | 290                  |
#      | 8             | 199.221.36.020 | 800.00            | 0.30           | 4         | Yoda                                   | 12101738082     | 8496723-0  | 0001             | 290            | F           | Pagto*Ganja            | 10066           | WEB               | DOC             | CREDIT    | 2023-08-09 00:00:00.000000 | SUCCESS  | 8496723-0          | 0001               | 290                  |
#      | 7             | 190.231.34.654 | 421.00            | 0.00           | 7         | Jabba the Hutt Enterprises 72513767098 | 03349624000107  | 8915627-3  | 0001             | 290            | J           | Pagto*Saxofone         | 10033           | APP               | PIX             | CREDIT    | 2023-08-08 00:00:00.000000 | SUCCESS  | 5432109-8          | 8485               | 341                  |
#      | 6             | 161.342.32.013 | 45.00             | 0.00           | 6         | Galactic Republic Ltda.                | 85561160000162  | 2465739-8  | 0001             | 290            | J           | Pagto*Manutenção naves | 10033           | APP               | PIX             | CREDIT    | 2023-08-07 00:00:00.000000 | SUCCESS  | 9617324-8          | 0001               | 260                  |
#      | 5             | 178.142.69.666 | 210.00            | 0.30           | 5         | Padmé Amidala                          | 21498650090     | 1359764-2  | 0001             | 290            | F           | Pagto*Boticário        | 10066           | WEB               | DOC             | DEBIT     | 2023-08-06 00:00:00.000000 | CANCELED | 1359764-2          | 0001               | 290                  |
#      | 4             | 199.221.36.020 | 800.00            | 0.30           | 4         | Yoda                                   | 12101738082     | 8496723-0  | 0001             | 290            | F           | Pagto*Ganja            | 10066           | WEB               | DOC             | CREDIT    | 2023-08-05 00:00:00.000000 | SUCCESS  | 8496723-0          | 0001               | 290                  |
#      | 3             | 198.222.20.009 | 999.00            | 0.45           | 3         | Obi-Wan Kenobi                         | 77683477060     | 2836175-0  | 0001             | 290            | F           | Pagto*Sabre Luz        | 10044           | ATM               | TED             | DEBIT     | 2023-08-04 00:00:00.000000 | PENDING  | 6781549-2          | 0001               | 290                  |
#      | 2             | 192.142.20.123 | 2000.00           | 0.00           | 2         | Darth Vader                            | 09566900090     | 9617324-8  | 0001             | 260            | F           | Pagto*Sabre Luz        | 10033           | APP               | PIX             | DEBIT     | 2023-08-03 00:00:00.000000 | SUCCESS  | 5840231-7          | 0001               | 290                  |
#    E as seguintes transações devem ser retornadas para a página 2
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description     | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 1             | 178.113.45.003 | 20.00             | 0.00           | 1         | Han Solo    | 59805714004     | 5840231-7  | 0001             | 290            | F           | Pagto*Sabre Luz | 10033           | APP               | PIX             | CREDIT    | 2023-07-26 00:00:00.000000 | PENDING | 5432109-8          | 8485               | 341                  |
#
#  Cenário: Realizar uma consulta pelo tipo de método de captura
#    Quando uma requisição de consulta de transações com os parâmetros "captureMethodType" valor "ATM" seja realizada
#    Então o serviço de listagem deve retornar o status code 200 - "OK"
#    E as seguintes transações devem ser retornadas para a página 1
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName    | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description     | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 3             | 198.222.20.009 | 999.00            | 0.45           | 3         | Obi-Wan Kenobi | 77683477060     | 2836175-0  | 0001             | 290            | F           | Pagto*Sabre Luz | 10044           | ATM               | TED             | DEBIT     | 2023-08-04 00:00:00.000000 | PENDING | 6781549-2          | 0001               | 290                  |
#
#  Cenário: Realizar uma consulta pelo tipo da transação
#    Quando uma requisição de consulta de transações com os parâmetros "transactionType" valor "TED" seja realizada
#    Então o serviço de listagem deve retornar o status code 200 - "OK"
#    E as seguintes transações devem ser retornadas para a página 1
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName    | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description     | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 3             | 198.222.20.009 | 999.00            | 0.45           | 3         | Obi-Wan Kenobi | 77683477060     | 2836175-0  | 0001             | 290            | F           | Pagto*Sabre Luz | 10044           | ATM               | TED             | DEBIT     | 2023-08-04 00:00:00.000000 | PENDING | 6781549-2          | 0001               | 290                  |
#
#  Cenário: Realizar uma consulta com dois parâmetros
#    Quando uma requisição de consulta de transações com os parâmetros "transactionType" valor "DOC" e "captureMethodType" valor "APP" seja realizada
#    Então o serviço de listagem deve retornar o status code 200 - "OK"
#    E as seguintes transações devem ser retornadas para a página 1
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName                            | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description    | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 7             | 190.231.34.654 | 421.00            | 0.00           | 7         | Jabba the Hutt Enterprises 72513767098 | 03349624000107  | 8915627-3  | 0001             | 290            | J           | Pagto*Saxofone | 10033           | APP               | DOC             | CREDIT    | 2023-08-08 00:00:00.000000 | SUCCESS | 5432109-8          | 8485               | 341                  |
#
#  Cenário: Realizar uma consulta pelo id da transação
#    Quando uma requisição de consulta de transações com os parâmetros "transactionId" valor "4" seja realizada
#    Então o serviço de listagem deve retornar o status code 200 - "OK"
#    E as seguintes transações devem ser retornadas para a página 1
#      | transactionId | transactionIp  | transactionAmount | transactionTax | accountId | accountName | accountDocument | accountNum | accountNumAgency | accountNumBank | accountType | description | captureMethodId | captureMethodType | transactionType | operation | transactionDat             | status  | otherAccountNumber | otherAccountAgency | otherAccountBankCode |
#      | 4             | 199.221.36.020 | 800.00            | 0.30           | 4         | Yoda        | 12101738082     | 8496723-0  | 0001             | 290            | F           | Pagto*Ganja | 10066           | WEB               | DOC             | CREDIT    | 2023-08-05 00:00:00.000000 | SUCCESS | 8496723-0          | 0001               | 290                  |
#
#  Cenário: Realizar uma consulta com dois parâmetros e retorno vazio
#    Quando uma requisição de consulta de transações com os parâmetros "transactionType" valor "PIX" e "captureMethodType" valor "ATM" seja realizada
#    Então o serviço de listagem deve retornar o status code 200 - "OK"
#    E o conteúdo retornado deve ser vazio
#
#    ##Cenários de Erro
#  Cenário: Realizar uma consulta com parâmetros inexistentes
#    Quando uma requisição de consulta de transações com os parâmetros "captureMethodType" valor "CAIXA" seja realizada
#    Então o serviço de listagem deve retornar o status code 400 - "Bad Request"
#
#  Cenário: Realizar uma consulta com id inexistente
#    Quando uma requisição de consulta com os parâmetros "transactionId" valor "14" seja realizada
#    Então o serviço de listagem deve retornar o status code 404 - "Not Found"
#
#  Cenário: Realizar uma consulta com serviço indisponível
#    Quando uma requisição de consulta de transações com os parâmetros "transactionType" valor "PIX"
#    E que o serviço esteja indisponível
#    Então o serviço de listagem deve retornar o status code 500 - "Internal Server Error"