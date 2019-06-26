function getDivInputsJSON(divId) {
    var formData = {};
    $("#" + divId).find('input,input[type="hidden"], input[type="text"],input[type="email"],input[type="date"], input[type="password"], input[type="checkbox"]:checked, input[type="radio"]:checked, select, textarea').each(function(e) {
        if (this.name === null || this.name === undefined || this.name === '')
            return;
        if ($(this).is('select')) {
        	formData[this.id]=$(this).find('option:selected').val();
        } else if ($(this).attr("type") == 'checkbox') {
            if ($(this).prop('checked') == true) {
                formData[this.id]=true;
            } else {
                formData[this.id]=false;
            }
        } else if ($(this).attr("type") == 'radio') {

            if ($(this).prop('checked') == true) {
                formData[this.id]=true;

            } else {
                formData[this.id]=false;

            }
        } else {
            formData[this.id]=this.value;

        }

    });
    return formData;
}

function clearAllInputs(divId) {
	$("#" + divId).find(':input').each(function() {
		if (this.type == 'submit') {
			// do nothing
		} else if (this.type == 'checkbox' || this.type == 'radio') {
			this.checked = false;
		} else if (this.type == 'file') {
			var control = $(this);
			control.replaceWith(control = control.clone(true));
		} else {
			$(this).val('');
		}
	});
}
	

function showMessageSuccess(){
	$('#infoPopContent').append('<img id="theImg" src="http://goactionstations.co.uk/wp-content/uploads/2017/03/Green-Round-Tick.png" />')
	$('#infoModal').modal('show');
}	
	
	
