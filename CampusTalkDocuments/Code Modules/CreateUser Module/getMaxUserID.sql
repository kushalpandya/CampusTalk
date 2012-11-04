DELIMITER $$

USE `campustalk`$$

DROP FUNCTION IF EXISTS `getMaxUserID`$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getMaxUserID`() RETURNS INT(11)
BEGIN
  
    DECLARE max_userid INT; 
    
     SELECT MAX(u.id) INTO max_userid FROM users u;
    RETURN max_userid;
    
END$$

DELIMITER ;