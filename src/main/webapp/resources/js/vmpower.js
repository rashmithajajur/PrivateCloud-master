function powerOn(ele){
	var vmname = ele.id;
	$.blockUI({ 
        message: 'Please wait while VM is getting powered on.'
    }); 
	$("a").css("cursor","arrow").click(false);
	$.ajax({
    	url: getContextPath() + '/powerOn/' + vmname,
    	//data: JSON.stringify(json),
    	type: "POST",

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
    			console.log(data.flag + " :: "+ '#'+vmname);
//    			$('#'+vmname).addClass('btn btn-error');
//    			$('#'+vmname).prop('value', 'Power Off');
    		} else {
    			/*$('#'+vmname).addClass('btn btn-success');
    			$('#'+vmname).prop('value', 'Power On');*/
    		}
    		$.unblockUI();
    		$("a").css("cursor","arrow").click(true);
    		location.reload(true);
    	}
    });
    event.preventDefault();
}

function powerOff(ele){
	var vmname = ele.id;
	$.blockUI({ 
        message: 'Please wait while VM is getting powered off.'
    }); 
	$("a").css("cursor","arrow").click(false);
	$.ajax({
    	url: getContextPath() + '/powerOff/' + vmname,
    	//data: JSON.stringify(json),
    	type: "POST",

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
//    			$('#'+vmname).addClass('btn btn-success');
//    			$('#'+vmname).prop('value', 'Power On');
    		} else {
//    			$('#'+vmname).addClass('btn btn-danger');
//    			$('#'+vmname).prop('value', 'Power Off');
    		}
    		$.unblockUI();
    		$("a").css("cursor","arrow").click(true);
    		location.reload(true);
    	}
    });
    event.preventDefault();
}

function destroyVM(ele){
	var vmname = ele.id.split("_")[1];
	$.blockUI({ 
        message: 'Please wait while VM is getting destroyed.'
    }); 
	$("a").css("cursor","arrow").click(false);
	$.ajax({
    	url: getContextPath() + '/destroyVM/' + vmname,
    	//data: JSON.stringify(json),
    	type: "POST",

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
    			console.log(data.flag + " :: "+ '#'+vmname);
//    			$('#'+vmname).addClass('btn btn-error');
//    			$('#'+vmname).prop('value', 'Power Off');
    		} else {
    			/*$('#'+vmname).addClass('btn btn-success');
    			$('#'+vmname).prop('value', 'Power On');*/
    		}
    		$.unblockUI();
    		$("a").css("cursor","arrow").click(true);
    		location.reload(true);
    		
    	}
    });
    event.preventDefault();
}