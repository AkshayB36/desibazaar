CREATE DATABASE  IF NOT EXISTS `testdesi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testdesi`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: testdesi
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `bid_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bid` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bid_id`),
  KEY `FK_9w7lrme4wblf1pe6r3bhsv11y` (`item_id`),
  KEY `FK_4rricb8rqjpqekqxntmcl0sny` (`user_id`),
  CONSTRAINT `FK_4rricb8rqjpqekqxntmcl0sny` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`),
  CONSTRAINT `FK_9w7lrme4wblf1pe6r3bhsv11y` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (1,305,'2015-06-30 10:00:00',2,'akshay@gmail.com'),(2,310,'2015-06-30 10:05:00',2,'varda@gmail.com'),(3,312,'2015-06-30 10:07:00',2,'akshay@gmail.com'),(4,315,'2015-06-30 10:10:00',2,'varda@gmail.com'),(5,320,'2015-06-30 10:13:00',2,'akshay@gmail.com'),(6,330,'2015-06-30 10:20:00',2,'varda@gmail.com'),(17,260,'2015-04-26 14:21:56',33,'ss8990@gmail.com'),(18,270,'2015-04-26 14:28:37',33,'ss8990@gmail.com'),(19,280,'2015-04-26 14:37:31',33,'varda@gmail.com');
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('Electronics',NULL),('Fashion',NULL),('Household',NULL),('Toy',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_price` float DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ends_at` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `selling_price` float DEFAULT NULL,
  `starts_at` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `buyer` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `seller` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK_bcqqk0vvj5nrob6u6vn9tcsvf` (`buyer`),
  KEY `FK_ar77d0xn40oiby5iis3gskhnj` (`category`),
  KEY `FK_f5b5dyh2i7kgmwv2kl24qv13r` (`seller`),
  CONSTRAINT `FK_ar77d0xn40oiby5iis3gskhnj` FOREIGN KEY (`category`) REFERENCES `category` (`name`),
  CONSTRAINT `FK_bcqqk0vvj5nrob6u6vn9tcsvf` FOREIGN KEY (`buyer`) REFERENCES `user` (`email`),
  CONSTRAINT `FK_f5b5dyh2i7kgmwv2kl24qv13r` FOREIGN KEY (`seller`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,100,'Suitable for hob types electric, gas, ceramic, halogen and solid hot plates','2014-07-30 15:00:00','img/cooker.jpg','Prestige Cooker',-1,NULL,NULL,'2013-10-30 14:24:41','ToStart',NULL,'Household','ss8990@gmail.com'),(2,300,'4.7\" Touchscreen A8 Chip with 64-bit Architecture and M8 Motion Co-processor 16 GB Memory','2015-06-30 10:00:00','img/iphone6.jpg','IPhone6',-1,NULL,NULL,'2013-10-30 00:00:00','Active',NULL,'Electronics','ss8990@gmail.com'),(8,767,'Recommended age: 2 to 4 years Standard Abacus 1 to 10','2015-04-30 18:30:30','img/abacus.jpg','Abacus Toy',4,'Worth every dollar',800,'2015-04-27 13:30:30','ToStart','varda@gmail.com','Toy','ss8990@gmail.com'),(33,250,'The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.','2015-04-26 15:00:00','img/712971-topic_ipad_2_0png','IPAD2',-1,NULL,0,'2015-04-26 14:00:00','Active',NULL,NULL,'ss8990@gmail.com');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('akshay@gmail.com',NULL,NULL,NULL,'akshay',0),('ss8990@gmail.com','UT Dr Charlotte','sarath','9848012345','sarath',0),('varda@gmail.com',NULL,NULL,NULL,'varda',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subscription`
--

DROP TABLE IF EXISTS `user_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_subscription` (
  `item_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  KEY `FK_b05rcgebt4wp86cy9gfcfrha5` (`email`),
  KEY `FK_5wxnr7d0f7af8qrat5gwei04u` (`item_id`),
  CONSTRAINT `FK_5wxnr7d0f7af8qrat5gwei04u` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FK_b05rcgebt4wp86cy9gfcfrha5` FOREIGN KEY (`email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subscription`
--

LOCK TABLES `user_subscription` WRITE;
/*!40000 ALTER TABLE `user_subscription` DISABLE KEYS */;
INSERT INTO `user_subscription` VALUES (2,'akshay@gmail.com'),(2,'varda@gmail.com'),(33,'ss8990@gmail.com'),(33,'varda@gmail.com');
/*!40000 ALTER TABLE `user_subscription` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-26 21:13:16
