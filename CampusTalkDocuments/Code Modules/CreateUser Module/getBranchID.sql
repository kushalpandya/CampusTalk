-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `getGroupID`( gname varchar(100)) RETURNS int(11)
    DETERMINISTIC
BEGIN
  
    DECLARE groupid INT; 
    
    SELECT g.groupid INTO  groupid FROM groups g WHERE g.name LIKE gname;
    RETURN groupid;
    
END
