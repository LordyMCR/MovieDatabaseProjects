package models;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
/**
 * <h1>Formats interface</h1>
 * <p>The Formats interface defines methods for handling different format types for HTTP requests.</p>
 */
public interface Formats {
	/**
	 * Method for handling GET requests and returning the response in the specified format.
	 * @param response HttpServletResponse object
	 * @param out PrintWriter object
	 * @param allFilms ArrayList of Film objects
	 */
	void get(HttpServletResponse response, PrintWriter out, ArrayList<Film> allFilms);
	/**
	 * Method for handling POST requests and returning the response in the specified format.
	 * @param out PrintWriter object
	 * @param data String representation of the data
	 * @param dao FilmDAO object
	 * @param f Film object
	 */
	void post(PrintWriter out, String data, FilmDAO dao, Film f);
	/**
	 * Method for handling PUT requests and returning the response in the specified format.
	 * @param out PrintWriter object
	 * @param data String representation of the data
	 * @param dao FilmDAO object
	 * @param f Film object
	 */
	void put(PrintWriter out, String data, FilmDAO dao, Film f);
	/**
	 * Method for handling DELETE requests and returning the response in the specified format.
	 * @param out PrintWriter object
	 * @param data String representation of the data
	 * @param dao FilmDAO object
	 * @param f Film object
	 */
	void delete(PrintWriter out, String data, FilmDAO dao, Film f);
}
