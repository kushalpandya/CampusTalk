Handlebars.registerHelper('getPictureUrl', function() {
	return objMyData.pictureUrl;
});
Handlebars.registerHelper('isPostOwner', function(userid,block) {
	if(userid == objMyData.userid)
		return block.fn(this);
});
Handlebars.registerHelper('notAlreadyAdded', function(postid,block) {
	if($("#divPost" + postid).length==0)
		return block.fn(this);
});
//getuserurl

Handlebars.registerHelper('getuserurl', function(userid) {
	return "javascript::alert('" + userid+ "')";
	
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
var skipRow=0;
function getNewPost(resetFlag){
	
	if(resetFlag== undefined){
		resetFlag=false;
	}
	
	$.ajax({
		url : 'Post/Get',
		type : 'post',
		data : {
			"skip" :skipRow,
			"row" :10
		},
		success:function(data) {
			if (data.status === 'success') {
				// Success
				console.log(data);
				var source   = $("#tmpltPostList").html();
				var template = Handlebars.compile(source);
				var html    = template(data);
				$("#feeds-list").append(html);
				if(data.posts.length>0)
					skipRow += data.posts.length;
				$('.feed-content p').emoticonize({
					//delay: 800,
					animate: true,
					//exclude: 'pre, code, .no-emoticons'
				});
			} else {
				// Failed
				showPopup(data.message);
			}
		},
		error : function() {
			showPopup('Some Error Occured, Please Refresh page');
		}
	});
}
getNewPost();
$(".feeds-block").scroll(function(){
	
    if($(this).scrollTop() == $(this).height() - $(window).height()){
    	getNewPost();
    }
});