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
-- Table structure for table `respuestas_preguntas`
--

DROP TABLE IF EXISTS `respuestas_preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuestas_preguntas` (
  `ID_RESPUESTA_PREGUNTA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PERSONA` int(11) NOT NULL,
  `ID_PREGUNTA` int(11) NOT NULL,
  `ID_OPCION_RESPUESTA` int(11) DEFAULT NULL,
  `TEXTO_RESPUESTA` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID_RESPUESTA_PREGUNTA`),
  KEY `RESPUESTAS_PREGUNTAS_ID_PERSONA_idx` (`ID_PERSONA`),
  KEY `RESPUESTAS_PREGUNTAS_ID_PREGUNTA_idx` (`ID_PREGUNTA`),
  KEY `RESPUESTAS_PREGUNTAS_ID_OPCION_RESPUESTA_idx` (`ID_OPCION_RESPUESTA`),
  CONSTRAINT `RESPUESTAS_PREGUNTAS_ID_OPCION_RESPUESTA` FOREIGN KEY (`ID_OPCION_RESPUESTA`) REFERENCES `opciones_respuestas` (`ID_OPCION_RESPUESTA`) ON UPDATE NO ACTION,
  CONSTRAINT `RESPUESTAS_PREGUNTAS_ID_PERSONA` FOREIGN KEY (`ID_PERSONA`) REFERENCES `personas` (`ID_PERSONA`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `RESPUESTAS_PREGUNTAS_ID_PREGUNTA` FOREIGN KEY (`ID_PREGUNTA`) REFERENCES `preguntas` (`ID_PREGUNTA`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas_preguntas`
--

LOCK TABLES `respuestas_preguntas` WRITE;
/*!40000 ALTER TABLE `respuestas_preguntas` DISABLE KEYS */;
INSERT INTO `respuestas_preguntas` VALUES (1,2,15,16,NULL),(2,2,25,22,NULL),(3,2,19,28,NULL),(4,2,16,25,NULL),(5,2,22,26,NULL),(6,2,14,NULL,'ojos - 09.2013'),(7,2,24,23,NULL),(8,2,26,17,NULL);
/*!40000 ALTER TABLE `respuestas_preguntas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-02 17:53:07
