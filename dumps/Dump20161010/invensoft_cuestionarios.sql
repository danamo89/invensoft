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
-- Table structure for table `cuestionarios`
--

DROP TABLE IF EXISTS `cuestionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuestionarios` (
  `ID_CUESTIONARIO` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(255) DEFAULT NULL,
  `DESCRIPCION` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID_CUESTIONARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionarios`
--

LOCK TABLES `cuestionarios` WRITE;
/*!40000 ALTER TABLE `cuestionarios` DISABLE KEYS */;
INSERT INTO `cuestionarios` VALUES (4,'CUESTIONARIO DE DESARROLLO PROFESIONAL',NULL),(5,'CUESTIONARIO DE SALUD OCUPACIONAL',NULL),(6,'Cuestionario para personal encuadrado en el área de TRANSPORTE',NULL),(7,'Cuestionario para personal encuadrado en el área de INFRAESTRUCTURA/VIA Y OBRA',NULL),(8,'Cuestionario para personal encuadrado en el área de MATERIAL RODANTE',NULL),(9,'Cuestionario para personal encuadrado en el área de BOLETERÍA',NULL),(10,'Cuestionario para personal encuadrado en el área de ADMINISTRACION',NULL),(11,'Cuestionario para personal encuadrado en el área de INFRAESTRUCTURA/VIA Y OBRA II',NULL),(12,'Cuestionario para personal encuadrado en el área de PERSONAL A BORDO',NULL),(13,'Cuestionario para personal encuadrado en el área de PERSONAL A BORDO II',NULL),(14,'Cuestionario para personal encuadrado en el área de TRANSPORTE II',NULL),(15,'Cuestionario para personal encuadrado en el área de MATERIAL RODANTE II',NULL),(16,'Cuestionario para personal encuadrado en el área de ADMINISTRACION II',NULL),(17,'Cuestionario para personal encuadrado en el área de ATENCIÓN INTEGRAL AL DISCAPACITADO',NULL),(18,'Cuestionario para personal encuadrado en el área de ATENCIÓN INTEGRAL AL DISCAPACITADO II',NULL);
/*!40000 ALTER TABLE `cuestionarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-10 22:08:58
