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
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preguntas` (
  `ID_PREGUNTA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_GRUPO_PREGUNTAS` int(11) NOT NULL,
  `TEXTO` varchar(255) NOT NULL,
  `ESTILO_OPCIONES` varchar(45) NOT NULL COMMENT 'CHECK\nRADIO\nINPUT\nAREA',
  `ORDEN` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PREGUNTA`),
  KEY `PREGUNTAS_ID_GRUPO_PREGUNTAS_idx` (`ID_GRUPO_PREGUNTAS`),
  CONSTRAINT `PREGUNTAS_ID_GRUPO_PREGUNTAS` FOREIGN KEY (`ID_GRUPO_PREGUNTAS`) REFERENCES `grupos_preguntas` (`ID_GRUPO_PREGUNTAS`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--

LOCK TABLES `preguntas` WRITE;
/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;
INSERT INTO `preguntas` VALUES (2,3,'¿Qué distancia aproximada recorre desde su casa a su lugar de trabajo?','Input',4),(3,4,'¿Posee otro trabajo actualmente?','Radio',2),(4,5,'¿Planea continuar con sus estudios? ¿Cuál/es y por qué?','Input',1),(5,5,'Mencione los conocimientos, cursos o capacitaciones que cree necesarios para desempeñarse en su puesto de trabajo','Area',4),(6,3,'¿Su vivienda se encuentra ubicada en un barrio ferroviario?','Radio',3),(7,4,'¿Qué puestos y/o áreas ha desempeñado dentro de la empresa?','Area',1),(8,3,'¿Posee vivienda propia o alquila?','Radio',2),(9,4,'¿Tiene o tuvo personal a cargo?','Radio',4),(10,5,'¿Qué programas de computación domina?','Input',3),(11,4,'En caso afirmativo indicar cuál','Input',3),(12,5,'¿Qué idiomas domina? Indicar nivel','Input',2),(13,3,'¿Proviene de familia ferroviaria?','Radio',1),(14,6,'Indicar cuales y en que año','Area',13),(15,6,'¿Toma algún medicamento actualmente?','Radio',6),(16,6,'¿Sufrió usted algún accidente dentro o fuera del trabajo?','Radio',10),(17,6,'¿Algún comentario que quiera realizar sobre su estado de salud?','Area',14),(18,6,'¿Qué padece? (su respuesta no es obligatoria)','Input',9),(19,6,'¿Padece usted algún problema físico o enfermedad crónica?','Radio',4),(20,6,'Indicar cuál','Input',5),(21,6,'Cantidad de cigarrillos por día','Input',2),(22,6,'¿Es fumador?','Radio',1),(23,6,'En caso afirmativo, explique brevemente que ocurrió y si le generó alguna secuela.','Area',11),(24,6,'Mano hábil','Radio',3),(25,6,'¿Tiene algún familiar directo enfermo?','Radio',8),(26,6,'¿Se realizó alguna cirugía?','Radio',12),(27,6,'Indicar cuál','Input',7),(28,7,'¿Estaría dispuesto a cambiar su lugar de residencia y/o base?','Radio',6),(29,7,'Puesto en el que se desempeña actualmente','Input',2),(30,7,'¿Cuál es la señal de mano ante peligro inminente?','Radio',5),(31,7,'¿Cuál de estos elementos no utilizan los Guardabarrera/Banderilleros?','Radio',3),(32,7,'¿Qué sistema de bloqueo utilizan los trenes entre Chascomús y Mar del Plata?','Radio',4),(33,7,'Considerando las tareas que Ud. desempeña, ¿en cuál de las siguientes áreas se ubica?','Radio',1),(34,7,'¿Estaría dispuesto a realizar tareas en la cuadrilla de vía?','Radio',7),(35,8,'¿Cuáles de estas herramientas son utilizadas en una cuadrilla de vía y obras?','Radio',4),(36,8,'Considerando las tareas que Ud. desempeña, ¿en cuál de las siguientes áreas se ubica?','Radio',1),(37,8,'¿Cuántos milímetros (mm) tiene la trocha ancha?','Radio',5),(38,8,'¿Estaría dispuesto a cambiar su lugar de residencia y/o base?','Radio',6),(39,8,'Puesto en el que se desempeña actualmente','Input',2),(40,8,'¿Cuál de estos elementos no es utilizado por una cuadrilla de vía y obras?','Radio',3),(41,9,'¿Estaría dispuesto a cambiar su lugar de residencia y/o base?','Radio',7),(42,9,'¿Estaría dispuesto a realizar tareas en la cuadrilla de vía?','Radio',8),(43,9,'Considerando las tareas que Ud. desempeña, ¿en cuál de las siguientes áreas se ubica?','Radio',1),(44,9,'¿Cuántos generadores posee una locomotora GT22?','Radio',3),(45,9,'¿Qué sistema de freno tienen los coches Materfer?','Radio',5),(46,9,'¿Cuántas triple válvulas tiene un coche remolcado?','Radio',4),(47,9,'El valor de QR es una medida relativa a','Radio',6),(48,9,'Puesto en el que se desempeña actualmente','Input',2),(49,10,'Considerando las tareas que Ud. desempeña, ¿en cuál de las siguientes áreas se ubica?','Radio',1),(50,10,'¿Estaría dispuesto a realizar tareas en la cuadrilla de vía?','Radio',6),(51,10,'¿Cuál de las siguientes opciones no es una clase de coche?','Radio',4),(52,10,'¿Cómo se denomina el sistema de venta utilizado por Ferrobaires?','Radio',3),(53,10,'¿Estaría dispuesto a cambiar su lugar de residencia y/o base?','Radio',5),(54,10,'Puesto en el que se desempeña actualmente','Input',2),(55,11,'Puesto en el que se desempeña actualmente','Input',2),(56,11,'Considerando las tareas que Ud. desempeña, ¿en cuál de las siguientes áreas se ubica?','Radio',1),(57,11,'¿Cuál es el medio de pago permitido por norma legal para montos mayores a $1.000 (pesos mil)?','Radio',3),(58,11,'¿Ferrobaires por ser Responsable Inscripto, qué tipo de comprobante NO puede emitir?','Radio',5),(59,11,'¿Para la conciliación bancaria de Julio, qué información utiliza?','Radio',6),(60,11,'¿Estaría dispuesto a cambiar su lugar de residencia?','Radio',7),(61,11,'¿A qué alícuota IVA se encuentra gravada la venta de pasaje?','Radio',4),(62,12,'¿Cuántos milímetros (mm) tiene la trocha angosta?','Radio',5),(63,12,'¿Cuáles de éstas herramientas son utilizadas en una cuadrilla de vía y obras?','Radio',4),(64,12,'¿Estaría dispuesto a cambiar su lugar de residencia y/o base?','Radio',6),(65,12,'Considerando las tareas que Ud. desempeña, ¿en cuál de las siguientes áreas se ubica?','Radio',1),(66,12,'Puesto en el que se desempeña actualmente','Input',2),(67,12,'¿Cuáles de los siguientes términos son utilizados como elementos de vía?','Radio',3);
/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;
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
