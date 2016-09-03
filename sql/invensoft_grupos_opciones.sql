-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: invensoft
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `grupos_opciones`
--

DROP TABLE IF EXISTS `grupos_opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos_opciones` (
  `ID_GRUPO_OPCIONES` int(11) NOT NULL AUTO_INCREMENT,
  `ID_GRUPO_PREGUNTAS` int(11) NOT NULL,
  `TIPO_VISUALIZACION` varchar(45) NOT NULL COMMENT 'INPUT\nRADIO\nCHECKBOX\nLIST',
  `TITULO` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_GRUPO_OPCIONES`),
  KEY `GRUPOS_OPCIONES_ID_GRUPO_PREGUNTAS_idx` (`ID_GRUPO_PREGUNTAS`),
  CONSTRAINT `GRUPOS_OPCIONES_ID_GRUPO_PREGUNTAS` FOREIGN KEY (`ID_GRUPO_PREGUNTAS`) REFERENCES `grupos_preguntas` (`ID_GRUPO_PREGUNTAS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos_opciones`
--

LOCK TABLES `grupos_opciones` WRITE;
/*!40000 ALTER TABLE `grupos_opciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupos_opciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-23  2:48:54
