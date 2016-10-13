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
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `ID_PERSONA` int(11) NOT NULL AUTO_INCREMENT,
  `LEGAJO` varchar(45) NOT NULL,
  `NOMBRES` varchar(100) NOT NULL,
  `APELLIDOS` varchar(100) NOT NULL,
  `ID_TIPO_IDENTIFICACION` int(11) NOT NULL,
  `NUMERO_IDENTIFICACION` varchar(45) NOT NULL,
  `ID_PAIS_ORIGEN` int(11) NOT NULL,
  `FECHA_NACIMIENTO` datetime DEFAULT NULL,
  `ID_ESTADO_CIVIL` int(11) NOT NULL,
  `DOMICILIO` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(150) DEFAULT NULL,
  `ID_LOCALIDAD` int(11) DEFAULT NULL,
  `NUMERO_DOMICILIO` varchar(5) DEFAULT NULL,
  `PISO` varchar(10) DEFAULT NULL,
  `DEPARTAMENTO` varchar(20) DEFAULT NULL,
  `CODIGO_POSTAL` varchar(10) DEFAULT NULL,
  `TELEFONO_PARTICULAR` varchar(45) DEFAULT NULL,
  `CELULAR` varchar(245) DEFAULT NULL,
  `CUIL` varchar(45) NOT NULL,
  `ID_SECTOR` int(11) DEFAULT NULL,
  `HORARIO` varchar(45) DEFAULT NULL,
  `JEFE_INMEDIATO` varchar(255) DEFAULT NULL,
  `OBRA_SOCIAL` varchar(100) DEFAULT NULL,
  `BANCO` varchar(100) DEFAULT NULL,
  `TIENE_CREDENCIAL_ART` varchar(1) DEFAULT NULL COMMENT 'S = SI\nN = NO',
  `LUGAR_DE_TRABAJO` varchar(255) DEFAULT NULL,
  `LUGAR_NACIMIENTO` varchar(255) DEFAULT NULL,
  `CAMISA` varchar(5) DEFAULT NULL,
  `BUZO` varchar(5) DEFAULT NULL,
  `PANTALON` varchar(5) DEFAULT NULL,
  `BOTINES` varchar(5) DEFAULT NULL,
  `CAMPERA` varchar(5) DEFAULT NULL,
  `EQ_LLUVIA` varchar(5) DEFAULT NULL,
  `ID_FOTO_PERSONA` int(11) DEFAULT NULL,
  `ID_PUESTO` int(11) DEFAULT NULL,
  `TIENE_CARNET_CONDUCTOR` varchar(1) DEFAULT NULL COMMENT 'S = SI\nN = NO\nX = N/A',
  `CARNET_CONDUCTOR_DESDE` datetime DEFAULT NULL,
  `CARNET_CONDUCTOR_HASTA` datetime DEFAULT NULL,
  `FECHA_REGISTRO` datetime DEFAULT CURRENT_TIMESTAMP,
  `ID_CATEGORIA_LABORAL` int(11) DEFAULT NULL,
  `LINEA` varchar(45) DEFAULT NULL,
  `FECHA_INGRESO` date DEFAULT NULL,
  `ANTIGUEDAD` varchar(45) DEFAULT NULL,
  `ID_GREMIO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PERSONA`),
  KEY `PERSONAS_ID_ESTADO_CIVIL_idx` (`ID_ESTADO_CIVIL`),
  KEY `PERSONAS_ID_TIPO_IDENTIFICACION_idx` (`ID_TIPO_IDENTIFICACION`),
  KEY `PERSONAS_ID_PAIS_ORIGEN_idx` (`ID_PAIS_ORIGEN`),
  KEY `PERSONAS_ID_SECTOR_idx` (`ID_SECTOR`),
  KEY `PERSONAS_ID_LOCALIDAD_idx` (`ID_LOCALIDAD`),
  KEY `PERSONAS_ID_FOTO_PERSONA_idx` (`ID_FOTO_PERSONA`),
  KEY `PERSONAS_ID_PUESTO_idx` (`ID_PUESTO`),
  KEY `PERSONAS_ID_CATEGORIA_LABORAL_idx` (`ID_CATEGORIA_LABORAL`),
  KEY `PERSONAS_ID_GREMIO_idx` (`ID_GREMIO`),
  CONSTRAINT `PERSONAS_ID_CATEGORIA_LABORAL` FOREIGN KEY (`ID_CATEGORIA_LABORAL`) REFERENCES `categorias_laborales` (`id_categoria_laboral`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_ESTADO_CIVIL` FOREIGN KEY (`ID_ESTADO_CIVIL`) REFERENCES `estados_civiles` (`ID_ESTADO_CIVIL`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_FOTO_PERSONA` FOREIGN KEY (`ID_FOTO_PERSONA`) REFERENCES `foto_personas` (`id_foto_persona`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_GREMIO` FOREIGN KEY (`ID_GREMIO`) REFERENCES `gremios` (`id_gremio`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_LOCALIDAD` FOREIGN KEY (`ID_LOCALIDAD`) REFERENCES `localidades` (`ID_LOCALIDAD`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_PAIS_ORIGEN` FOREIGN KEY (`ID_PAIS_ORIGEN`) REFERENCES `paises` (`ID_PAIS`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_PUESTO` FOREIGN KEY (`ID_PUESTO`) REFERENCES `puestos` (`id_puesto`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_SECTOR` FOREIGN KEY (`ID_SECTOR`) REFERENCES `sectores` (`ID_SECTOR`) ON UPDATE NO ACTION,
  CONSTRAINT `PERSONAS_ID_TIPO_IDENTIFICACION` FOREIGN KEY (`ID_TIPO_IDENTIFICACION`) REFERENCES `tipos_identificacion` (`ID_TIPO_IDENTIFICACION`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (2,'999','DAVID','NAVARRO MORA',2,'123456789',12,'2016-08-01 00:00:00',1,'AAA','a@a.com',333,'1234','a','a torre c lado h','1234','123456789','2123332123131','99-99999999-9',5,'','','','',NULL,'CUP','CARTAGENA','44','M','44','41','M','M',2,2,NULL,NULL,NULL,'2016-10-02 11:25:11',1123,'Roca',NULL,NULL,1);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-12 22:38:17
