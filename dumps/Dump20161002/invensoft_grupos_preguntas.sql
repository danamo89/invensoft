-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: invensoft
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `grupos_preguntas`
--

DROP TABLE IF EXISTS `grupos_preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos_preguntas` (
  `ID_GRUPO_PREGUNTAS` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CUESTIONARIO` int(11) NOT NULL,
  `TITULO` varchar(255) DEFAULT NULL,
  `ORDEN` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_GRUPO_PREGUNTAS`),
  KEY `GRUPOS_PREGUNTAS_ID_CUESTIONARIO_idx` (`ID_CUESTIONARIO`),
  CONSTRAINT `GRUPOS_PREGUNTAS_ID_CUESTIONARIO` FOREIGN KEY (`ID_CUESTIONARIO`) REFERENCES `cuestionarios` (`ID_CUESTIONARIO`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos_preguntas`
--

LOCK TABLES `grupos_preguntas` WRITE;
/*!40000 ALTER TABLE `grupos_preguntas` DISABLE KEYS */;
INSERT INTO `grupos_preguntas` VALUES (3,4,'ENTORNO PERSONAL',3),(4,4,'TRAYECTORIA',2),(5,4,'HISTORIA EDUCATIVA Y DE FORMACIÓN',1),(6,5,'',1),(7,6,'',1),(8,7,'',1),(9,8,'',1),(10,9,'',1),(11,10,'',1),(12,11,'',1);
/*!40000 ALTER TABLE `grupos_preguntas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-02 17:53:10
