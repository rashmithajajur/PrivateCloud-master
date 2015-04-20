$(document).ready(function() {

  $('#username').blur(function(event) {

	var uname = $('#username').val();
	

    $.ajax({
    	url: getContextPath() + '/checkuname/' + uname,
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
    			$('#unamegroup').addClass('control-group');
    			$('#unamemsg').text('');
    		} else {
    			$('#unamegroup').addClass('control-group error');
    			$('#username').val('');
    			$('#unamemsg').text('User name is not available.');
    		}
    	}
    });

    event.preventDefault();
  });

});