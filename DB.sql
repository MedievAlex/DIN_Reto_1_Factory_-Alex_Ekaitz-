CREATE DATABASE dinfactory;
USE dinFactory;

CREATE TABLE user (
	username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20),
    name VARCHAR(20),
    lastname VARCHAR(20),
    edad int
);