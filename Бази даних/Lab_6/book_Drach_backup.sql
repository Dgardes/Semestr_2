-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: book_Drach
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_ID` int NOT NULL AUTO_INCREMENT,
  `b_name` varchar(100) NOT NULL,
  `b_author` varchar(100) NOT NULL,
  `b_year` year NOT NULL,
  `b_price` decimal(7,2) DEFAULT '0.00',
  `b_count` int DEFAULT '0',
  `b_cat_ID` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`book_ID`),
  KEY `b_cat_ID` (`b_cat_ID`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`b_cat_ID`) REFERENCES `catalogs` (`cat_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'JavaScript г ЄЁиҐ­?','ђҐў  Ћ.Ќ.',2008,39.90,10,1),(2,'Visual FoxPro 9.0','Љ«ҐЇЁ­Ё­ ‚.Ѓ.',2007,627.00,2,1),(3,'C++ џЄ ў?­ у','’Ё¬®д?хў ‚.‚.',2009,207.10,4,1),(4,'‘вў®аҐ­­п Їа®Ја ¬ §  ¤®Ї®¬®Ј®о C#','” а®­®ў ‚.‚.',2008,160.55,1,1),(5,'Delphi. Ќ а®¤­? Ї®а ¤Ё','?ЄаЁ«м Ђ.Ђ.',2007,230.85,6,1),(6,'Delphi. Џ®ў­Ґ ЄҐа?ў­Ёжвў®','‘ге аҐў Њ.',2008,475.00,6,1),(7,'Џа®дҐб?©­Ґ Їа®Ја ¬гў ­­п ­  PHP','?«®бб­Ґ©Ј« „¦.',2006,293.55,5,1),(8,'„®бЄ®­ «Ё© Є®¤','Њ ЄЄ®­­Ґ«« ‘.',2007,732.45,1,1),(9,'Џа ЄвЁЄ  Їа®Ја ¬гў ­­п','ЉҐа­ЁЈ ­ Ѓ.',2004,203.30,12,1),(10,'ЏаЁ­жЁЇЁ ¬ аиагвЁ§ ж?х г Internet','•Ґ«ҐЎЁ ‘.',2001,406.60,4,2),(11,'Џ®игЄ г Internet','ѓгбҐў ‚.‘.',2004,101.65,2,2),(12,'Web-Є®­бвагоў ­­п','„гў ­®ў Ђ.Ђ.',2003,168.15,6,2),(13,'‘ ¬®ўзЁвҐ«м ?­вҐа­Ґв','Љ®­бв ­вЁ­®ў ћ.Џ.',2009,114.95,4,2),(14,'Џ®Їг«па­? ?­вҐа­Ґв-Ўа г§ҐаЁ','Њ аЁ­Ё­ ‘.Ђ.',2007,77.90,6,2),(15,'‘Ї?«Єгў ­­п ў ?­вҐа­Ґв?','ќЄб«Ґа Ђ.',2006,80.75,5,2),(16,'Ѓ §Ё ¤ ­Ёе','Њ «ЁеЁ­  Њ.Џ.',2006,309.70,2,3),(17,'Ѓ §Ё ¤ ­Ёе. ђ®§а®ЎЄ  ¤®¤ вЄ?ў','ђг¤ЁЄ®ў  ‹.‚.',2006,179.55,6,3),(18,'ђ®§ЄаЁввп в у¬­Ёжм SQL','ЋЇЇҐ«м ќ.',2007,190.00,3,3),(19,'Џа ЄвЁЄг¬ § Access','‡®«®в®ў  ‘.€.',2007,82.65,6,3),(20,'Љ®¬Ї\'овҐа­? ¬ҐаҐ¦?','’ ­­Ґ­Ў г¬ ќ.',2007,598.50,6,4),(21,'ЊҐаҐ¦?. Џ®игЄ ­ҐбЇа ў­®бвҐ©','ЃЁЈҐ«®г ‘.',2005,412.30,4,4),(22,'ЃҐ§ЇҐЄ  ¬ҐаҐ¦','ЃаҐЈЈ ђ.',2006,438.90,5,4),(23,'Ђ­ «?§ в  ¤? Ј­®бвЁЄ  Є®¬Ї\'овҐа­Ёе ¬ҐаҐ¦','•®Ј¤ « „¦.',2001,326.80,3,4),(24,'‹®Є «м­? ®ЎзЁб«оў «м­? ¬ҐаҐ¦?','…Ї ­Ґи­ЁЄ®ў Ђ.',2005,77.90,8,4),(25,'–Ёда®ў  д®в®Ја д?п','Ќ ¤Ґ¦¤Ё­ Ќ.',2004,141.55,20,5),(26,'Њг§Ёз­Ё© Є®¬Ї\'овҐа ¤«п Ј?в аЁбв ','ЏҐвҐ«Ё­ ђ.ћ.',2004,206.15,15,5),(27,'‚?¤Ґ® ­  ЏЉ','”Ґ¤®а®ў  Ђ.',2003,219.45,10,5),(28,'Њг«мвЁЇ«?Є ж?п г Flash','ЉЁаЄЇ ваЁЄ ѓ.',2006,200.45,20,5),(29,'‡ ЇЁб CD в  DVD','ѓг«мвпҐў Ђ.Љ.',2003,158.65,12,5),(30,'‡ ЇЁб в  ®Ўа®ЎЄ  §ўгЄг ­  Є®¬Ї\'овҐа?','‹®п­Ёз Ђ.Ђ.',2008,48.45,8,5);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogs`
--

DROP TABLE IF EXISTS `catalogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogs` (
  `cat_ID` int NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(20) NOT NULL,
  PRIMARY KEY (`cat_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogs`
--

LOCK TABLES `catalogs` WRITE;
/*!40000 ALTER TABLE `catalogs` DISABLE KEYS */;
INSERT INTO `catalogs` VALUES (1,'Џа®Ја ¬гў ­­п'),(2,'?­вҐа­Ґв'),(3,'Ѓ §Ё ¤ ­Ёе'),(4,'Љ®¬Ї\'овҐа­? ¬ҐаҐ¦?'),(5,'Њг«мвЁ¬Ґ¤? ');
/*!40000 ALTER TABLE `catalogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_ID` int NOT NULL AUTO_INCREMENT,
  `o_user_ID` int NOT NULL,
  `o_book_ID` int NOT NULL,
  `o_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `o_number` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_ID`),
  KEY `o_book_ID` (`o_book_ID`),
  KEY `o_user_ID` (`o_user_ID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`o_book_ID`) REFERENCES `books` (`book_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`o_user_ID`) REFERENCES `users` (`user_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,3,8,'2009-01-04 10:39:38',1),(2,6,10,'2009-02-10 09:40:29',2),(3,1,20,'2009-02-18 13:41:05',4),(4,4,20,'2009-03-10 18:20:00',1),(5,3,20,'2009-03-17 19:15:36',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_ID` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) NOT NULL,
  `u_patronymic` varchar(20) NOT NULL,
  `u_surname` varchar(20) NOT NULL,
  `u_phone` varchar(12) DEFAULT NULL,
  `u_email` varchar(20) DEFAULT NULL,
  `u_status` enum('active','passive','lock','gold') DEFAULT 'passive',
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ћ«ҐЄб ­¤а','‚ «Ґа?©®ўЁз','?ў ­®ў','58-98-78','ivanov@gmail.com','active'),(2,'‘ҐаЈ?©','?ў ­®ўЁз','‹®бҐў','90-57-77','lo-sev@gmail.com','passive'),(3,'?Ј®а','ЊЁЄ®« ©®ўЁз','‘Ё¬®­®ў','95-66-61','si-monov@mail.ua','active'),(4,'Њ ЄбЁ¬','ЏҐва®ўЁз','Љг§­Ґж®ў',NULL,'kuz-netsov@mail.ua','active'),(5,'Ђ­ в®«?©','ћа?©®ўЁз','ЏҐва®ў',NULL,NULL,'lock'),(6,'Ћ«ҐЄб ­¤а','Ћ«ҐЄб ­¤а®ўЁз','Љ®а­ҐҐў','89-78-36','korneev@i.ua','gold'),(8,'Ћ«мЈ ','ЏҐва?ў­ ','”Ґ¤®а®ў ','53-56-58',NULL,'active'),(9,'?аЁ­ ','‚®«®¤Ё¬Ёа?ў­ ','‡®«®в®ў ',NULL,'zolotova@i.ua','gold');
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

-- Dump completed on 2026-04-08 22:45:16
