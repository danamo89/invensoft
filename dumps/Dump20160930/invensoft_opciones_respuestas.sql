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
-- Table structure for table `opciones_respuestas`
--

DROP TABLE IF EXISTS `opciones_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opciones_respuestas` (
  `ID_OPCION_RESPUESTA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PREGUNTA` int(11) NOT NULL,
  `TEXTO` varchar(255) DEFAULT NULL,
  `ORDEN` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_OPCION_RESPUESTA`),
  KEY `OPCIONES_RESPUESTAS_ID_PREGUNTA_idx` (`ID_PREGUNTA`),
  CONSTRAINT `OPCIONES_RESPUESTAS_ID_PREGUNTA` FOREIGN KEY (`ID_PREGUNTA`) REFERENCES `preguntas` (`ID_PREGUNTA`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opciones_respuestas`
--

LOCK TABLES `opciones_respuestas` WRITE;
/*!40000 ALTER TABLE `opciones_respuestas` DISABLE KEYS */;
INSERT INTO `opciones_respuestas` VALUES (4,13,'No',2),(5,3,'No',2),(6,8,'Alquila',2),(7,6,'No',2),(8,9,'No',2),(9,13,'Si',1),(10,8,'Otra',3),(11,6,'Si',1),(12,8,'Propia',1),(13,3,'Si',1),(14,9,'Si',1),(15,22,'Si',1),(16,15,'No',2),(17,26,'Si',1),(18,25,'Si',1),(19,15,'Si',1),(20,16,'Si',1),(21,26,'No',2),(22,25,'No',2),(23,24,'Derecha',1),(24,24,'Izquierda',2),(25,16,'No',2),(26,22,'No',2),(27,19,'Si',1),(28,19,'No',2),(29,34,'No',2),(30,28,'Si',1),(31,34,'Si',1),(32,33,'Boletería',4),(33,30,'Brazo derecho extendido',1),(34,33,'Transporte',1),(35,30,'NS/NC',5),(36,28,'No',2),(37,31,'Todas las anteriores',5),(38,33,'Administración',5),(39,31,'NS/NC',6),(40,31,'Linterna',4),(41,32,'Winter',3),(42,31,'Calzas',3),(43,32,'Block Staff',1),(44,33,'Mecánica',2),(45,32,'Harper',2),(46,33,'Vía y Obra/Infraestructura',3),(47,30,'Ambos brazos extendidos  horizontalmente con  movimientos oscilantes',2),(48,30,'Ambos brazos extendidos  verticalmente',3),(49,31,'Silbato',2),(50,32,'Ninguno de los anteriores',4),(51,31,'Bandera roja',1),(52,30,'Ninguno de los anteriores',4),(53,32,'NS/NC',5),(54,35,'Gato Joyce',3),(55,35,'Tirafondeadora',1),(56,35,'Morsa',4),(57,36,'Vía y Obra/Infraestructura',3),(58,36,'Mecánica',2),(59,40,'Zapata',4),(60,36,'Transporte',1),(61,37,'NS/NC',5),(62,35,'NS/NC',6),(63,35,'Bateadora',2),(64,36,'Boletería',4),(65,37,'Ninguno de los anteriores',4),(66,37,'1.800 mm',3),(67,35,'Todas las anteriores',5),(68,40,'NS/NC',6),(69,37,'1.435 mm',2),(70,36,'Administración',5),(71,40,'Tirafondos',2),(72,40,'Eclisas',1),(73,37,'1.000 mm',1),(74,38,'Si',1),(75,38,'No',2),(76,40,'Todas las anteriores',5),(77,40,'Durmientes',3),(78,41,'No',1),(79,46,'NS/NC',1),(80,46,'Ninguna delas anteriores',2),(81,46,'Dos',3),(82,44,'NS/NC',1),(83,43,'Administración',1),(84,43,'Mecánica',2),(85,43,'Vía y Obra/Infraestructura',3),(86,47,'Ruedas',1),(87,47,'Boggies',2),(88,43,'Transporte',4),(89,45,'Ambos',1),(90,44,'Uno',2),(91,45,'Vacío',2),(92,46,'Una',4),(93,47,'Ninguna de los anteriores',3),(94,45,'Ninguno de los anteriores',3),(95,43,'Boletería',5),(96,44,'Dos',3),(97,44,'Ninguna de las anteriores',4),(98,42,'No',1),(99,44,'Seis',5),(100,47,'NS/NC',4),(101,45,'NS/NC',4),(102,41,'Si',2),(103,47,'Sistema neumático',5),(104,46,'Cuatro',5),(105,45,'Aire Comprimido',5),(106,42,'Si',2),(107,50,'SI',1),(108,52,'NS/NC',5),(109,51,'Intermedia',3),(110,50,'NO',2),(111,53,'NO',2),(112,52,'SEREP',3),(113,51,'Pulman',2),(114,49,'Vía y Obra/Infraestructura',3),(115,53,'SI',1),(116,49,'Boletería',4),(117,49,'Mecánica',2),(118,51,'Primera',1),(119,52,'Ninguna de las anteriores',4),(120,51,'Ninguna delas anteriores',5),(121,49,'Transporte',1),(122,49,'Administración',5),(123,51,'NS/NC',6),(124,52,'SISTEL',2),(125,51,'Súper Pulman',4),(126,52,'UNICEP',1),(127,56,'Mecánica',2),(128,57,'Todas las anteriores',4),(129,59,'Extracto bancario de Junio, contabilidad de Julio y partidas pendientes de Junio',1),(130,56,'Transporte',1),(131,58,'Ninguno de los anteriores',4),(132,60,'Si',1),(133,61,'21%',2),(134,58,'B',2),(135,61,'Ninguna delas anteriores',4),(136,57,'Efectivo',3),(137,59,'Extracto bancario de Julio, contabilidad de Julio y partidas pendientes de Junio',2),(138,58,'C',3),(139,59,'NS/NC',5),(140,57,'Cheque',2),(141,61,'NS/NC',5),(142,57,'NS/NC',5),(143,59,'Extracto bancario de Julio, contabilidad de Junio y partidas pendientes de Junio',3),(144,56,'Vía y Obra/Infraestructura',3),(145,61,'10.50%',1),(146,60,'No',2),(147,57,'Transferencia',1),(148,56,'Boletería',4),(149,58,'A',1),(150,56,'Administración',5),(151,59,'Ninguna de las anteriores',4),(152,61,'27%',3),(153,64,'No',2),(154,62,'1.800 mm',3),(155,62,'1.000 mm',1),(156,65,'Transporte',1),(157,63,'Llave T',3),(158,65,'Boletería',4),(159,67,'NS/NC',5),(160,65,'Administración',5),(161,67,'Marmita',2),(162,67,'Todas las anteriores',4),(163,62,'Ninguno de los anteriores',4),(164,62,'1.435 mm',2),(165,63,'Machete',4),(166,63,'Todas las anteriores',5),(167,64,'Si',1),(168,67,'Aguja',3),(169,63,'Barreta de uña',2),(170,63,'Gato',1),(171,63,'NS/NC',6),(172,67,'Corazón',1),(173,62,'NS/NC',5),(174,65,'Mecánica',2),(175,65,'Vía y Obra/Infraestructura',3);
/*!40000 ALTER TABLE `opciones_respuestas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-30 14:22:50
