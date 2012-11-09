//isNotAleadyAddedGroup

Handlebars.registerHelper('isNotAleadyAddedGroup', function(groupeid, block) {
	if ($("#trGroup" + groupeid).length == 0)
		return block.fn(this);
});
Handlebars.registerHelper('isNotAleadyAddedUser', function(userid, block) {
	if ($("#trUser" + userid).length == 0)
		return block.fn(this);
});

Handlebars.registerHelper('getGroupStatus', function(status) {
	if (status === "V")
		return "Active";
	else
		return "Inactive";
});

Handlebars.registerHelper('getuserstatus', function(status) {
	if (status === "V")
		return "Active";
	else if(status === "N")
		return "New";
	else if(status === "R")
		return "Verify";
	else if(status === "B")
		return "Block";
	else
		return "InActive";
});
Handlebars.registerHelper('isModerator', function(messageid, block) {
	if (!(objMyData.role.toLowerCase() === "student" || objMyData.role.toLowerCase() === "moderator"))
			return block.fn(this);
});


Handlebars.registerHelper('isNotAlreadyAddedMember', function(userid, block) {
	if ($("#trMember" + userid).length == 0)
		return block.fn(this);
});

Handlebars.registerHelper('isNotAleadyAddedRole', function(roleid, block) {
	if ($("#trRole" + roleid).length == 0)
		return block.fn(this);
});
Handlebars.registerHelper('isNotAleadyAddedBranch', function(branchid, block) {
	if ($("#trBranch" + branchid).length == 0)
		return block.fn(this);
});

// Java script for controlpanel.jsp will here
var lastRoleid;
var EDITUSER_ID = 0;
var DELETEUSER_ID = 0;

var EDIT_GROUP_ID = 0;
var EDIT_GROUP_USER_ID = 0;

var DELETE_GROUP_ID = 0;
var DELETE_GROUP_USER_ID = 0;
var GROUP_ID = 0;

/** ********************************Group***************************************** */

// To Save Group
function insertGroup() {
	var gname = $("#txtGroupName").val();
	var gdescription = $("#txtGroupDescription").val();
	$.post("Group/Process", {
		type : "SaveData",
		name : gname,
		description : gdescription,
	// status:gstatus
	},

	function(data) {
		if (data.status === "success") {
			$("#CreateGroup").modal("hide");
			$("#txtGroupName").val("");
			$("#txtGroupDescription").val("");
			successOverlay(true, "Group Added Successfully");
			showGroup();
		} else {
			$("#CreateGroup").modal("hide");
			errorOverlay(true, "Oops!! Error in adding Group");
		}

	});

}

// To edit group data
$("a[href='#EditGroup']").live(
		"click",
		function(e) {
			EDIT_GROUP_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var gname = $(this).parents().eq(1).find("td:nth-child(2)").text();
			var gdescription = $(this).parents().eq(1).find("td:nth-child(3)")
					.text();
			var gstatus = $(this).parents().eq(1).find("td:nth-child(4)")
					.text();

			console.log("edit_groupId= " + EDIT_GROUP_ID + " gname= " + gname
					+ " gdesc= " + gdescription + " gstatu= " + gstatus);
			$("#txtEditGroupName").val(gname);
			$("#txtEditGroupDescription").val(gdescription);
			if (gstatus === "Active")
				gstatus = "1";
			else
				gstatus = "0";
			$("#drpGroupStatus").val(gstatus);
		});

function editGroup() {
	var gname = $("#txtEditGroupName").val();
	var gdescription = $("#txtEditGroupDescription").val();
	var gstatus = $("#drpGroupStatus").val();
	$.post("Group/Process", {
		type : "EditData",
		groupid : EDIT_GROUP_ID,
		name : gname,
		description : gdescription,
		status : gstatus
	},

	function(data) {
		if (data.status === "success") {
			$("#EditGroup").modal("hide");
			successOverlay(true, "Group Updated Successfully");
			showGroup(true);
		} else {
			errorOverlay(true, "Oops!! Error in Updating Group Details");
		}
	});
}

// to delete group

$("a[href='#DeleteGroup']").live(
		"click",
		function(e) {
			DELETE_GROUP_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());

		});

function DeleteGroup() {
	$.post("Group/Process", { // Requesting to servlet
		type : "DeleteData", // Input parameters
		groupid : DELETE_GROUP_ID
	}, function(data) { // Return JSON Object
		if (data.status === "success") {
			$("#DeleteGroup").modal("hide");
			successOverlay(true, "Group Deleted Successfully");
			showGroup(true);
		} else {
			errorOverlay(true, "Oops!! Error in Deleting Group");
		}
	});

}

function showGroup(flag) {
	if (flag != undefined)
		$("#tblGroup tbody").html("");
	$.post("Group/Process", { // Requesting to servlet
		type : "GetData", // Input parameters
	}, function(data) { // Return JSON Object
		if (data.status === "success") {
			var src = $("#getGroupData").html();
			var template = Handlebars.compile(src);
			var output = template(data);
			$("#tblGroup tbody").append(output);
			// $("#drpBranch select").add(output);
		}
	});
}

// Load Group data at page load

$("a[href='#groups']").live("click", function(e) {
	showGroup();
});



/** ********************************GroupMember***************************************** */

$("a[href='#ViewGroup']").live("click", function(e) {
	
	GROUP_ID=parseInt($(this).parents().eq(1).find("td:nth-child(1)").text());
	var groupId = parseInt($(this).parents().eq(1).find(
	"td:nth-child(1)").text());
	var groupName = $(this).parents().eq(1).find("td:nth-child(2)")
		.text();

	$("#ViewGroup #dlgLabel").text(groupName);
	$("#ViewGroup").css("margin-left",
	($("#ViewGroup").outerWidth() / 2) * -1);
	showGroupMembers(true);
		
});

function showGroupMembers(flag){
	if(flag != undefined){
		$("#tblGroupUser tbody").html("");
	}
	$.post("CreateGroupMember", {		//Requesting to servlet 
		type : "GetData",		//Input parameters		
		groupid : GROUP_ID
	    },
	function(data) {				// Return JSON Object
		
			if(data.status==="success") {
				var src = $("#getGroupMemberData").html();
				var template = Handlebars.compile(src);
				var output = template(data);			
				$("#tblGroupUser tbody").append(output);
			}
	});

}
$("a[href='#DeleteGroupUser']").live("click", function(e) {
	e.preventDefault();	
	DELETE_GROUP_USER_ID = parseInt($(this).parents().eq(1).find("td:nth-child(1)").text());
		
});

function DeleteGroupUser(){
	$.post("CreateGroupMember", {		//Requesting to servlet 
		type : "DeleteData",		//Input parameters
		groupid:GROUP_ID,
		userid:DELETE_GROUP_USER_ID
		},
		
	function(data) {				// Return JSON Object
			if(data.status==="success"){ 
				$("#DeleteGroupUser").modal("hide");
				showGroupMembers(true);
				 successOverlay(true,"Member is removed from groupe");
			}
			  else {
				  errorOverlay("Opss!! Error while removing member from group");
				  }
			});		
		

}

function insertGroupMember() {
	var uemail = $("#txtInsertUserEmail").val();
	var position = $("select[name='txtUserPosition']").val();

	
	$.post("CreateGroupMember", {
		type : "SaveData",
		name : GROUP_ID,
		email : uemail,
		position : position
	// status:gstatus
	},

	function(data) {
		if (data.status === "success") {
			$("#btnCancelAdd").click();
			showGroupMembers();
			successOverlay(true,"Member Added");
			
		} else {
			errorOverlay(true,"Opps! Error in adding new Member");
		}

	});
	/*
	 * alert("email = "+email); alert("branch = "+branch); alert("role =
	 * "+role); alert("year = "+year);
	 */

}

/*$("a[href='#EditGroupUser']").live(
		"click",
		function(e) {
			EDIT_GROUP_USER_ID = parseInt($(a[href='#EditGroupUser']).parents().eq(1).find(
					"td:nth-child(1)").text());
			// var
			// gposition=$(this).parents().eq(1).find("td:nth-child(2)").text();
			// var gstatus=$("select [name='txtEditGroupStatus']").val();

			console.log("edit_group_User_Id= " + EDIT_GROUP_USER_ID);

			/*
			 * $.post("CreateGroupMember",{ type:"EditData",
			 * groupid:EDIT_GROUP_ID, position:gposition, status:gstatus },
			 * function(data) {
			 * 
			 * });
			 
		});*/

function editGroupMember() {
	EDIT_GROUP_ID = parseInt($(this).parents().eq(1).find(
	"td:nth-child(1)").text());
	EDIT_GROUP_USER_ID = parseInt($("a[href='#EditGroupUser']").parents().eq(1).find(
	"td:nth-child(1)").text());
	console.log("edit_group_User_Id= " + EDIT_GROUP_USER_ID);
	var gposition = $("#txtEditGroupPosition").val();
	var gstatus = $("#txtEditGroupStatus").val();

	$.post("CreateGroup", {
		type : "EditData",
		groupid : EDIT_GROUP_ID,
		position : gposition,
		status : gstatus
	},

	function(data) {

	});
}

/** ********************************Role***************************************** */
// To Save Role
$("a[id='txtNewRole']").on("click", function(e) {
	e.preventDefault();
	if (!$(this).hasClass("disabled")) {

		var roleName = $("#txtRole").val();
		$.post("CreateRole", { // Requesting to servlet
			type : "SaveData", // Input parameters
			name : roleName

		}, function(data) { // Return JSON Object
			// console.log(data);
			// console.log("Branches : "+data.branch[0].name+",
			// "+data.branch[1].name+", "+data.branch[2].name);

			if (data.status === "success") {
				loadAllRoles();
				successOverlay(true,"Role Added Successfully");
			} else {
				erroOverlay(true,"Oppss!! Error in adding new role");
			}
		});

	} else {

	}
});

// Edit Role
$('#btnSave').on("click", function(e) {
	e.preventDefault();
	var name = $("input[name='txtRoleName']").val();

	$.post("CreateRole", {
		type : "EditData",
		rolesid : lastRoleId,
		name : name

	}, function(data) {

		if (data.status === "success") {
			$("#EditRole").modal("hide");
			loadAllRoles();
			successOverlay(true,"Role updated Successfully");
		} else {
			errorOverlay(true,"Oppss!! Error in updating role");
		}
	}

	);
});

// Delete Role, Select Role
$('a[href="#roles"]').on("click", function(e) {
	e.preventDefault();
	loadAllRoles();

});
function loadAllRoles(){
	$.post("CreateRole", { // Requesting to servlet
		type : "GetData", // Input parameters
	}, function(data) { // Return JSON Object

		if (data.status === "success") {
			$("#tblRoles tbody").html("");
			var src = $("#getRole").html();
			var template = Handlebars.compile(src);
			var output = template(data);

			$("#tblRoles tbody").append(output);
			
			$("#drpBranch select").add(output);
			$("a[href='#EditRole']").on("click", function(e) {
				e.preventDefault();
				var roleName = $(this).attr("data-rolename");

				lastRoleId = $(this).attr("data-roleid");
				$("#EditRole").find("input[name='txtRoleName']").val(roleName);

			});

			$("a[href='#DeleteRole']").on("click", function(e) {
				lastRoleId = $(this).attr("data-roleid");
				e.preventDefault();
				$.post("CreateRole", {
					type : "DeleteData",
					rolesid : lastRoleId

				}, function(data) {
					
					if (data.status === "success") {
						loadAllRoles();
						successOverlay(true,"Role Deleted Successfully");
					} else {
						errorOverlay(true,"Oppss!! Error in deleting role.");
					}
				}

				);

			});

		}

	});
}

// To Save Branch

$("a[id='txtNewBranch']").on("click", function(e) {
	e.preventDefault();
	if (!$(this).hasClass("disabled")) {

		var branchName = $("#txtBranch").val();

		$("#txtBranchName").val(branchName);

	} else {

	}
});
$('#btnAdd').on("click", function(e) {
	var branchName = $("#txtBranchName").val();
	var duration = $("#txtDuration").val();
	e.preventDefault();
	$.post("CreateBranch", { // Requesting to servlet
		type : "SaveData", // Input parameters
		branchName : branchName,
		duration : duration

	}, function(data) { // Return JSON Object
		// console.log(data);
		// console.log("Branches : "+data.branch[0].name+",
		// "+data.branch[1].name+", "+data.branch[2].name);

		if (data.status === "success") {
			$("#CreateBranch").modal("hide");
			loadBranch();
			successOverlay(true,"New Branch Added Sucessfully.");
		} else {
			errorOverlay(true,"Oppss!! Error in Adding branch");
		}
	});

});

// Get Data
$('a[href="#branch"]').on(
		"click",
		function(e) {
			e.preventDefault();
loadBranch(true);
		});
function loadBranch(flag){
	if(flag!=undefined){
		$("#tblBranch tbody").html("");
	}
	$.post("CreateBranch", { // Requesting to servlet
		type : "GetData", // Input parameters
	}, function(data) { // Return JSON Object
		// console.log(data);
		// console.log("Branches : "+data.branch[0].name+",
		// "+data.branch[1].name+", "+data.branch[2].name);
		// alert(data.status);

		if (data.status === "success") {
			var src = $("#getBranch").html();
			var template = Handlebars.compile(src);
			var output = template(data);

			$("#tblBranch tbody").append(output);
			$("#drpBranch select").add(output);
			$("a[href='#EditBranch']").on(
					"click",
					function(e) {
						e.preventDefault();
						var branchName = $(this).attr("data-branchname");

						lastBranchId = $(this).attr("data-branchid");
						$("#EditBranch").find(
								"input[name='txtBranchName1']").val(
								branchName);

						var duration = $(this).attr("data-duration");

						$("#EditBranch").find(
								"input[name='txtDuration1']").val(
								duration);

					});
			$("a[href='#DeleteBranch']").on("click", function(e) {
				lastBranchId = $(this).attr("data-branchid");
				e.preventDefault();
				$.post("CreateBranch", {
					type : "DeleteData",
					branchid : lastBranchId

				}, function(data) {

					if (data.status === "success") {
						loadBranch(true);
						successOverlay(true,"Branch Deleted Sucessfully.");
					} else {
						errorOverlay(true,"Oppss!! Error in deleting branch");
					}
				}

				);

			});

		}

	});
}
// Edit Branch

$('#btnSave1').on("click", function(e) {
	e.preventDefault();
	var name1 = $("input[name='txtBranchName1']").val();
	var duration = $("input[name='txtDuration1']").val();

	$.post("CreateBranch", {
		type : "EditData",
		branchid : lastBranchId,
		duration : duration,
		name : name1

	}, function(data) {

		if (data.status === "success") {
			$("#EditBranch").modal("hide");
			loadBranch(true);
			successOverlay(true,"Branch Updated Sucessfully.");
		} else {
			errorOverlay(true,"Oppss!! Error in Updating branch");
		}
	}

	);
});

/** ****** start script for User **** */

$("#btnSaveCreateUser").on("click", function(e) {
	e.preventDefault();
	var uemail = $("textarea[name='txtAUserEmail']").val();
	var ubranch = $("#drpABranch").val();
	var urole = $("#drpARole").val();
	var uyear = $("input[name='txtUserYear']").val();
	$.post("CreateUser", { // Requesting to servlet
		type : "SaveData", // Input parameters
		email : uemail,
		branch : ubranch,
		year : uyear,
		role : urole
	}, function(data) { // Return JSON Object
		if (data.status === "success") {
			loaduser();
			$("#CreateUser").modal("hide");
			successOverlay(true,"Users Added Successfully");
			// showPopup("Data is successfully added");
		} else {
			errorOverlay(true,"Opps!! Error in adding new Users");
		}
	});

});

$("a[href='#CreateUser']").on("click", function(e) {
	e.preventDefault();

	if (!$(this).hasClass("disabled"))
		$("#CreateUser").modal('show');
	else
		$("#CreateUser").modal('hide');
});

$("a[href='#users']").on("click", function(e) {
	e.preventDefault();
	loaduser(true);
});

function loaduser(flag){

	if(flag!=undefined){
		$("#tblUsers tbody").html("");
	}
	$.post("CreateUser", { // Requesting to servlet
		type : "UserData", // Input parameters
	}, function(data) { // Return JSON Object
		if (data.status === "success") {
			var src = $("#getUsers").html();
			var template = Handlebars.compile(src);
			var output = template(data);
			$("#tblUsers tbody").append(output);
			$.post("CreateUser", { // Requesting to servlet
				type : "BranchData", // Input parameters
			}, function(data) { // Return JSON Object
				if (data.status === "success") {
					var src = $("#getBranches").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					$("#drpEBranch").html(output);
					$("#drpABranch").html(output);
				}
			});

			$.post("CreateUser", { // Requesting to servlet
				type : "RoleData", // Input parameters
			}, function(data) { // Return JSON Object
				// console.log(data);
				// console.log("Role : "+data.role[0].name+",
				// "+data.role[1].name+", "+data.role[2].name+",
				// "+data.role[3].name);
				if (data.status === "success") {
					var src = $("#getRoles").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					$("#drpERole").html(output);
					$("#drpARole").html(output);
					// $("#drpBranch select").add(output);
				}
			});
		}
	});
	
}
$("a[href='#EditUser']").live(
		"click",
		function(e) {
			e.preventDefault();
			EDITUSER_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var userEmail = $(this).parents().eq(1).find("td:nth-child(2)")
					.text();
			var branchYear = $(this).parents().eq(1).find("td:nth-child(4)")
					.text();
			var role = $(this).parents().eq(1).find("td:nth-child(5)").text();
			var status= $(this).parents().eq(1).find("td:nth-child(6)").text();
			
			var bdata = branchYear.split("-");
			var ustatus = $("select [name='txtUserStatus']").val();
			$("#EditUser #dlgLabel").text("Edit User - " + userEmail);
			$("#EditUser").find("input[name='txtUserEmail']").val(userEmail);
			$("#EditUser").find("input[name='txtUserYear']").val(bdata[1]);
			
			$("input[name=txtEUserEmail]").val(userEmail);
			$("select[name=txtUserGroup]").val(bdata[0]);
			$("input[name=txtEUserYear]").val(bdata[1]);
			$("select[name=txtUserPrivilege]").val(role);
			$("select[name=txtEUserStatus]").val(status);
			
			

		});
$("a[href='#DeleteUser']").live(
		"click",
		function(e) {
			e.preventDefault();

			DELETEUSER_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			/*
			 * var userEmail =
			 * $(this).parents().eq(1).find("td:nth-child(2)").text(); var
			 * branchYear =
			 * $(this).parents().eq(1).find("td:nth-child(4)").text(); var role =
			 * $(this).parents().eq(1).find("td:nth-child(5)").text(); var
			 * bdata=branchYear.split("-"); var status=$("select
			 * [name='txtUserStatus']").val();
			 */
			// alert(DELETEUSER_ID);
			// console.log("$$$$$$ "+DELETEUSER_ID);
		});

$("#btnEditCreateUser").on(
		"click",
		function(e) {
			e.preventDefault();
			var uemail = $("input[name='txtEUserEmail']").val();
			var ubranch = $("#drpEBranch").val();
			// alert($("select[name='txtUserGroup']").val());
			var urole = $("#drpERole").val();
			var uyear = $("input[name='txtEUserYear']").val();
			var ustatus = $("#drpEStatus").val();
			
			if (ustatus === "Active")
				ustatus="V";
			else if(ustatus === "New")
				ustatus="N";
			else if(ustatus === "Verify")
				ustatus="R";
			else if(ustatus === "Block")
				ustatus="B";
			else
				ustatus="D";
						
			$.post("CreateUser", { // Requesting to servlet
				'type' : "EditData", // Input parameters
				'id' : EDITUSER_ID,
				'email' : uemail,
				'branch' : ubranch,
				'year' : uyear,
				'role' : urole,
				'stat' : ustatus
			}, function(data) { 
				if($.trim(data.status)==="success"){
					loaduser(true);
					$("#EditUser").modal("hide");
					successOverlay(true,"User Data Updated Successfully");
				}else{
					errorOverlay(true,"Opps!! Error in updating user data");
				}
			});
		});

$("#btnDeleteCreateUser").on("click", function(e) {
	e.preventDefault();
	$.post("CreateUser", { // Requesting to servlet
		type : "DeleteData", // Input parameters
		id : DELETEUSER_ID,
	}, function(data) { // Return JSON Object
		if($.trim(data.status)==="success"){
			loaduser(true);
			$("#DeleteUser").modal("hide");
			successOverlay(true,"User Deleted Successfully");
		}else{
			errorOverlay(true,"Opps!! Error in Deleting User");
		}
	});

	});


/******** End script for User *****/



//Get Data
function startReportAbuse() {
	$("#tblReport tbody").html("");
	$.get("ReportAbuses", {		//Requesting to servlet 
		type : "GetData",		//Input parameters		
		},
	function(data) {				// Return JSON Object
	
			if(data.status==="success") {
				var src = $("#getReportAbuses").html();
				var template = Handlebars.compile(src);
				var output = template(data);
				
				$("#tblReport tbody").append(output);
				
				$("a[href='#dlgUserProfile']").on(
						"click",
						function() {
							loadProfile("email",$(this).data("email"))
							
						});
				
				
				
				//$("#drpBranch select").add(output);
				$("a[href='#BlockPost']").on(
						"click",
						function() {
						
							var pId = $(this).attr("data-pid");
							
							$.get("ReportAbuses",
									{
										type : "BlockPost",
										pid : pId
										
										
									},
									function(data)
									{
										
										if(data.status==="success")
											{
											successOverlay(true,"Post Blocked Sucessfully.");
											startReportAbuse()
											}
										else
											{
											errorOverlay(true,"Opps!! Error in Block Post");
											}
									}
							
							);
						});
				$("a[href='#BlockUser']").on(
						"click",
						function() {
						
							var id = $(this).attr("data-id");
							var pid = $(this).attr("data-pid");
							$.get("ReportAbuses",
									{
										type : "BlockUser",
										id : id,
										pid : pid
										
										
									},
									function(data)
									{
										
										if(data.status==="success")
											{
											startReportAbuse();
											successOverlay(true,"User Blocked Sucessfully.");
											}
										else
											{
											errorOverlay(true,"Opps!! Error in BlockUser");
											}
									}
							
							);
						});

				$("a[href='#Check']").on(
						"click",
						function() {
						
							//var id = $(this).attr("data-id");
							var pid = $(this).attr("data-pid");
							
							$.get("ReportAbuses",
									{
										type : "Check",
										pid : pid,
										rstatus : "R"
										
									},
									function(data)
									{
										
										if(data.status==="success")
										{
											startReportAbuse();
											successOverlay(true,"Check Sucessfully.");
											}
										else
											{
											errorOverlay(true,"Opps!! Error in Check");
											}
									}
							
							);
						});
				$("a[href='#ViewUser']").on(
						"click",
						function() {
						
							var id = $(this).attr("data-pid");
							$.get("ReportAbuses",
									{
										type : "ViewUser",
										pid : id,
																			
									},
									function(data)
									{
										
										if(data.status==="success")
											{
												var src = $("#getReport").html();
												var template = Handlebars.compile(src);
												var output = template(data);
												$("#tblReport1 tbody").append(output);
												//$("#drpBranch select").add(output);
												
											}
										else
											{
											errorOverlay(true,"Opps!! Error in View user");
											}
									}
							
							);
						});

				$("a[href='#ViewBlockUser']").on(
						"click",
						function() {
						
							var id = $(this).attr("data-id");
							$.get("ReportAbuses",
									{
										type : "ViewBlock",
										id : id														
									},
									function(data)
									{
										
										if(data.status==="success")
											{
												//alert("Check Sucessfully.");
												var src = $("#getBlockUser").html();
												var template = Handlebars.compile(src);
												var output = template(data);
												
												$("#tblBlockUser tbody").append(output);
												//$("#drpBranch select").add(output);
												$("a[href='#UnblockUser']").on(
														"click",
														function() {
														
															var id = $(this).attr("data-id");
															
															$.get("ReportAbuses",
																	{
																		type : "UnblockUser",
																		id : id
																																					
																	},
																	function(data)
																	{
																		
																		if(data.status==="success")
																			{
																			successOverlay(true,"Unblock Sucessfully.");
																				
																			}
																		else
																			{
																			errorOverlay(true,"Opps!! Error in Unblock User");
																			}
																	}
															
															);
														});
											}
										else
											{
											errorOverlay(true,"Opps!! Error ");
											}
									}
							
							);
						});
				
			}
			
	});
};

$("a[href='#reports']").live("click", function(e) {
	startReportAbuse();
});


function loadProfile(type,detail){
	$.ajax({
		url : 'User/Profile',
		type : 'post',
		data : {
			"type" : type,
			"data" : detail			
		},
		success : function(data) {
			if (data.status === 'success') {
				//
				var source = $("#tmpltuserProfile").html();
				var template = Handlebars.compile(source);
				var html = template(data);
				$("#divProfile").html(html);
				$("#dlgUserProfile").modal();
				$("#btnModrate").click(function(e){
					var uEmail= $(this).data("email");
				
					$.ajax({
						url : 'User/Profile',
						type : 'post',
						data : {
							"type" : "m",
							"data" : uEmail			
						},
						success : function(data) {
							if (data.status === 'success') {
								successOverlay(true, data.message);
							} else {
								// Failed
								errorOverlay(true, data.message);
								
							}
						},
						error : function() {
							errorOverlay(true, 'Oops! something went wrong. Please refresh the page');
					
						}

					});		
				});
					
			} else {
				// Failed
				errorOverlay(true, data.message);
				
			}
		},
		error : function() {
			errorOverlay(true, 'Oops! something went wrong. Please refresh the page');
	
		}
	});
	
	
	
	
}

if(objMyData.role.toLowerCase()=="moderator"){
	
	startReportAbuse();
	$("#reports").show();
	

}else {
	showGroup();
}
