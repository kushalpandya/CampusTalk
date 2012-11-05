CREATE DATABASE  IF NOT EXISTS `campustalk` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `campustalk`;
-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: campustalk
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.10.1

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `branchid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`branchid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'M.Sc.I.T',2),(2,'B.Tech',4);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT,
  `postid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `detail` text NOT NULL,
  `enttime` datetime NOT NULL,
  `status` char(2) NOT NULL,
  PRIMARY KEY (`commentid`),
  KEY `user_idx` (`userid`),
  KEY `cpost_idx` (`postid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,3,1,':D :D ;-)','2012-11-03 05:57:57','A'),(2,1,1,'\\m/ <3','2012-11-03 06:00:08','A'),(3,3,1,'Hey.. :)','2012-11-03 06:01:05','A'),(4,1,1,'New Comment :-P','2012-11-03 19:36:23','A'),(5,2,1,'Yo Yo ..','2012-11-04 04:21:35','A'),(6,2,2,'Hey  :)','2012-11-04 06:05:08','A'),(7,3,2,'WHat happen ;-)','2012-11-04 06:05:26','A'),(8,2,1,'Whats up !!','2012-11-04 14:15:55','A'),(9,2,1,'test','2012-11-04 23:23:52','A'),(10,2,1,'new :-','2012-11-04 23:24:47','A'),(11,4,1,'Hey hey','2012-11-04 23:25:47','A'),(12,7,1,'tetetete','2012-11-04 23:26:16','A'),(13,7,1,':-)','2012-11-04 23:29:03','A'),(14,5,2,'New :-)','2012-11-05 04:58:17','A'),(15,2,2,'\\m/','2012-11-05 04:58:33','A');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventattends`
--

DROP TABLE IF EXISTS `eventattends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventattends` (
  `eventid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` char(2) NOT NULL,
  PRIMARY KEY (`eventid`,`userid`),
  KEY `userid` (`userid`),
  KEY `eauser_idx` (`userid`),
  KEY `eventid_idx` (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventattends`
--

LOCK TABLES `eventattends` WRITE;
/*!40000 ALTER TABLE `eventattends` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventattends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `eventid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` varchar(500) NOT NULL,
  `fromdate` datetime NOT NULL,
  `todate` datetime NOT NULL,
  `place` varchar(200) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`eventid`),
  KEY `user_idx` (`userid`),
  KEY `user_idx1` (`userid`),
  KEY `euser_idx` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupmembers`
--

DROP TABLE IF EXISTS `groupmembers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupmembers` (
  `groupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `position` varchar(100) NOT NULL,
  `status` char(2) NOT NULL,
  `entdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `gmuser` (`userid`),
  KEY `gid_idx` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupmembers`
--

LOCK TABLES `groupmembers` WRITE;
/*!40000 ALTER TABLE `groupmembers` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupmembers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `groupid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `status` char(2) NOT NULL,
  PRIMARY KEY (`groupid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messagedetails`
--

DROP TABLE IF EXISTS `messagedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messagedetails` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `message` text NOT NULL,
  `status` char(2) NOT NULL,
  `enttime` datetime NOT NULL,
  PRIMARY KEY (`messageid`),
  KEY `userid_idx` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messagedetails`
--

LOCK TABLES `messagedetails` WRITE;
/*!40000 ALTER TABLE `messagedetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `messagedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `messageid` int(11) NOT NULL,
  `touserid` int(11) NOT NULL,
  `status` char(2) NOT NULL,
  PRIMARY KEY (`messageid`,`touserid`),
  KEY `mmid_idx` (`messageid`),
  KEY `muser_idx` (`touserid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `postid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `detail` text NOT NULL,
  `type` char(2) NOT NULL,
  `status` char(2) NOT NULL,
  `enttime` datetime NOT NULL,
  `lastmodifytime` datetime DEFAULT NULL,
  PRIMARY KEY (`postid`),
  KEY `puserid_idx` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,1,'First Post... :-)','P','A','2012-11-03 01:22:16','2012-11-03 19:36:23'),(2,1,'Second Post :-)','A','A','2012-11-03 01:29:00','2012-11-05 04:58:33'),(3,1,':-D','A','A','2012-11-03 01:29:43','2012-11-04 06:05:26'),(4,1,'New pOst','P','A','2012-11-04 04:21:20','2012-11-04 23:25:47'),(5,2,'My Post :-)','P','A','2012-11-04 07:07:42','2012-11-05 04:58:17'),(6,1,'Test Post :D','A','A','2012-11-04 22:02:29','2012-11-04 22:02:29'),(7,1,'again Test Post','A','A','2012-11-04 22:03:02','2012-11-04 23:29:03'),(8,1,'again Test Post :-)','P','A','2012-11-04 22:03:15','2012-11-04 22:03:15'),(9,1,'yo yo Honey Singh :-)','A','A','2012-11-04 22:05:56','2012-11-04 22:05:56');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportabuses`
--

DROP TABLE IF EXISTS `reportabuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reportabuses` (
  `postid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `enttime` datetime NOT NULL,
  `status` char(2) NOT NULL,
  `detail` text NOT NULL,
  PRIMARY KEY (`postid`,`userid`),
  KEY `user_idx` (`userid`),
  KEY `post_idx` (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportabuses`
--

LOCK TABLES `reportabuses` WRITE;
/*!40000 ALTER TABLE `reportabuses` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportabuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(3,'Moderator'),(4,'Professore'),(2,'Student');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userroles` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`roleid`),
  KEY `userid_idx` (`userid`),
  KEY `role_idx` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userroles`
--

LOCK TABLES `userroles` WRITE;
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` VALUES (1,2);
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `registerwith` varchar(20) DEFAULT NULL,
  `status` char(1) NOT NULL,
  `authstring` varchar(100) DEFAULT NULL,
  `authdate` datetime DEFAULT NULL,
  `registerdate` datetime DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` varchar(7) DEFAULT NULL,
  `cityid` int(11) DEFAULT NULL,
  `branchid` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `pictureUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `branch_idx` (`branchid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'saiyedfaishal@gmail.com','9cb966ff0bb252eeaf8be1b658ec0115','google','V','4ae69bbfed3f4974b6cbbd3d681cec092b8d9266249a47ff93b10e7914b917d6','2012-11-19 14:23:19','2012-10-28 05:11:12','Faishal','Saiyed',NULL,'male',NULL,1,2011,'https://lh3.googleusercontent.com/-XVHns1ycTI0/AAAAAAAAAAI/AAAAAAAAAN0/31SL_TsfpRM/photo.jpg?sz=50'),(2,'samrocker4rock@gmail.com','7c26dc798bda94a414a866030d530ea1','google','V',NULL,NULL,'2012-11-04 05:38:44','Samir','Patel',NULL,'male',0,2,2010,'https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg?sz=50');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'campustalk'
--

--
-- Dumping routines for database 'campustalk'
--
/*!50003 DROP FUNCTION IF EXISTS `getBranchID` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getBranchID`(bname VARCHAR(50)) RETURNS int(11)
BEGIN
  
    DECLARE branchid INT; 
    
     SELECT b.branchid INTO branchid FROM branch b WHERE b.name LIKE bname;
    RETURN branchid;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getMaxCommentID` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getMaxCommentID`() RETURNS int(11)
BEGIN
      DECLARE cid INT;
      
      SELECT MAX(comment.commentid) INTO cid FROM comment;
      
      RETURN cid;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getMaxPostID` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getMaxPostID`() RETURNS int(11)
BEGIN
          
          DECLARE pid INT;
          SELECT MAX(postid) INTO pid FROM posts;
          RETURN pid; 		
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getMaxUserID` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getMaxUserID`() RETURNS int(11)
BEGIN
  
    DECLARE max_userid INT; 
    
     SELECT MAX(u.id) INTO max_userid FROM users u;
    RETURN max_userid;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getUserBranch` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getUserBranch`( email VARCHAR(100)) RETURNS varchar(50) CHARSET latin1
BEGIN
  
    DECLARE bname VARCHAR(50); 
    
    
    
   
    SELECT branch.name INTO bname FROM branch , users WHERE users.branchid =branch.branchid AND users.email = email;
    RETURN bname;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getUserFirstName` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getUserFirstName`( email VARCHAR(100)) RETURNS varchar(50) CHARSET latin1
BEGIN
  
    DECLARE fname INT; 
    
    SELECT u.firstname INTO fname FROM users u WHERE u.email LIKE email;
    RETURN fname;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getUserID` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getUserID`(email VARCHAR(50)) RETURNS int(11)
BEGIN
  
    DECLARE userid INT; 
    
     SELECT u.id INTO userid FROM users u WHERE u.email LIKE email;
    RETURN userid;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getUserLastName` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getUserLastName`( email VARCHAR(100)) RETURNS varchar(50) CHARSET latin1
BEGIN
  
    DECLARE lname INT; 
    
    SELECT u.lastname INTO lname FROM users u WHERE u.email LIKE email;
    RETURN lname;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getUserName` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getUserName`( email VARCHAR(100)) RETURNS varchar(101) CHARSET latin1
BEGIN
  
    DECLARE lname INT; 
    
    RETURN CONCAT(getUserFirstName(),getUserLastName());
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `getUserYear` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `getUserYear`( email VARCHAR(100)) RETURNS varchar(50) CHARSET latin1
BEGIN
  
    DECLARE uyear INT; 
    
    SELECT u.year INTO uyear FROM users u WHERE u.email LIKE email;
    RETURN NAME;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `isAuthorisedUserForPost` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `isAuthorisedUserForPost`(rpostid int,userid int) RETURNS tinyint(1)
BEGIN
Declare upostid int;
	SELECT 
    `posts`.`postid` AS `postid` 
into upostid 
FROM
    `posts`
WHERE
    (`posts`.`postid` = rpostid)
        AND (`posts`.`type` = 'A')
        AND (`posts`.`status` = 'A') ;

IF upostid IS NULL THEN
SELECT 
    `posts`.`postid` AS `postid`
into upostid 
FROM
    `posts`
WHERE
    (`posts`.`postid` = rpostid)
        AND (`posts`.`userid` IN (SELECT 
            `users`.`id`
        FROM
            `users`
        WHERE
            `users`.`branchid` IN (SELECT 
                    `users`.`branchid`
                FROM
                    `users`
                WHERE
                    (`users`.`id` = userid)))
        AND (`posts`.`type` = 'P')
        AND (`posts`.`status` = 'A'));

end if;
	if upostid is NULL then
		return true;
else
	return false;
end if;
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `UserGetAuthDate` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `UserGetAuthDate`(email VARCHAR(50)) RETURNS date
BEGIN
	DECLARE authdate DATETIME;
	
	SELECT u.authdate INTO authdate FROM users u WHERE u.email LIKE email;
        
        RETURN authdate;
           		
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `UserGetAuthString` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `UserGetAuthString`( email VARCHAR(50)) RETURNS varchar(100) CHARSET utf8
BEGIN
    DECLARE aString VARCHAR(100);
    SELECT u.authstring INTO aString FROM users u WHERE u.email LIKE email;
    RETURN aString ;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP FUNCTION IF EXISTS `UserGetStatus` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `UserGetStatus`( email VARCHAR(50)) RETURNS char(1) CHARSET latin1
BEGIN
    DECLARE ustatus CHAR(1);
    SELECT u.status INTO ustatus FROM users u WHERE u.email LIKE email;
    RETURN ustatus ;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `CommentOnPost` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `CommentOnPost`(IN postid INT,IN userid INT,IN detail TEXT,OUT commentid INT)
BEGIN
	if isAuthorisedUserForPost(postid,userid) = 0 then

     INSERT INTO comment(postid,userid,detail,enttime,STATUS) VALUE(postid,userid,detail,SYSDATE(),'A');
     
     SET commentid = getMaxCommentID();
     /* Status: A- indicate active comment :*/
     
     UPDATE posts SET posts.lastmodifytime = SYSDATE() WHERE posts.postid =postid;
end if;	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteCommentForPost` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `deleteCommentForPost`(IN dCommentId int, in dUserId int,out flag boolean)
BEGIN
	Declare dTest int ;
	select `comment`.`userid` into dTest from `campustalk`.`comment` where `comment`.`commentid` =dCommentId and `comment`.`userid`= dUserId;
	if dTest is null then
		select `comment`.`userid` into dTest from `campustalk`.`comment`,`campustalk`.`posts` where `comment`.`commentid` =dCommentId  and `comment`.`postid`=`posts`.`postid` and `posts`.`userid`=dUserId;
		if dTest is null then
			set flag=true;
		else
			set flag=false;
		end if;
	else
		set flag=true;
	end if;
if flag = true then
 update `campustalk`.`comment` set `campustalk`.`status`='D' where `campustalk`.`commentid`=dCommentId;
end if;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getBranchById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getBranchById`(IN rbranchid INT)
BEGIN
	select `branch`.* from `campustalk`.`branch` where `branch`.`branchid`=rbranchid ;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getCommentOnPost` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getCommentOnPost`(IN postid INT,IN _START INTEGER, 
IN _LIMIT INTEGER , in ruserid int)
BEGIN
   
    /* status = A indicate active comment */
      
      DECLARE cstatus CHAR(2);
 if isAuthorisedUserForPost(postid,ruserid) = 0 then
      SET cstatus ='A';
   /*   select * from comment where comment.postid = postid and comment.status ='A' limit 3;*/
      
      
         PREPARE STMT FROM "select c.* from (SELECT `comment`.*,`users`.`firstname`,`users`.`lastname`,`users`.`pictureurl` FROM `comment`,`users` WHERE comment.postid = ? AND `comment`.`userid` =`users`.`id` AND comment.status =? order by comment.enttime desc LIMIT ?,? ) c order by c.enttime "; 
         SET @v_postid = postid;
         SET @v_status = cstatus;
         
         SET @START = _START; 
         SET @LIMIT = _LIMIT; 
      
         EXECUTE STMT USING @v_postid, @v_status, @START, @LIMIT;
else 
		select `comment`.* from `campustalk`.`comment` where 1!=1;
	end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getPostsForUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getPostsForUser`(IN remail VARCHAR(50),IN rSkip INT,in rRows INT )
BEGIN
SET @s = CONCAT('SELECT `posts`.`postid` AS `postid`,`posts`.`userid` AS `userid`,`posts`.`detail` AS `detail`,`posts`.`type` AS `type`,`posts`.`status` AS `status`,`posts`.`enttime` AS `enttime`,`posts`.`lastmodifytime` AS `lastmodifytime`,`users`.`firstname`,`users`.`lastname`,`users`.`pictureurl`,(select count(`comment`.`userid`) from `comment` where `comment`.`postid`=`posts`.`postid`) as nocomment FROM `posts`,`users`  WHERE `posts`.`userid`=`users`.`id` and  ((`posts`.`type` = \'A\') AND (`posts`.`status` = \'A\')) 
 UNION 
(SELECT `posts`.`postid` AS `postid`,`posts`.`userid` AS `userid`,`posts`.`detail` AS `detail`,`posts`.`type` AS `type`,`posts`.`status` AS `status`,`posts`.`enttime` AS `enttime`,`posts`.`lastmodifytime` AS `lastmodifytime`,`users`.`firstname`,`users`.`lastname`,`users`.`pictureurl`,(select count(`comment`.`userid`) from `comment` where `comment`.`postid`=`posts`.`postid`) as nocomment FROM `posts`,`users` 
 WHERE `posts`.`userid`=`users`.`id` and (`posts`.`userid` IN (SELECT `users`.`id` FROM `users` WHERE  `users`.`branchid` IN (SELECT `users`.`branchid` FROM `users` WHERE (`users`.`email` = "',remail,'"))) AND (`posts`.`type` = \'P\') AND (`posts`.`status` = \'A\'))) order by lastmodifytime desc limit ', rSkip ,', ', rRows); 

PREPARE stmt1 FROM @s;
EXECUTE stmt1; 
DEALLOCATE PREPARE stmt1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getRoleById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getRoleById`(in uroleid int)
BEGIN
	select `roles`.* from `campustalk`.`roles` where `roles`.`roleid` = uroleid ;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserRoleById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getUserRoleById`(IN mroleid INT)
BEGIN
	select `userroles`.* from `campustalk`.`userroles` where `userroles`.`userid`= mroleid;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PostInsert` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `PostInsert`(IN userid INT,IN content TEXT, IN ptype CHAR(2),OUT postid INT)
BEGIN
    
    
    INSERT INTO posts(userid,detail,TYPE,STATUS,enttime,lastmodifytime) VALUES(userid,content,ptype,'A',SYSDATE(),SYSDATE());
    
    SET postid = getMaxPostID();
    
   /* STATUS : A -> by default status is ACTIVE when post inserted */
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `UserDetailFromEmail` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `UserDetailFromEmail`(IN useremail VARCHAR(100))
BEGIN
Select `id`,`email`,`password`,`registerwith`,`status`,`authstring`,`authdate`,`registerdate`,`firstname`,`lastname`,`birthdate`,`gender`,`cityid`,`branchid`,`year`,`pictureurl`
From users
where email=useremail;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `UserLogin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `UserLogin`(IN uemail VARCHAR(50), IN upassword VARCHAR(100), OUT loginFlag int)
BEGIN
	 Declare tPassword VARCHAR(100);
	 Declare userid int;
	 Declare ustatus varchar(1);
	 SELECT u.id , u.password,u.status INTO userid,tPassword,ustatus  FROM users u WHERE u.email=uemail;
    	SET loginFlag =FALSE;
		IF userid != 0 THEN
			if tPassword=md5(upassword)	then
				if(ustatus="V") then
					SET loginFlag = TRUE;
				end if;
				if(ustatus="P") then
					SET loginFlag = TRUE;
					update `users` set `users`.`authstring`=null,`users`.`authdate`=null,`users`.`status`="R" where `users`.`email`=uemail;
				end if;
			end if;
			
		END IF;
		select * from users u where u.email=uemail;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UserNewRegisteration` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `UserNewRegisteration`(
	IN userid INT,
	IN fname VARCHAR(50),
	IN lname VARCHAR(50), 
	IN gender VARCHAR(7),
	IN passwd VARCHAR(100),
    IN authstring VARCHAR(100),
    IN registerwith VARCHAR(20),
	IN picURL VARCHAR(200))
BEGIN 
               
        /* GET CITY ID FROM GIVEN CITY..and also add one more parameter for city ONCE TABLE CREATED FOR THE SAME */
		         
        UPDATE  users  u SET u.firstname = fname , u.lastname =lname ,u.gender = gender,u.password =MD5(passwd), u.registerwith = registerwith, u.authstring =authstring, u.authdate =ADDDATE(SYSDATE(),INTERVAL 15 DAY ) , u.status = 'R', u.pictureurl = picURL 
        WHERE u.id = userid AND u.status ='N'; 
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `UserVerifyRegistration` */;
ALTER DATABASE `campustalk` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `UserVerifyRegistration`( IN email VARCHAR(50),IN aString VARCHAR(100),OUT errorCode INT)
BEGIN 
		
        IF UserGetStatus(email) = 'R' THEN
			IF UserGetAuthString(email) = aString THEN
				IF SYSDATE() <=UsergetAuthDate(email)  THEN
					UPDATE  users  u SET u.status ='V' ,u.registerdate = SYSDATE(),u.authstring=null,u.authdate=null WHERE u.email LIKE email ; 
					SET  errorcode = 0;
				ELSE
					SET errorcode=3;
				END IF;
			ELSE
				SET errorcode=2;
			END IF;
		ELSE 
          SET errorcode=1;
		END IF;        	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `campustalk` CHARACTER SET utf8 COLLATE utf8_general_ci ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-05 15:12:20
