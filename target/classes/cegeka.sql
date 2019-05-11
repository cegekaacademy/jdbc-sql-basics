-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: cegeka
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `player` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `id_team` bigint(20) unsigned DEFAULT NULL,
  `position` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_team` (`id_team`),
  CONSTRAINT `player_ibfk_1` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'Popescu Mihai',6,1,'Defender'),(2,'Ionescu Daniel',10,1,'Central Midfield'),(3,'Pascovici Daniel',1,2,'Goalkeeper'),(4,'Voicu Mihai',3,3,'Defender');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `team` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'FCSB',20),(2,'CFR Cluj',25),(3,'Viitorul Constanta',23);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-06  8:52:15


-- INSERT INTO EMPLOYEE--
insert into employee values (12228, 'Rose', 15, 1968);
insert into employee values (33645, 'Angela', 1, 3443);
insert into employee values (45692, 'Frank', 17, 1608);
insert into employee values (56118, 'Patrik', 7, 1345);
insert into employee values (58725, 'Lisa', 11,2330);
insert into employee values (74197, 'Kimberley', 16, 4372);
insert into employee values (78454, 'Bonnie', 8, 1771);
insert into employee values (83565, 'Michael', 6, 2017);
insert into employee values (98607, 'Todd', 5, 3396);
insert into employee values (99989, 'Joe', 9, 3573);


--INSERT INTO CITY---
insert into city values (1,'Tokio','JPN', '2212', 3000000);
insert into city values (2,'Fukushima', 'JPN', '2232', 1200);
insert into city values (3, 'Nagasaki', 'JPN', '2212', 400000);
insert into city values (4, 'Bucuresti', 'RO', '2241', 11111);


--INSERT INTO GRADES--

insert into grades values(1,0,9);
insert into grades values(2,10,19);
insert into grades values(3,20,29);
insert into grades values(4,30,39);
insert into grades values(5,40,49);
insert into grades values(6,50,59);
insert into grades values(7,60,69);
insert into grades values(8,70,79);
insert into grades values(9,80,89);
insert into grades values(10,90,100);

---INSERT INTO STUDENDS---

insert into students values(1,'Alex',20);
insert into students values(2,'Gigi',35);
insert into students values(3,'Vlad',45);
insert into students values(4,'Maria',55);
insert into students values(5,'Elisabeta',65);
insert into students values(6,'Fiona',75);
insert into students values(7,'Grig',85);
insert into students values(8,'Violeta',86);
insert into students values(9, 'Georgiana', 87);
insert into students values(10,'Alexandra',89);
insert into students values(11,'SixtyNine',91);
insert into students values(12,'AceHood',97);
insert into students values(13, 'Niky Minaj', 100);