var stompClient = null;



/**
 * Sock js Connection
 * @returns
 */
function connect() {
    var socket = new SockJS('/service-monitor');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        var caller = $('#caller').val();
        
        stompClient.subscribe('/topic/service-status/'+caller, function (serviceMessageDTOResponse) {
        	var serviceMessageDTO = JSON.parse(serviceMessageDTOResponse.body);
        	drawTable(serviceMessageDTO);
        });
    });
}

/**
 * Draw table when message receive
 * @param serviceMessageDTO
 * @returns
 */
function drawTable(serviceMessageDTO){
	const checkProperty = $('#'+serviceMessageDTO.serviceId+"_"+serviceMessageDTO.callerId);
	if(checkProperty.length <= 0){
		var trClass = "table-danger";
		if(serviceMessageDTO.isAlive){
			trClass = "table-success";
		}
    	var tableStr = '<tr id="tr_'+serviceMessageDTO.serviceId+"_"+serviceMessageDTO.callerId+'" class="'+trClass+'">'+
					      '<td>'+serviceMessageDTO.host+'</td>'+
					      '<td>'+serviceMessageDTO.port+'</td>'+
					      '<td id="'+serviceMessageDTO.serviceId+"_"+serviceMessageDTO.callerId+'">'+serviceMessageDTO.message+'</td>'+
					    '</tr>';

    	$("#serviceStatusTableBody").append(tableStr);
	}else{
		var trClassDanger = "table-danger";
		var trClassSuccess = "table-success";
		if(serviceMessageDTO.isAlive){
			$("#tr_"+serviceMessageDTO.serviceId+"_"+serviceMessageDTO.callerId).removeClass(trClassDanger).addClass(trClassSuccess);
		}else{
			$("#tr_"+serviceMessageDTO.serviceId+"_"+serviceMessageDTO.callerId).removeClass(trClassSuccess).addClass(trClassDanger);
		}
		
		 
		 $('#'+serviceMessageDTO.serviceId+"_"+serviceMessageDTO.callerId).text(serviceMessageDTO.message);
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



/**
 * Disconnect connection
 * @returns
 */
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}



/**
 * Get all caller services status by caller
 * @returns
 */
function getCallerServicesStatusByCaller() {
	connect();
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    getAllCallers();
});
