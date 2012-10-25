var POSTBOX_BOTTOM_MARGIN = 20;

$(".feed-comment-reveal").on("click", function(e){
	e.preventDefault();
	var commentCount = $(this).parent().find(".feed-comments li").length;
	
	$(this).parents().eq(2).css("marginBottom", (commentCount*7).toString()+"%");
	$(this).hide();
	$(".feed-comments, .feed-comment-box").slideDown();
});
				
$("#postBox").on("keypress", function(e) {
	var postBox = $(this);
	var boxH = postBox.height();
	if(e.keyCode == 13 && boxH <= 80)
	{
		postBox.height(boxH+20);
		$(".feeds-list").css("marginTop",(POSTBOX_BOTTOM_MARGIN+130).toString()+"px");
	}
});
				
$("#postBox").on("blur", function() {
	if($(this).val().trim() === "")
	{
		$(this).val("").height(20);
		$(".feeds-list").css("marginTop","20px");
	}
});

$(".feed-comments li").on("mouseenter", function() {
	$(this).find(".comment-action").show();
});

$(".feed-comments li").on("mouseleave", function() {
	$(this).find(".comment-action").hide();
});

//Model Windows Show Function 

function showPopup(templateHTML){
	$('#popupLink').avgrund({			
		width: 380, // max is 640px
		height: 280, // max is 350px
		showClose: true, // switch to 'true' for enabling close button 
		showCloseText: 'Close', // type your text for close button
		closeByEscape: true, // enables closing popup by 'Esc'..
		closeByDocument: true, // ..and by clicking document itself
		holderClass: '', // lets you name custom class for popin holder..
		overlayClass: '', // ..and overlay block
		enableStackAnimation: false, // another animation type
		onBlurContainer: '', // enables blur filter for specified block 
		template: templateHTML
	}).click();
}