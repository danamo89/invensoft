ALTER TABLE `invensoft`.`personas` 

ADD COLUMN `CAMISA` INT(2) NULL AFTER `LUGAR_NACIMIENTO`
,
ADD COLUMN `BUZO` VARCHAR(1) NULL AFTER `CAMISA`
,
ADD COLUMN `PANTALON` INT(2) NULL AFTER `BUZO`
,
ADD COLUMN `BOTINES` INT(2) NULL AFTER `PANTALON`
,
ADD COLUMN `CAMPERA` VARCHAR(1) NULL AFTER `BOTINES`
,
ADD COLUMN `EQ_LLUVIA` INT(2) NULL AFTER `CAMPERA`;

ALTER TABLE `invensoft`.`personas` 
ADD COLUMN `FOTO` BLOB NULL AFTER `EQ_LLUVIA`;

