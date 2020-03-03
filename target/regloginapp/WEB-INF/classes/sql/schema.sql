CREATE SCHEMA IF NOT EXISTS regloginapp;

DROP TABLE IF EXISTS regloginapp.users;

CREATE TABLE regloginapp.users (
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  admin NUMBER(1) NOT NULL,
  PRIMARY KEY (username)
);

DROP TABLE IF EXISTS regloginapp.user_roles;

CREATE TABLE regloginapp.user_roles (
  user_role_id int(10) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  role VARCHAR (45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_role_username (role, username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES regloginapp.users (username)
);

