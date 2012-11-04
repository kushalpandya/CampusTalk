DELIMITER $$

USE `campustalk`$$

DROP PROCEDURE IF EXISTS `updateUserStatus`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUserStatus`( IN id INT(11),IN ustatus CHAR(1))
BEGIN     
        
        UPDATE  users  u SET u.status = ustatus WHERE u.id = id; 
        	
END$$

DELIMITER ;