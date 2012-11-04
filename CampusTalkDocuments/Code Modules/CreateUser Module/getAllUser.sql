
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUser`()
BEGIn

 select users.id , users.email,users.firstname,users.lastname,
 branch.name as branch, users.year ,roles.name as role, users.status from roles 
inner join userroles  on roles.roleid = userroles.roleid inner join users  on userroles.userid = users.id
 inner join branch on users.branchid = branch.branchid  where users.status!='D' ;

END
