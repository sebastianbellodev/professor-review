CREATE DATABASE  IF NOT EXISTS `professorperformanceevaluation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `professorperformanceevaluation`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: professorperformanceevaluation
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `academicoffering`
--

DROP TABLE IF EXISTS `academicoffering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academicoffering` (
  `idAcademicOffering` int NOT NULL AUTO_INCREMENT,
  `idSyllabus` int NOT NULL,
  `idProfessor` int NOT NULL,
  PRIMARY KEY (`idAcademicOffering`),
  KEY `FK_AcademicOffering_Syllabus_idx` (`idSyllabus`),
  KEY `FK_AcademicOffering_Professor_idx` (`idProfessor`),
  CONSTRAINT `FK_AcademicOffering_Professor` FOREIGN KEY (`idProfessor`) REFERENCES `professor` (`idProfessor`),
  CONSTRAINT `FK_AcademicOffering_Syllabus` FOREIGN KEY (`idSyllabus`) REFERENCES `syllabus` (`idSyllabus`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academicoffering`
--

LOCK TABLES `academicoffering` WRITE;
/*!40000 ALTER TABLE `academicoffering` DISABLE KEYS */;
INSERT INTO `academicoffering` VALUES (1,1,13);
/*!40000 ALTER TABLE `academicoffering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directory`
--

DROP TABLE IF EXISTS `directory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `directory` (
  `idDirectory` int NOT NULL AUTO_INCREMENT,
  `idFaculty` int NOT NULL,
  `idProfessor` int NOT NULL,
  PRIMARY KEY (`idDirectory`),
  KEY `FK_Directory_Faculty_idx` (`idFaculty`),
  KEY `FK_Directory_Professor_idx` (`idProfessor`),
  CONSTRAINT `FK_Directory_Faculty` FOREIGN KEY (`idFaculty`) REFERENCES `faculty` (`idFaculty`),
  CONSTRAINT `FK_Directory_Professor` FOREIGN KEY (`idProfessor`) REFERENCES `professor` (`idProfessor`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory`
--

LOCK TABLES `directory` WRITE;
/*!40000 ALTER TABLE `directory` DISABLE KEYS */;
INSERT INTO `directory` VALUES (6,15,13),(7,15,14),(8,15,15);
/*!40000 ALTER TABLE `directory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationalexperience`
--

DROP TABLE IF EXISTS `educationalexperience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educationalexperience` (
  `idEducationalExperience` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`idEducationalExperience`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalexperience`
--

LOCK TABLES `educationalexperience` WRITE;
/*!40000 ALTER TABLE `educationalexperience` DISABLE KEYS */;
INSERT INTO `educationalexperience` VALUES (1,'Maestría en Auditoria');
/*!40000 ALTER TABLE `educationalexperience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationalprogram`
--

DROP TABLE IF EXISTS `educationalprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educationalprogram` (
  `idEducationalProgram` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `idFaculty` int NOT NULL,
  PRIMARY KEY (`idEducationalProgram`),
  KEY `FK_EducationalProgram_idx` (`idFaculty`),
  CONSTRAINT `FK_EducationalProgram_Faculty` FOREIGN KEY (`idFaculty`) REFERENCES `faculty` (`idFaculty`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalprogram`
--

LOCK TABLES `educationalprogram` WRITE;
/*!40000 ALTER TABLE `educationalprogram` DISABLE KEYS */;
INSERT INTO `educationalprogram` VALUES (1,'Licenciatura en Administración',13),(2,'Licenciatura en Contaduría',13),(3,'Licenciatura en Gestión y Dirección de Negocios',13),(4,'Licenciatura en Sistemas Computacionales Administrativos',13),(5,'Especialización en Administración del Comercio Exterior',13),(6,'Maestría en Auditoria',13),(7,'Maestría en Gestión de las Tecnologías de Información en las Organizaciones',13),(8,'Doctorado en Ciencias Administrativas y Gestión para el Desarrollo',13),(9,'Licenciatura en Economía',14),(10,'Licenciatura en Geografía',14),(11,'Maestría en Economía Ambiental y Ecológica',14),(12,'Doctorado en Finanzas Públicas',14),(13,'Licenciatura en Estadística',15),(14,'Licenciatura en Ingeniería en Sistemas y Tecnologías de la Información',15),(15,'Licenciatura en Ingeniería de Software',15),(16,'Licenciatura en Ingeniería de Ciberseguridad e Infraestructura de Cómputo',15),(17,'Licenciatura en Redes y Servicios de Cómputo',15),(18,'Licenciatura en Tecnologías Computacionales',15),(19,'Especialización en Métodos Estadísticos',15),(20,'Maestría en Sistemas Interactivos Centrados en el Usuario',15),(21,'Maestría en Gestión de Calidad',15),(22,'Doctorado en Ciencias de la Computación',15),(23,'Licenciatura en Administración de Negocios Internacionales ',16),(24,'Licenciatura en Ciencias Políticas y Gestión Pública',16),(25,'Licenciatura en Desarrollo del Talento Humano en las Organizaciones',16),(26,'Licenciatura en Publicidad y Relaciones Públicas',16),(27,'Maestría en Gestión de Recursos Humanos, Trabajo y Organizaciones',16),(28,'Facultad de Danza Contemporánea',2),(29,'Licenciatura en Derecho',17);
/*!40000 ALTER TABLE `educationalprogram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `idFaculty` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`idFaculty`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Facultad de Artes Plásticas'),(2,'Facultad de Danza'),(3,'Facultad de Música'),(4,'Facultad de Teatro'),(5,'Facultad de Biología'),(6,'Facultad de Ciencias Agrícolas'),(7,'Facultad de Bioanálisis'),(8,'Facultad de Enfermería'),(9,'Facultad de Medicina'),(10,'Facultad de Nutrición'),(11,'Facultad de Odontología'),(12,'Facultad de Psicología'),(13,'Facultad de Contaduría y Administración'),(14,'Facultad de Economía'),(15,'Facultad de Estadística e Informática'),(16,'Facultad de Ciencias Administrativas y Sociales'),(17,'Facultad de Derecho'),(18,'Facultad de Sociología'),(19,'Facultad de Antropología'),(20,'Facultad de Filosofía'),(21,'Facultad de Historia'),(22,'Facultad de Idiomas'),(23,'Facultad de Letras Españolas'),(24,'Facultad de Pedagogía'),(25,'Facultad de Arquitectura'),(26,'Facultad de Física'),(27,'Facultad de Matemáticas'),(28,'Facultad de Ingeniería Civil'),(29,'Facultad de Química Farmacéutica Biológica'),(30,'Facultad de Ciencias Químicas'),(32,'Facultad de Instrumentación Electrónica');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `idProfessor` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `lastName` varchar(150) NOT NULL,
  PRIMARY KEY (`idProfessor`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (13,'Maribel','Trejo'),(14,'Mario Alberto','Hernández'),(15,'Karim','León'),(16,'Susana','Domínguez'),(17,'Violeta','Ugalde'),(18,'José Luis','Ortíz'),(19,'María de los Ángeles','Salazar'),(20,'Lucas','Fernández'),(21,'Ximena','Fuentes'),(22,'Lourdes','Trejo');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `idReview` int NOT NULL AUTO_INCREMENT,
  `stars` int NOT NULL,
  `comment` text NOT NULL,
  `idSchoolPeriod` int NOT NULL,
  `idAcademicOffering` int NOT NULL,
  `registrationNumber` varchar(10) NOT NULL,
  PRIMARY KEY (`idReview`),
  KEY `FK_Review_SchoolPeriod_idx` (`idSchoolPeriod`),
  KEY `FK_Review_AcademicOffering_idx` (`idAcademicOffering`),
  KEY `FK_Review_Student_idx` (`registrationNumber`),
  CONSTRAINT `FK_Review_AcademicOffering` FOREIGN KEY (`idAcademicOffering`) REFERENCES `academicoffering` (`idAcademicOffering`),
  CONSTRAINT `FK_Review_SchoolPeriod` FOREIGN KEY (`idSchoolPeriod`) REFERENCES `schoolperiod` (`idSchoolPeriod`),
  CONSTRAINT `FK_Review_Student` FOREIGN KEY (`registrationNumber`) REFERENCES `student` (`registrationNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,5,'Buena clase',1,1,'zs19014012');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schoolperiod`
--

DROP TABLE IF EXISTS `schoolperiod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schoolperiod` (
  `idSchoolPeriod` int NOT NULL AUTO_INCREMENT,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`idSchoolPeriod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolperiod`
--

LOCK TABLES `schoolperiod` WRITE;
/*!40000 ALTER TABLE `schoolperiod` DISABLE KEYS */;
INSERT INTO `schoolperiod` VALUES (1,'2023-06-16','2023-09-26');
/*!40000 ALTER TABLE `schoolperiod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `registrationNumber` varchar(10) NOT NULL,
  `name` varchar(150) NOT NULL,
  `lastName` varchar(150) NOT NULL,
  `emailAddress` varchar(150) NOT NULL,
  `phoneNumber` varchar(25) DEFAULT NULL,
  `biography` text,
  `active` int DEFAULT '0',
  `activationDate` datetime DEFAULT NULL,
  `oneTimePassword` varchar(6) DEFAULT NULL,
  `idEducationalProgram` int NOT NULL,
  PRIMARY KEY (`registrationNumber`),
  KEY `FK_Student_EducationalProgram_idx` (`idEducationalProgram`),
  CONSTRAINT `FK_Student_EducationalProgram` FOREIGN KEY (`idEducationalProgram`) REFERENCES `educationalprogram` (`idEducationalProgram`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('zs19014012','María José','Torres','zs19014012@estudiantes.uv.mx','2281590651',NULL,1,NULL,NULL,15),('zs20015698','Armando Omar','Obando','zs20015698@estudiantes.uv.mx',NULL,NULL,0,NULL,NULL,15),('zs20015699','Saraí','Castillo','zs20015699@estudiantes.uv.mx',NULL,NULL,1,NULL,NULL,15),('zs20015700','Jonatan','Alarcón','zs20015700@estudiantes.uv.mx',NULL,NULL,0,NULL,NULL,15),('zs20015715','Óscar Iván','Olivares','zs20015715@estudiantes.uv.mx',NULL,NULL,1,NULL,NULL,15),('zs20015730','Sebastián','Bello','zs20015730@estudiantes.uv.mx',NULL,NULL,0,NULL,NULL,15),('zs20015739','Martín','Hernández','zs20015732@estudiantes.uv.mx','2281604463',NULL,1,NULL,'FYBu6F',15),('zs20015760','Álvaro','Barradas','zs20015760@estudiantes.uv.mx',NULL,NULL,0,NULL,NULL,15);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syllabus`
--

DROP TABLE IF EXISTS `syllabus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `syllabus` (
  `idSyllabus` int NOT NULL AUTO_INCREMENT,
  `idEducationalProgram` int NOT NULL,
  `idEducationalExperience` int NOT NULL,
  PRIMARY KEY (`idSyllabus`),
  KEY `FK_Syllabus_EducationalProgram_idx` (`idEducationalExperience`),
  KEY `FK_Syllabus_EducationalExperience` (`idEducationalProgram`),
  CONSTRAINT `FK_Syllabus_EducationalExperience` FOREIGN KEY (`idEducationalProgram`) REFERENCES `educationalprogram` (`idEducationalProgram`),
  CONSTRAINT `FK_Syllabus_EducationalProgram` FOREIGN KEY (`idEducationalExperience`) REFERENCES `educationalexperience` (`idEducationalExperience`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syllabus`
--

LOCK TABLES `syllabus` WRITE;
/*!40000 ALTER TABLE `syllabus` DISABLE KEYS */;
INSERT INTO `syllabus` VALUES (1,11,1),(2,21,1);
/*!40000 ALTER TABLE `syllabus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(450) NOT NULL,
  `registrationNumber` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_User_RegistrationNumber_idx` (`registrationNumber`),
  CONSTRAINT `FK_User_RegistrationNumber` FOREIGN KEY (`registrationNumber`) REFERENCES `student` (`registrationNumber`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('majotigartua','b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79','zs19014012'),('sebastianbello','eef32edbe8a667280b1bd1ab77274829b77e533995c5f482b970e5b380778222','zs20015730');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-19  9:02:30
