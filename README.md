# DIRT-API (Declaração de informações e registro de transações)

This API receives transactions and registers them in a database. The transaction contains details such as IP address,
amount, tax, account ID, description, capture method, transaction type, operation, transaction date and time, status,
and other account details. The capture method specifies the type of device used for the transaction (WEB, APP, or ATM),
while the transaction type indicates the payment method used (PIX, TED, or DOC). The operation field specifies whether
the transaction was a credit or debit. The other account field includes details of the recipient's bank account,
such as account number, agency, and bank code. The API can handle transactions in only pending status. Once received,
the API registers these transactions in the database for future treatment.

### Summary

- [Architecture](#architecture);
- [Installation (Docker)](#running-docker);
- [Database Connection](#database-connection);

### Architecture

![Architecture](./src/main/resources/images/architecture.png)

- The transaction API is responsible for the CRUD (Create, Read, Update, and Delete) of transactions and calls the
  database to store the information.

- A batch processing job is responsible for generating report files containing transaction information of accounts.

- There is a mocked service (in pink) available to simulate a notification system.

The database modelling diagram:

![Database](./src/main/resources/images/database.png)

The status for transaction are:

![Status](./src/main/resources/images/status.png)

The transaction status always starts as pending and can go to success or cancelled, and cannot be changed later.

---
### Development Environment

#### Running Docker
- [Download and install Docker](https://docs.docker.com/get-docker/)
- Run `docker-compose up` to create an instance of the oracle database.
- Wait for the log `DATABASE IS READY TO USE!` before running the application or checking the database.

#### Database Connection
- If you find it necessary to consult the database and validate the connection:
- HOST: localhost
- SID: XEPDB1
- PORT: 1521
- USER: dirt
- PASSWORD: sippar123
- URL: jdbc:oracle:thin:localhost:1521/XEPDB1

##### Important
In some database administration tools, the default connection is made using SID (System Identifier), which can be changed to URL or Service Name. However, if you are unable to connect using URL or Service Name, you should pay attention to the identifier of the PDB (Pluggable Database), which is typically set as SID connection like: `jdbc:oracle:thin:@localhost:1521:XEPDB1`, resulting in the [ORA-12505 error](https://docs.oracle.com/en/database/oracle/oracle-database/19/errmg/ORA-12500.html#GUID-BD6AAC21-3F15-4F70-B3C5-064ADCF5EC51), This occurs because of the use of " :  " in the connection with an unknown SID. In this case, it is necessary to use Service Name connection " / ". example: `jdbc:oracle:thin:@localhost:1521/XEPDB1`.

---