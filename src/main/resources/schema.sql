-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: car-rental-system
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles`
(
    `role_id`     int                                                    NOT NULL AUTO_INCREMENT,
    `role_name`   varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `id` (`role_id`),
    UNIQUE KEY `role_name` (`role_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles`
    DISABLE KEYS */;
INSERT INTO `roles`
VALUES (4, 'SUPER_ADMIN', 'Super Admin'),
       (5, 'ADMIN', 'Admin'),
       (6, 'USER', 'User');
/*!40000 ALTER TABLE `roles`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `role_id_pk_idx` (`role_id`),
    CONSTRAINT `role_id_pk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `user_id_pk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles`
    DISABLE KEYS */;
INSERT INTO `user_roles`
VALUES (3, 5),
       (4, 4);
/*!40000 ALTER TABLE `user_roles`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `user_id`    int                                                     NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `password`   varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `firstname`  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci       DEFAULT NULL,
    `lastname`   varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci       DEFAULT NULL,
    `email`      varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `address`    varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci      DEFAULT NULL,
    `phone`      varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `created_at` timestamp                                               NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp                                               NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `userId_UNIQUE` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users`
    DISABLE KEYS */;
INSERT INTO `users`
VALUES (3, 'admin', '$2a$10$4zu5oGYmEHgUTK8b5vcOgegsd8.99Ex0x3amg7.TOqSFyqgYQgcyy', 'Admin', 'Admin', 'admin@miu.edu',
        'Fairfield, Iowa', '[641]-244-1234', '2024-07-26 16:26:29', '2024-07-26 16:26:30'),
       (4, 'superadmin', '$2a$10$IQsAnGA1ZcKDVLSM2fo5u.kznCwhBrN1RPmfScjGxIznHki50jPN2', 'Super Admin', '',
        'superadmin@miu.edu', 'Fairfield, Iowa', '[641]-244-1235', '2024-07-26 16:31:11', '2024-07-26 16:31:11');
/*!40000 ALTER TABLE `users`
    ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars`
(
    `car_id`               int NOT NULL AUTO_INCREMENT,
    `make`                  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `model`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `year`                  int DEFAULT NULL,
    `license_plate_number`  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `rental_price`          float NOT NULL,
    `is_available`          boolean DEFAULT NULL,
    PRIMARY KEY (`car_id`),
    UNIQUE KEY `licensePlateNumber_UNIQUE` (`license_plate_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars`
(
    `car_id`               int NOT NULL AUTO_INCREMENT,
    `make`                  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `model`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `year`                  int DEFAULT NULL,
    `license_plate_number`  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `rental_price`          float NOT NULL,
    `is_available`          boolean DEFAULT NULL,
    PRIMARY KEY (`car_id`),
    UNIQUE KEY `licensePlateNumber_UNIQUE` (`license_plate_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping routines for database 'car-rental-system'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-07-27 21:55:29
