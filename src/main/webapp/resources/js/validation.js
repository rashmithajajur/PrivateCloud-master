$(document).ready(function() {

  $('#addVM').click(function(event) {

	  $('#os').val()
	  
	  
	  
//	  var producer = $('#producer').val();
//	  var model = $('#model').val();
//	  var price = $('#price').val();
//	  var json = { "producer" : producer, "model" : model, "price": price};

    $.ajax({
    	url: '/checkVM/' + $('#vmname').val(),
    	//data: JSON.stringify(json),
    	type: "GET",
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
    	success: function(data) {
    		var respContent = "";
    		console.log(data);
    		
//		respContent += "<span class="success">Smartphone was created: [";
//		respContent += smartphone.producer + " : ";
//		respContent += smartphone.model + " : " ;
//		respContent += smartphone.price + "]</span>";

    		/*$("#sPhoneFromResponse").html(respContent);  */ 	
    		
    	}
    });

    event.preventDefault();
  });

});