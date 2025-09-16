CREATE DATABASE din_factory;
USE din_Factory;

CREATE TABLE user (
	username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20),
    name VARCHAR(20),
    lastname VARCHAR(20),
    edad int
);