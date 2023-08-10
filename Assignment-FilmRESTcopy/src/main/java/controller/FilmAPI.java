package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;
import models.FormatFactory;
import models.Formats;

/**
 * <h1>Servlet implementation class FilmAPI</h1>
 * <p>The FilmAPI servlet class handles HTTP requests for films, using FormatFactory and FilmDAO objects to handle different formats
 * and interact with the database.</p>
 */
@WebServlet("/FilmAPI")
public class FilmAPI extends HttpServlet {
	/**
	 * Unique identifier for a serializable class, used during deserialization to verify compatibility.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Instance of FactoryFactory class, which is used to create and return different types of Formats based on
	 * the provided format type.
	 */
	private FormatFactory ff = new FormatFactory();   
	/**
	 * Handles the GET request by calling the getAllFilms method of the FilmDAO class. Then using the getFormat method of the ff
	 * FormatFactory object to determine the desired format type based on the Accept header in the request. Calls the get
	 * method of the returned format class to handle formatting the films and writing them to the response.
	 * @param request The HttpServletRequest object representing the client's request.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String format = request.getHeader("Accept");
		PrintWriter out = response.getWriter();
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		
		Formats getFormat = ff.getFormat(format);
		getFormat.get(response, out, allFilms);
	}
	/**
	 * Handles the POST request by getting the format of the data from the request's "Content-Type" header, reading the data
	 * from the request, creating a Film object, and using the FilmDAO object to interact with the database. It then uses the
	 * getFormat method of the ff FormatFactory object to determine the desired format type based on the Content-Type header in
	 * the request and calls the post method of the returned format class to handle formatting the films and writing them to the
	 * database.
	 * @param request The HttpServletRequest object representing the client's request.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataFormat = request.getHeader("Content-Type");
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Film f = new Film();
		FilmDAO dao = new FilmDAO();
		PrintWriter out = response.getWriter();
		
		Formats postFormat = ff.getFormat(dataFormat);
		postFormat.post(out, data, dao, f);
	}
	/**
	 * Handles the PUT request by getting the format of the data from the request's "Content-Type" header, reading the data
	 * from the request, creating a Film object, and using the FilmDAO object to interact with the database. It then uses the
	 * getFormat method of the ff FormatFactory object to determine the desired format type based on the Content-Type header
	 * in the request and calls the put method of the returned format class to handle formatting the films and writing them to
	 * the database.
	 * @param request The HttpServletRequest object representing the client's request.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataFormat = request.getHeader("Content-Type");
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Film f = new Film();
		FilmDAO dao = new FilmDAO();
		PrintWriter out = response.getWriter();
		
		Formats putFormat = ff.getFormat(dataFormat);
		putFormat.put(out, data, dao, f);
	}
	/**
	 * Handles the DELETE request by getting the format of the data from the request's "Content-Type" header, reading the data
	 * from the request, creating a Film object, and using the FilmDAO object to interact with the database. It then uses the
	 * getFormat method of the ff FormatFactory object to determine the desired format type based on the Content-Type header
	 * in the request and calls the put method of the returned format class to handle formatting the films and writing them to
	 * the database.
	 * @param request The HttpServletRequest object representing the client's request.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException throws if a servlet-related error occurs
	 * @throws IOException throws if an I/O error occurs
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataFormat = request.getHeader("Content-Type");
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Film f = new Film();
		FilmDAO dao = new FilmDAO();
		PrintWriter out = response.getWriter();
		
		Formats deleteFormat = ff.getFormat(dataFormat);
		deleteFormat.delete(out, data, dao, f);
	}
}
