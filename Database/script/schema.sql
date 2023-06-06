-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: professorperformanceevaluation
-- ------------------------------------------------------
-- Server version	8.0.32

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academicoffering`
--

LOCK TABLES `academicoffering` WRITE;
/*!40000 ALTER TABLE `academicoffering` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directory`
--

LOCK TABLES `directory` WRITE;
/*!40000 ALTER TABLE `directory` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalexperience`
--

LOCK TABLES `educationalexperience` WRITE;
/*!40000 ALTER TABLE `educationalexperience` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalprogram`
--

LOCK TABLES `educationalprogram` WRITE;
/*!40000 ALTER TABLE `educationalprogram` DISABLE KEYS */;
INSERT INTO `educationalprogram` VALUES (1,'Licenciatura en Ingeniería de Software',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Facultad de Estadística e Informática');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolperiod`
--

LOCK TABLES `schoolperiod` WRITE;
/*!40000 ALTER TABLE `schoolperiod` DISABLE KEYS */;
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
  `active` bit(1) DEFAULT NULL,
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
INSERT INTO `student` VALUES ('zs19014012','María José','Igartua','zs19014012@estudiantes.uv.mx',NULL,NULL,NULL,NULL,NULL,1),('zs20015698','Armando Omar','Muñoz','zs20015698@estudiantes.uv.mx',NULL,NULL,NULL,NULL,NULL,1),('zs20015699','Saraí','Hernández','zs20015699@estudiantes.uv.mx',NULL,NULL,NULL,NULL,NULL,1),('zs20015700','Jonathan','Alarcón','zs20015700@estudiantes.uv.mx',NULL,NULL,NULL,NULL,NULL,1),('zs20015730','Sebastián','Olivares','zs20015730@estudiantes.uv.mx',NULL,NULL,NULL,NULL,NULL,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syllabus`
--

LOCK TABLES `syllabus` WRITE;
/*!40000 ALTER TABLE `syllabus` DISABLE KEYS */;
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
  CONSTRAINT `FK_User_RegistrationNumber` FOREIGN KEY (`registrationNumber`) REFERENCES `student` (`registrationNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('sebastianbello','566d4c109d5ab4a24c54f1c6f86bd55c3850cd8a81093bc071f6b80fc94a791c','zs20015730');
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

-- Dump completed on 2023-06-06  7:59:48
