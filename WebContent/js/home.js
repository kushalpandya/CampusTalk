Handlebars.registerHelper('getPictureUrl', function() {
	return objMyData.pictureUrl;
});
Handlebars.registerHelper('isPostOwner', function(userid, block) {
	if (userid == objMyData.userid)
		return block.fn(this);
});
Handlebars.registerHelper('notAlreadyAddedPost', function(postid, block) {
	if ($("#divPost" + postid).length == 0)
		return block.fn(this);
});
Handlebars.registerHelper('notAlreadyAddedComment', function(commentid, block) {
	if ($("#liComment" + commentid).length == 0)
		return block.fn(this);
});
Handlebars.registerHelper('getuserurl', function(userid) {
	return "javascript::alert('" + userid + "')";

});
Handlebars.registerHelper('getCommentIcon', function(userid) {
	return "javascript::alert('" + userid + "')";

});
Handlebars.registerHelper('getMsgClass', function(userid) {
	if(objMyData.userid==userid)
		return "left";
	else
		return "right";
});
Handlebars.registerHelper('isUnreadMessage', function(rCount) {
	if(rCount > 0 )
		return "unread";
	
});
Handlebars.registerHelper('notAlreadyAddedMessage', function(messageid, block) {
	if ($("#divMessageThread" + messageid).length == 0)
		return block.fn(this);
});
Handlebars.registerHelper('getDateTime', function(enttime) {
	var rtnDate = moment(enttime, "YYYY-MM-DD hh:mm:ss").fromNow();
	if(rtnDate.indexOf("days") < 0)
		return rtnDate;
	else
		return moment(enttime, "YYYY-MM-DD hh:mm:ss").calendar();
});

$("#btnPost").click(function() {
	var postData = $.trim($("#postBox").val());
	var postType = $("#selectPostType").val();
	if (postData.length < 3) {
		errorOverlay(true, "Post must have 3 or more characters!");
		return;
	}

	$.ajax({
		url : 'Post/New',
		type : 'post',
		data : {
			"postType" : postType,
			"postData" : postData
		},
		success : function(data) {
			if (data.status === 'success') {
				// Success
				getNewPost(true);
				$("#postBox").val("");
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
var skipRow = 0;
function getNewPost(resetFlag) {
	
	if (resetFlag == undefined) {
		resetFlag = false;
	}
	
	if(resetFlag){
		skipRow=0;
		$("#feeds-list").html("");
	}

	$.ajax({
		url : 'Post/Get',
		type : 'post',
		data : {
			"skip" : skipRow,
			"row" : 10
		},
		success : function(data) {
			if (data.status === 'success') {
				// Success
				console.log(data);
				var source = $("#tmpltPostList").html();
				var template = Handlebars.compile(source);
				var html = template(data);
				
					$("#feeds-list").append(html);
				if (data.posts.length > 0)
					skipRow += data.posts.length;
				$('.feed-content p, .feed-comments-block .feed-comments li .comment-body').emoticonize({animate : true});
				$(".txt-comment").keyup(function(event) {
					if (event.keyCode == 13) {
						event.preventDefault();
						if(!event.shiftKey)
							commentOnPost(this);
					}
				});
				afterLoadPost();
				
				//Iterate over each comment-box and hide comments if they exist, and adjust margin-bottom for each post-card.
				$.each($(".feed-comments"), function(i) {
					var currfeed = $(this);
					if (parseInt(currfeed.parent().data("comments")) > 0)
						currfeed.parent().find(".feed-comment-box").hide();
					else
						currfeed.parent().find(".feed-comment-reveal").hide();
					currfeed.hide();
					currfeed.parents().eq(2).css("marginBottom", currfeed.parents().eq(2).height());
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
getNewPost();

$(".feeds-more").on("click", function() {
	getNewPost();
});

function commentOnPost(txtComment) {
	var commentdata = $.trim($(txtComment).val());
	if (commentdata.length < 3) {
		alert("Comment must have at least 2 or more characters!");
		return;
	}
	$.ajax({
		url : 'Comment/New',
		type : 'post',
		data : {
			"commentdata" : commentdata,
			"postid" : $(txtComment).data("postid")
		},
		success : function(data) {
			if (data.status === 'success') {
				// Success
				loadCommentForPost($('#href' + $(txtComment).data("postid")),true);
				$(txtComment).val("");
				
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

function loadCommentForPost(post,resetFlag) {
	if (resetFlag == undefined) {
		resetFlag = false;
	}
	if(resetFlag)
		$("#postComments" + postid).html("");
		postid = post.data("postid");
	$.ajax({
				url : 'Comment/Get',
				type : 'post',
				data : {
					"postid" : postid

				},
				success : function(data) {
					if (data.status === 'success') {
						// Success
						console.log(data);
						var source = $("#tmpltCommentList").html();
						var template = Handlebars.compile(source);
						var html = template(data);
						$("#postComments" + postid).append(html);
						$("#postComments" + postid).emoticonize({
							animate : true,
						});
						
						post.parent().find(".feed-comments, .feed-comment-box").slideDown(function() {
							post.parents().eq(2).css("marginBottom", post.parents().eq(1).height());
						});
						post.hide();

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

function afterLoadPost() {
	$(".feed-comment-reveal").on("click", function(e) {
		e.preventDefault();
		loadCommentForPost($(this));
	});
}

$("#btnSendNewMessage").click(function(e){
	e.preventDefault();
	var toEmails,msgDetail;
	toEmails= $("#txtEmailNewMsg").val();
	msgDetail= $("#txtNewMsgDetail").val();
	if(msgDetail.length < 2){
		alert("Message must have at least 2 or more characters!");
		return;
	}
	sendNewMessage(toEmails,msgDetail,"bulk");
});

function sendNewMessage(toUsers,msgDetail,type,objTxt){
	$.ajax({
		url : 'Message/New',
		type : 'post',
		data : {
			"tousers" : toUsers,
			"message" : msgDetail			
		},
		success : function(data) {
			if (data.status === 'success') {
				// Success
				if(type === "bulk"){
					$("#btnCancelNewMessage").click();
				} 
			} else {
				// Failed
				errorOverlay(true, data.message);
				if(type === "bulk"){
					$("#btnCancelNewMessage").click();
				} else {
					$(objTxt).val(msgDetail);
				}
			}
		},
		error : function() {
			errorOverlay(true, 'Oops! something went wrong. Please refresh the page');
			if(type === "bulk"){
				$("#btnCancelNewMessage").click();
			} else {
				$(objTxt).val(msgDetail);
			}
		}
	});
	
}

var lastUserEmail;
$(".account-tray #showMessages").on("click", function(e) {
	e.preventDefault();
	$.ajax({
		url : 'Messages/UsersList',
		type : 'post',
		success : function(data) {
			if (data.status === 'success') {
				// Success
				console.log(data);
				var source = $("#tmpltRecipientList").html();
				var template = Handlebars.compile(source);
				var html = template(data);
				$("#ulRecipientList").html(html);
				$(".MessageUserList").click(function(e){
					e.preventDefault();
					var userid= parseInt($(this).attr("data-userid"));
					lastUserEmail=$(this).attr("data-email"); 
					$("#divMessageThread").html("");
					$.ajax({
						url : 'Messages/Get',
						type : 'post',
						data:{
							'userid' : userid
						},
						success : function(data) {
							if (data.status === 'success') {
								// Success
								var source = $("#tmpltMessageThread").html();
								var template = Handlebars.compile(source);
								var html = template(data);
								$("#divMessageThread").html(html);
								$("#divMessageThread").emoticonize({animate : true});
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
				$("#dlgMessages").modal();
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

$("#txtAreaThreadNewMsg").keyup(function(e){
	if (event.keyCode == 13 && !event.shiftKey) {
		event.preventDefault();
		var msgDetail= $(this).val();
		$(this).val("");
		if(msgDetail.length < 2){
			alert("Message must have at least 2 or more characters!");
			$(this).val(msgDetail);
			return;
		}
		sendNewMessage(lastUserEmail,msgDetail,"new",this);
	}
});
