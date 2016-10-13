-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: invensoft
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `cuestionarios_sectores`
--

DROP TABLE IF EXISTS `cuestionarios_sectores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuestionarios_sectores` (
  `ID_CUESTIONARIO_SECTOR` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CUESTIONARIO` int(11) NOT NULL,
  `ID_SECTOR` int(11) NOT NULL,
  PRIMARY KEY (`ID_CUESTIONARIO_SECTOR`),
  KEY `CUESTIONARIOS_SECTORES_ID_CUESTIONARIO_idx` (`ID_CUESTIONARIO`),
  KEY `CUESTIONARIOS_SECTORES_ID_SECTOR_idx` (`ID_SECTOR`),
  CONSTRAINT `CUESTIONARIOS_SECTORES_ID_CUESTIONARIO` FOREIGN KEY (`ID_CUESTIONARIO`) REFERENCES `cuestionarios` (`ID_CUESTIONARIO`) ON UPDATE NO ACTION,
  CONSTRAINT `CUESTIONARIOS_SECTORES_ID_SECTOR` FOREIGN KEY (`ID_SECTOR`) REFERENCES `sectores` (`ID_SECTOR`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionarios_sectores`
--

LOCK TABLES `cuestionarios_sectores` WRITE;
/*!40000 ALTER TABLE `cuestionarios_sectores` DISABLE KEYS */;
INSERT INTO `cuestionarios_sectores` VALUES (1,6,1),(2,7,2),(3,8,3),(4,9,4),(5,10,5),(8,11,2),(9,17,7),(10,18,7);
/*!40000 ALTER TABLE `cuestionarios_sectores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-10 22:08:59
