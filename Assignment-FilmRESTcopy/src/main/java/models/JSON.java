package models;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import database.FilmDAO;
/**
 * <h1>JSON class</h1>
 * <p>The JSON class is an implementation of the Formats interface. The class overrides the get, post, put, and delete methods of
 * the Formats interface to handle the HTTP request and response in JSON format. It uses the GSON library to convert the Film
 * objects to and from JSON.</p>
 */
public class JSON implements Formats {
	/**
	 * This method is an implementation of the Formats interface's get method. It sets the content type of the HttpServletResponse
	 * to "application/json" and uses the Gson library to convert the ArrayList of Film objects into a JSON string. It then writes
	 * the JSON string to the PrintWriter and closes it.
	 * @param response The HttpServletResponse object to set the content type of.
	 * @param out The PrintWriter to write the JSON string to.
	 * @param allFilms The ArrayList of Film objects to convert to a JSON string.
	 */
	@Override
	public void get(HttpServletResponse response, PrintWriter out, ArrayList<Film> allFilms) {
		response.setContentType("application/json");
		Gson gson = new Gson();
		String json = gson.toJson(allFilms);
		out.write(json);
		out.close();
	}
	/**
	 * Method that handles the HTTP POST request for a Film object in JSON format. It converts the input data in JSON format to a
	 * Film object using GSON, and uses the FilmDAO object to insert the Film into the database. The method writes a message "Film
	 * inserted" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in JSON format
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void post(PrintWriter out, String data, FilmDAO dao, Film f) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		f = gson.fromJson(data, Film.class);
		
		try {
			dao.insertFilm(f);
			out.write("Film inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	/**
	 * Method that handles the HTTP PUT request for a Film object in JSON format. It converts the input data in JSON format to a
	 * Film object using GSON, and uses the FilmDAO object to update the Film on the database. The method writes a message "Film
	 * uodated" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in JSON format
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void put(PrintWriter out, String data, FilmDAO dao, Film f) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		f = gson.fromJson(data, Film.class);
		
		try {
			dao.updateFilm(f);
			out.write("Film updated");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	/**
	 * Method that handles the HTTP DELETE request for a Film object in JSON format. It converts the input data in JSON format to a
	 * Film object using GSON, and uses the FilmDAO object to delete the Film from the database. The method writes a message "Film
	 * deleted" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in JSON format
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void delete(PrintWriter out, String data, FilmDAO dao, Film f) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		f = gson.fromJson(data, Film.class);
		
		try {
			dao.deleteFilm(f);
			out.write("Film deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
