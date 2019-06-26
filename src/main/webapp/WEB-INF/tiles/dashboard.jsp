<script>
	$("#title").html("Home  -  Dashboard");
</script>
<body>

	<div class="col-md-12 dashboardContainer pl-0 pr-0">
		<div class="requestDiv requestDivTransparent ">
			<div class="col-md-2">PO No</div>
			<div class="col-md-1">Date</div>
			<div class="col-md-2">Consignee Name</div>
			<div class="col-md-3">Contact/TP</div>
			<!-- <div class="col-md-1">Customer</div> -->
			<div class="col-md-4 pull-right"
				style="background-color: lightcyan; border-radius: 20px;">
				<input type="text" class="height: 20px !important;"
					style="height: 20px !important; margin-top: 1px; margin-bottom: 5px;"
					id="markSearch" name="markSearch" height="20px"
					placeholder="Last 4 digits">
				<button class="btn btn-danger" onclick="quikAcceptShow();"
					type="button">Quick Accept</button>
			</div>
		</div>
	</div>

	<div class="col-md-12 dashboardContainer pl-0 pr-0" id="testsLists"></div>


</body>
<script type="text/javascript" src="resources/js/dashboard.js"></script>


<!-- Page related scripts End -->
