
//$(document).ready(function () {

/**
 * Save Caller
 * @returns
 */
	function save(){
		var formData = getDivInputsJSON("callerDiv");
		
		 $.ajax({
		        url: "/api/caller/",
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify(formData),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		        success: function (data) {
		        	clearAllInputs("divCaller");
		        	showMessageSuccess();
		        },
		        error: function (data, status, er) {
		        	console.log("Error");
		        }
		    });
	}


	/**
	 * Get All callers 
	 * @returns
	 */
	function getCallers() {
		var caller = $("#caller").val();
		
	    $.ajax({
	        url: "/api/caller/all",
	        type: 'GET',
	        success: function (data) {
	        	
	        	if(data != null && data.result != null){
	        		drawTable(data.result);
	        	}
	        	
	        		//var newOption = new Option(data.result.id, data.result.name, false, false);
	        		//$('#mySelect2').append(newOption).trigger('change');
	        },
	    });
	}
	
	/**
	 * Draw table for callers
	 * @param callerArr
	 * @returns
	 */
	function drawTable(callerArr){
		
		for(var a = 0 ; a < callerArr.length; a++){
			var tableStr = '<tr>'+
						      '<td>'+callerArr[a].name+'</td>'+
						    '</tr>';
			$("#callerTableBody").append(tableStr);
		}
	}	
	
	getCallers();

//})