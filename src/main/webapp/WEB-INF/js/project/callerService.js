
/**
 * Get user subscribe and remaining servicers 
 * @returns
 */
function getSubscribeAndUnSubscribeService() {
	getCallerService();
}


/**
 * Get caller servicer
 * @returns
 */
function getCallerService() {
	var caller = $("#caller").val();
	
    $.ajax({
        url: "/api/caller/"+caller,
        type: 'GET',
        success: function (data) {
        	getAllService(data.result.callerServiceDTOList);
        },
    });
}

/**
 * Get All services and filter 
 * @returns
 */
function getAllService(callerSserviceDTOList) {
	
	var serviceArr = [];
    $.ajax({
        url: "/api/service/all",
        type: 'GET',
        success: function (data) {
        	
        	var isFound = false;
        	for(var a = 0; a < data.result.length; a++){
        		
        		for(var b = 0; b < callerSserviceDTOList.length; b++){
        			
        			if(data.result[a].id == callerSserviceDTOList[b].serviceDTO.id){
        				
        				var subscribeObj = data.result[a];
        				subscribeObj.callerServiceId= callerSserviceDTOList[b].id;
        				subscribeObj.pollingFrequency = callerSserviceDTOList[b].pollingFrequency;
        				subscribeObj.graceTime = callerSserviceDTOList[b].graceTime;
        				serviceArr.push(subscribeObj);
        				isFound = true;
        				break;
        			}
        		}
        		
        		if(!isFound){
        			var subscribeObj = data.result[a];
        			subscribeObj.callerServiceId = 0;
        			subscribeObj.pollingFrequency = "";
    				subscribeObj.graceTime = "";
        			serviceArr.push(subscribeObj);
        		}
        		isFound = false;
        		
        	}
        	drawTable(serviceArr);
        	
        },
    });
}

/**
 * Draw table for services
 * @param serviceArr
 * @returns
 */
function drawTable(serviceArr){
	
	for(var a = 0 ; a < serviceArr.length; a++){
		var tableStr = '<tr>'+
					      '<td>'+serviceArr[a].host+'</td>'+
					      '<td>'+serviceArr[a].port+'</td>'+
					      '<td><input type="text" class="form-control" id="pollingFrequency_'+a+'" name="pollingFrequency_'+a+'" placeholder="Polling Frequency" value="'+serviceArr[a].pollingFrequency+'"></td>'+
					      '<td><input type="text" class="form-control" id="graceTime_'+a+'" name="graceTime_'+a+'" placeholder="Grace Time" value="'+serviceArr[a].graceTime+'"></td>';
					      if(serviceArr[a].callerServiceId > 0 ){
					    	  tableStr += '<td id="subscribe_'+a+'"><button type="button" id="subscribe_btn_'+a+'"  class="btn btn-danger px-4" onclick="unSubscribe('+a+')">Unsubscribe</button></td>'+
					    	  			  '<td id="update_'+a+'"><button type="button" id="update_btn_'+a+'" class="btn btn-danger px-4" onclick="update('+a+')">Update</button></td>';	    
					      }else{
					    	  tableStr += '<td id="subscribe_'+a+'"><button type="button" id="subscribe_btn_'+a+'" class="btn btn-primary px-4" onclick="subscribe('+a+')">Subscribe</button></td>'+
					    	  			  '<td id="update_'+a+'"></td>';
					      }
					      
					      
					      
					      tableStr += '<input type="hidden" id="serviceId_'+a+'" name="serviceId_'+a+'" value="'+serviceArr[a].id+'">'+
					      '<input type="hidden" id="id_'+a+'" name="id_'+a+'" value="'+serviceArr[a].callerServiceId+'">'+
					    '</tr>';
		$("#callerServiceTableBody").append(tableStr);
	}

}


/**
 * Save the callers' services
 * @param index
 * @returns
 */
function subscribe(index){
	
	var callerId = $("#caller").val();
	var pollingFrequency = $("#pollingFrequency_"+index).val();
	var graceTime = $("#graceTime_"+index).val();
	var serviceId = $("#serviceId_"+index).val();

	if(pollingFrequency != null && pollingFrequency > 0){
		var formData = {};
		formData.pollingFrequency = pollingFrequency;
		formData.graceTime = graceTime;
		formData.serviceId = serviceId;
		formData.callerId = callerId;
		
		 $.ajax({
		        url: "/api/caller/"+callerId+"/service/",
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify(formData),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		        success: function (data) {
		        	setUnSubscribeButton(index, data.result.id);
		        	showMessageSuccess();
		        },
		        error: function (data, status, er) {
		        	console.log("Error");
		        }
		    });
	}else{
		alert("Polling Frequency must grater than 0 second");
	}
}

/**
 * Change the subscribe button to Un subscribe
 * @param index
 * @param id
 * @returns
 */
function setUnSubscribeButton(index, id){
	$("#subscribe_btn_"+index).remove();
	
	$("#id_"+index).val(id);

	$("#subscribe_"+index).append('<button type="button" id="subscribe_btn_'+index+'"  class="btn btn-danger px-4" onclick="unSubscribe('+index+')">Unsubscribe</button>');
	$("#update_"+index).append('<button type="button" id="update_btn_'+index+'" class="btn btn-danger px-4" onclick="update('+index+')">Update</button>');
	
	
}


/**
 * Remove the callers' service
 * @returns
 */
function unSubscribe(index){
	var callerId = $("#caller").val();
	var pollingFrequency = $("#pollingFrequency_"+index).val();
	var graceTime = $("#graceTime_"+index).val();
	var serviceId = $("#serviceId_"+index).val();

	var formData = {};
	formData.pollingFrequency = pollingFrequency;
	formData.graceTime = graceTime;
	formData.serviceId = serviceId;
	formData.callerId = callerId;
	
	 $.ajax({
	        url: "api/caller/"+callerId+"/service/"+serviceId,
	        type: 'DELETE',
	        dataType: 'json',
	        contentType: 'application/json',
	        mimeType: 'application/json',
	        success: function (data) {
	        	setSubscribeButton(index)
	        	showMessageSuccess();
	        },
	        error: function (data, status, er) {
	        	console.log("Error");
	        }
	    });
}


/**
 * Change the un subscribe button to subscribe
 * @param index
 * @returns
 */
function setSubscribeButton(index){
	$("#subscribe_btn_"+index).remove();
	$("#update_btn_"+index).remove();
	$("#pollingFrequency_"+index).val("");
	$("#graceTime_"+index).val("");
	$("#id_"+index).val(0);
	
	
	$("#subscribe_"+index).append('<button type="button" id="subscribe_btn_'+index+'"  class="btn btn-primary px-4" onclick="subscribe('+index+')">Subscribe</button>');
	
	
}


/**
 * Update the callers' services
 * @param index
 * @returns
 */
function update(index){
	
	var callerId = $("#caller").val();
	var pollingFrequency = $("#pollingFrequency_"+index).val();
	var graceTime = $("#graceTime_"+index).val();
	var serviceId = $("#serviceId_"+index).val();
	var callerServiceId =  $("#id_"+index).val();

	
	if(pollingFrequency != null && pollingFrequency > 0){
		var formData = {};
		formData.id = callerServiceId;
		formData.pollingFrequency = pollingFrequency;
		formData.graceTime = graceTime;
		formData.serviceId = serviceId;
		formData.callerId = callerId;
		
		 $.ajax({
		        url: "/api/caller/"+callerId+"/service/"+serviceId,
		        type: 'PUT',
		        dataType: 'json',
		        data: JSON.stringify(formData),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		        success: function (data) {
		        	showMessageSuccess();
		        },
		        error: function (data, status, er) {
		        	console.log("Error");
		        }
		    });
	}else{
		alert("Polling Frequency must grater than 0 second");
	}
}


/**
 * get All Callers from the DB
 * @returns
 */
function getAllCallers() {
    $.ajax({
        url: "/api/caller/all",
        type: 'GET',
        success: function (data) {
        	if(data != null && data.result != null){
        		
	        	for(var a = 0; a < data.result.length; a++){
	        		addCallerToDropdown(data.result[a])
	        	}
        	}
        },
    });
}


/**
 * Add caller to dropdown
 * @param callerObj
 * @returns
 */
function addCallerToDropdown(callerObj){
		var newOption = new Option(callerObj.name, callerObj.id, false, false);
		$('#caller').append(newOption).trigger('change');
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    getAllCallers();
    getCallerService();
   // getSubscribeAndUnSubscribeService();
});