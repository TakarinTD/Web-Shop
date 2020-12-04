-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: dbdemoshop
-- ------------------------------------------------------
-- Server version	8.0.19

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

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `img_url` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Women',NULL,NULL),(16,'Men',NULL,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `guest_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_detail_id` int NOT NULL,
  `unit_price` float DEFAULT NULL,
  `quantities_product` int DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `total_product_pay` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_detail_order1_idx` (`order_id`),
  KEY `fk_order_detail_product_detail1_idx` (`product_detail_id`),
  CONSTRAINT `fk_order_detail_order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_order_detail_product_detail1` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (5,11,61,NULL,2,NULL,22000),(6,11,20,NULL,2,NULL,24000),(7,12,65,NULL,2,NULL,22800),(8,12,19,NULL,3,NULL,36000),(9,13,18,NULL,4,NULL,24000),(10,13,23,NULL,3,NULL,18000),(11,14,23,NULL,3,0.5,18000),(12,14,19,NULL,3,0.5,18000),(13,14,18,NULL,3,0.5,18000),(14,15,22,NULL,2,0.5,12000),(15,15,20,NULL,2,0.5,12000),(16,16,21,NULL,3,0.5,18000),(17,17,23,NULL,3,0.5,18000),(18,18,23,NULL,3,0.5,18000),(19,19,19,NULL,3,0.5,18000),(20,20,19,NULL,3,0.5,18000),(21,21,46,NULL,2,0.2,19201.6),(22,22,19,NULL,2,0.5,12000),(23,22,18,NULL,2,0.5,12000),(24,22,23,NULL,2,0.5,12000),(25,23,21,NULL,2,0.5,12000),(26,23,18,NULL,2,0.5,12000),(27,23,47,NULL,3,0.2,28804.8);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `reciver_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `delivery_method` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `payment_method` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_pay` float DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `note` text COLLATE utf8_unicode_ci,
  `order_date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user1_idx` (`user_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'Phạm Nguyên',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'Nguyễn Văn A',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1,'Nguyễn Quỳnh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,2,'Nguyễn Quỳnh','Hà Nội',NULL,NULL,NULL,0,0,'0',NULL,NULL),(5,2,'Nguyễn Quỳnh Anh','Hà Nội',NULL,NULL,NULL,0,0,'0',NULL,NULL),(7,2,'Nguyễn Quỳnh Anh','Hà Nội',NULL,NULL,NULL,0,0,'0',NULL,NULL),(8,NULL,'Nguyễn Quỳnh Anh','Hà Nội',NULL,NULL,NULL,0,0,'0',NULL,NULL),(9,NULL,'Nguyễn Quỳnh Anh','Hà Nội',NULL,NULL,NULL,0,0,'0',NULL,NULL),(10,NULL,'Nguyễn Quỳnh Anh','Hà Nội',NULL,NULL,NULL,0,0,'0',NULL,NULL),(11,NULL,'Nguyên','Đại La - Hai Bà Trưng - Hà Nội hahaha','0947060528','flash','cod',46000,0,'Không có yêu cầu gì thêm','2020-11-26 16:33:23','2020-11-26 16:33:23'),(12,NULL,'Nguyên Phạm','Hà Nội','0947060528','slow','cod',58800,0,'không có','2020-11-26 16:57:12','2020-11-26 16:57:12'),(13,NULL,'Nguyên Phạm','Đại La - Hai Bà Trưng - Hà Nội haha','0947060528','slow','cod',45100,0,'giao vào giờ hành chính','2020-11-30 09:40:51','2020-11-30 09:40:51'),(14,NULL,'Nguyên Phạm Văn','Thanh Hóa','0947060528','slow','cod',57700,0,'hahahahahahaha','2020-11-30 10:49:34','2020-11-30 10:49:34'),(15,NULL,'Nguyên hahahaha','Thanh Hóa','0947060528','flash','cod',27200,0,'sdfsdfdsf','2020-11-30 11:02:30','2020-11-30 11:02:30'),(16,NULL,'Nguyên','Đại La - Hai Bà Trưng - Hà Nội hahaha','0947060528','flash','cod',20900,0,'test đơn hàng','2020-11-30 11:07:45','2020-11-30 11:07:45'),(17,NULL,'hhahahaha','Thanh Nhàn, Hà Nội','0947060528','slow','cod',19900,0,'Không có yêu cầu đặc biệt','2020-11-30 11:57:33','2020-11-30 11:57:33'),(18,NULL,'Nguyên','Hà Nội','0947060528','slow','cod',19900,0,'sdfsdf','2020-11-30 12:06:17','2020-11-30 12:06:17'),(19,NULL,'kjsdhfksdfh','Hà Nội','0947060528','slow','cod',19900,0,'Không có','2020-11-30 15:28:39','2020-11-30 15:28:39'),(20,NULL,'Nguyên Phạm','Đại La - Hai Bà Trưng - Hà Nội hahaha','0947060528','flash','cod',20900,0,'dsfsdfsdfdf','2020-11-30 15:31:26','2020-11-30 15:31:26'),(21,1,'Phạm Văn Nguyên hahaha','Hà Nội,','0947060528','slow','cod',21161.6,0,'không','2020-12-02 23:46:53','2020-12-02 23:46:53'),(22,1,'Phạm Văn Nguyên hahaha','Hà Nội,','0947060528','slow','cod',38800,0,'không có yêu cầu gì','2020-12-03 00:03:35','2020-12-03 00:03:35'),(23,1,'Nguyễn Văn A','Hà Nội,','0947060528','slow','cod',56444.8,0,'Giao hàng vào chủ nhật','2020-12-03 00:10:11','2020-12-03 00:10:11');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `product_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `img_url` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `purchase_price` float DEFAULT NULL,
  `sale_price` float DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `promotion_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `fk_product_promotion1_idx` (`promotion_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_promotion1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'iphone','product1.jpg',1000,1200,'CLorem ipsum dolor sit amet, consectetur adipiscing elit. Ut lobortis malesuada mi id tristique. Sed ipsum nisi, dapibus at faucibus non, dictum a diam. Nunc vitae interdum diam. Sed finibus, justo vel maximus facilisis, sapien turpis euismod tellus, vulputate semper diam ipsum vel tellus.',NULL,NULL,3),(15,16,'Áo phông','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng','2020-11-10 15:13:22','2020-11-10 15:13:22',3),(16,1,'SamSung','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,4),(17,1,'Áo dạ hội','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,4),(18,1,'Áo dài','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,3),(19,1,'Váy dạ hội','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,3),(20,1,'Váy','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,4),(21,1,'Váy','product1.jpg',1000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,4),(22,1,'Váy','product1.jpg',10000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,3),(23,1,'Váy','product1.jpg',10000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,3),(25,16,'Áo vest tây','product3-1.jpg',10000,12001,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,4),(26,16,'Áo dạ mùa đông','product3-1.jpg',10000,12002,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,4),(27,16,'Áo vest tây','product3-1.jpg',10000,12003,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(28,16,'Áo dạ mùa đông','product3-1.jpg',10000,12004,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(29,16,'Áo vest tây','product3-1.jpg',10000,12005,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(30,16,'Áo dạ mùa đông','product3-1.jpg',10000,12006,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(31,16,'Áo vest tây','product3-1.jpg',10000,12004,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(32,16,'Áo dạ mùa đông','product3-1.jpg',10000,12003,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(33,16,'Áo vest tây','product3-1.jpg',10000,12012,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(34,16,'Áo dạ mùa đông','product3-1.jpg',10000,12000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(35,16,'Áo vest tây','product3-1.jpg',10000,11000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(36,16,'Áo dạ mùa đông','product3-1.jpg',10000,12009,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(37,16,'Áo vest tây','product3-1.jpg',10000,12008,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(38,16,'Áo dạ mùa đông','product3-1.jpg',10000,12004,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(39,16,'Áo dạ mùa đông','product3-1.jpg',10000,11400,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(40,16,'Áo dạ mùa đông','product3-1.jpg',10000,12006,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(41,16,'Áo dạ mùa đông','product3-1.jpg',10000,12007,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(42,16,'Áo vest tây','product3-1.jpg',10000,13000,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(43,16,'Áo vest tây','product3-1.jpg',10000,12004,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(44,16,'Áo vest tây','product3-1.jpg',10000,12504,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(45,16,'Áo vest tây','product3-1.jpg',10000,12006,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(46,16,'Áo dạ mùa đông','product3-1.jpg',10000,12002,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL),(47,16,'Áo dạ mùa đông','product3-1.jpg',10000,12111,'Sản phẩm mới nhất, rất chất lượng',NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_color`
--

DROP TABLE IF EXISTS `product_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_color`
--

LOCK TABLES `product_color` WRITE;
/*!40000 ALTER TABLE `product_color` DISABLE KEYS */;
INSERT INTO `product_color` VALUES (1,'red',NULL,NULL),(2,'blue',NULL,NULL),(3,'white',NULL,NULL),(5,'Vàng',NULL,NULL);
/*!40000 ALTER TABLE `product_color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `product_color_id` int NOT NULL,
  `product_size_id` int NOT NULL,
  `image` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`product_id`,`product_color_id`,`product_size_id`),
  KEY `fk_product_detail_product_color1_idx` (`product_color_id`),
  KEY `fk_product_detail_product_size1_idx` (`product_size_id`),
  KEY `fk_product_detail_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_detail_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_product_detail_product_color1` FOREIGN KEY (`product_color_id`) REFERENCES `product_color` (`id`),
  CONSTRAINT `fk_product_detail_product_size1` FOREIGN KEY (`product_size_id`) REFERENCES `product_size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (3,1,1,1,'product1.jpg'),(8,1,1,2,'product1.jpg'),(9,1,1,3,'product1.jpg'),(18,15,1,2,'product1.jpg'),(19,15,1,3,'product3-1.jpg'),(20,15,2,1,'product9.jpg'),(21,15,2,2,'product3-1.jpg'),(22,15,2,3,'product3-1.jpg'),(23,15,1,1,'product3-1.jpg'),(24,16,1,1,'product9.jpg'),(25,16,1,2,'product9.jpg'),(26,16,1,3,'product9.jpg'),(27,16,2,1,'product9.jpg'),(28,16,2,2,'product9.jpg'),(29,16,2,3,'product9.jpg'),(30,17,1,1,'product9.jpg'),(31,17,1,2,'product9.jpg'),(32,17,1,3,'product9.jpg'),(33,18,1,1,'product9.jpg'),(34,19,1,1,'product9.jpg'),(35,20,1,1,'product9.jpg'),(36,21,1,1,'product9.jpg'),(37,22,1,1,'product3-1.jpg'),(45,23,1,1,'product3-1.jpg'),(46,25,1,1,'product3-1.jpg'),(47,26,1,1,'product3-1.jpg'),(48,27,1,1,'product3-1.jpg'),(49,28,1,1,'product3-1.jpg'),(50,29,1,1,'product3-1.jpg'),(51,25,1,2,'product3-1.jpg'),(52,26,1,2,'product3-1.jpg'),(53,27,1,2,'product3-1.jpg'),(54,28,1,2,'product3-1.jpg'),(55,29,1,2,'product3-1.jpg'),(56,30,1,1,'product3-1.jpg'),(57,31,1,1,'product3-1.jpg'),(58,32,1,1,'product3-1.jpg'),(59,33,1,1,'product3-1.jpg'),(60,34,1,1,'product3-1.jpg'),(61,35,1,1,'product3-1.jpg'),(62,36,1,1,'product3-1.jpg'),(63,37,1,1,'product3-1.jpg'),(64,38,1,1,'product3-1.jpg'),(65,39,1,1,'product3-1.jpg'),(66,1,2,1,'product3-1.jpg'),(67,1,2,3,'product3-1.jpg'),(68,1,2,2,'product3-1.jpg'),(69,1,3,1,'product3-1.jpg'),(70,1,3,2,'product3-1.jpg'),(71,1,3,3,'product3-1.jpg'),(72,40,1,1,'product3-1.jpg'),(73,41,1,1,'product3-1.jpg'),(74,42,1,1,'product3-1.jpg'),(75,43,1,1,'product3-1.jpg'),(76,44,1,1,'product3-1.jpg'),(77,45,1,1,'product3-1.jpg'),(78,46,1,1,'product3-1.jpg'),(79,47,1,1,'product3-1.jpg');
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `size_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
INSERT INTO `product_size` VALUES (1,'S',NULL,NULL),(2,'M',NULL,NULL),(3,'L',NULL,NULL),(5,'M',NULL,NULL);
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `promotion_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `img_url` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (3,'Siêu sales 11-11','2020-11-10 16:35:59',NULL,NULL,NULL,0.5),(4,'Siêu sales tháng 12','2020-11-10 16:35:59',NULL,NULL,NULL,0.2),(5,'Siêu sales 11-11','2020-11-10 16:38:30',NULL,NULL,NULL,0.5),(6,'Siêu sales tháng 12','2020-11-10 16:38:30',NULL,NULL,NULL,0.2),(7,'Siêu sales cuối năm','2020-11-10 16:39:59',NULL,NULL,NULL,0.5),(8,'Siêu sales tết dương lịch','2020-11-10 16:39:59',NULL,NULL,NULL,0.2),(9,'Siêu sales cuối năm','2020-11-10 16:41:11',NULL,NULL,NULL,0.5),(10,'Siêu sales tết dương lịch','2020-11-10 16:41:11',NULL,NULL,NULL,0.2),(11,'Siêu sales cuối năm','2020-11-10 16:43:13',NULL,NULL,NULL,0.5),(12,'Siêu sales tết dương lịch','2020-11-10 16:43:13',NULL,NULL,NULL,0.2);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_review`
--

DROP TABLE IF EXISTS `reply_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `review_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reply_review_user1_idx` (`user_id`),
  KEY `fk_reply_review_review1_idx` (`review_id`),
  CONSTRAINT `fk_reply_review_review1` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`),
  CONSTRAINT `fk_reply_review_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_review`
--

LOCK TABLES `reply_review` WRITE;
/*!40000 ALTER TABLE `reply_review` DISABLE KEYS */;
INSERT INTO `reply_review` VALUES (1,2,'Bạn m ua ở đâu vậy','2020-12-04 00:05:19','2020-12-04 00:05:19',14),(2,3,'Đổi trả hàng như thế nào bạn','2020-12-04 00:06:19','2020-12-04 00:06:19',14),(3,3,'tôi reply lại comment của bạn','2020-12-04 13:19:00','2020-12-04 13:19:00',14),(4,3,'tôi reply lại bạn lần 2','2020-12-04 13:35:29','2020-12-04 13:35:29',14),(5,3,'tôi reply comment của bạn lần 3 :))','2020-12-04 13:49:27','2020-12-04 13:49:27',14),(6,3,'test reply comment ','2020-12-04 14:07:35','2020-12-04 14:07:35',13),(7,3,'test reply comment này lần thứ 2','2020-12-04 14:11:32','2020-12-04 14:11:32',13),(8,3,'test reply comment này lần thứ 3','2020-12-04 14:24:14','2020-12-04 14:24:14',13);
/*!40000 ALTER TABLE `reply_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  `title` text COLLATE utf8_unicode_ci,
  `comment` text COLLATE utf8_unicode_ci,
  `img` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ratting` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_review_product1_idx` (`product_id`),
  KEY `fk_review_user1_idx` (`user_id`),
  CONSTRAINT `fk_review_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_review_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,1,'Sản phẩm chất lượng','Chất lượng sản phẩm rất tốt trong tầm giá, ủng hộ shop',NULL,4,'2020-11-26 16:33:23','2020-11-26 16:33:23'),(2,1,2,'Chất lượng tốt','Đơn hàng được vận chuyển nhanh chóng, đóng gói cẩn thận, sản phẩm tốt trong tầm giá',NULL,5,'2020-11-26 16:33:23','2020-11-26 16:33:23'),(4,1,1,'đfgfdgfdsgkjdfhgkdf','dklfgjslkfdjgkfdjglkdsfjglksdfg',NULL,5,'2020-12-03 16:39:27','2020-12-03 16:39:27'),(5,1,1,'test prepend','sản phẩm tốt hahahahahaha',NULL,4,'2020-12-03 16:58:48','2020-12-03 16:58:48'),(6,1,1,'édfsdfsd','dsfsdfsdfdsf',NULL,5,'2020-12-03 17:10:15','2020-12-03 17:10:15'),(7,1,1,'édfsdfsdsadsgdsagadgd','dsfsdfadgasdagsdgdgsdfdsf',NULL,5,'2020-12-03 17:11:39','2020-12-03 17:11:39'),(8,1,1,'test prepend lần 2','dfdsgdagdgkdkjaslkdsjglkdjglkdjglkdjglkdjgl',NULL,4,'2020-12-03 17:18:26','2020-12-03 17:18:26'),(9,1,1,'sản phẩm đẹp','chất vải tốt, phù hợp trong tầm giá',NULL,5,'2020-12-03 23:08:46','2020-12-03 23:08:46'),(10,1,1,'test lần n','hehehehehehehehe',NULL,4,'2020-12-03 23:18:00','2020-12-03 23:18:00'),(11,1,1,'test lần thứ nnnn','fsdkhfksadhfkahfdksahdfk',NULL,5,'2020-12-04 00:05:00','2020-12-04 00:05:00'),(12,1,1,'test lần thứ 1000','fsdkhfksadhfkahfdksahdfk',NULL,5,'2020-12-04 00:05:19','2020-12-04 00:05:19'),(13,1,1,'fgdfhgsdfhfdh','dsfsdagsdhdhsdhhasdh',NULL,0,'2020-12-04 00:06:09','2020-12-04 00:06:09'),(14,1,1,'test lần thứ mnnn','addgdsgdsgsdagsdg',NULL,5,'2020-12-04 00:43:55','2020-12-04 00:43:55');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `verification_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nguyen@gmail.com','$2a$10$hfG4XHgUBw7IBrXKyRqf..rv5mrljKD.cNObXgX1RA4csw.p1BZqe','Phạm Văn Nguyên',NULL,'Hà Nội','0947060528',NULL,1,'2020-11-10 11:45:41','2020-12-03 22:38:47'),(2,'nguyenpham@gmail.com','$2a$10$XfH895FPu6myDctO84v3/OunJz0/5BfNhCzSz5emtdu466xR5Xjh6','NguyenPham',NULL,NULL,NULL,NULL,0,'2020-11-10 11:45:41','2020-11-10 11:45:41'),(3,'nguyenhaha@gmail.com','$2a$10$XfH895FPu6myDctO84v3/OunJz0/5BfNhCzSz5emtdu466xR5Xjh6','Nguyen dep trai',NULL,'Hà Nội','0947060528',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(1,2),(3,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_detail_id` int NOT NULL,
  `quantity_available` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_warehouse_product_detail1_idx` (`product_detail_id`),
  CONSTRAINT `fk_warehouse_product_detail1` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,3,5),(2,8,6),(3,9,7),(4,18,0),(5,19,3),(6,20,2),(7,21,6),(8,22,4),(9,23,0),(10,24,7),(11,25,3),(12,26,1),(13,27,6),(14,28,8),(15,29,3),(16,30,45),(17,31,6),(18,32,4),(19,33,8),(20,34,3),(21,35,4),(22,36,8),(23,37,3),(24,45,4),(25,46,0),(26,47,3),(27,48,7),(28,49,9),(29,50,2),(30,51,1),(31,52,3),(32,53,4),(33,54,5),(34,55,6),(35,56,7),(36,57,6),(37,58,4),(38,59,3),(39,60,0),(40,61,0),(41,62,7),(42,63,8),(43,64,6),(44,65,0),(45,66,2),(46,67,3),(47,68,1),(48,69,4),(49,70,9),(50,71,4),(51,72,3),(52,73,15),(53,74,20),(54,75,6),(55,76,8),(56,77,9),(57,78,8),(58,79,5);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-04 15:39:12
