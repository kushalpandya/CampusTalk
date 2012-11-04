-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editUser`(in id int(11),
in email varchar(50),
in branchname varchar(50),
in year int(11),
in rolename varchar(20),
in status char(2))
BEGIN


update users u set u.email=email, u.branchid=getBranchID(branchname), u.year=year, u.status=status
where u.id=id;

update userroles ur set ur.roleid=getRoleID(rolename) where ur.id=id;

END
