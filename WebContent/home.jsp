<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="org.campustalk.entity.CampusTalkUsers"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CampusTalk</title>
		<link rel="icon" href="assets/favicon.ico" sizes="32x32" />
		<link rel="shortcut icon" href="assets/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"	media="all" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/avgrund.css" media="all" />
		<link rel="stylesheet/less" type="text/css" href="less/style.less" media="all" />
		<link href="css/cssemoticons.css" media="screen" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/less-1.3.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.avgrund.js"></script>
		<script type="text/javascript">
			$(function() {
				less.env = "development";
				less.watch();
			});
			var objMyData = [];
			objMyData=${requestScope.userJSon};
		</script>
	</head>
	<body>
		<div class="container header">
			<img src="assets/logo_small.png" class="campustalk-logo" />
			<div class="search-block input-append">
				<input type="text" placeholder="Search..." class="txt-flat" />
				<button class="btn btn-flat">
					<i class="icon-search"></i>
				</button>
			</div>
		</div>
		<div class="account-tray">
			<img src="${requestScope.User.pictureUrl}" class="account-user-img" />
			<div class="profile-block">
				<div class="profile-details">
					<img src="${requestScope.User.pictureUrl}" class="profile-img" />
					<table class="profile-options">
						<tr>
							<td>
								<label class="user-title">${requestScope.User.firstname}  ${requestScope.User.lastname}</label>
							</td>
						</tr>
						<tr>
							<td>
								<label class="user-group-title">${requestScope.Branch.name} ${requestScope.User.year}</label>
							</td>
						</tr>
						<tr>
							<td style="padding-bottom: 15px;">
								<a href="#" id="showMessages" class="user-option">Messages</a>
							</td>
						</tr>
						<tr>
							<td style="padding-bottom: 15px;">
								<a href="#" id="showEvents" class="user-option">Events</a>
							</td>
						</tr>
					</table>
				</div>
				<div class="profile-actions">
					<button id="btnLogout" class="btn btn-flat btn-blue">Logout</button>
					<button id="btnSettings" class="btn btn-flat btn-blue">Settings</button>
				</div>
			</div>
		</div>
		<div class="container feeds-block">
			<div class="post-card">
				<div class="post-user-bar">
					<img src="${requestScope.User.pictureUrl}" class="post-user-img" />
					<div class="post-content">
						<textarea id="postBox" rows="1" cols="30" placeholder="Share something..." class="txt-flat"></textarea>
						<select class="span2 sel-flat" id="selectPostType">
							<option value="P">Private</option>
							<option value="A">Public</option>
						</select>
						<button class="btn btn-flat" id="btnPost">Post</button>
					</div>
				</div>
			</div>
			<div class="feeds-list" id="feeds-list"></div>
			<div class="feeds-more">Load More Posts &#9660;</div>
		</div>
		<!-- Messages Dialog -->
		<div id="dlgMessages" class="modal hide fade modal-flat">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Messages</h4>
			</div>
			<div class="modal-drawer">
				<legend>Compose Message</legend>
				<form class="form-horizontal form-add-member">
					<div class="control-group">
						<label class="control-label">To</label>
						<div class="controls">
							<input type="text" placeholder="Name of recepient" data-mode="multiple" id="txtEmailNewMsg" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Message</label>
						<div class="controls">
							<textarea rows="4" placeholder="Write a message..." id="txtNewMsgDetail" ></textarea>
						</div>
					</div>
					<div class="control-group drawer-button-group">
						<button type="button" class="btn btn-flat" id="btnCancelNewMessage">Cancel</button>
						<a href="#" class="btn btn-green" id="btnSendNewMessage">Send</a>
					</div>
				</form>	
			</div>
			<div class="modal-body">
				<ul class="recipient-list">
					<li><a href="#">Borat</a>
					<li><a href="#">Bob</a>
					<li><a href="#">Marley</a>
					<li><a href="#">Ashton</a>
					<li><a href="#">George</a>
					<li><a href="#">Al Ghazi</a>
					<li><a href="#">Weezy</a>
					<li><a href="#">Dicky</a>
					<li><a href="#">Ramesh</a>
					<li><a href="#">Suresh</a>
					<li><a href="#">Jignesh</a>
					<li><a href="#">S. Narayan</a>
				</ul>
				<div class="message-thread">
					<div class="message pull-left">
						<label class="sender">Borat</label>
						<label class="timestamp">Sent at 2:37 PM</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-right">
						<label class="sender">Faishal</label>
						<label class="timestamp">Sent at 2:37 PM</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-left">
						<label class="sender">Borat</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-right">
						<label class="sender">Faishal</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-left">
						<label class="sender">Borat</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-right">
						<label class="sender">Faishal</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-left">
						<label class="sender">Borat</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
					<div class="message pull-right">
						<label class="sender">Faishal</label>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas</p>
					</div>
				</div>
				<div class="message-editor">
					<textarea placeholder="Write a reply... (press Enter to send message)"></textarea>
					<!--
					<label class="checkbox">
						Press Enter to Send <input type="checkbox" name="chkEnterSend" />
					</label>
					-->
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-left">
					<button class="btn btn-green" id="btnNewMessage">New Message</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
				</div>
			</div>
			<div class="modal-disable-overlay">&nbsp;</div>
		</div>
		<a id='popupLink' href='' style='display:none'>Show</a>
		<script type="text/x-handlebars-template" id='tmpltPostList'>
 		{{#if posts}}
			{{#each posts}}
				{{#notAlreadyAddedPost postid}}
					<div class="feed-card" id='divPost{{postid}}'>
						<div class="feed-user-bar">
							<a href="#"><img src="{{pictureurl}}" class="feed-user-img" /></a>
							<span class="feed-user-title"><a href="{{getuserurl id}}">{{firstname}} {{lastname}}</a></span>
							<span class="feed-timestamp muted">Monday, 5 November 2012 at 23:32</span>
							<div class="dropdown drp-flat feed-card-menu">
								<a href="#" data-toggle="dropdown" class="dropdown-toggle"><i class="icon-tasks"></i></a>
								<ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu">
									{{#isPostOwner userid}}
									<li><a tabindex="-1" href="#">Delete</a></li>
									{{/isPostOwner}}
									<li><a tabindex="-1" href="#">Report as spam/abuse</a></li>
								</ul>
							</div>
						</div>
						<div class="feed-content">
							<p>{{detail}}</p>
							<div class="feed-comments-block" data-comments='{{nocomment}}'>
								<a href="#" class="feed-comment-reveal comment-load" rel="full" data-postid="{{postid}}" id='href{{postid}}'>{{nocomment}} comments. &dArr;</a>
								<ul class="feed-comments" id="postComments{{postid}}">
								</ul>
								<div class="feed-comment-box">
									<img src="{{getPictureUrl}}" class="post-user-img" width="30" />
									<textarea data-postid="{{postid}}" class="txt-flat txt-comment" placeholder="Leave comment..."></textarea>
								</div>
							</div>
						</div>
					</div>
				{{/notAlreadyAddedPost}}
			{{/each}}
		{{/if}}
		</script>
		<script type="text/x-handlebars-template" id='tmpltCommentList'>
		{{#each comments}}
			{{#notAlreadyAddedComment commentid}}
				<li><img src="{{pictureurl}}" class="comment-user-img"/><br/>{{detail}}<span class="comment-user">{{firstname}} {{lastname}}</span><span class="comment-timestamp muted">Monday, 5 November 2012 at 23:32</span><a class="comment-action" href="#"><i class="icon-remove" title="Delete"></i></a></li>
			{{/notAlreadyAddedComment}}
		{{/each}}
		</script>
		<script type="text/javascript" src="js/script.js"></script>
		<script type="text/javascript" src="js/handlebars.js"></script>
		<script type="text/javascript" src="js/bootstrap-typeaheadN.js"></script>
		<script type="text/javascript" src="js/home.js"></script>
		<script src="js/cssemoticons.min.js" type="text/javascript"></script>	
	</body>
</html>