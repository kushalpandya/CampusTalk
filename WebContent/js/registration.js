$(document).ready(function() {
	// LinkedIn registration
	$('.btn-linkedin').click(function() {
		IN.User.authorize();
		return false;
	});
	// call back fuunction if user is authorised
	IN.Event.on(IN, 'auth', function() {
		IN.User.isAuthorized(function() {
			// using js sdk get liked user details.
			IN.API.Profile("me").fields([ "id" ]).result(function(result) {
				// After Authentication Process to next step
				return;
			});
		});
	});

	// Facebook Registration
	$('.btn-facebook').click(function() {
		FB.login(function(response) {
			if (response.authResponse) {
				// Get User Details
				FB.api('/me', function(respo) {
					// After Authentication Process to next step
				});

			} else {
				// User close auth window
				alert('User cancelled login or did not fully authorize.');
			}
		}, {
			scope : 'email'
		});

	});
});
