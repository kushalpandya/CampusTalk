DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRole`()
BEGIN
    select * from roles;
END
