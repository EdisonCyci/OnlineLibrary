-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `ISBN` varchar(20) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `EditionNumber` int(11) NOT NULL DEFAULT '0',
  `CopyRight` varchar(45) NOT NULL,
  `Author` varchar(100) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('0132121360','Android for Programmers: An App-Driven Approach',1,'2012','Paul, Harvey & Abby Dietel, Michael Morgano',30),('0132151006','Internet & World Wide Web How to Program',5,'2012','Paul, Harvey & Abby Dietel',30),('0132575655','Java How to Program, Late Objects Version',10,'2015','Paul & Harvey Dietel',30),('013299044X','C How to Program',7,'2013','Paul & Harvey Dietel',30),('0132990601','Simply Visual Basic 2010',4,'2013','Paul, Harvey & Abby Dietel',30),('0133378713','C++ How to Program',9,'2014','Paul & Harvey Dietel',30),('0133379337','Visual C# 2012 How to Program',5,'2014','Paul & Harvey Dietel',30),('0133406954','Visual Basic 2012 How to Program',6,'2014','Paul, Harvey & Abby Dietel',30),('0133570924','Android for Programmers: An App-Driven Approach, Volume 1',2,'2014','Paul, Harvey & Abby Dietel',30),('0133764036','Android How to Program',2,'2015','Paul, Harvey & Abby Dietel',30),('0133807800','Java How to Program',10,'2015','Paul & Harvey Dietel',30),('0136151574','Visual C++ How to Program',2,'2008','Paul & Harvey Dietel, Dan Quirk',30);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowedbooks`
--

DROP TABLE IF EXISTS `borrowedbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrowedbooks` (
  `ISBN` int(11) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `EditionNumber` int(11) NOT NULL DEFAULT '0',
  `CopyRight` varchar(4) NOT NULL,
  `Authors` varchar(100) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '0',
  `borrowedBy` int(11) NOT NULL,
  `borrowedOn` datetime NOT NULL,
  `daysOverdue` int(5) DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `borrowedBy_idx` (`borrowedBy`),
  CONSTRAINT `borrowedBy` FOREIGN KEY (`borrowedBy`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowedbooks`
--

LOCK TABLES `borrowedbooks` WRITE;
/*!40000 ALTER TABLE `borrowedbooks` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowedbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `type` char(1) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Edison','Cyci','Edisoncuci@yahoo.com','A','root'),(2,'Peter','Brown','peterb@gmail.com.com','U','brownsauce'),(3,'Samuel','Smith','sSmith@gmail.com','U','smithers'),(4,'Tom','Thumb','ThumbTom@gmail.com','U','bigthumb');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-21 18:57:33
