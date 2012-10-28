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
$('.btn-facebook')
		.click(
				function() {
					FB
							.login(
									function(response) {
										if (response.authResponse) {
											// Get User Details
											FB
													.api(
															'/me',
															function(resp) {
																var tmpData = {
																	'firstName' : resp.first_name,
																	'lastName' : resp.last_name,
																	'pictureUrl' : 'http://graph.facebook.com/'
																			+ resp.id
																			+ '/picture',
																	'gender' : resp.gender

																};
																showEmailDiv(
																		tmpData,
																		'facebook');
																// After
																// Authentication
																// Process to
																// next step
															});
										} else {
											// User close auth window
											showPopup('<p>User cancelled login or did not fully authorize.</p>')

										}
									}, {
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
	$.ajax({
		url : 'User/Registration/New',
		type : 'post',
		data : userData,
		success : function(data) {
			if (data.status === 'fail') {
				// Failed
				showPopup(data.message);
			} else {
				// Sucess
				showPopup(data.message);
				hideEmailDiv();
			}
		},error:function(){
			showPopup('Some Error Occured, Please Refresh page');
		}
	});

})
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
