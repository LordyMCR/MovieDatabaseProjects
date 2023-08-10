package models;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
/**
 * <h1>PlainText class</h1>
 * <p>The PlainText class is an implementation of the Formats interface. The class overrides the get, post, put, and delete methods
 * of the Formats interface to handle the HTTP request and response in Plain Text format. It uses the toString method in the Film
 * class, and a split string method to convert the Film objects to and from Plain Text.
 * Text.</p>
 */
public class PlainText implements Formats {
	/**
	 * This method is an implementation of the Formats interface's GET method. It sets the content type of the HttpServletResponse
	 * to "text/plain" and uses the toString method in the Film class to convert the ArrayList of Film objects into a PlainText
	 * string. It then writes string to the PrintWriter and closes it.
	 * @param response The HttpServletResponse object to set the content type of.
	 * @param out The PrintWriter to write the Plain Text string to.
	 * @param allFilms The ArrayList of Film objects to convert to a Plain Text string.
	 */
	@Override
	public void get(HttpServletResponse response, PrintWriter out, ArrayList<Film> allFilms) {
		response.setContentType("text/plain");
		String output = allFilms.toString();
		out.write(output);
		out.close();
	}
	/**
	 * Method that handles the HTTP POST request for a Film object in Plain Text format. It converts the input data in Plain Text
	 * to a Film object using the split string method, and uses the FilmDAO object to insert the Film into the database. The method 
	 * writes a message "Film inserted" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the
	 * exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in plain text
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void post(PrintWriter out, String data, FilmDAO dao, Film f) {
		String[] dataArray = data.split("#");
	    f.setTitle(dataArray[0]);
	    f.setYear(Integer.parseInt(dataArray[1]));
	    f.setDirector(dataArray[2]);
	    f.setStars(dataArray[3]);
	    f.setReview(dataArray[4]);
		
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
	 * Method that handles the HTTP PUT request for a Film object in Plain Text format. It converts the input data in Plain Text
	 * to a Film object using the split string method, and uses the FilmDAO object to update the Film on the database. The method 
	 * writes a message "Film updated" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the
	 * exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in plain text
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void put(PrintWriter out, String data, FilmDAO dao, Film f) {
		String[] dataArray = data.split("#");
		f.setId(Integer.parseInt(dataArray[0]));
		f.setTitle(dataArray[1]);
	    f.setYear(Integer.parseInt(dataArray[2]));
	    f.setDirector(dataArray[3]);
	    f.setStars(dataArray[4]);
	    f.setReview(dataArray[5]);
		
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
	 * Method that handles the HTTP DELETE request for a Film object in Plain Text format. It converts the input data in Plain Text
	 * to a Film object using the split string method, and uses the FilmDAO object to delete the Film on the database. The method 
	 * writes a message "Film deleted" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the
	 * exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in plain text
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void delete(PrintWriter out, String data, FilmDAO dao, Film f) {
		String[] dataArray = data.split("#");
		f.setId(Integer.parseInt(dataArray[0]));
		
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
