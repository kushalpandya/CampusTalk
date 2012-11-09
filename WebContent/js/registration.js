var LOGIN_STATUS = $("span.login-status");

// LinkedIn registration
$('.btn-linkedin').click(function() {
	IN.User.authorize();
	return false;
});
// call back fuunction if user is authorised
var userData;
IN.Event.on(IN, 'auth', function() {
	if (IN.User.isAuthorized()) {
		// using js sdk get liked user details.
		IN.API.Profile("me").result(function(result) {
			showEmailDiv(result.values[0], 'linkedin');
		});
	}
	;
});

// Facebook Registration
$('.btn-facebook').click(function(){
	FB.login(function(response){
		if (response.authResponse) {
		// Get User Details
			FB.api('/me',function(resp){
				var tmpData = {
						'firstName' : resp.first_name,
						'lastName' : resp.last_name,
						'pictureUrl' : 'http://graph.facebook.com/'+ resp.id+ '/picture',
						'gender' : resp.gender
				};
				showEmailDiv(tmpData,'facebook');
			});
		} else {
		// User close auth window
			errorOverlay(true, 'User cancelled login or did not fully authorize his account.');
		}
	},{
		scope : 'email'
	});
});

$('.btn-google').click(function() {
	var config = {
		'client_id' : '384694029279.apps.googleusercontent.com',
		'scope' : 'https://www.googleapis.com/auth/plus.me'
	};
	gapi.auth.authorize(config, function() {
		gapi.client.load('plus', 'v1', function() {
			var request = gapi.client.plus.people.get({
				'userId' : 'me'
			});
			request.execute(function(resp) {
				var tmpData = {
					'firstName' : resp.name.givenName,
					'lastName' : resp.name.familyName,
					'pictureUrl' : resp.image.url,
					'gender' : resp.gender

				};
				showEmailDiv(tmpData, 'google');
			});
		});
	});
});

function showEmailDiv(result, registerwith) {
	userData = result;
	userData['registerWith'] = registerwith;
	$(".account-block").slideUp('slow');
	$(".signup-block").slideDown('slow');
}

function hideEmailDiv() {
	userData = [];
	$(".signup-block").slideUp('slow');
	$(".account-block").slideDown('slow');
	$("#txtPassword").val("");
	$("#txtConfirmPassword").val("");
}

$('#frmRegistration').submit(function(e) {
	e.preventDefault();
	userData['email'] = $("#txtEmail").val();
	userData['password'] = $("#txtPassword").val();
	userData['cpassword'] = $("#txtConfirmPassword").val();
	if(userData['email'].indexOf('@') < 0 ){
		errorOverlay(true, "Invalid Email address");
		return;
	}
	if(userData['password']==""){
		errorOverlay(true, "Please Enter Password");
		return;
	}
	if(userData['cpassword']==""){
		errorOverlay(true, "Please Enter Confirm Password");
		return;
	}
	if(userData['cpassword'] != userData['password']){
		errorOverlay(true, "Password and Confirm Password not match");
		return;
	}
	if(userData['cpassword'].length < 7){
		errorOverlay(true, "Minimum Password length is 7");
		return;
	}
	
	$.ajax({
		url : 'User/Registration/New',
		type : 'post',
		data : userData,
		success : function(data) {
			if (data.status === 'success') {
				// Success
				successOverlay(true, data.message);
				hideEmailDiv();
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
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.search);
	if (results == null)
		return "";
	else
		return decodeURIComponent(results[1].replace(/\+/g, " "));
}
if (getParameterByName("q") != "" && getParameterByName("e") != "") {
	$.ajax({
		url : 'User/Registration/Verify',
		type : 'post',
		data : {
			"q" : getParameterByName("q"),
			"e" : getParameterByName("e")
		},
		success : function(data) {
			if (data.status === 'success') {
				// Success
				successOverlay(true, data.message);
			} else {
				// Failed
				errorOverlay(true, data.message);
			}
		},
		error : function() {
			errorOverlay(true,'Oops! something went wrong. Please refresh the page');
		}
	});
}

$('#frmLogin').submit(function(e) {
	e.preventDefault();
	var email = $("#txtLoginEmail").val();
	var password = $("#txtLoginPassword").val();
	var rememberMe= ($('#chkbLoginRemember').is(':checked'))?"true" : "false";
	$.ajax({
		url : 'Login',
		type : 'post',
		data : {
			'email' : email,
			'password':password,
			'remember':rememberMe,
			'type': 'login'
		},
		success : function(data) {
			if (data.status === 'success') {
				// success
				successfullLogin();
			} else {
				// Failed
				LOGIN_STATUS.text(data.message).show();
			}
		},
		error : function() {
			LOGIN_STATUS.text('Oops! something went wrong while logging you in. Try again.').show();
		}
	});
});

function successfullLogin() {
	window.location = "home.jsp";
}


function doAutoLogin() {
	$("#txtLoginEmail").val(getCookie("CampusTalkEmail"));
	$.post("Login", {
		type : "sessionlogin"
	}, function(data) {
		if (data.status === "success") {
			successfullLogin();
		}
	});
}
doAutoLogin();
