package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * The UpdateFilm class is a servlet that handles requests and responses for the films resource. It retrieves the film's information
 * from the request and updates an existing record on the database using FilmDAO class.
 * @see FilmDAO
 * @see Film
 */
@WebServlet("/UpdateFilm")
public class UpdateFilm extends HttpServlet {
	/**
	 * Unique identifier for a serializable class, used during deserialization to verify compatibility.
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Handles HTTP GET requests for the UpdateFilm resource. It retrieves the film's information from the database using the FilmDAO
     * class, based on the id provided in the request, sets the film's information as request attributes, and forwards the request to
     * the updateFilmForm.jsp page for displaying an edit form with populated fields.
     * @param request The request object that contains the client's request
     * @param response The response object that will be sent to the client
     * @throws ServletException throws if a servlet-related error occurs
     * @throws IOException throws if an I/O error occurs
     */   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("updateFilmId"));
		
		FilmDAO dao = new FilmDAO();
		Film f = new Film();
		
		f.setId(dao.getFilmByID(id).getId());
		f.setTitle(dao.getFilmByID(id).getTitle());
		f.setYear(dao.getFilmByID(id).getYear());
		f.setDirector(dao.getFilmByID(id).getDirector());
		f.setStars(dao.getFilmByID(id).getStars());
		f.setReview(dao.getFilmByID(id).getReview());
		
		request.setAttribute("id", f.getId());
		request.setAttribute("title", f.getTitle());
		request.setAttribute("year", f.getYear());
		request.setAttribute("director", f.getDirector());
		request.setAttribute("stars", f.getStars());
		request.setAttribute("review", f.getReview());
		
		RequestDispatcher rd = request.getRequestDispatcher("updateFilmForm.jsp");
		rd.forward(request, response);
	}
	/**
	 * Handles HTTP POST requests for the UpdateFilm resource. It retrieves the film's information from the request, creates a new Film
	 * object, and updates the existing film on the database using FilmDAO class, then redirects to the films servlet.
	 * @param request The request object that contains the client's request
	 * @param response The response object that will be sent to the client
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title").toUpperCase();
		int year = Integer.parseInt(request.getParameter("year"));
		String director = request.getParameter("director").toUpperCase();
		String stars = request.getParameter("stars").toUpperCase();
		String review = request.getParameter("review");
		
		FilmDAO dao = new FilmDAO();
		Film newF = new Film();
		
		newF.setId(id);
		newF.setTitle(title);
		newF.setYear(year);
		newF.setDirector(director);
		newF.setStars(stars);
		newF.setReview(review);
		
		try {
			dao.updateFilm(newF);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./films");
	}
}
