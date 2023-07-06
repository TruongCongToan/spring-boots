USE `security`;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `full_name` varchar(100) NOT NULL,
                        `user_name` varchar(100) DEFAULT NULL,
                        `pass_word` varchar(45) NOT NULL,
                        `created_date` date DEFAULT NULL,
                        `created_by` varchar(100) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
