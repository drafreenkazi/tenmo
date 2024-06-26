BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account, transfer,transfer_type,transfer_status CASCADE;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);

CREATE TABLE transfer_type
(
  transfer_type_id int NOT NULL,
  transfer_type_desc varchar(10) NOT NULL,
  CONSTRAINT PK_transfer_type PRIMARY KEY  (transfer_type_id)
);
	
CREATE TABLE transfer_status
(
	transfer_status_id int NOT NULL,
	transfer_status_desc varchar(10) NOT NULL,
  CONSTRAINT PK_transfer_status PRIMARY KEY  (transfer_status_id)	
);
	
	
CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 5001
  NO MAXVALUE;
  
	
CREATE TABLE transfer
(
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	transfer_type_id int NOT NULL,
	transfer_status_id int NOT NULL,
    account_from int NOT NULL,
	account_to int NOT NULL,
	amount decimal(13, 2) NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY  (transfer_id),
	CONSTRAINT FK_transfer_from FOREIGN KEY(account_from) REFERENCES account (account_id),
	CONSTRAINT FK_transfer_to FOREIGN KEY(account_to) REFERENCES account (account_id),
	CONSTRAINT FK_transfer_transfer_type FOREIGN KEY (transfer_type_id) REFERENCES transfer_type (transfer_type_id),
	CONSTRAINT FK_transfer_transfer_status FOREIGN KEY (transfer_status_id) REFERENCES transfer_status (transfer_status_id)
);

--initial data load
Insert into transfer_type (transfer_type_id,transfer_type_desc) values (0, 'Send');
Insert into transfer_type (transfer_type_id,transfer_type_desc) values (1, 'Request');

Insert into transfer_status (transfer_status_id,transfer_status_desc) values (0, 'Pending');
Insert into transfer_status (transfer_status_id,transfer_status_desc) values (1, 'Approved');
Insert into transfer_status (transfer_status_id,transfer_status_desc) values (2, 'Rejected');
COMMIT;
