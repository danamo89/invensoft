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
-- Table structure for table `educaciones_no_formales`
--

DROP TABLE IF EXISTS `educaciones_no_formales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educaciones_no_formales` (
  `ID_EDUCACION_NO_FORMAL` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PERSONA` int(11) NOT NULL,
  `TIPO_EDUCACION_NO_FORMAL` varchar(50) NOT NULL,
  `FECHA` datetime DEFAULT NULL,
  `INSTITUCION` varchar(255) NOT NULL,
  `TEMA` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_EDUCACION_NO_FORMAL`),
  KEY `EDUCACIONES_NO_FORMALES_ID_PERSONA_idx` (`ID_PERSONA`),
  CONSTRAINT `EDUCACIONES_NO_FORMALES_ID_PERSONA` FOREIGN KEY (`ID_PERSONA`) REFERENCES `personas` (`ID_PERSONA`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educaciones_no_formales`
--

LOCK TABLES `educaciones_no_formales` WRITE;
/*!40000 ALTER TABLE `educaciones_no_formales` DISABLE KEYS */;
INSERT INTO `educaciones_no_formales` VALUES (1,2,'Curso','2016-08-01 00:00:00','educacion it','seguridad informatica');
/*!40000 ALTER TABLE `educaciones_no_formales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-23  2:48:53
