/** ******************** Globals ********************** */

var POSTBOX_BOTTOM_MARGIN = 20;

/** ******************** Builders ********************** */
var LOADER_OVERLAY = $("<div class='loading-overlay'><i class='loader-icon'></i></div>");
var ERROR_OVERLAY = $("<div class='error-overlay'><div class='error-box'><div class='error-close'>&times;</div><span>&#9888;</span><label><label></div></div>");
var SUCCESS_OVERLAY = $("<div class='success-overlay'><div class='success-box'><div class='success-close'>&times;</div><span>&#10004;</span><label><label></div></div>");

/** ******************** Initializers ********************** */

$.ajaxSetup({
	beforeSend : function(jqXHR, settings) {
		$("body").append(LOADER_OVERLAY);
	},
	complete : function(jqXHR, settings) {
		$("body").find(".loading-overlay").remove();
	}
});

//THIS CAN BE A PERFORMANCE BOTTLE-NECK, AVOID UNLESS ABSOLUTELY NECESSARY
$(window).on("resize", function() {
	$(".feeds-block").height($(window).height() * 80/100);
});

/** ******************** Account tray script ********************** */

$(".account-tray #btnLogout").click(function(e) {
	e.preventDefault();
	window.location = "Logout";
});

$(".account-tray #btnSettings").on("click", function(e) {

	$.ajax({
		url : 'User/Profile',
		type : 'post',
		data : {
			"type" : "email",
			"data" : objMyData.email		
		},
		success : function(data) {
			$("#txtFname").val(data.firstname);
			$("#txtLname").val(data.lastname);
			$("#txtBirthDate").val(data.birthdate);
			$("#rd" + data.gender.toLowerCase()).attr("checked","checked");
			$("#dlgAccountSettings").modal();
		},
		error : function() {
			errorOverlay(true, 'Oops! something went wrong. Please refresh the page');
	
		}

	});		
	
	
});

$("#dlgAccountSettings, #dlgEvents").on("shown", function(e) {
	var modal = $(this);
	modal.find("input[name$='Date']").datepicker({
		format: "dd-mm-yyyy",
		weekStart: 1,
		autoclose: true,
		todayHighlight: true
	});
	
	modal.find("input[name$='Time']").timepicker();
});

$("#dlgAccountSettings form button[name='btnChangePassword']").on("click", function(e) {
	var modal = $(this).parents().eq(4);
	modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
	modal.find(".modal-drawer").slideDown(250);
});

/*Open Drawer on Active Modal*/
$("#dlgMessages #btnNewMessage, #dlgUserProfile #btnNewMessage").on("click", function(e) {
	e.preventDefault();
	var modal = $(this).parents().eq(2);
	modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
	modal.find(".modal-drawer").slideDown(250);
});

/*Close Drawer on Active Modal*/
$(" #dlgAccountSettings #btnCancelChangePass, #dlgMessages #btnCancelNewMessage, #dlgUserProfile #btnCancelNewMessage").on("click", function(e) {
	var modal = $(this).parents().eq(3);
	modal.find(".modal-disable-overlay").hide();
	modal.find(".modal-drawer").slideUp(250);
});

/* Show Event New Event Form in Drawer */
$("#dlgEvents #btnNewEvent").on("click", function() {
	var modal = $(this).parents().eq(2);
	modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
	modal.find(".modal-drawer, .form-create-event").slideDown(250);
});

/* Show Event Event Details in Drawer */
$("#dlgEvents a[href='#EventDetails']").on("click", function(e) {
	e.preventDefault();
	var modal = $(this).parents().eq(5);
	modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
	modal.find(".modal-drawer, .form-view-event").slideDown(250).find(".form-view-event").show();
});

/* Close the Event Drawer */
$("#dlgEvents #btnCancelNewEvent").on("click", function() {
	var modal = $(this).parents().eq(3);
	modal.find(".modal-disable-overlay").hide();
	modal.find(".modal-drawer, [class^='form']").slideUp(250);
});

/** ******************** home.html script ********************** */
$("#postBox").on(
		"keypress",
		function(e) {
			var postBox = $(this);
			var boxH = postBox.height();
			if (e.keyCode == 13 && boxH <= 80) {
				postBox.height(boxH + 20);
				$(".feeds-list").css("marginTop",
						(POSTBOX_BOTTOM_MARGIN + 130).toString() + "px");
			}
		});

$("#postBox").on("blur", function() {
	if ($(this).val().trim() === "") {
		$(this).val("").height(20);
		$(".feeds-list").css("marginTop", "20px");
	}
});

function afterLoadComments() {
	$(".feed-comments li").on("mouseenter", function() {
		$(this).find(".comment-action").show();
	});

	$(".feed-comments li").on("mouseleave", function() {
		$(this).find(".comment-action").hide();
	});
}

$("input[name='txtBirthDate']").on("click", function(e) {
	$(this).datepicker("show");
});

/** ******************** controlpanel.html script ********************** */

$("#ViewGroup").on("shown", function(e) {
	var modal = $(this);
	modal.css("margin-left", (modal.outerWidth() / 2) * -1);
});

$(".input-append input").on("keyup", function() {
	if ($(this).val().trim().length > 0)
		$(this).parent().find("a").removeClass("disabled");
	else
		$(this).parent().find("a").addClass("disabled");
});

$("a[href='#CreateGroup']").on("click", function(e) {
	e.preventDefault();
	if (!$(this).hasClass("disabled")) {
		var groupName = $(this).parent().find("input").val();
		$("#CreateGroup").modal('show');
		$("#CreateGroup").find("input[name='txtGroupName']").val(groupName);
	} else
		$("#CreateGroup").modal('hide');
});

$("a[href='#EditGroup']").on(
		"click",
		function(e) {
			var groupId = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var groupName = $(this).parents().eq(1).find("td:nth-child(2)")
					.text();

			$("#EditGroup #dlgLabel").text("Edit Group - " + groupName);
			$("#EditGroup").find("input[name='txtGroupName']").val(groupName);
		});

$("a[href='#ViewGroup']").on(
		"click",
		function(e) {
			var groupId = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var groupName = $(this).parents().eq(1).find("td:nth-child(2)")
					.text();

			$("#ViewGroup #dlgLabel").text(groupName);
			$("#ViewGroup").css("margin-left",
					($("#ViewGroup").outerWidth() / 2) * -1);
		});

$("a[href='#EditGroupUser']").on("click", function(e) {
	e.preventDefault();
	$(".modal-disable-overlay").show();
	var dropform = $(".modal-dropform");
	dropform.css("height", "295");
	dropform.slideDown(250).find("form.form-edit-member").show();
});

$("#btnAddGroupMembers").on("click", function(e) {
	$(".modal-disable-overlay").show();
	var dropform = $(".modal-dropform");
	dropform.css("height", "315");
	dropform.slideDown(250).find("form.form-add-member").show();
});

$("#btnCancelEdit, #btnCancelAdd").on("click", function(e) {
	$(".modal-disable-overlay").hide();
	$(".modal-dropform").slideUp(250).find("form").hide();
});

$("a[href='#CreateUser']").on("click", function(e) {
	e.preventDefault();
	if (!$(this).hasClass("disabled"))
		$("#CreateUser").modal('show');
	else
		$("#CreateUser").modal('hide');
	$("textarea[name=txtAUserEmail]").val($("input[name=txtNewUser]").val());
});

$("a[href='#EditUser']").on(
		"click",
		function(e) {
			var userId = parseInt($(this).parents().eq(1).find(
					"td:nth-child(1)").text());
			var userEmail = $(this).parents().eq(1).find("td:nth-child(2)")
					.text();

			$("#EditUser #dlgLabel").text("Edit User - " + userEmail);
			$("#EditUser").find("input[name='txtUserEmail']").val(userEmail);
		});

/** ******************** General methods ********************** */

//Loading Animation Overlay
function loadingOverlay(toggle) {
	toggle ? $("body").append(LOADER_OVERLAY) : $("body").find(".loading-overlay").remove();
}

//Error Message Overlay
function errorOverlay(toggle, message) {
	if(toggle)
	{
		$("body").append(ERROR_OVERLAY);
		$("body .error-overlay .error-box label").text(message);
		
		$("body .error-overlay .error-box .error-close").bind("click", function(e) {
			$("body").find(".error-overlay").remove();
		});
	}
	else
	{
		$("body .error-overlay .error-box .error-close").unbind("click");
		$("body").find(".error-overlay").remove();
	}
}

//Success Message Overlay
function successOverlay(toggle, message) {
	if(toggle)
	{
		$("body").append(SUCCESS_OVERLAY);
		$("body .success-overlay .success-box label").text(message);
		
		$("body .success-overlay .success-box .success-close").bind("click", function(e) {
			$("body").find(".success-overlay").remove();
		});
	}
	else
	{
		$("body .success-overlay .success-box .success-close").unbind("click");
		$("body").find(".success-overlay").remove();
	}
}

// Get Cookie from Browser
function getCookie(check_name) {
	// first we'll split this cookie up into name/value pairs
	// note: document.cookie only returns name=value, not the other components
	var a_all_cookies = document.cookie.split(';');
	var a_temp_cookie = '';
	var cookie_name = '';
	var cookie_value = '';
	var b_cookie_found = false; // set boolean t/f default f

	for (i = 0; i < a_all_cookies.length; i++) {
		// now we'll split apart each name=value pair
		a_temp_cookie = a_all_cookies[i].split('=');

		// and trim left/right whitespace while we're at it
		cookie_name = a_temp_cookie[0].replace(/^\s+|\s+$/g, '');

		// if the extracted name matches passed check_name
		if (cookie_name == check_name) {
			b_cookie_found = true;
			// we need to handle case where cookie has no value but exists (no =
			// sign, that is):
			if (a_temp_cookie.length > 1) {
				cookie_value = unescape(a_temp_cookie[1].replace(/^\s+|\s+$/g,
						''));
			}
			// note that in cases where cookie is initialized but no value, null
			// is returned
			return cookie_value.replace(/"/g, "");
			break;
		}
		a_temp_cookie = null;
		cookie_name = '';
	}
	if (!b_cookie_found) {
		return null;
	}
}
