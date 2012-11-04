-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `getRoleID`( rname nVARCHAR(20)) RETURNS int(11)
    DETERMINISTIC
BEGIN
  
    DECLARE roleid iNT; 
    
    SELECT r.roleid INTO roleid FROM roles r WHERE r.name LIKE rname;
    RETURN roleid;
    
END
