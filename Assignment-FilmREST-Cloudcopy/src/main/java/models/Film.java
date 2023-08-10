package models;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <h1>Film class</h1>
 * <p>The Film class is a model that stores fields for id, title, year, director, stars, and review. It has
 * constructors, getters and setters, and a toString method. Additionally, it uses Jakarta XML Bind annotations
 * to specify the XML representation of the object.</p>
 */
@XmlRootElement(name = "film")
@XmlType(propOrder={"id", "title", "year", "director", "stars", "review"})
public class Film {
	/** int variable that holds the id of the film */
	int id;
	/** String variable that holds the title of the film */
	String title;
	/** int variable that holds the year of the film */
	int year;
	/** String variable that holds the director of the film */
	String director;
	/** String variable that holds the stars of the film */
	String stars;
	/** String variable that holds the review of the film */
	String review;
	/**
	 * Constructor that creates a Film object with the provided id, title, year, director, stars, and review.
	 * @param int id
	 * @param String title
	 * @param int year
	 * @param String director
	 * @param String stars
	 * @param String review
	 */
	public Film(int id, String title, int year, String director, String stars, String review) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}
	/** Default constructor that creates an empty Film object. */
	public Film() {
		super();
	}
	/** Retrieves the film id 
	 * @return int id */
	public int getId() {
		return id;
	}
	/** Sets the film id
	 * @param int id */
	public void setId(int id) {
		this.id = id;
	}
	/** Retrieves the film title
	 * @return String title */
	public String getTitle() {
		return title;
	}
	/** Sets the film title
	 * @param String title */
	public void setTitle(String title) {
		this.title = title;
	}
	/** Retrieves the film year
	 * @return int year */
	public int getYear() {
		return year;
	}
	/** Sets the film year
	 * @param int year */
	public void setYear(int year) {
		this.year = year;
	}
	/** Retrieves the film director
	 * @return String director */
	public String getDirector() {
		return director;
	}
	/** Sets the film director
	 * @param String director */
	public void setDirector(String director) {
		this.director = director;
	}
	/** Retrieves the film stars
	 * @return String stars */
	public String getStars() {
		return stars;
	}
	/** Sets the film stars
	 * @param String stars */
	public void setStars(String stars) {
		this.stars = stars;
	}
	/** Retrieves the film review
	 * @return String review */
	public String getReview() {
		return review;
	}
	/** Sets the film review
	 * @param String review */
	public void setReview(String review) {
		this.review = review;
	}
	/** Retrieves a string representation of the Film object in the format "id#title#year#director#stars#review\n", used
	 * for the Format PlainText format
	 * @return film in string format */
	@Override
	public String toString() {
		return id + "#" + title + "#" + year + "#" + director + "#" + stars + "#" + review + "\n";
	}   
}