$(document).ready(function () {
  	$('#tableGet').DataTable({
		autoWidth: false,
		responsive: true,
		columnDefs: [
			{orderable: false, targets: -1},
			{width: "9%", targets: -1}
		]
	});
	$('#tableGet').show();
});