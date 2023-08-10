<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
 	<link type="text/css" rel="stylesheet" href="./css/site.css">
	<title>Update Film</title>
</head>
<body>
	<div class="container">
		<div class="text-center" id="updateFilmDiv">
			<h2>Update Film</h2>
			<hr>
			<form class="form-group" method="POST" action="./UpdateFilm" >
				<input type="hidden" name="id" id="updateFilmFormId" value="${id}">
				<div class="form-floating">
					<input type="text" maxlength="100" class="form-control" name="title" placeholder="Enter title" value="${title}" required>
					<label for="titleAdd">Title</label>
				</div><br>
				<div class="form-floating">
					<input type="number" min="1900" max="2024" step="1" class="form-control" name="year" placeholder="Enter year" value="${year}" required>
					<label for="yearAdd">Year (between 1900 and 2024)</label>
				</div><br>
				<div class="form-floating">
					<input type="text" maxlength="100" class="form-control" name="director" placeholder="Enter director" value="${director}" required>
					<label for="directorAdd">Director</label>
				</div><br>
				<div class="form-floating">
					<input type="text" maxlength="100" class="form-control" name="stars" placeholder="Enter stars" value="${stars}" required>
					<label for="starsAdd">Stars</label>
				</div><br>
				<div class="form-floating">
					<textarea class="form-control" name="review" id="reviewAdd" placeholder="Enter review" required>${review}</textarea>
					<label for="reviewAdd">Review</label>
				</div><hr>
				<button type="submit" class="btn btn-warning"><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-wrench-adjustable' viewBox='0 0 16 16'><path d='M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z'/><path d='M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z'/></svg>   Update Film</button>
				<a href="./films"><button type="button" class="btn btn-secondary">Cancel</button></a>
				
			</form>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		
</body>
</html>