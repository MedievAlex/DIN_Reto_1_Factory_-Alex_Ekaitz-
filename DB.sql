CREATE DATABASE din_factory;
USE din_factory;

CREATE TABLE User (
	U_USERNAME VARCHAR(20) PRIMARY KEY,
    U_PASSWORD VARCHAR(20),
    U_NAME VARCHAR(20),
    U_LASTNAME VARCHAR(20),
    U_TYPE ENUM('Client', 'Admin')
);

INSERT INTO User_B VALUES 
("Almendra","alm","Almendrito","Tostado","Admin"),
("Garbanzo","gar","Garbancito","Cocido","Client");
