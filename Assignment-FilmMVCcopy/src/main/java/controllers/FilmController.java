package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;
/**
 * The FilmController class is a servlet that handles requests and responses for the films resource. It retrieves a list
 * of all films from the database and forwards it to the index.jsp page for display.
 * @see FilmDAO
 * @see Film
 */
@WebServlet("/films")
public class FilmController extends HttpServlet {
	/**
	 * Unique identifier for a serializable class, used during deserialization to verify compatibility.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Handles HTTP GET requests for the films resource. Retrieves a list of all films from the database using the FilmDAO
	 * class, sets it as a request attribute, and forwards the request to the index.jsp page for display.
	 * @param request The request object that contains the client's request
	 * @param response The response object that will be sent to the client
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		request.setAttribute("films", allFilms);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	/**
	 * Handles HTTP POST requests for the films resource. This method currently delegates the handling of the request to the doGet()
	 * method.
	 * @param request The request object that contains the client's request
	 * @param response The response object that will be sent to the client
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
