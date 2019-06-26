<link rel="stylesheet" href="resources/css/project/callerService.css">
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-8">
	        <form class="form-inline">
	        	<div id="callerDiv">
		            <div class="form-group">
	                    <label for="caller">Caller: </label>
	                    <div class="selectRow">
					        <select id="caller" name="caller" onchange="getSubscribeAndUnSubscribeService()">
					            <option></option><!-- Needed to show X image to clear the select -->
					        </select>
					    </div>
	                </div>
				</div>
			</form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="callerServiceTable" class="table table-striped">
                <thead>
	                <tr>
	                    <th>Host</th>
	                    <th>Port</th>
	                    <th>Polling Frequency</th>
	                    <th>Grace Time</th>
	                    <th></th>
	                    <th></th>
	                </tr>
                </thead>
                <tbody id="callerServiceTableBody">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
	$.fn.select2.defaults = $.extend($.fn.select2.defaults, {
	    allowClear: true, // Adds X image to clear select
	    closeOnSelect: true, // Only applies to multiple selects. Closes the select upon selection.
	    placeholder: 'Select...',
	    minimumResultsForSearch: 15 // Removes search when there are 15 or fewer options
	});
	
	$(document).ready(
		function () {
		    // Single select example if using params obj or configuration seen above
		    var configParamsObj = {
		        placeholder: 'Select Caller...', // Place holder text to place in the select
		        minimumResultsForSearch: 3 // Overrides default of 15 set above
	    };
	    $("#caller").select2(configParamsObj);
	});
</script>



<script src="resources/js/project/callerService.js"></script>
