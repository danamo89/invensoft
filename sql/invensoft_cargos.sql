DROP TABLE IF EXISTS `cargos`;

CREATE TABLE `cargos` (

  `ID_CARGO` INT(11) NOT NULL AUTO_INCREMENT
, `DESCRIPCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_CARGO`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;