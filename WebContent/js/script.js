var POSTBOX_BOTTOM_MARGIN = 20;
var LOADER_OVERLAY = $("<div class='loading-overlay'><i class='loader-icon'></i></div>");

/** ******************** Initializers ********************** */

$.ajaxSetup({
	beforeSend : function(jqXHR, settings) {
		$("body").append(LOADER_OVERLAY);
	},
	complete : function(jqXHR, settings) {
		$("body").find(".loading-overlay").remove();
	}
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

$(".account-tray #showMessages").on("click", function(e) {
	e.preventDefault();
	$("#dlgMessages").modal();
});

$(".account-tray #btnSettings").on("click", function(e) {
	$("#dlgAccountSettings").modal();
	$("input[name='txtBirthDate']").datepicker({
		format: "mm-dd-yyyy",
		weekStart: 1
	});
});

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

$("#btnNewMessage").on("click", function(e) {
	var modal = $(this).parents().eq(2);
	modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
	modal.find(".modal-drawer").slideDown(250);
});

$("form button[name='btnChangePassword']").on("click", function(e) {
	var modal = $(this).parents().eq(4);
	modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
	modal.find(".modal-drawer").slideDown(250);
});

$("#btnCancelNewMessage").on("click", function(e) {
	var modal = $(this).parents().eq(3);
	modal.find(".modal-disable-overlay").hide();
	modal.find(".modal-drawer").slideUp(250);
});

$("#btnCancelChangePass").on("click", function(e) {
	$("#dlgAccountSettings .modal-disable-overlay").hide();
	$("#dlgAccountSettings .modal-drawer").slideUp(250);
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

//
function loadingOverlay(toggle) {
	toggle ? $("body").append(LOADER_OVERLAY) : $("body").find(
			".loading-overlay").remove();
}

// Model Windows Show Function
function showPopup(templateHTML) {
	$('#popupLink').avgrund({
		width : 380, // max is 640px
		height : 280, // max is 350px
		showClose : true, // switch to 'true' for enabling close button
		showCloseText : 'Close', // type your text for close button
		closeByEscape : true, // enables closing popup by 'Esc'..
		closeByDocument : true, // ..and by clicking document itself
		holderClass : '', // lets you name custom class for popin holder..
		overlayClass : '', // ..and overlay block
		enableStackAnimation : false, // another animation type
		onBlurContainer : '', // enables blur filter for specified block
		template : templateHTML
	}).click();
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

$("#btnLogout").click(function(e) {
	e.preventDefault();
	window.location = "Logout";
});
