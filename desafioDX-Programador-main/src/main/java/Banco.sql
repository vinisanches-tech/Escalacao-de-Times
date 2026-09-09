-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: escalacao_times
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `composicao_time`
--

DROP TABLE IF EXISTS `composicao_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composicao_time` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_id` bigint NOT NULL,
  `integrante_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_escalacao_times` (`time_id`),
  KEY `fk_escalacao_integrante` (`integrante_id`),
  CONSTRAINT `fk_escalacao_integrante` FOREIGN KEY (`integrante_id`) REFERENCES `integrante` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_escalacao_times` FOREIGN KEY (`time_id`) REFERENCES `time` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composicao_time`
--

LOCK TABLES `composicao_time` WRITE;
/*!40000 ALTER TABLE `composicao_time` DISABLE KEYS */;
INSERT INTO `composicao_time` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3),(7,3,2);
/*!40000 ALTER TABLE `composicao_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `integrante`
--

DROP TABLE IF EXISTS `integrante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `integrante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `funcao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `integrante`
--

LOCK TABLES `integrante` WRITE;
/*!40000 ALTER TABLE `integrante` DISABLE KEYS */;
INSERT INTO `integrante` VALUES (1,'ala','Michael Jordan'),(2,'ala-pivô','Denis Rodman'),(3,'ala','Scottie Pippen');
/*!40000 ALTER TABLE `integrante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `nome_do_clube` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time`
--

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES (1,'1994-01-01','Chicago Bulls'),(2,'1995-01-01','Chicago Bulls'),(3,'1993-01-01','Detroit Pistons');
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-08 17:34:46
