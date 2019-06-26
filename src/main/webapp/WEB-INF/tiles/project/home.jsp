
<link rel="stylesheet" href="resources/css/project/home.css">

<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row" id="callerDiv">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="caller">Caller: </label>
                    <div class="selectRow">
				        <select id="caller" name="caller" onchange="getCallerServicesStatusByCaller()">
				            <option></option><!-- Needed to show X image to clear the select -->
				        </select>
				    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="serviceStatusTable" class="table table-striped">
                <thead>
                <tr>
                    <th>Service Host</th>
                    <th>Service Post</th>
                    <th>Service Status</th>
                </tr>
                </thead>
                <tbody id="serviceStatusTableBody">
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

<script src="resources/js/project/home.js"></script>
