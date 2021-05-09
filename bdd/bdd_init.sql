DROP DATABASE IF EXISTS `hesscode`;
CREATE DATABASE `hesscode`;

USE hesscode;

CREATE TABLE user (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    password VARCHAR(50) NOT NULL,
    nickname VARCHAR(10) NOT NULL,
    mail VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE friends (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user1_id BIGINT UNSIGNED REFERENCES user(id),
    user2_id BIGINT UNSIGNED REFERENCES user(id),
    statusDemande SMALLINT,
    demandeCreer DATE,
    demandeUpdate DATE
);

CREATE TABLE document (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    path varchar(200) NOT NULL,
    user_id BIGINT UNSIGNED REFERENCES user(id),
    dateCreation DATETIME,
    dateUpdate DATETIME
);

CREATE TABLE accessDocument (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    document_id BIGINT UNSIGNED REFERENCES document(id),
    user_id BIGINT UNSIGNED REFERENCES user(id),
    droitAccees DATE
)