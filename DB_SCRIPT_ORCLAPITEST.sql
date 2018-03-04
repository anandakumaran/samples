CREATE DATABASE  IF NOT EXISTS `orclapitest` 
USE `orclapitest`;

--
-- Table structure `DATA`
--

DROP TABLE IF EXISTS `DATA`;

CREATE TABLE `DATA` (
  `ID_KEY` varchar(100) NOT NULL,
  `VALUE` varchar(100) DEFAULT NULL,
  `HASH` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID_KEY`)
);

