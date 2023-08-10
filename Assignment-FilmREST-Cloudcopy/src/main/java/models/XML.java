package models;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
/**
 * <h1>XML class</h1>
 * <p>The XML class is an implementation of the Formats interface. The class overrides the get, post, put, and delete methods of
 * the Formats interface to handle the HTTP request and response in XML format. It uses Jakarta XML Bind (JAXB) to convert between
 * Java objects and XML.</p>
 */
public class XML implements Formats {
	/**
	 * This method is an implementation of the Formats interface's GET method. It sets the content type of the HttpServletResponse
	 * to "application/xml" and uses the Gson library to convert the ArrayList of Film objects into a JSON string. It then writes
	 * the JSON string to the PrintWriter and closes it.
	 * @param response The HttpServletResponse object to set the content type of.
	 * @param out The PrintWriter to write the JSON string to.
	 * @param allFilms The ArrayList of Film objects to convert to a JSON string.
	 */
	@Override
	public void get(HttpServletResponse response, PrintWriter out, ArrayList<Film> allFilms) {
		response.setContentType("application/xml");
		FilmList fl = new FilmList(allFilms);
		StringWriter sw = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(FilmList.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(fl, sw);
			out.write(sw.toString());
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	/**
	 * Method that handles the HTTP POST request for a Film object in XML format. It converts the input data in XML format to a
	 * Film object using JAXB, and uses the FilmDAO object to insert the Film into the database. The method writes a message "Film
	 * inserted" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in JSON format
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void post(PrintWriter out, String data, FilmDAO dao, Film f) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			f = (Film) jaxbUnmarshaller.unmarshal(new StringReader(data));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
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
	 * Method that handles the HTTP PUT request for a Film object in XML format. It converts the input data in XML format to a
	 * Film object using JAXB, and uses the FilmDAO object to update the Film on the database. The method writes a message "Film
	 * uodated" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in JSON format
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void put(PrintWriter out, String data, FilmDAO dao, Film f) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			f = (Film) jaxbUnmarshaller.unmarshal(new StringReader(data));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
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
	 * Method that handles the HTTP DELETE request for a Film object in XML format. It converts the input data in XML format to a
	 * Film object using JAXB, and uses the FilmDAO object to delete the Film from the database. The method writes a message "Film
	 * deleted" to the PrintWriter if the insertion is successful, otherwise it prints the stack trace of the exception thrown.
	 * @param out PrintWriter object to write the response to the client
	 * @param data String representation of the Film object in JSON format
	 * @param dao FilmDAO object to interact with the database
	 * @param f Film object to be inserted
	 */
	@Override
	public void delete(PrintWriter out, String data, FilmDAO dao, Film f) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			f = (Film) jaxbUnmarshaller.unmarshal(new StringReader(data));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
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
