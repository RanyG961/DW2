-- MariaDB dump 10.19  Distrib 10.5.10-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: hesscode
-- ------------------------------------------------------
-- Server version	10.5.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acceesDocument`
--

DROP TABLE IF EXISTS `acceesDocument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acceesDocument` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `document_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `droitLecture` tinyint(1) NOT NULL DEFAULT 0,
  `droitEcriture` tinyint(1) NOT NULL DEFAULT 0,
  `dateAccees` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `document_id` (`document_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `acceesDocument_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `acceesDocument_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acceesDocument`
--

LOCK TABLES `acceesDocument` WRITE;
/*!40000 ALTER TABLE `acceesDocument` DISABLE KEYS */;
/*!40000 ALTER TABLE `acceesDocument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conv`
--

DROP TABLE IF EXISTS `conv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conv` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user1_id` bigint(20) unsigned DEFAULT NULL,
  `user2_id` bigint(20) unsigned DEFAULT NULL,
  `pathConv` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateConv` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user1_id` (`user1_id`),
  KEY `user2_id` (`user2_id`),
  CONSTRAINT `conv_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`),
  CONSTRAINT `conv_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conv`
--

LOCK TABLES `conv` WRITE;
/*!40000 ALTER TABLE `conv` DISABLE KEYS */;
/*!40000 ALTER TABLE `conv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `dateCreation` datetime DEFAULT NULL,
  `dateUpdate` datetime DEFAULT NULL,
  `is_public` tinyint(1) NOT NULL DEFAULT 0,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `document_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (109,'/home/ranyg961/MEGAsync/S6/DW2/hesscode/target/projetweb-1.0-SNAPSHOT/WEB-INF/documents/1_15-05-2021_12:31:52.txt',1,'2021-05-15 12:31:52',NULL,0,'1_15-05-2021_12:31:52.txt'),(110,'/home/ranyg961/MEGAsync/S6/DW2/hesscode/target/projetweb-1.0-SNAPSHOT/WEB-INF/documents/1_15-05-2021_12:35:00.txt',1,'2021-05-15 12:35:00',NULL,0,'1_15-05-2021_12:35:00.txt'),(111,'/home/ranyg961/MEGAsync/S6/DW2/hesscode/target/projetweb-1.0-SNAPSHOT/WEB-INF/documents/1_15-05-2021_12:35:41.txt',1,'2021-05-15 12:35:41',NULL,0,'1_15-05-2021_12:35:41.txt'),(112,'/home/ranyg961/MEGAsync/S6/DW2/hesscode/target/projetweb-1.0-SNAPSHOT/WEB-INF/documents/1_15-05-2021_12:41:30.txt',1,'2021-05-15 12:41:30',NULL,0,'1_15-05-2021_12:41:30.txt');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user1_id` bigint(20) unsigned DEFAULT NULL,
  `user2_id` bigint(20) unsigned DEFAULT NULL,
  `statusDemande` smallint(6) DEFAULT NULL,
  `demandeCreer` date DEFAULT NULL,
  `demandeUpdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user1_id` (`user1_id`),
  KEY `user2_id` (`user2_id`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupe` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `createur_id` bigint(20) unsigned DEFAULT NULL,
  `nom` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateCreation` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `createur_id` (`createur_id`),
  CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`createur_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupe`
--

LOCK TABLES `groupe` WRITE;
/*!40000 ALTER TABLE `groupe` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membre`
--

DROP TABLE IF EXISTS `membre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membre` (
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `groupe_id` bigint(20) unsigned DEFAULT NULL,
  `aRejoint` datetime DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `groupe_id` (`groupe_id`),
  CONSTRAINT `membre_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `membre_ibfk_2` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membre`
--

LOCK TABLES `membre` WRITE;
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_conv` bigint(20) unsigned DEFAULT NULL,
  `user1_id` bigint(20) unsigned DEFAULT NULL,
  `user2_id` bigint(20) unsigned DEFAULT NULL,
  `message` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dateMsg` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_conv` (`id_conv`),
  KEY `user1_id` (`user1_id`),
  KEY `user2_id` (`user2_id`),
  CONSTRAINT `msg_ibfk_1` FOREIGN KEY (`id_conv`) REFERENCES `conv` (`id`),
  CONSTRAINT `msg_ibfk_2` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`),
  CONSTRAINT `msg_ibfk_3` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg`
--

LOCK TABLES `msg` WRITE;
/*!40000 ALTER TABLE `msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` date NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Rany','Ghazzawi','1996-08-11','$2a$12$z/u6Z69wOe4gmPZX5ASKMucL452wqQ5GQDm4/kRonEd2FveATWtBy','RanyG961','ranywwe@hotmail.com',1),(2,'Mohammed-Bashir','Al Mahdi','1998-01-20','$2a$12$APGMj/csrnxLEDnQRVxIHuQ1TkgQDhPI633nw6x7NEcYXH3QmutP.','MoBash313','mohammedbashir.m@gmail.com',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-15 13:20:00
