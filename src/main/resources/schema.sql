-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: miu-labs.cjmumw2ksa6u.us-east-2.rds.amazonaws.com    Database: car-rental-system
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `reservation_id` bigint NOT NULL AUTO_INCREMENT,
  `effective_date` date DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `reservation_date` date DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `vehicle_id` bigint NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (37,'2024-08-13','2024-08-14','2024-08-13',5,2),(38,'2024-08-14','2024-08-23','2024-08-15',8,2),(41,'2024-08-15','2024-08-16','2024-08-15',8,2);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `id` (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (4,'SUPER_ADMIN','Super Admin'),(5,'ADMIN','Admin'),(6,'USER','User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_pk_idx` (`role_id`),
  CONSTRAINT `role_id_pk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `user_id_pk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (4,4),(3,5),(5,6),(6,6),(7,6),(8,6),(9,6);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `firstname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `lastname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `userId_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'admin','$2a$10$4zu5oGYmEHgUTK8b5vcOgegsd8.99Ex0x3amg7.TOqSFyqgYQgcyy','Admin','Admin','admin@miu.edu','Fairfield, Iowa','[641]-244-1234','2024-07-26 16:26:29','2024-07-26 16:26:30'),(4,'superadmin','$2a$10$IQsAnGA1ZcKDVLSM2fo5u.kznCwhBrN1RPmfScjGxIznHki50jPN2','Super Admin','','superadmin@miu.edu','Fairfield, Iowa','[641]-244-1235','2024-07-26 16:31:11','2024-07-26 16:31:11'),(5,'vle','$2a$10$BMmWBIfR06ciPCZSQNCUzu6kwzvMSY3rvC6w3HsWAVtH7F5dFARfW','Van Nhat','Le','vle@miu.edu','Fairfield, Iowa','[111]-111-1111','2024-08-05 15:06:38','2024-08-05 15:06:38'),(6,'nle','$2a$10$dJEaSZkAJKqteZumKdNt7O5SLEvcRF.3Ad/6xbgfQx654PP/m9wVm','Ngoc Thinh','Le','nle@miu.edu','1000 N 4th Str','[641]-345-2344','2024-08-10 15:16:51','2024-08-10 15:16:51'),(7,'vunguyen','$2a$10$QWwZIZFCNvjLNFvB80s1TO1HrtJNgn5vuJ4Pa25F8gjkybA9gFCsu','Vu','Nguyen','huynhanhvu.nguyen@miu.edu','MIU','[641]-233-0220','2024-08-10 15:18:08','2024-08-10 15:18:08'),(8,'aha','$2a$10$PUGUE73lglLaultYvtf04.Doe/yL6soewthWUzv3iM/Spu2j3N4vC','Anh Tuan','Ha','aha@miu.edu','1000 North 4t Street','[720]-921-4515','2024-08-10 16:47:49','2024-08-10 16:47:49'),(9,'duy.nguyen','$2a$10$PPVu5ED5QNknDz7112arLO6NOx1oKgnPO.Klbzvl5RXapsRIl.v2.','Van Duy','Nguyen','duy.nguyen@miu.edu',NULL,'1234567890','2024-08-12 15:43:03','2024-08-12 15:43:03');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `vehicle_id` bigint NOT NULL AUTO_INCREMENT,
  `make` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `year` int DEFAULT NULL,
  `license_plate_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `rental_price` float NOT NULL,
  `available_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT 'https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png',
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `licensePlateNumber_UNIQUE` (`license_plate_number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES (1,'Mazda','CX-30',2021,'BOSTON 123 JKY Jefferson',234,'rented','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(2,'Toyota','Altis',2021,'IOWA 234 JKY Jefferson',123,'available','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(4,'Honda','CIVIC',2019,'IL 234 JKY Jefferson',567,'rented','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(5,'Toyota','Camry',2020,'IL 987 JKY Jefferson',432,'available','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(8,'Huyndai','Accent',2019,'IW 234',231,'available','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(9,'Chervolet','Joy Plus',2015,'IW 123321',100,'available','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(10,'Tesla','Model S',2020,'CA 4321 JKY Jefferson',456,'available','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png'),(11,'Tesla','Model Y',2022,'BS 123 JKY Jefferson',142,'under maintenance','https://assets.gcs.ehi.com/content/enterprise_cros/data/vehicle/bookingCountries/US/CARS/XXAR.doi.768.high.imageSmallThreeQuarterNodePath.png/1587645528243.png');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'car-rental-system'
--
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-15 12:21:52
