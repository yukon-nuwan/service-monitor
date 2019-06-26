

	/**
	 * Save Service
	 * @returns
	 */
	function saveService(){
		var formData = getDivInputsJSON("serviceDiv");
		
		
		
		 $.ajax({
		        url: "/api/service/",
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify(formData),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		        success: function (data) {
		        	var serviceArr = [];
		    		serviceArr [0] = data.result;
		    		drawTable(serviceArr);
		        	clearAllInputs("divService");
		        	showMessageSuccess();
		        },
		        error: function (data, status, er) {
		        	console.log("Error");
		        }
		    });
	}
	
	/**
	 * Update Service
	 * @returns
	 */
	function updateService(){
		var id = $("#id").val();
		if(id != null && id > 0){
		var formData = getDivInputsJSON("serviceDiv");
			 $.ajax({
			        url: "/api/service/"+id,
			        type: 'PUT',
			        dataType: 'json',
			        data: JSON.stringify(formData),
			        contentType: 'application/json',
			        mimeType: 'application/json',
			        success: function (data) {
			        	$('#save').attr("disabled", false);	
			        	$('#update').attr("disabled", true);	
			    		$('#delete').attr("disabled", true);	
			    		
			    		var serviceArr = [];
			    		serviceArr [0] = data.result;
			    		drawTable(serviceArr);
			        	clearAllInputs("divService");
			        	showMessageSuccess();
			        },
			        error: function (data, status, er) {
			        	console.log("Error");
			        }
			    });
		}
	}
	
	/**
	 * Delete Service
	 * @returns
	 */
	function deleteService(){
		var id = $("#id").val();
		if(id != null && id > 0){
			 $.ajax({
			        url: "/api/service/"+id,
			        type: 'DELETE',
			        dataType: 'json',
			        contentType: 'application/json',
			        mimeType: 'application/json',
			        success: function (data) {
			        	clearAllInputs("divService");
			        	showMessageSuccess();
			        },
			        error: function (data, status, er) {
			        	console.log("Error");
			        }
			    });
		}
	}
	
	
	/**
	 * row click set data to update or delete
	 * @param index
	 * @returns
	 */
	function rowClick(index){
		
		$("#host").val($("#host_"+index).text());
		$("#outageStart").val($("#outageStart_"+index).text());
		$("#outageEnd").val($("#outageEnd_"+index).text())
		$("#port").val($("#port_"+index).text());
		$("#id").val($("#id_"+index).val())
		
		$('#save').attr("disabled", true);	
		$('#update').attr("disabled", false);	
		$('#delete').attr("disabled", false);	
		
		
	}
	
	/**
	 * Get All service 
	 * @returns
	 */
	function getServices() {
		
	    $.ajax({
	        url: "/api/service/all",
	        type: 'GET',
	        success: function (data) {
	        	
	        	if(data != null && data.result != null){
	        		drawTable(data.result);
	        	}
	        },
	    });
	}
	
	/**
	 * Draw table for service
	 * @param serviceArr
	 * @returns
	 */
	function drawTable(serviceArr){
		for(var a = 0 ; a < serviceArr.length; a++){
			if(serviceArr[a].host == null){
				serviceArr[a].host = "";
			}
			
			if(serviceArr[a].port == null){
				serviceArr[a].port = "";
			}
			
			if(serviceArr[a].outageStart == null){
				serviceArr[a].outageStart = "";
			}
			
			if(serviceArr[a].outageEnd == null){
				serviceArr[a].outageEnd = "";
			}
			
			if(serviceArr[a].host == null){
				serviceArr[a].host = "";
			}
			
			if(serviceArr[a].id == null){
				serviceArr[a].id = 0;
			}
			
			if($("#tr_"+serviceArr[a].id).length > 0){
				$("#tr_"+serviceArr[a].id).remove();
			}
			var tableStr = '<tr id="tr_'+serviceArr[a].id+'" onclick="rowClick('+serviceArr[a].id+')">'+
						      '<td id="host_'+serviceArr[a].id+'">'+serviceArr[a].host+'</td>'+
						      '<td id="port_'+serviceArr[a].id+'">'+serviceArr[a].port+'</td>'+
						      '<td id="outageStart_'+serviceArr[a].id+'">'+serviceArr[a].outageStart+'</td>'+
						      '<td id="outageEnd_'+serviceArr[a].id+'">'+serviceArr[a].outageEnd+'</td>'+
						      '<input type="hidden" id="id_'+serviceArr[a].id+'" name="id_'+serviceArr[a].id+'" value="'+serviceArr[a].id+'">'+
						    '</tr>';
			$("#serviceTableBody").append(tableStr);
		}
	}
	
	function setTime(){
		
		for(var a = 0; a < 61; a++){
			
			var value = a;
			if(a < 10){
				value = "0"+a;
			}

			var newOptionHourStart = new Option(value, value, false, false);
			var newOptionHourEnd = new Option(value, value, false, false);
			var newOptionMinStart = new Option(value, value, false, false);
			var newOptionminEnd = new Option(value, value, false, false);
			if(a < 25){
				
				$('#hourStart').append(newOptionHourStart).trigger('change');
				$('#hourEnd').append(newOptionHourEnd).trigger('change');
			}
			$('#minStart').append(newOptionMinStart).trigger('change');
			$('#minEnd').append(newOptionminEnd).trigger('change');
		}
		
	}
	
	
	getServices();
	
	
	$(document).ready(
			function () {
				setTime();
				
			});
			
			