// Ajax calls scripts

function makeAjaxCall(url, type, dataFormat, dataToAPI, successCallback) {
	 $.ajax({
		 url: url,
		 type: type,
		 headers: (type === "GET") ? {Accept : dataFormat} : {},
		 contentType : (type !== "GET") ? dataFormat : "",
		 data: (type !== "GET") ? dataToAPI : "",
	 	success: successCallback
	 });
}

function getAllFilms(dataType, resultRegion) {	
	var dataFormat = $('#' + dataType).val();
	
	makeAjaxCall(address, "GET", dataFormat, "", function (data) {
	    showResponseText(data, resultRegion, dataFormat);
	});
	
}

function addFilm(dataType) {
	var dataFormat = $('#' + dataType).val();	
	var title = $('#titleAdd').val().toUpperCase();
	var year = parseInt($('#yearAdd').val());
	var director = $('#directorAdd').val().toUpperCase();
	var stars = $('#starsAdd').val().toUpperCase();
	var review = $('#reviewAdd').val();
				
	var data = {
				title: title,
				year: year,
				director: director,
				stars: stars,
				review: review
	};
	
	if(title != "" && year != "" && director != "" && stars != "" && review != "") {
		var dataToAPI = "";
		if(dataFormat == "application/json") {
			dataToAPI = JSON.stringify(data);
		} else if(dataFormat == "application/xml") {
			dataToAPI = "<film><title>" + title + "</title>";
			dataToAPI += "<year>" + year + "</year>";
			dataToAPI += "<director>" + director + "</director>";
			dataToAPI += "<stars>" + stars + "</stars>";
			dataToAPI += "<review>" + review + "</review></film>";			
		} else if(dataFormat == "text/plain") {
			dataToAPI = title + "#" + year + "#" + director + "#" + stars + "#" + review; 
		}
	
		makeAjaxCall(address, "POST", dataFormat, dataToAPI, function () {
			showAlert("#addAlert");
		    $("#formatButton").click();
		});
	}
}

function editFilm(dataType) {
	var dataFormat = $('#' + dataType).val();
	var id = parseInt($('#idEdit').val());
	var title = $('#titleEdit').val().toUpperCase();
	var year = parseInt($('#yearEdit').val());
	var director = $('#directorEdit').val().toUpperCase();
	var stars = $('#starsEdit').val().toUpperCase();
	var review = $('#reviewEdit').val();
				
	var data = {
				id: id,
				title: title,
				year: year,
				director: director,
				stars: stars,
				review: review
	};
	
	if(id != "" && title != "" && year != "" && director != "" && stars != "" && review != "") {
		var dataToAPI = "";
		if(dataFormat == "application/json") {
			dataToAPI = JSON.stringify(data);
		} else if(dataFormat == "application/xml") {
			dataToAPI = "<film><id>" + id + "</id>";
			dataToAPI += "<title>" + title + "</title>";
			dataToAPI += "<year>" + year + "</year>";
			dataToAPI += "<director>" + director + "</director>";
			dataToAPI += "<stars>" + stars + "</stars>";
			dataToAPI += "<review>" + review + "</review></film>";						
		} else if(dataFormat == "text/plain") {
			dataToAPI = id + "#" + title + "#" + year + "#" + director + "#" + stars + "#" + review; 
		}
		
		makeAjaxCall(address, "PUT", dataFormat, dataToAPI, function () {
		    $("#formatButton").click();
		});
	}
}

function deleteFilm(dataType) {
	var dataFormat = $('#' + dataType).val();
	var id = parseInt($('#idDelete').val());
				
	var data = {
				id: id,
	};
	
	if(id != "") {
		var dataToAPI = "";
		
		if(dataFormat == "application/json") {
			dataToAPI = JSON.stringify(data);
		} else if(dataFormat == "application/xml") {
			dataToAPI = "<film><id>" + id + "</id></film>";
		} else if(dataFormat == "text/plain") {
			dataToAPI = id + "#";
		}
		
		makeAjaxCall(address, "DELETE", dataFormat, dataToAPI, function () {
		    $("#formatButton").click();
		});
	}
}