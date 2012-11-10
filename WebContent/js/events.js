var eventId1 = 0;
$("#btnSaveNewEvent").on("click", function(e) {

	var formfields = $("#dlgEvents .form-create-event").jsonify({
		stringify : false
	});

	formfields["type"] = "CEvent";

	$.post("CreateEvent", formfields, function(data) {
		
				if(data.status==="success")
					{
						successOverlay(true, "Event Created Successfully");
					}
				else
					{
						errorOverlay(true, "Oops!! Problems in Creating Event Occured");
					}
	});

});

$("#dlgAccountSettings, #dlgEvents").on("shown", function(e) {
	var modal = $(this);
	modal.find("input[name$='Date']").datepicker({
		format: "dd-mm-yyyy",
		weekStart: 1,
		autoclose: true,
		todayHighlight: true
	}).on(
		"changeDate",
		function(e) {
			$("#tblEventData tbody").html("");
			var eDate = $('#txtEventDate').val();
			if ($.trim(eDate) == "")
				return;

			$.post("CreateEvent", {
				type : "GetData",
				eventDate : eDate
			},

			function(data) {

				if (data.status === "success") {
					var src = $("#getEventData").html();
					var template = Handlebars.compile(src);
					var output = template(data);

					$("#tblEventData tbody").append(output);

					$("#dlgEvents a[href='#EventDetails']").on(
							"click",
							
							function(e) {
								var src = $("#getEventDataDetails").html();	
								eventId1 = parseInt($(this).parents().eq(1)
										.find("td:nth-child(1)").text());
								

								
								$.post("CreateEvent", {

									type : "GetDetails",
									eId : eventId1

								}, function(data) {

									
									if (data.status === "success") {

										// e.preventDefault();
										
										var src = $("#getEventDataDetails")
												.html();
										var template = Handlebars.compile(src);
										var output = template(data);
										
										$("#frmEventDetails").html(output);

									}

									$("#btnJoinEvent").on("click", function(e) {
										e.preventDefault();	
										// alert(eventId1);
										// var eId = $(this).attr("data-eid");

										$.post("CreateEvent", {
											type : "joinEvent",
											eventId : eventId1
										}, function(data) {
											// alert(data.status);
											if (data.status === "success") {
												successOverlay(true, "Event Joined Succesfulluy.");
											} else {
												errorOverlay(true, "Oops!! Problem Occured");

											}
										}

										);

									});
									
									$("#btnCancelNewEvents").on("click", function(e){
										var modal = $(this).parents().eq(3);
										modal.find(".modal-disable-overlay").hide();
										modal.find(".modal-drawer, [class^='form']").slideUp(250);
									});

								});
								var modal = $(this).parents().eq(5);
								modal.find(".modal-disable-overlay").css(
										"height",
										modal.find(".modal-body").height())
										.show();
								modal.find(".modal-drawer, .form-view-event")
										.slideDown(250)
										.find(".form-view-event").show();

							});
				}

			});

		});
	modal.find("input[name$='Time']").timepicker();
});