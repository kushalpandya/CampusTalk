DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllBranch`()
BEGIN
    select * from branch;
END
