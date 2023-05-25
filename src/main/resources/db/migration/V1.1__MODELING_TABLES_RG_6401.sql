CREATE TABLE ACCOUNT
(
    IDT_ACCOUNT        NUMBER(38) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME_ACCOUNT       VARCHAR2(60) NOT NULL,
    DOCUMENT           VARCHAR2(18) NOT NULL,
    NUM_ACCOUNT        VARCHAR2(20) NOT NULL,
    NUM_ACCOUNT_AGENCY VARCHAR2(6)  NOT NULL,
    NUM_ACCOUNT_BANK   VARCHAR2(4)  NOT NULL,
    COD_ACCOUNT_TYPE   CHAR(1)      NOT NULL
);

CREATE TABLE STATUS
(
    COD_STATUS NUMBER(3, 0) PRIMARY KEY,
    DES_STATUS VARCHAR2(20) NOT NULL
);


CREATE TABLE OPERATION
(
    COD_OPERATION NUMBER(3, 0) PRIMARY KEY,
    DES_OPERATION VARCHAR2(20) NOT NULL
);


CREATE TABLE CAPTURE_METHOD_TYPE
(
    COD_CAPTURE_METHOD_TYPE NUMBER(3, 0) PRIMARY KEY,
    DES_CAPTURE_METHOD_TYPE VARCHAR2(20) NOT NULL
);


CREATE TABLE TRANSACTION_TYPE
(
    COD_TRANSACTION_TYPE NUMBER(3, 0) PRIMARY KEY,
    DES_TRANSACTION_TYPE VARCHAR2(20) NOT NULL
);


CREATE TABLE TRANSACTION
(
    IDT_TRANSACTION          NUMBER(38) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NUM_IP                   VARCHAR2(15)  NOT NULL,
    NUM_AMOUNT               NUMBER(14, 2) NOT NULL,
    NUM_TAX                  NUMBER(3, 2)  NOT NULL,
    IDT_ACCOUNT              NUMBER(38)    NOT NULL,
    DES_TRANSACTION          VARCHAR2(100) NOT NULL,
    IDT_CAPTURE_METHOD       VARCHAR2(30)  NOT NULL,
    COD_CAPTURE_METHOD_TYPE  NUMBER(3, 0)  NOT NULL,
    COD_TRANSACTION_TYPE     NUMBER(3, 0)  NOT NULL,
    COD_OPERATION            NUMBER(3, 0)  NOT NULL,
    DAT_TRANSACTION          TIMESTAMP(6)  NOT NULL,
    COD_STATUS               NUMBER(3, 0)  NOT NULL,
    NUM_OTHER_ACCOUNT        VARCHAR2(20)  NOT NULL,
    NUM_OTHER_ACCOUNT_AGENCY VARCHAR2(6)   NOT NULL,
    NUM_OTHER_ACCOUNT_BANK   VARCHAR2(4)   NOT NULL,
    CONSTRAINT COD_CAPTURE_METHOD_TYPE FOREIGN KEY (COD_CAPTURE_METHOD_TYPE) REFERENCES CAPTURE_METHOD_TYPE (COD_CAPTURE_METHOD_TYPE),
    CONSTRAINT COD_TRANSACTION_TYPE FOREIGN KEY (COD_TRANSACTION_TYPE) REFERENCES TRANSACTION_TYPE (COD_TRANSACTION_TYPE),
    CONSTRAINT COD_OPERATION FOREIGN KEY (COD_OPERATION) REFERENCES OPERATION (COD_OPERATION),
    CONSTRAINT COD_STATUS FOREIGN KEY (COD_STATUS) REFERENCES STATUS (COD_STATUS)
);