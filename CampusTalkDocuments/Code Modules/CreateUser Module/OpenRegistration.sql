DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `openRegistration`(IN email VARCHAR(50),
	IN bname VARCHAR(50), 
	IN uyear INT, 
	IN roletype VARCHAR(20))
BEGIN
    DECLARE bid,rid,max_userid INT;
	
	SET bid = getBranchID(bname); /* return branch id for given branch name */
	
	INSERT INTO users   (email, branchid, YEAR, STATUS) VALUES  (email,bid,uyear, 'N') ; /*  status = N indicate new user that added by admin*/
	
	SET rid = getRoleID(roletype);  /* return role id for given role  */
        
	SET max_userid = getMaxUserID(); /* return max userid of table users and i.e the user inserted through previous query i.e last */
	INSERT INTO userroles(userid,roleid) VALUES(max_userid,rid);
END
