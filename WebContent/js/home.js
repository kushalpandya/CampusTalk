Handlebars.registerHelper('getPictureUrl', function() {
	return objMyData.pictureUrl;
});
$("#btnPost").click(function(){
	var postData=$.trim($("#postBox").val());
	var postType=$("#selectPostType").val();
	if(postData.length <3){
		showPopup("Min Post length is 3");
		return;
	}
	
	$.ajax({
		url : 'Post/New',
		type : 'post',
		data : {
			"postType" :postType,
			"postData" :postData
		},
		success:function(data) {
			if (data.status === 'success') {
				// Success
				showPopup(data.message);
				$("#postBox").val("");
			} else {
				// Failed
				showPopup(data.message);
			}
		},
		error : function() {
			showPopup('Some Error Occured, Please Refresh page');
		}
	});
});