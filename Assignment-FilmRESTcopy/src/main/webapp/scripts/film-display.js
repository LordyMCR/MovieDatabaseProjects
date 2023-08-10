// Film display scripts

var getFilmsArray = [];

function showResponseText(data, resultRegion, dataFormat) {
    if(dataFormat == "application/json") {
		var result = JSON.stringify(data);
    	var films = JSON.parse(result);
    	handleData(films, films, resultRegion, dataFormat);
    } else if(dataFormat == "application/xml") {
	    var films = data.getElementsByTagName("film");
	    var rows = new Array(films.length);
	    var subElementNames = ["id", "title", "year", "director", "stars", "review"];
	    for(var i = 0; i < films.length; i++) {
	      rows[i] = getElementValues(films[i], subElementNames);
	    }
	    var arrayToObjs = rows.map(x => ({
				id: parseInt(x[0]),
				title: x[1],
				year: parseInt(x[2]),
				director: x[3],
				stars: x[4],
				review: x[5]
		}));
	    handleData(arrayToObjs, rows, resultRegion, dataFormat)
    } else if (dataFormat == "text/plain") {
		var rawData = data.toString();
	    var rowStrings = rawData.split(/[\n\r]+/);
	    var rows = new Array(rowStrings.length);
	    for(var i = 0; i < rowStrings.length; i++) {
	      rows[i] = rowStrings[i].substring(1).split("#");
	    }
	    var arrayToObjs = rows.map(x => ({
				id: parseInt(x[0]),
				title: x[1],
				year: parseInt(x[2]),
				director: x[3],
				stars: x[4],
				review: x[5]
		}));
	    handleData(arrayToObjs, rows, resultRegion, dataFormat)    	
	}
 }
 
 function handleData(arrayData, tableData, resultRegion, dataFormat) {
	if (getFilmsArray != []) {
		getFilmsArray = [];
		getFilmsArray.push(arrayData);
	} else {
		getFilmsArray.push(arrayData);
	}
    var table = getTable(tableData, dataFormat);
    htmlInsert(resultRegion, table);
 }

function getBodyContent(element) {
  element.normalize();
  return(element.childNodes[0].nodeValue);
}

function getElementValues(element, subElementNames) {
  var values = new Array(subElementNames.length);
  for(var i = 0; i < subElementNames.length; i++) {
    var name = subElementNames[i];
    var subElement = element.getElementsByTagName(name)[0];
    values[i] = getBodyContent(subElement);
  }
  return(values);
}

function htmlInsert(id, htmlData) {
	$('#' + id).html(htmlData);
}

function getTable(films, dataFormat) {
	var table = "<table id='tableToDataTable' class='cell-border compact stripe'>\n" +
              "<thead><tr><th class='th-sm'>ID</th><th class='th-sm'>Title</th><th class='th-sm'>Year</th><th class='th-sm'>Director</th><th class='th-sm'>Stars</th><th class='th-sm'>Review</th><th class='th-sm'>Actions</th></tr></thead><tbody>" + 
              getTableRows(films, dataFormat) + 
              "</table>" +
              editFilmModalBuild() +
              deleteFilmModalBuild();		  
	datatablePlugin();
	return(table);
}

function getTableRows(films, dataFormat) {
	if(dataFormat == "application/json") {
		var body = "";
		for(var i = 0; i < films.length; i++) {
			body += "  <tr>";
			var film = films[i];
		    body += "<td>" + film.id + "</td><td>" + film.title + "</td><td>" + film.year + "</td><td>" + film.director + "</td><td>" + film.stars + "</td><td>" + film.review + "</td>";
			body += "<td><button type='submit' class='editButton btn btn-warning btn-sm' data-bs-toggle='modal' data-bs-target='#editFilmModal' value=\""+film.id+"\"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-wrench-adjustable' viewBox='0 0 16 16'><path d='M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z'/><path d='M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z'/></svg></button>";
			body += "<button type='submit' data-bs-toggle='modal' data-bs-target='#deleteFilmModal' class='deleteButton btn btn-danger btn-sm' value=\""+film.id+"\"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash' viewBox='0 0 16 16'><path d='M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z'/><path fill-rule='evenodd' d='M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z'/></svg></button></td>";
			body += "</tr>\n";			
		}
		body += "</tbody>\n";
	    return(body);
	} else if(dataFormat == "application/xml") {
		var body = "";
		for(var i = 0; i < films.length; i++) {
			body += "  <tr>";
			var film = films[i];
			
			for(var j = 0; j < film.length; j++) {
				
				body += "<td>" + film[j] + "</td>";
			}
			body += "<td><button type='submit' data-bs-toggle='modal' data-bs-target='#editFilmModal' class='editButton btn btn-warning btn-sm' value=\"" + film[0]+ "\"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-wrench-adjustable' viewBox='0 0 16 16'><path d='M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z'/><path d='M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z'/></svg></button>";
			body += "<button type='submit' data-bs-toggle='modal' data-bs-target='#deleteFilmModal' class='deleteButton btn btn-danger btn-sm' value=\"" + film[0]+ "\"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash' viewBox='0 0 16 16'><path d='M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z'/><path fill-rule='evenodd' d='M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z'/></svg></button></td>";
			body += "</tr>\n";
		}
		body += "</tbody>\n";
	    return(body);
	} else if(dataFormat == "text/plain") {
		var body = "";
		for(var i = 0; i < films.length-1; i++) {
		  body += "  <tr>";
		  var row = films[i];
		  for(var j = 0; j < row.length; j++) {
		    body += "<td>" + row[j] + "</td>";
		  }
		  body += "<td><button type='submit' data-bs-toggle='modal' data-bs-target='#editFilmModal' class='editButton btn btn-warning btn-sm' value=\"" + row[0].replace(/\s/g,'') + "\"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-wrench-adjustable' viewBox='0 0 16 16'><path d='M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z'/><path d='M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z'/></svg></button>";
		  body += "<button type='submit' data-bs-toggle='modal' data-bs-target='#deleteFilmModal' class='deleteButton btn btn-danger btn-sm' value=\"" + row[0].replace(/\s/g,'') + "\"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash' viewBox='0 0 16 16'><path d='M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z'/><path fill-rule='evenodd' d='M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z'/></svg></button></td>";
		  body += "</tr>\n";
		}
		body += "</tbody>\n";
		return(body);
	}
}

function datatablePlugin() {
	$(document).ready(function () {
	  	$('#tableToDataTable').DataTable({
    		autoWidth: false,
    		responsive: true,
    		columnDefs: [
   				{orderable: false, targets: -1},
   				{width: "9%", targets: -1},
   				{}
			]
    	});
    	$('#tableToDataTable').show();
	});
}

function editFilmModalBuild() {
	handleEditFilmModal();

	var modal = "";
	modal += "<div class='modal fade' id='editFilmModal' tabindex='-1' aria-labelledby='editFilmModalLabel' aria-hidden='true'>";
	modal += 	"<div class='modal-dialog'>";
	modal +=		"<div class='modal-content'>";
	modal += 			"<div class='modal-header'>";
	modal += 				"<h1 class='modal-title fs-5' id='editFilmModalLabel'>Edit Film</h1>";
	modal += 				"<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>";
	modal += 			"</div>";
	modal += 			"<div class='modal-body'>";
	modal += 				"<form id='editFilmForm' class='was-validated'>";
	modal +=					"<input type='hidden' name='idEdit' id='idEdit' value=''>";
	modal +=					"<div class='form-floating'>"
	modal += 						"<input type='text' max-length='100' class='form-control' name='titleEdit' id='titleEdit' placeholder='Enter title' value='' required>";
	modal +=						"<label for='titleEdit'>Title</label>";
	modal += 					"</div><br>";
	modal +=					"<div class='form-floating'>"	
	modal += 						"<input type='number' min='1900' max='2024' step='1' class='form-control' name='yearEdit' id='yearEdit' placeholder='Enter year' value='' required>";
	modal +=						"<label for='yearEdit'>Year (between 1990 and 2024)</label>";
	modal += 					"</div><br>";
	modal +=					"<div class='form-floating'>"
	modal += 						"<input type='text' max-length='100' class='form-control' name='directorEdit' id='directorEdit' placeholder='Enter director' value='' required>";
	modal +=						"<label for='directorEdit'>Director</label>";
	modal += 					"</div><br>";
	modal +=					"<div class='form-floating'>"
	modal += 						"<input type='text' max-length='100' class='form-control' name='starsEdit' id='starsEdit' placeholder='Enter stars' value='' required>";
	modal +=						"<label for='starsEdit'>Stars</label>";
	modal += 					"</div><br>";
	modal +=					"<div class='form-floating'>"
	modal += 						"<textarea class='form-control' name='reviewEdit' id='reviewEdit' placeholder='Enter review' value='' required></textarea>";
	modal +=						"<label for='reviewEdit'>Review</label>";
	modal +=					"</div>"
	modal += 			"</div>";
	modal += 			"<div class='modal-footer'>";
	modal += 				"<button type='submit' class='btn btn-warning' id='editFilmSubmit' ><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-wrench-adjustable' viewBox='0 0 16 16'><path d='M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z'/><path d='M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z'/></svg>    Edit</button>";
	modal += 				"</form><button type='button' class='btn btn-secondary' data-bs-dismiss='modal'>Cancel</button>";
	modal += 			"</div></div></div></div>";
	
	return modal;
}

function deleteFilmModalBuild() {
	handleDeleteFilmModal();
	
	var modal = "";
	modal += "<div class='modal fade' id='deleteFilmModal' tabindex='-1' aria-labelledby='deleteFilmModalLabel' aria-hidden='true'>";
	modal += 	"<div class='modal-dialog'>";
	modal +=		"<div class='modal-content'>";
	modal += 			"<div class='modal-header'>";
	modal += 				"<h1 class='modal-title fs-5' id='deleteFilmModalLabel'>Delete Film</h1>";
	modal += 				"<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>";
	modal += 			"</div>";
	modal +=			"<div class='modal-body'>";
	modal +=				"<p>Are you sure you want to delete this film from the database?</p>";
	modal +=				"<form><input type='text' name='idDelete' id='idDelete' value=''></form>";	
	modal +=			"</div>";
	modal += 			"<div class='modal-footer'>";
	modal += 				"<button type='submit' class='btn btn-danger' id='deleteButtonSubmit' data-bs-dismiss='modal'><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash' viewBox='0 0 16 16'><path d='M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z'/><path fill-rule='evenodd' d='M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z'/></svg>    Delete</button>";
	modal += 				"<button type='button' class='btn btn-secondary' data-bs-dismiss='modal'>Cancel</button>";
	modal += 			"</div>";
	modal +=		"</div>";
	modal +=	"</div>";
	modal += "</div>";
			 
	return modal;
}

