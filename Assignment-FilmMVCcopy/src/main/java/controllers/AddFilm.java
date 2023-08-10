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
 * The AddFilm class is a servlet that handles requests and responses for the films resource. It retrieves the film's information
 * from the request and inserts into the database using FilmDAO class.
 * @see FilmDAO
 * @see Film
 */
@WebServlet("/AddFilm")
public class AddFilm extends HttpServlet {
	/**
	 * Unique identifier for a serializable class, used during deserialization to verify compatibility.
	 */
	private static final long serialVersionUID = 1L;
    /**
     * Handles HTTP GET requests for the AddFilm resource. It forwards the request to the addFilmForm.html page for displaying the
     * form for adding a new film.
     * @param request The request object that contains the client's request
     * @param response The response object that will be sent to the client
     * @throws ServletException throws if a servlet-related error occurs
     * @throws IOException throws if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("addFilmForm.html");
		rd.forward(request, response);
	}
	/**
	 * Handles HTTP POST requests for the AddFilm resource. It retrieves the film's information from the request, creates a new Film
	 * object, and inserts into the database using FilmDAO class, then redirects to the films servlet.
	 * @param request The request object that contains the client's request
	 * @param response The response object that will be sent to the client
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String title = request.getParameter("title").toUpperCase();
		int year = Integer.parseInt(request.getParameter("year"));
		String director = request.getParameter("director").toUpperCase();
		String stars = request.getParameter("stars").toUpperCase();
		String review = request.getParameter("review");
		
		FilmDAO dao = new FilmDAO();
		Film newF = new Film();
		
		newF.setTitle(title);
		newF.setYear(year);
		newF.setDirector(director);
		newF.setStars(stars);
		newF.setReview(review);
		
		try {
			dao.insertFilm(newF);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./films");
	}
}
