var eventId1=0;
$("#btnSaveNewEvent").on("click", function(e){
	
	var formfields = $("#dlgEvents .form-create-event").jsonify({stringify : false});
	
	formfields["type"] = "CEvent";
	
	$.post("CreateEvent",formfields, function()
	{
		
		
		
	});
	
});




$("#dlgEvents").on("click", function(e) {
	
	var eDate = $('#txtEventDate').val();
	if($.trim(eDate)=="")
		return;
	
	$.post("CreateEvent", {
		type: "GetData",
		eventDate : eDate
	}, 
	
	function(data)
	{
		
		if(data.status === "success")
			{
			var src = $("#getEventData").html();
			var template = Handlebars.compile(src);
			var output = template(data);

			$("#tblEventData tbody").append(output);
			
			$("#dlgEvents a[href='#EventDetails']").on("click", function(e) {
				
				 eventId1= parseInt($(this).parents().eq(1).find(
				"td:nth-child(1)").text());
				eventId1=2;
				
				//alert(eventId1);
				
			$.post("CreateEvent", {
					
					type : "GetDetails",
					eId : eventId1
					
				}, function(data){
				
				alert(data.status);
				if(data.status==="success")
					{
					
			//		e.preventDefault();
					
				var src = $("#getEventDataDetails").html();
				var template = Handlebars.compile(src);
				var output = template(data);
				$("#frmEventDetails").append(output);
					
					
					
								
					
					}
				
				$("#btnJoinEvent").on("click", function(e){
					//alert(eventId1);
					//var eId = $(this).attr("data-eid");
					
					$.post(
					"CreateEvent", {
						type: "joinEvent",
						eventId : eventId1
					},	function(data){
						//alert(data.status);
						if(data.status==="success")
							{
								alert("Done");
							}
						else{
							alert("error");
							
						}
					}
					
					);
					
					
				});

				
				});
			var modal = $(this).parents().eq(5);
			modal.find(".modal-disable-overlay").css("height",modal.find(".modal-body").height()).show();
			modal.find(".modal-drawer, .form-view-event").slideDown(250).find(".form-view-event").show();
			
			
			}); 
			}
	
	});	
	
});




