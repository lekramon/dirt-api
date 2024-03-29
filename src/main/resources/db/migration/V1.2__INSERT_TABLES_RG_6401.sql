INSERT INTO STATUS (COD_STATUS, DES_STATUS)
VALUES (1, 'PENDING');
INSERT INTO STATUS (COD_STATUS, DES_STATUS)
VALUES (2, 'CANCELED');
INSERT INTO STATUS (COD_STATUS, DES_STATUS)
VALUES (3, 'SUCCESS');

INSERT INTO OPERATION (COD_OPERATION, DES_OPERATION)
VALUES (1, 'CREDIT');
INSERT INTO OPERATION (COD_OPERATION, DES_OPERATION)
VALUES (2, 'DEBIT');

INSERT INTO CAPTURE_METHOD_TYPE (COD_CAPTURE_METHOD_TYPE, DES_CAPTURE_METHOD_TYPE)
VALUES (1, 'WEB');
INSERT INTO CAPTURE_METHOD_TYPE (COD_CAPTURE_METHOD_TYPE, DES_CAPTURE_METHOD_TYPE)
VALUES (2, 'APP');
INSERT INTO CAPTURE_METHOD_TYPE (COD_CAPTURE_METHOD_TYPE, DES_CAPTURE_METHOD_TYPE)
VALUES (3, 'ATM');

INSERT INTO TRANSACTION_TYPE (COD_TRANSACTION_TYPE, DES_TRANSACTION_TYPE)
VALUES (1, 'PIX');
INSERT INTO TRANSACTION_TYPE (COD_TRANSACTION_TYPE, DES_TRANSACTION_TYPE)
VALUES (2, 'TED');
INSERT INTO TRANSACTION_TYPE (COD_TRANSACTION_TYPE, DES_TRANSACTION_TYPE)
VALUES (3, 'DOC');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Han Solo', '59805714004', '5840231-7', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Darth Vader', '09566900090', '9617324-8', '0001', '260', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Obi-Wan Kenobi', '77683477060', '2836175-0', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Yoda', '12101738082', '8496723-0', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Padmé Amidala', '21498650090', '1359764-2', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Chewbacca', '47636668009', '4265978-3', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Luke Skywalker', '32028048042', '7534206-9', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Leia Organa', '32103010000', '3098671-5', '0001', '290', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Anakin Skywalker', '02500844064', '6781549-2', '0001', '260', 'F');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Mace Windu', '14814890036', '2187659-3', '0001', '290', 'F');


INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Death Star Corporation Ltda.', '70402870000172', '9287163-5', '0001', '260', 'J');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Galactic Empire Ltda.', '84670121000130', '4738291-6', '0001', '260', 'J');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Stormtrooper Corps S.A.', '81905908000146', '6158942-3', '0001', '260', 'J');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Jabba the Hutt Enterprises 72513767098', '03349624000107', '8915627-3', '0001', '290', 'J');

INSERT INTO ACCOUNT (NAME_ACCOUNT, DOCUMENT, NUM_ACCOUNT, NUM_ACCOUNT_AGENCY, NUM_ACCOUNT_BANK, COD_ACCOUNT_TYPE)
VALUES ('Galactic Republic Ltda.', '85561160000162', '2465739-8', '0001', '290', 'J');
