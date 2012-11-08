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
	if(status==="V")
		return "Active"
	else
		return "Inactive"
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

/** ********************************Group*****************************************  */

//To Save Group
function insertGroup() {
	var gname = $("#txtGroupName").val();
	var gdescription = $("#txtGroupDescription").val();
	$.post("Group/Create", {
		type : "SaveData",
		name : gname,
		description : gdescription,
	//status:gstatus
	},

	function(data) {
		if (data.status === "success") {
			$("#CreateGroup").modal("hide");
			$("#txtGroupName").val("");
			$("#txtGroupDescription").val("");
			sucessOverlay("Group Added Successfully");
			showGroup();
		} else {
			$("#CreateGroup").modal("hide");
			errorOverlay("Oops!! Error in adding Group");
		}

	});

}

//To edit group data
$("a[href='#EditGroup']").live(
		"click",
		function(e) {
			EDIT_GROUP_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var gname = $(this).parents().eq(1).find("td:nth-child(2)").text();
			var gdescription = $(this).parents().eq(1).find("td:nth-child(3)").text();
			var gstatus = $(this).parents().eq(1).find("td:nth-child(4)").text();
			
			console.log("edit_groupId= " + EDIT_GROUP_ID + " gname= " + gname
					+ " gdesc= " + gdescription + " gstatu= " + gstatus);
			$("#txtEditGroupName").val(gname);
			$("#txtEditGroupDescription").val(gdescription);
			if(gstatus==="Active")
				gstatus="1"
			else
				gstatus="0"
			$("#drpGroupStatus").val(gstatus);

			/*$.post("CreateGroup",{
				type:"EditData",
				groupid:EDIT_GROUP_ID,
				name:gname,
				description:gdescription,
				status:gstatus
			},
			function(data)
			{
				
			});*/
		});

function editGroup() {

	console.log("hiral edits");

	var gname = $("#txtEditGroupName").val();
	var gdescription = $("#txtEditGroupDescription").val();
	var gstatus = $("#drpGroupStatus").val();

	console.log("edit_groupId= " + EDIT_GROUP_ID + " gname= " + gname
			+ " gdesc= " + gdescription + " gstatu= " + gstatus);
	$.post("CreateGroup", {
		type : "EditData",
		groupid : EDIT_GROUP_ID,
		name : gname,
		description : gdescription,
		status : gstatus
	},

	function(data) {
		alert(data.status);
	});
}

//to delete group

$("a[href='#DeleteGroup']").live(
		"click",
		function(e) {

			DELETE_GROUP_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());

		});

function DeleteGroup() {
	console.log("Hiral " + DELETE_GROUP_ID);

	$.post("CreateGroup", { //Requesting to servlet 
		type : "DeleteData", //Input parameters
		groupid : DELETE_GROUP_ID
	}, function(data) { // Return JSON Object
		console.log(data.status);
		console.log(data.err);
		//console.log(data);

		if (data.status === "success") {
			alert("Data is successfully deleted...");
		} else {
			alert("Data is not delete....");
		}
	});

}

function showGroup(){
	$.post("CreateGroup", { //Requesting to servlet 
		type : "GetData", //Input parameters		
	}, function(data) { // Return JSON Object
		if (data.status === "success") {
			var src = $("#getGroupData").html();
			var template = Handlebars.compile(src);
			var output = template(data);
			$("#tblGroup tbody").append(output);
			//$("#drpBranch select").add(output);
		}
	});
}


//Load Group data at page load 
showGroup();

$("a[href='#groups']").live("click", function(e) {
	showGroup();
});

/** ********************************GroupMember*****************************************  */

$("a[href='#ViewGroup']").live(
		"click",
		function(e) {

			console.log("hiral");
			GROUP_ID = parseInt($(this).parents().eq(1).find("td:nth-child(1)")
					.text());

			$.post("CreateGroupMember", { //Requesting to servlet 
				type : "GetData", //Input parameters		
				groupid : GROUP_ID
			}, function(data) { // Return JSON Object
				//console.log(data);
				//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);	
				alert(data.status);
				if (data.status === "success") {
					var src = $("#getGroupMemberData").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					$("#tblGroupUser tbody").append(output);
					//$("#drpBranch select").add(output);
				}
			});

		});

/** ********************************Role*****************************************  */
//To Save Role 
$("a[id='txtNewRole']").on("click", function(e) {
	if (!$(this).hasClass("disabled")) {

		var roleName = $("#txtRole").val();
		$.get("CreateRole", { //Requesting to servlet 
			type : "SaveData", //Input parameters
			name : roleName

		}, function(data) { // Return JSON Object
			//console.log(data);
			//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);

			if (data.status === "success") {
				alert("Data is successfully added");
			} else {
				alert("Data already Exist");
			}
		});

	} else {

	}
});

//Edit Role
$('#btnSave').on("click", function(e) {

	var name = $("input[name='txtRoleName']").val();

	$.get("CreateRole", {
		type : "EditData",
		rolesid : lastRoleId,
		name : name

	}, function(data) {

		if (data.status === "success") {
			alert("Data Updated Successfully.");
		} else {
			alert("Data Already Exist");
		}
	}

	);
});

// Delete Role, Select Role
$('a[href="#roles"]').on("click", function(e) {
	//alert("Shreeji");

	$.get("CreateRole", { //Requesting to servlet 
		type : "GetData", //Input parameters		
	}, function(data) { // Return JSON Object
		//console.log(data);
		//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);
		//alert(data.status);

		if (data.status === "success") {
			var src = $("#getRole").html();
			var template = Handlebars.compile(src);
			var output = template(data);

			$("#tblRoles tbody").append(output);
			$("#drpBranch select").add(output);
			$("a[href='#EditRole']").on("click", function(e) {

				var roleName = $(this).attr("data-rolename");

				lastRoleId = $(this).attr("data-roleid");
				$("#EditRole").find("input[name='txtRoleName']").val(roleName);

			});

			$("a[href='#DeleteRole']").on("click", function(e) {
				lastRoleId = $(this).attr("data-roleid");

				$.get("CreateRole", {
					type : "DeleteData",
					rolesid : lastRoleId

				}, function(data) {

					if (data.status === "success") {
						alert("Delete Sucessfully.");
					} else {
						alert("Something Went Wrong");
					}
				}

				);

			});

		}

	});
});

//To Save Branch 

$("a[id='txtNewBranch']").on("click", function(e) {
	if (!$(this).hasClass("disabled")) {

		var branchName = $("#txtBranch").val();

		$("#txtBranchName").val(branchName);

	} else {

	}
});
$('#btnAdd').on("click", function(e) {
	var branchName = $("#txtBranchName").val();
	var duration = $("#txtDuration").val();

	$.get("CreateBranch", { //Requesting to servlet 
		type : "SaveData", //Input parameters
		branchName : branchName,
		duration : duration

	}, function(data) { // Return JSON Object
		//console.log(data);
		//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);

		if (data.status === "success") {
			alert("Data is successfully added");
		} else {
			alert("Data already Exist");
		}
	});

});

//Get Data
$('a[href="#branch"]').on(
		"click",
		function(e) {

			$.get("CreateBranch", { //Requesting to servlet 
				type : "GetData", //Input parameters		
			}, function(data) { // Return JSON Object
				//console.log(data);
				//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);
				//alert(data.status);

				if (data.status === "success") {
					var src = $("#getBranch").html();
					var template = Handlebars.compile(src);
					var output = template(data);

					$("#tblBranch tbody").append(output);
					$("#drpBranch select").add(output);
					$("a[href='#EditBranch']").on(
							"click",
							function(e) {

								var branchName = $(this)
										.attr("data-branchname");

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

						$.get("CreateBranch", {
							type : "DeleteData",
							branchid : lastBranchId

						}, function(data) {

							if (data.status === "success") {
								alert("Delete Sucessfully.");
							} else {
								alert("Something Went Wrong");
							}
						}

						);

					});

				}

			});
		});

// Edit Branch

$('#btnSave1').on("click", function(e) {

	var name1 = $("input[name='txtBranchName1']").val();
	var duration = $("input[name='txtDuration1']").val();

	$.get("CreateBranch", {
		type : "EditData",
		branchid : lastBranchId,
		duration : duration,
		name : name1

	}, function(data) {

		if (data.status === "success") {
			alert("Data Updated Successfully.");

		} else {
			alert("Data Already Exist");
		}
	}

	);
});

/******** start script for User *****/

$("#btnSaveCreateUser").on("click", function(e) {

	var uemail = $("textarea[name='txtAUserEmail']").val();

	var ubranch = $("#drpABranch").val();
	//alert($("select[name='txtUserGroup']").val());
	var urole = $("#drpARole").val();
	var uyear = $("input[name='txtUserYear']").val();

	//alert("email = "+email);
	//alert("branch = "+branch);
	///alert("role = "+role);
	//alert("year = "+year);

	$.post("CreateUser", { //Requesting to servlet 
		type : "SaveData", //Input parameters
		email : uemail,
		branch : ubranch,
		year : uyear,
		role : urole
	}, function(data) { // Return JSON Object
		//console.log(data);
		//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);	
		if (data.status === "success") {
			//			showPopup("Data is successfully added");		
		} else {//showPopup("Data is not added");
		}
	});

});

$("a[href='#CreateUser']").on("click", function(e) {
	e.preventDefault();

	$.post("CreateUser", { //Requesting to servlet 
		type : "BranchData", //Input parameters
	}, function(data) { // Return JSON Object
		//console.log(data);
		//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);

		if (data.status === "success") {
			var src = $("#getBranches").html();
			var template = Handlebars.compile(src);
			var output = template(data);
			$("#drpABranch").append(output);
		}
	});

	$.post("CreateUser", { //Requesting to servlet 
		type : "RoleData", //Input parameters
	}, function(data) { // Return JSON Object
		//console.log(data);
		//console.log("Role : "+data.role[0].name+", "+data.role[1].name+", "+data.role[2].name+", "+data.role[3].name);
		if (data.status === "success") {
			var src = $("#getRoles").html();
			var template = Handlebars.compile(src);
			var output = template(data);
			$("#drpARole").append(output);
			//$("#drpBranch select").add(output);
		}
	});

	if (!$(this).hasClass("disabled"))
		$("#CreateUser").modal('show');
	else
		$("#CreateUser").modal('hide');
});

$("a[href='#users']").on("click", function(e) {
	//alert("Shreeji");

	$.post("CreateUser", { //Requesting to servlet 
		type : "UserData", //Input parameters		
	}, function(data) { // Return JSON Object
		//console.log(data);
		//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);	
		if (data.status === "success") {
			var src = $("#getUsers").html();
			var template = Handlebars.compile(src);
			var output = template(data);
			$("#tblUsers tbody").append(output);
			//$("#drpBranch select").add(output);
		}
	});

});

$("a[href='#EditUser']").live(
		"click",
		function(e) {

			EDITUSER_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var userEmail = $(this).parents().eq(1).find("td:nth-child(2)")
					.text();
			var branchYear = $(this).parents().eq(1).find("td:nth-child(4)")
					.text();
			var role = $(this).parents().eq(1).find("td:nth-child(5)").text();
			var bdata = branchYear.split("-");
			var ustatus = $("select [name='txtUserStatus']").val();

			//	console.log("Id : "+userId+" Email : "+userEmail+ " Branch : "+bdata[0]+" Year : "+bdata[1]+" role : "+role+" status :"+status);
			/*
			 $.post("CreateUser", {		//Requesting to servlet 
			 type : "EditData",		//Input parameters
			 id:EDITUSER_ID,
			 email:uemail,
			 bname:bdata[0],
			 year:bdata[1],
			 role:role,
			 stat:""
			 },
			 function(data) {				// Return JSON Object
			 //console.log(data);
			 //console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);	
			 if(data.status==="success") {
			 showPopup("Data is successfully added");		
			 }	
			 else
			 {showPopup("Data is not added");}
			 });*/

			//alert("user id : "+userId+ " user email : "+userEmail);
			$("#EditUser #dlgLabel").text("Edit User - " + userEmail);
			$("#EditUser").find("input[name='txtUserEmail']").val(userEmail);
			$("#EditUser").find("input[name='txtUserYear']").val(bdata[1]);

			$.post("CreateUser", { //Requesting to servlet 
				type : "BranchData", //Input parameters
			}, function(data) { // Return JSON Object
				//console.log(data);
				//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);

				if (data.status === "success") {
					var src = $("#getBranches").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					$("#drpEBranch").append(output);
				}
			});

			$.post("CreateUser", { //Requesting to servlet 
				type : "RoleData", //Input parameters
			}, function(data) { // Return JSON Object
				//console.log(data);
				//console.log("Role : "+data.role[0].name+", "+data.role[1].name+", "+data.role[2].name+", "+data.role[3].name);
				if (data.status === "success") {
					var src = $("#getRoles").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					$("#drpERole").append(output);
					//$("#drpBranch select").add(output);
				}
			});

		});
$("a[href='#DeleteUser']").live(
		"click",
		function(e) {

			DELETEUSER_ID = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			/*var userEmail = $(this).parents().eq(1).find("td:nth-child(2)").text();
			var branchYear = $(this).parents().eq(1).find("td:nth-child(4)").text();
			var role = $(this).parents().eq(1).find("td:nth-child(5)").text();
			var bdata=branchYear.split("-");
			var status=$("select [name='txtUserStatus']").val();*/
			//alert(DELETEUSER_ID);
			//console.log("$$$$$$ "+DELETEUSER_ID);
		});

$("#btnEditCreateUser").on(
		"click",
		function(e) {
			var uemail = $("input[name='txtEUserEmail']").val();
			var ubranch = $("#drpEBranch").val();
			//alert($("select[name='txtUserGroup']").val());
			var urole = $("#drpERole").val();
			var uyear = $("input[name='txtEUserYear']").val();
			var ustatus = $("#drpEStatus").val();
			//var ustatus=ustatus.substr(0,2);
			/*alert("email = "+email);
			alert("branch = "+branch);
			alert("role = "+role);
			alert("year = "+year);*/

			console.log("Console : " + uemail + ubranch + urole + uyear
					+ ustatus + EDITUSER_ID);

			$.post("CreateUser", { //Requesting to servlet 
				type : "EditData", //Input parameters
				id : EDITUSER_ID,
				email : uemail,
				branch : ubranch,
				year : uyear,
				role : urole,
				stat : ustatus
			}, function(data) { // Return JSON Object
				//console.log(data);
				//console.log("Branches : "+data.branch[0].name+", "+data.branch[1].name+", "+data.branch[2].name);	
				/*if(data.status==="success") {
					
				//		showPopup("Data is successfully added");		
				}	
				else
					{//showPopup("Data is not added");
					
					}*/
				//	console.log(data);
				console.log(data.status);
				console.log(data);
			});
		});

$("#btnDeleteCreateUser").on("click", function(e) {
	console.log("Shreeji " + DELETEUSER_ID);

	$.post("CreateUser", { //Requesting to servlet 
		type : "DeleteData", //Input parameters
		id : DELETEUSER_ID,
	}, function(data) { // Return JSON Object
		console.log(data.status);
		console.log(data);

	});

});

/******** End script for User *****/
