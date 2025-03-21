/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `proyecto_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `proyecto_db`;

CREATE TABLE IF NOT EXISTS `companies` (
  `nit` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `sector` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `contact_name` varchar(100) DEFAULT NULL,
  `contact_lastname` varchar(100) DEFAULT NULL,
  `contact_position` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`nit`),
  KEY `fk_company_user_id` (`user_id`),
  CONSTRAINT `fk_company_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `companies` (`nit`, `name`, `sector`, `contact_phone`, `contact_name`, `contact_lastname`, `contact_position`, `user_id`) VALUES
	('0987', 'jhngb', 'ngbfv', '524', 'kmjhn', 'bgfvd', 'fvd', 18),
	('1111', 'Samsung', 'Tecnologia', '74123698', 'Camilo', 'Ortega', 'CEO', 14),
	('123', 'asd', 'asd', '123333', 'asd', 'asdasdas', 'asdsa', NULL),
	('123456789', 'Tech Corp', 'Software', '3111111111', 'Carlos', 'Perez', 'CEO', NULL),
	('1234567890', 'Apple', 'todo', '9876', 'yo', 'alo', 'ceo', 9),
	('14789', 'Xiaomi', 'tecnologia', '79465', 'Yei', 'Gem', 'CEO', 15),
	('15', 'APPLE', 'tecnología', '3158479160', 'Camilo', 'Ortega', 'CEO', NULL),
	('741', 'EMPRESA', 'FVDS', '1234', 'DFVDS', 'SFDSDFS', 'DFSDF', 2);

CREATE TABLE IF NOT EXISTS `projects` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `summary` text DEFAULT NULL,
  `objectives` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `max_months` int(11) DEFAULT NULL,
  `budget` decimal(10,2) DEFAULT NULL,
  `date` date DEFAULT curdate(),
  `state` enum('RECEIVED','ACCEPTED','REJECTED','IN_EXECUTION','CLOSED') NOT NULL,
  `company_nit` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_nit` (`company_nit`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`company_nit`) REFERENCES `companies` (`nit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `projects` (`id`, `name`, `summary`, `objectives`, `description`, `max_months`, `budget`, `date`, `state`, `company_nit`) VALUES
	('2680af7a-6adb-4f07-9703-88602adfaf83', 'CAN', NULL, 'CANNNNNN', 'SFVDSF', 2, 15000.00, '2025-03-20', 'ACCEPTED', '0987'),
	('cbf99e9d-c7d0-4dda-9019-d7665c198b02', 'ALO', NULL, 'nada', 'HOLA', 2, 1.00, '2025-03-20', 'IN_EXECUTION', '1234567890'),
	('ea075685-4b1a-499a-b8ab-aaaa5c3119ec', 'Redmi', NULL, 'sdfsdf', 'afdsd', 3, 213.00, '2004-02-02', 'ACCEPTED', '14789');

CREATE TABLE IF NOT EXISTS `students` (
  `id` char(36) NOT NULL,
  `user_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `program` varchar(100) NOT NULL,
  `project_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `students` (`id`, `user_id`, `first_name`, `last_name`, `program`, `project_id`) VALUES
	('922ce4da-fa56-4abd-be71-dd49d4eb8d4e', 17, 'YEIXON', 'JULIAN', 'ING. SIS', NULL),
	('a0f197c7-f135-4c97-bc7c-c79ffc8572d8', 3, 'Camilin', 'Ortega', 'SIs', NULL),
	('d52dfc6d-4d71-4ef6-9ef2-14a6947c044e', 20, 'isabella', 'd', 'sis', NULL);

CREATE TABLE IF NOT EXISTS `student_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` char(36) NOT NULL,
  `project_id` varchar(36) NOT NULL,
  `status` enum('RECEIVED','ACCEPTED','REJECTED','IN_EXECUTION','CLOSED') DEFAULT 'RECEIVED',
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `student_projects_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `student_projects_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `student_projects` (`id`, `student_id`, `project_id`, `status`) VALUES
	(1, 'a0f197c7-f135-4c97-bc7c-c79ffc8572d8', 'cbf99e9d-c7d0-4dda-9019-d7665c198b02', 'REJECTED');

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Empresa','Coordinador','Estudiante') NOT NULL,
  `profile_completed` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `users` (`id`, `username`, `password`, `role`, `profile_completed`) VALUES
	(1, 'admin', 'admin123', 'Admin', 0),
	(2, 'empresa', 'empresa123', 'Empresa', 0),
	(3, 'Camilo', '123456789', 'Estudiante', 1),
	(9, 'APPLE', '1234', 'Empresa', 1),
	(10, 'CAN MAYOR', 'CAN', 'Estudiante', 0),
	(13, 'CAN', '7411', 'Estudiante', 0),
	(14, 'SAMSUNG', 'ALO', 'Estudiante', 0),
	(15, 'XIAOMI', '789', 'Empresa', 0),
	(17, 'YEI', 'YEI', 'Estudiante', 0),
	(18, 'huawei', '12', 'Empresa', 1),
	(20, 'ISA', '78-', 'Estudiante', 1),
	(21, 'Libardo', '741', 'Coordinador', 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
