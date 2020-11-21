DROP DATABASE IF EXISTS "tacasadb";

CREATE DATABASE IF NOT EXISTS `tacasadb` /*!40100 DEFAULT CHARACTER SET latin1 */;

use tacasadb;

DROP TABLE IF EXISTS `TActivity`;
DROP TABLE IF EXISTS `TWorkshop`;
DROP TABLE IF EXISTS `TCategory`;


CREATE TABLE IF NOT EXISTS `TWorkshop` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(50) NOT NULL,
`author` varchar(50) NOT NULL,
`objective` varchar(100) NOT NULL,
`category` varchar(50) NOT NULL,
`keywords` varchar(75) NOT NULL,
`duration` time NOT NULL,

PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `TActivity` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(50) NOT NULL,
`description` varchar(100) NOT NULL,
`public_text` varchar(200) NOT NULL,
`duration` time NOT NULL,
`workshop_id` INT NOT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`workshop_id`) REFERENCES tworkshop(`id`)
);

CREATE TABLE IF NOT EXISTS `TCategory` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(50) NOT NULL,
`status` varchar(8) NOT NULL,

PRIMARY KEY (`id`)
);