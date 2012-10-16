$(document).ready(function() {
	$('.btn-linkedin').click(function(){
		IN.User.authorize();
		return false;
	});
	// call back fuunction if user is authorised
	IN.Event.on(IN, 'auth', function() {
		IN.User.isAuthorized(function() {
			// using js sdk get liked user details.
			IN.API.Profile("me").fields([ "id" ]).result(function(result) {
				// After Authentication Process to next stemp				
				return;
			});
		});
	});
});
