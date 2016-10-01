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
-- Table structure for table `puestos`
--

DROP TABLE IF EXISTS `puestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `puestos` (
  `id_puesto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_puesto`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puestos`
--

LOCK TABLES `puestos` WRITE;
/*!40000 ALTER TABLE `puestos` DISABLE KEYS */;
INSERT INTO `puestos` VALUES (1,'2do. Jefe de Distrito'),(2,'Administrativo'),(3,'Alistamiento'),(4,'Artesano'),(5,'Auxiliar de estación'),(6,'Ayudante Mecánico de Señales'),(7,'Azafata'),(8,'Camarero'),(9,'Cambista'),(10,'Capataz Cuadrilla'),(11,'Capataz de Vía'),(12,'Conductor'),(13,'Conductor Ayudante'),(14,'Dibujante'),(15,'División Operativa'),(16,'Electromecánico'),(17,'Encomiendas'),(18,'Guarda Paso a Nivel'),(19,'Guardatren'),(20,'Informes'),(21,'Inspector de Artesanos'),(22,'Inspector Vía'),(23,'Inspector Zonal'),(24,'Jefe de distrito'),(25,'Jefe de Estación'),(26,'Limpieza'),(27,'Mantenimiento'),(28,'Mecánico'),(29,'Mecánico de Señales'),(30,'Mecánico de Telecomunicaciones'),(31,'Oficial Soldador'),(32,'Operario de Vía'),(33,'Operario de Vía Puente'),(34,'Patrullero de Vía'),(35,'Peón General'),(36,'Puesto Control Zonal'),(37,'Relevante'),(38,'Seguridad y control'),(39,'Señalero'),(40,'Señalero Inspector'),(41,'SEREP (varios)'),(42,'Superintendente'),(43,'Tesorero');
/*!40000 ALTER TABLE `puestos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-30 14:22:49
