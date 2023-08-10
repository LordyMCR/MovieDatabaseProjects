// Webpage functionality scripts

const address = "./FilmAPI";

$(document).ready(function() {
	init();
});

function init() {
	hideAlerts();
	bindEvents();
	getAllFilms("format","allFilms");
}

function hideAlerts() {
	$("#addAlert").hide();
	$("#editAlert").hide();
	$("#deleteAlert").hide();
}

function bindEvents() {
	$("#formatButton").click(function() {
		getAllFilms("format", "allFilms");
	});
	handleFormValidation();
	handleAddFilmModal();
}

function handleFormValidation() {
	var forms = document.querySelectorAll('.needs-validation')
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }
        form.classList.add('was-validated')
     
        if (form.checkValidity()) {
          event.preventDefault();
          addFilm("format");
          $("#addFilmModal").modal('hide');
          form.classList.remove('was-validated');
        }
      }, false)
    });
}

function handleAddFilmModal() {
  $("#addFilmModal").on("hidden.bs.modal", function() {
    resetForm("#addFilmForm");
  });
}

function resetForm(formId) {
  $(formId)[0].reset();
  $(formId).removeClass("was-validated");
}

function handleEditFilmModal() {
	$(document).ready(function() {
		$(".editButton").click(function() {
			$("#idEdit").val($(this).val().replace(/\s/g, ''));
			var idSearch = parseInt($("#idEdit").val());
			var result;
			for (var i=0; i<getFilmsArray.length; i++) {
				var film = getFilmsArray[i];
				result = film.find(x => x.id == idSearch);
			}
				
			$("#titleEdit").val(result.title);
			$("#yearEdit").val(result.year);
			$("#directorEdit").val(result.director);
			$("#starsEdit").val(result.stars);
			$("#reviewEdit").val(result.review);
		});
		
		// Edit film when modal submit button is clicked
		$("#editFilmSubmit").click(function(e) {
			if($("#editFilmForm")[0].checkValidity()) {	
				e.preventDefault();
				editFilm("format");
				$("#editFilmModal").modal('hide');
				showAlert("#editAlert");
			}
		});
	});
}

function handleDeleteFilmModal() {
	$(document).ready(function() {
		$(".deleteButton").click(function() {
			$("#idDelete").val($(this).val().replace(/\s/g, ''));
		});
		
		// Delete film when modal submit button is clicked
		$("#deleteButtonSubmit").click(function() {
			deleteFilm("format");
			showAlert("#deleteAlert");
		});
	});
}

function showAlert(alertID) {
  $(alertID).fadeTo(2000, 500).slideUp(500, function() {
    $(alertID).slideUp(500);
  });
}