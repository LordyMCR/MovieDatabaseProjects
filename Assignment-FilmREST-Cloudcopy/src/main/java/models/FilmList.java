package models;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
/**
 * <h1>FilmList class</h1>
 * <p>The FilmList class models a list of Film objects. It has a List field, constructors, getter and setter
 * methods, and uses Jakarta XML Bind annotations for the XML representation.</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "films")
public class FilmList {
	/** Private field that stores the list of Film objects */
	@XmlElement(name = "film")
	private List<Film> filmList;
	/** Default constructor that creates an empty FilmList object. */
	public FilmList() {}
	/**
	 * Constructor that creates an instance of FilmList with a parameter of List of Film Objects
	 * @param filmList the List of Film objects to be stored in the filmList field
	 */
	public FilmList(List<Film> filmList) {
		this.filmList = filmList;
	}
	/** @return filmList the List of Film objects */
	public List<Film> getFilmList() {
		return filmList;
	}
	/** @param filmList the List of Film objects */
	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}
}
