DROP DATABASE IF EXISTS `hesscode`;
CREATE DATABASE `hesscode`;

USE hesscode;

CREATE TABLE users (
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
    user1_id BIGINT UNSIGNED REFERENCES users(id),
    user2_id BIGINT UNSIGNED REFERENCES users(id),
    statusDemande SMALLINT,
    demandeCreer DATE,
    demandeUpdate DATE
);

CREATE TABLE document (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    path varchar(200) NOT NULL,
    user_id BIGINT UNSIGNED REFERENCES users(id),
    dateCreation DATETIME,
    dateUpdate DATETIME,
    is_public BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE accessDocument (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    document_id BIGINT UNSIGNED REFERENCES document(id),
    user_id BIGINT UNSIGNED REFERENCES users(id),
    droitLecture BOOLEAN NOT NULL DEFAULT FALSE,
    droitEcriture BOOLEAN NOT NULL DEFAULT FALSE,
    droitAccees DATE
);

CREATE TABLE groupe (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    createur_id BIGINT UNSIGNED REFERENCES users(id),
    nom VARCHAR(50) NOT NULL,
    dateCreation DATE
);

CREATE TABLE membre (
    user_id BIGINT UNSIGNED REFERENCES users(id),
    groupe_id BIGINT UNSIGNED REFERENCES groupe(id),
    aRejoint DATETIME
);

CREATE TABLE conv (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user1_id BIGINT UNSIGNED REFERENCES users(id),
    user2_id BIGINT UNSIGNED REFERENCES users(id),
    pathConv varchar(200) NOT NULL,
    dateConv DATETIME
);

CREATE TABLE msg (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_conv BIGINT UNSIGNED REFERENCES conv(id),
    user1_id BIGINT UNSIGNED REFERENCES users(id),
    user2_id BIGINT UNSIGNED REFERENCES users(id),
    message TEXT,
    dateMsg DATETIME
);