<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="org.campustalk.entity.CampusTalkUsers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CampusTalk</title>
		<link rel="icon" href="assets/favicon.ico" sizes="32x32" />
		<link rel="shortcut icon" href="assets/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"	media="all" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.datepicker.min.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.timepicker.min.css" media="all" />
		<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
		<link rel="stylesheet/less" type="text/css" href="less/style.less" media="all" />
		<link href="css/cssemoticons.css" media="screen" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/less-1.3.1.min.js"></script>
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
				<input type="text" placeholder="Search..." class="txt-flat" id="txtSearchBox" />
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
						<c:if test="${requestScope.isAdmin eq 1}"> 
							<tr>
								<td style="padding-bottom: 15px;">
									<a href="controlpanel.jsp" id="showControlpanel" class="user-option">ControlPanel</a>
								</td>
							</tr>
						</c:if>
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
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label">To</label>
						<div class="controls">
							<input type="text" placeholder="Name of recepient" data-mode="multiple" id="txtEmailNewMsg" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Recipients</label>
						<div class="controls"></div>
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
				<ul class="recipient-list" id= "ulRecipientList">
				</ul>
				<div class="message-thread" id="divMessageThread" >
					<span class="info-label muted">No Conversation Selected</span>
				</div>
				<div class="message-editor">
					<textarea placeholder="Write a reply... (press Enter to send message)" id="txtAreaThreadNewMsg"></textarea>
					<!-- <label class="checkbox">
						Press Enter to Send <input type="checkbox" name="chkEnterSend" />
					</label> -->
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
		<!-- Account Settings Dialog -->
		<div id="dlgAccountSettings" class="modal hide fade modal-flat">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Settings</h4>
			</div>
			<div class="modal-drawer">
				<legend>Change Password</legend>
				<form class="form-inline">
					<div class="control-group">
						<label class="control-label">Current Password</label>
						<div class="controls">
							<input type="password" name="txtCurrPass" id="txtCurrPass"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">New Password</label>
						<div class="controls">
							<input type="password" name="txtNewPass" id="txtNewPass"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Confirm Password</label>
						<div class="controls">
							<input type="password" name="txtConfirmNewPass" id="txtConfirmNewPass"/>
						</div>
					</div>
					<div class="control-group drawer-button-group">
						<button type="button" class="btn btn-flat" id="btnCancelChangePass">Cancel</button>
						<a href="#" class="btn btn-green" id="btnSaveNewPass">Save</a>
					</div>
				</form>	
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" >First Name</label>
						<div class="controls">
							<input type="text" id="txtFname"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" >Last Name</label>
						<div class="controls">
							<input type="text" id="txtLname"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Birth Date</label>
						<div class="controls">
							<input type="text" id="txtBirthDate" placeholder="DD-MM-YYYY" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Gender</label>
						<div class="controls">
							<label class="radio">
								<input type="radio" name="rdGender"  id="rdmale" value="Male" />Male
							</label>
							<label class="radio">
								<input type="radio"  name="rdGender" id="rdfemale" value="Female" />Female
							</label>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="button" name="btnChangePassword" class="btn">Change Password</button>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="pull-left">
					<button class="btn btn-red" id="btnDAct">Deactivate Account</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
					<button class="btn btn-green" id="btnSaveChanges">Save Changes</button>
				</div>
			</div>
			<div class="modal-disable-overlay">&nbsp;</div>
		</div>
		<!-- User Profile Dialog -->
		<div id="dlgUserProfile" class="modal hide fade modal-flat">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Details</h4>
			</div>
			<div class="modal-drawer">
				<legend>Compose Message</legend>
				<form class="form-horizontal">
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
			<div class="modal-body" id="divProfile">
				<div class="user-profile">
					<div class="pull-left">
						<img src="https://lh3.googleusercontent.com/-XVHns1ycTI0/AAAAAAAAAAI/AAAAAAAAAN0/31SL_TsfpRM/photo.jpg" class="user-img" />
						<div class="user-activity">
							<span class="user-posts"><i class="icon-list-alt"></i>&nbsp;6</span>
							<span class="user-comments"><i class="icon-comment"></i>&nbsp;10</span>
						</div>
					</div>
					<div class="pull-right">
						<label>Kushal Pandya</label>
						<label>MSc. I.T. - 2011</label>
						<table>
							<tbody>
								<tr>
									<td>Email</td><td>kushal.pandya04@gmail.com</td>
								</tr>
								<tr>
									<td>Gender</td><td>Male</td>
								</tr>
								<tr>
									<td>Born</td><td>22 July 1990</td>
								</tr>
								<tr>
									<td>City</td><td>Rajkot</td>
								</tr>
								<tr align="left">
									<td colspan="2"><button class="btn btn-yellow"><i class="icon-user"></i>&nbsp;Make Moderator</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-left">
					<button class="btn btn-green" id="btnNewMessage">Send Private Message</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-blue">Show All Posts</button>
					<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
				</div>
			</div>
			<div class="modal-disable-overlay">&nbsp;</div>
		</div>
		
		<!-- Events Dialog -->
		<div id="dlgEvents" class="modal hide fade modal-flat">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Events</h4>
			</div>
			<div class="modal-drawer">
				<legend class="form-create-event">Create New Event</legend>
				<form class="form-horizontal form-create-event">
					<div class="control-group datetime-picker">
						<label class="control-label">Start Date/Time</label>
						<div class="controls">
							<div class="input-append date">
								<input type="text" name="txtEventStartDate" class="input-small"/>
								<span class="add-on"><i class="icon-calendar"></i></span>
							</div>
						</div>
						<div class="controls">
							<div class="input-append bootstrap-timepicker-components">
								<input type="text" name="txtEventStartTime" class="input-mini"/>
								<span class="add-on"><i class="icon-time"></i></span>
							</div>
						</div>
					</div>
					<div class="control-group datetime-picker">
						<label class="control-label">End Date/Time</label>
						<div class="controls">
							<div class="input-append date">
								<input type="text" name="txtEventEndDate" class="input-small"/>
								<span class="add-on"><i class="icon-calendar"></i></span>
							</div>
						</div>
						<div class="controls">
							<div class="input-append bootstrap-timepicker-components">
								<input type="text" name="txtEventEndTime" class="input-mini"/>
								<span class="add-on"><i class="icon-time"></i></span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Place</label>
						<div class="controls">
							<input type="text" name="txtEventPlace">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Subject</label>
						<div class="controls">
							<input type="text" name="txtEventSubject">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Description</label>
						<div class="controls">
							<textarea rows="2" placeholder="Detailed description of event..." name="txtEventDesc" id="txtEventDesc" ></textarea>
						</div>
					</div>
					<div class="control-group drawer-button-group">
						<button type="button" class="btn btn-flat" id="btnCancelNewEvent">Cancel</button>
						<a href="#" class="btn btn-green" id="btnSaveNewEvent">Save</a>
					</div>
				</form>
				<legend id="legEventDetails" class="form-view-event">Event Details</legend>
				<script id="getEventDataDetails" type="text/x-handlebars-template">
				{{#if EventDetails}}
					{{#each EventDetails}}
					<div class="control-group">
						<label class="control-label">Duration</label>
						<div class="controls">
							<label>{{fdate}} to {{tdate}}</label> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Venue</label>
						<div class="controls">
							<label>{{place}}</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Subject</label>
						<div class="controls">
							<label>{{title}}</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Description</label>
						<div class="controls">
							<p>{{desc}}</p>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Members</label>
						<div class="controls">
							<p>{{numofattempt}} Attending</p>
						</div>
					</div>
					<div class="control-group drawer-button-group pull-right">
						{{#ifalreadyjoin joinStatus}}
						<button type="button" class="btn btn-blue" id="btnJoinEvent" data-toggle="button" data-eid="{{eid}}" ><i class="icon-thumbs-up icon-white"></i>&nbsp;Join</button>
						{{/ifalreadyjoin}}
						<button type="button" class="btn btn-flat" id="btnCancelNewEvents">Cancel</button>
					</div>
					{{/each}}
				{{else}}
					<option value="-1" selected>No Value Selected...</option>											
				{{/if}}
				</script>
				<form class="form-horizontal form-view-event" id="frmEventDetails" ></form>
			</div>
			<div class="modal-body">
				<div class="input-append">
					<input type="text" id="txtEventDate" name="txtEventOnDate" class="input-medium">
					<span class="add-on"><i class="icon-calendar"></i></span>
				</div>
				<script id="getEventData" type="text/x-handlebars-template">
				{{#if Event}}
					{{#each Event}}
						{{#isalredyaddedevent eid}}
							<tr id="trevents{{eid}}">
								<td>{{eid}}</td>									
								<td>{{title}}</td>
								<td>{{fdate}}</td>
								<td><a href="#EventDetails" data-id="{{eid}}" > <i class="icon-eye-open"></i>&nbsp;Details</a></td>
							</tr>
						{{/isalredyaddedevent}}
					{{/each}}
				{{else}}
					<option value="-1" selected>No Valuse Selected...</option>						
				{{/if}}	
				</script>
				<table class="table table-hover" id="tblEventData">
					<thead>
						<tr>
							<th>Sr. No.</th>
							<th>Subject</th>
							<th>Time</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div class="modal-footer">
				<div class="pull-left">
					<button class="btn btn-green" id="btnNewEvent">New Event</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-flat" data-dismiss="modal" aria-hidden="true">Close</button>
				</div>
			</div>
			<div class="modal-disable-overlay">&nbsp;</div>
		</div>
		<script type="text/x-handlebars-template" id='tmpltuserProfile'>
		<div class="user-profile">
			<div class="pull-left">
				<img src="{{pictureurl}}" class="user-img" />
				<div class="user-activity">
					<span class="user-posts"><i class="icon-list-alt"></i>&nbsp;{{nopost}}</span>
					<span class="user-comments"><i class="icon-comment"></i>&nbsp;{{nocomment}}</span>
				</div>
			</div>
			<div class="pull-right">
				<label>{{firstname}} {{lastname}}</label>
				<label>{{branch}} - {{year}}</label>
				<table>
					<tbody>
						<tr>
							<td>Email</td><td>{{email}}</td>
						</tr>
						<tr>
							<td>Gender</td><td>{{gender}}</td>
						</tr>
						<tr>
							<td>Born</td><td>{{birthdate}}</td>
						</tr>
						<tr>
							<td>City</td><td>{{city}}</td>
						</tr>
						<c:if test="${isAllow eq 1}" >
							{{#isModerator id}}
								<tr align="left">
									<td colspan="2"><button class="btn btn-yellow" id='btnModrate' data-email="{{email}}"><i class="icon-user"></i>&nbsp;Make Moderator</button></td>
								</tr>
							{{/isModerator}}
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		</script>
		<script type="text/x-handlebars-template" id='tmpltPostList'>
 		{{#if posts}}
			{{#each posts}}
				{{#notAlreadyAddedPost postid}}
					<div class="feed-card" id='divPost{{postid}}' data-userid="{{userid}}">
						<div class="feed-user-bar">
							<a href="#profile" data-userid="{{userid}}"><img src="{{pictureurl}}" class="feed-user-img" /></a>
							<span class="feed-user-title"><a href="#profile" data-userid="{{userid}}" >{{firstname}} {{lastname}}</a></span>
							<span class="feed-timestamp muted">{{getDateTime entTime}}</span>
							<div class="dropdown drp-flat feed-card-menu">
								<a href="#" data-toggle="dropdown" class="dropdown-toggle"><i class="icon-tasks"></i></a>
								<ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu" data-postid={{postid}}>
									{{#isPostOwner userid}}
									<li><a tabindex="-1" href="#" class="postdelete">Delete</a></li>
									{{/isPostOwner}}
									<li><a tabindex="-1" href="#" class="reportAbuse">Report as spam/abuse</a></li>
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
				<li id='liComment{{commentid}}'><img src="{{pictureurl}}" class="comment-user-img"/><br/>{{detail}}<span class="comment-user"><a href="#profile" data-userid="{{userid}}" >{{firstname}} {{lastname}}</a></span><span class="comment-timestamp muted">{{getDateTime entTime}}</span><a class="comment-action" href="#"><i class="icon-remove" title="Delete"></i></a></li>
			{{/notAlreadyAddedComment}}
		{{/each}}
		</script>
		<script type="text/x-handlebars-template" id='tmpltRecipientList'>
			{{#each userlist}}
				<li><a href="#" class="message-recipient {{isUnreadMessage unreadmsg}}" title="{{email}}" data-userid="{{userid}}" data-email="{{email}}"><img class="recipient-img" src="{{pictureurl}}"/><span class="recipient-title">{{firstname}} {{lastname}} </span><span class="recipient-mail">{{formatEmail email}}</span></a>
			{{/each}}	
		</script>
		<script type="text/x-handlebars-template" id='tmpltMessageThread'>
			{{#each messages}}
				{{#notAlreadyAddedMessage messageid}}
					<div class="message pull-{{getMsgClass userid}}" id="divMessageThread{{messageid}}" data-messageid="{{messageid}}">
						<label class="sender">{{firstname}}</label>
						<label class="timestamp">{{getDateTime enttime}}</label>
						<p>{{message}}</p>
					</div>
				{{/notAlreadyAddedMessage}}
			{{/each}}
		</script>
		<script type="text/javascript" src="js/handlebars.js"></script>
		<script type="text/javascript" src="js/moment.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.typeahead.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.datepicker.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.timepicker.min.js"></script>
		<script type="text/javascript" src="js/jquery.jsonify-0.2.min.js"></script>
		<script type="text/javascript" src="js/cssemoticons.min.js"></script>	
		<script type="text/javascript" src="js/script.js"></script>
		<script type="text/javascript" src="js/home.js"></script>
		<script type="text/javascript" src="js/events.js"></script>
	</body>
</html>
