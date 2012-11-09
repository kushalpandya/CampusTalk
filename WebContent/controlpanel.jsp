<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CampusTalk</title>
		<link rel="icon" href="assets/favicon.ico" sizes="32x32">
		<link rel="shortcut icon" href="assets/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
		<link rel="stylesheet/less" type="text/css" href="less/style.less" media="all" />
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/less-1.3.1.min.js"></script>
		<script type="text/javascript" src="js/handlebars.js"></script>
		<script type="text/javascript">
			$(function() {
				less.env = "development";
				less.watch();
				$("#ViewGroup").find("form").hide();
			});
			var objMyData = [];
			objMyData = ${requestScope.userJSon}
		</script>
	</head>
	<body>
		<div class="container header">
			<img src="assets/logo_small.png" class="campustalk-logo" />
		</div>
		<div class="account-tray">
			<img src="${requestScope.User.pictureUrl}" class="account-user-img" />
			<div class="profile-block">
				<div class="profile-details">
					<img src="${requestScope.User.pictureUrl}" class="profile-img" />
					<table class="profile-options">
						<tr>
							<td><label class="user-title">${requestScope.User.firstname} ${requestScope.User.lastname}</label></td>
						</tr>
						<tr>
							<td><label class="user-group-title">Administrator</label></td>
						</tr>
					</table>
				</div>
				<div class="profile-actions">
					<button id="btnLogout" class="btn btn-flat btn-blue">Logout</button>
					<button id="btnSettings" class="btn btn-flat btn-blue">Settings</button>
				</div>
			</div>
		</div>
		<div class="container panels-block">
			<ul class="nav nav-pills" id="panel-tabs">
				<li class="active"><a href="#groups" data-toggle="pill">Groups</a></li>
				<li><a href="#users" data-toggle="pill">Users</a></li>
				<li><a href="#roles" data-toggle="pill">Roles</a></li>
				<li><a href="#branch" data-toggle="pill">Branch</a></li>
				<li><a href="#reports" data-toggle="pill">Abuse Reports</a></li>
			</ul>
			<div class="tab-content">
				<!-- Groups Pill -->
				<div class="tab-pane active" id="groups">
					<div class="input-append">
						<input type="text" name="txtNewGroup" placeholder="Enter group name to create..." class="span3" />
						<a href="#CreateGroup" role="button" class="btn disabled">Add</a>
					</div>
					<script id="getGroupData" type="text/x-handlebars-template">
					{{#if group}}
						{{#each group}}
							{{#isNotAleadyAddedGroup groupid}}
								<tr id='trGroup{{groupid}}'>
									<td>{{groupid}}</td>									
									<td>{{name}}</td>
									<td>{{description}}</td>
									<td>{{getGroupStatus status}}</td>		
									<td><a href="#EditGroup" data-toggle="modal"><i class="icon-edit"></i>&nbsp;Edit</a></td>
									<td><a href="#DeleteGroup" data-toggle="modal"><i class="icon-trash"></i>&nbsp;Delete</a></td>
									<td><a href="#ViewGroup" data-toggle="modal"><i class="icon-eye-open"></i>&nbsp;View all</a></td>
								</tr>
							{{/isNotAleadyAddedGroup}}
						{{/each}}
					{{else}}
						<option value="-1" selected>No Valuse Selected...</option>	
					{{/if}}
					</script>
					<table id="tblGroup" class="table table-stripped table-hover">
						<thead>
							<tr>
								<th>Group ID</th>
								<th>Name</th>
								<th>Description</th>
								<th>Status</th>
								<th colspan="3">Actions</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!-- Create Group Modal -->
					<div class="modal hide fade modal-flat" id="CreateGroup" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Create Group</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Name</label>
									<div class="controls">
										<input type="text" id="txtGroupName" name="txtGroupName" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Description</label>
									<div class="controls">
										<textarea id="txtGroupDescription" name="txtGroupDesc"></textarea>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button id="btnGroupSave" class="btn btn-green" onclick="insertGroup()">Save</button>
						</div>
					</div>
					<!-- Edit Group Modal -->
					<div class="modal hide fade modal-flat" id="EditGroup" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Edit Group</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Name</label>
									<div class="controls">
										<input type="text" id="txtEditGroupName" name="txtGroupName" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Description</label>
									<div class="controls">
										<textarea id="txtEditGroupDescription" name="txtGroupDesc"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Status</label>
									<div class="controls">
										<select id="drpGroupStatus" name="txtGroupStatus">
											<option value="-1" selected>Select status...</option>
											<option value="0">Inactive</option>
											<option value="1">Active</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Cancel</button>
							<button class="btn btn-green" onclick="editGroup()">Save</button>
						</div>
					</div>
					<!-- Delete Group modal -->
					<div class="modal hide fade modal-flat" id="DeleteGroup" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Delete Group</h3>
						</div>
						<div class="modal-body">
							<h2 id="dlgLabel">Are you sure you want to delete this Group ?</h2>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button id="btnDeleteGroup" class="btn btn-green" onclick="DeleteGroup()">Delete</button>
						</div>
					</div>
					<!-- View Group Modal -->
					<div class="modal hide fade modal-flat" id="ViewGroup" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel"></h3>
						</div>
						<div class="modal-dropform">
							<form class="form-horizontal form-add-member">
								<legend>Add Users</legend>
								<div class="control-group">
									<label class="control-label">Email List</label>
									<div class="controls">
										<input type="text" id="txtInsertUserEmail" name="txtUserEmail"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Level</label>
									<div class="controls">
										<select name="txtUserPosition">
											<option value="-1" selected>Select level...</option>
											<option value="Convenor">Convenor</option>
											<option value="Deputy Convenor">Deputy Convenor</option>
											<option value="Member">Member</option>
										</select>
									</div>
								</div>								
								<div class="control-group button-group">
									<button type="button" class="btn btn-flat" id="btnCancelAdd">Cancel</button>
									<button type="button" class="btn btn-green" onclick="insertGroupMember()">Save</button>
								</div>
							</form>
						</div>
						<script id="getGroupMemberData" type="text/x-handlebars-template">
						{{#if groupmember}}
							{{#each groupmember}}
								{{#isNotAlreadyAddedMember userid}}
								<tr id="trMember{{userid}}">
									<td>{{userid}}</td>									
									<td>{{email}}</td>
									<td>{{name}}</td>
									<td>{{role}}</td>
									<td><a href="#DeleteGroupUser" data-toggle="modal"><i class="icon-trash"></i>&nbsp;Delete</a></td>
								</tr>
								{{/isNotAlreadyAddedMember}}
							{{/each}}
						{{else}}
							<option value="-1" selected>No Valuse Selected...</option>
						{{/if}}	
						</script>
						<div class="modal-body">
							<table class="table table-stripped table-hover" id="tblGroupUser">
								<thead>
									<tr>
										<th>User ID</th>
										<th>Email</th>
										<th>Name</th>
										<th>Level</th>
										<th colspan="2">Actions</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-green" id="btnAddGroupMembers">Add Members</button>
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
						</div>
						<div class="modal-disable-overlay">&nbsp;</div>
					</div>
					<!-- Delete Group Member modal -->
					<div class="modal hide fade modal-flat" id="DeleteGroupUser" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Delete GroupMember</h3>
						</div>
						<div class="modal-body">
							<h2 id="dlgLabel">Are you sure you want to delete this Group Member ?</h2>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button id="btnDeleteGroup" class="btn btn-green" onclick="DeleteGroupUser()">Delete</button>
						</div>
					</div>
				</div>
				<!-- Users Pill -->
				<div class="tab-pane" id="users">
					<div class="input-append">
						<input type="text" name="txtNewUser" placeholder="Enter user name to create..." class="span3" />
						<a href="#CreateUser" role="button" class="btn disabled">Add</a>
					</div>
					<script id="getUsers" type="text/x-handlebars-template">
					{{#if user}}
						{{#each user}}
							{{#isNotAleadyAddedUser id}}
								<tr id="trUser{{id}}">
									<td>{{id}}</td>									
									<td>{{email}}</td>
									<td>{{firstname}} {{lastname}}</td>									
									<td>{{branch}}-{{year}}</td>
									<td>{{role}}</td>
									<td>{{getuserstatus status}}</td>
									<td><a href="#EditUser" name="shreeji" data-toggle="modal"><i class="icon-edit"></i>&nbsp;Edit</a></td>
									<td><a href="#DeleteUser" data-toggle="modal"><i class="icon-trash"></i>&nbsp;Delete</a></td>
								</tr>
							{{/isNotAleadyAddedUser}}						
						{{/each}}
					{{else}}
						<option value="-1" selected>No Valuse Selected...</option>
					{{/if}}
					</script>
					<table id="tblUsers" class="table table-stripped table-hover">
						<thead>
							<tr>
								<th>User ID</th>
								<th>Email</th>
								<th>Name</th>
								<th>Branch</th>
								<th>Role</th>
								<th>Status</th>
								<th colspan="2">Actions</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!-- Create User Modal -->
					<script id="getBranches" type="text/x-handlebars-template">
					{{#if branch}}
						{{#each branch}}
							<option value={{name}}>{{name}}</option>						
						{{/each}}
					{{else}}
						<option value="-1" selected>No Valuse Selected...</option>				
					{{/if}}
					</script>
					<script id="getRoles" type="text/x-handlebars-template">
					{{#if role}}
						{{#each role}}
							<option value={{name}}>{{name}}</option>						
						{{/each}}
					{{else}}
						<option value="-1" selected>No Valuse Selected...</option>				
					{{/if}}
					</script>
					<div class="modal hide fade modal-flat" id="CreateUser" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Create User</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Email List</label>
									<div class="controls">
										<textarea name="txtAUserEmail"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Branch</label>
									<div class="controls">
										<select name="txtUserGroup" id="drpABranch">
											<option value="-1" selected>Select group...</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Year</label>
									<div class="controls">
										<input type="text" name="txtUserYear" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Role</label>
									<div class="controls">
										<select name="txtUserPrivilege" id="drpARole">
											<option value="-1" selected>Select level...</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button id="btnSaveCreateUser" class="btn btn-green">Save</button>
						</div>
					</div>
					<!-- Delete User modal -->
					<div class="modal hide fade modal-flat" id="DeleteUser" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Delete User</h3>
						</div>
						<div class="modal-body">
							<h2 id="dlgLabel">Are you sure you want to delete this user ?</h2>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button id="btnDeleteCreateUser" class="btn btn-green">Delete</button>
						</div>
					</div>
					<!-- Edit User Modal -->
					<div class="modal hide fade modal-flat" id="EditUser" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Edit User</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Email</label>
									<div class="controls">
										<input type="text" name="txtEUserEmail" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Branch</label>
									<div class="controls">
										<select name="txtUserGroup" id="drpEBranch">
											<option value="-1" selected>Select Branch...</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Year</label>
									<div class="controls">
										<input type="text" name="txtEUserYear" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Role</label>
									<div class="controls">
										<select name="txtUserPrivilege" id="drpERole">
											<option value="-1" selected>Select Role...</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Status</label>
									<div class="controls">
										<select name="txtEUserStatus" id="drpEStatus">
											<option value="-1" selected>Select status...</option>
											<option value="Inactive">Inactive</option>
											<option value="Active">Active</option>
											<option value="Disable">Disable</option>
											<option value="Block">Block</option>
											<option value="Verify">Verify</option>
											<option value="New">New</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button id="btnEditCreateUser" class="btn btn-green">Save</button>
						</div>
					</div>
				</div>
				<!-- Roles Pill -->
				<div class="tab-pane" id="roles">
					<div class="input-append">
						<input type="text" id="txtRole" name="txtRole" placeholder="Enter Role name to create..." class="span3" />
						<a href="#" id="txtNewRole" role="button" class="btn disabled">Add</a>
					</div>
					<script id="getRole" type="text/x-handlebars-template">
					{{#if Roles}}
						{{#each Roles}}
							{{#isNotAleadyAddedRole rolesid}}
								<tr id="trRole{{rolesid}}">
									<td>{{rolesid}}</td>									
									<td>{{name}}</td>
									<td><a href="#EditRole" data-roleid="{{rolesid}}" data-rolename="{{name}}" name="EditRole" data-toggle="modal"><i class="icon-edit"></i>&nbsp;Edit</a></td>
									<td><a href="#DeleteRole" data-roleid="{{rolesid}}" name="DeleteRole" "data-toggle="modal"><i class="icon-trash"></i>&nbsp;Delete</a></td>
								</tr>						
							{{/isNotAleadyAddedRole}}
						{{/each}}
					{{else}}
						<option value="-1" selected>No Valuse Selected...</option>				
					{{/if}}	
					</script>
					<table class="table table-stripped table-hover" id="tblRoles">
						<thead>
							<tr>
								<th>Role ID</th>
								<th>Name</th>
								<th colspan="2">Actions</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!-- Edit Role Modal -->
					<div class="modal hide fade modal-flat" id="EditRole" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Edit Role</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Role</label>
									<div class="controls">
										<input type="text" name="txtRoleName" />
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button class="btn btn-green" id="btnSave">Save</button>
						</div>
					</div>
					<div class="modal hide fade modal-flat" id="DeleteRole" tabindex="-1" role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Delete Role</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Role</label>
									<div class="controls">
										<input type="text" name="txtUserEmail" />
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
							<button class="btn btn-green">Delete</button>
						</div>
					</div>
				</div>
				<!-- Branch Pill -->
				<div class="tab-pane" id="branch">
					<div class="input-append">
						<input type="text" id="txtBranch" name="txtBranch" placeholder="Enter branch name to create..." class="span3" />
						<a href="#CreateBranch" id="txtNewBranch" role="button"	data-toggle="modal" class="btn disabled">Add</a>
					</div>
					<script id="getBranch" type="text/x-handlebars-template">
					{{#if Branch}}
						{{#each Branch}}
							{{#isNotAleadyAddedBranch branchid}}
								<tr id="trBranch{{branchid}}">
									<td>{{branchid}}</td>									
									<td>{{name}}</td>
									<td>{{duration}}</td>
									<td><a href="#EditBranch" data-branchid="{{branchid}}" data-branchname="{{name}}" data-duration="{{duration}}" name="EditBranch" data-toggle="modal"><i class="icon-edit"></i>&nbsp;Edit</a></td>
									<td><a href="#DeleteBranch" data-branchid="{{branchid}}" name="DeleteBranch" "data-toggle="modal"><i class="icon-trash"></i>&nbsp;Delete</a></td>
								</tr>
							{{/isNotAleadyAddedBranch}}						
						{{/each}}
					{{else}}
						<option value="-1" selected>No Valuse Selected...</option>				
					{{/if}}	
					</script>
					<table class="table table-stripped table-hover" id="tblBranch">
						<thead>
							<tr>
								<th>Branch ID</th>
								<th>Name</th>
								<th>Duration</th>
								<th colspan="2">Actions</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!-- Create Group Modal -->
					<div class="modal hide fade modal-flat" id="CreateBranch" tabindex="-1"	role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 id="dlgLabel">Create Branch</h3>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">Name</label>
									<div class="controls">
										<input type="text" id="txtBranchName" name="txtBranchName" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Duration</label>
									<div class="controls">
										<input type="text" id="txtDuration" name="txtDuration" />
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-flat" data-dismiss="modal"
								aria-hidden="true">Close</button>
						<button class="btn btn-green" id="btnAdd">Save</button>
					</div>
				</div>
				<!-- Edit Branch Modal -->
				<div class="modal hide fade modal-flat" id="EditBranch" tabindex="-1"
					role="dialog" aria-labelledby="dlgLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 id="dlgLabel">Edit Group</h3>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label">Name</label>
								<div class="controls">
									<input type="text" name="txtBranchName1" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Duration</label>
								<div class="controls">
									<input type="text" name="txtDuration1" />
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Cancel</button>
						<button class="btn btn-green" id="btnSave1">Save</button>
					</div>
				</div>
			</div>
				<!-- Abuse Reports Pill -->
				<div class="tab-pane" id="reports">
					<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus</p>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/script.js"></script>
		<script type="text/javascript" src="js/controlpanel.js"></script>
	</body>
</html>
