CREATE DATABASE CMPE283;
USE CMPE283;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  fname varchar(30) NOT NULL DEFAULT '',
  lname varchar(30) NOT NULL DEFAULT '',
  email varchar(30) NOT NULL DEFAULT '',
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  created datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  lastlogin datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,fname,lname,email,password,enabled)
VALUES ('rohan','Rohan','Bhanderi','rohan.bhanderi@sjsu.edu','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO users(username,fname,lname,email,password,enabled)
VALUES ('alex','alex','alex','alex@alex.com','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO user_roles (username, role)
VALUES ('rohan', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('rohan', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');

CREATE  TABLE vm (
    Id INT(11) NOT NULL AUTO_INCREMENT,
    vmname VARCHAR(45) NOT NULL ,
    username varchar(45) NOT NULL,
    PRIMARY KEY (Id),
     UNIQUE KEY vmi_username (vmname,username),
   KEY fk_username_idx1 (username),
   CONSTRAINT fk_username_idx1 FOREIGN KEY (username) REFERENCES users (username)  );
   
   