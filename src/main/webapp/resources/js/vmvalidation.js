$(document).ready(function() {

  $('#vmname').blur(function(event) {

	var vname = $('#vmname').val();
	

    $.ajax({
    	url: getContextPath() + '/checkvname/' + vname,
    	//data: JSON.stringify(json),
    	type: "GET",

    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
//    	beforeSend: function(xhr) {
//    		xhr.setRequestHeader("Accept", "text/plain");
//    		xhr.setRequestHeader("Content-Type", "text/plain");
//    	},
    	success: function(data) {
    		console.log(data);
    		if(data.flag){
    			$('#vnamegroup').addClass('control-group');
    			$('#vnamemsg').text('');
    		} else {
    			$('#vnamegroup').addClass('control-group error');
    			$('#vmname').val('');
    			$('#vnamemsg').text('This VM already Exists.');
    		}
    	}
    });

    event.preventDefault();
  });

});