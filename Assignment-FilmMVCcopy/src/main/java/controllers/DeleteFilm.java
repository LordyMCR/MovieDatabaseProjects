package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;
/**
 * The DeleteFilm class is a servlet that handles requests and responses for the films resource. It retrieves the film's information
 * from the request and deletes an existing record on the database using FilmDAO class.
 * @see FilmDAO
 * @see Film
 */
@WebServlet("/DeleteFilm")
public class DeleteFilm extends HttpServlet {
	/**
	 * Unique identifier for a serializable class, used during deserialization to verify compatibility.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Handles HTTP POST requests for the DeleteFilm resource. This method currently delegates the handling of the request to the doPost()
	 * method.
	 * @param request The request object that contains the client's request
	 * @param response The response object that will be sent to the client
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * Handles HTTP POST requests for the DeleteFilm resource. It retrieves the film's information from the request, creates a new Film
	 * object, and deletes the existing film from the database using FilmDAO class, then redirects to the films servlet.
	 * @param request The request object that contains the client's request
	 * @param response The response object that will be sent to the client
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("deleteFilmId"));
		FilmDAO dao = new FilmDAO();
		Film f = new Film();
		
		f.setId(id);
		
		try {
			dao.deleteFilm(f);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./films");	
	}
}
