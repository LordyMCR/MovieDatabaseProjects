package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Film;
/**
 * <h1>Film Data Access Object (DAO) class</h1>
 * <p>The FilmDAO class is responsible for all database operations related to the Film model. It uses the
 * DatabaseConnectionSingleton to establish a connection to the database.</p>
 */
public class FilmServiceImplDAO {
	/**
	 * DatabaseConnectionSingleton instance to establish connection to the database.
	 */
	DatabaseConnectionSingleton conn;
	/**
	 * PreparedStatement to execute database queries.
	 */
    PreparedStatement prepStmt;
    /**
	 * Film instance to store a single Film object, used for iteration purposes.
	 */
	Film oneFilm = null;
	/**
	 * Constructor for the FilmDAO class.
	 */
	public FilmServiceImplDAO() {}
	/**
	 * Helper method used to iterate through a ResultSet and store each Film object into a string for returning
	 * @param rs ResultSet containing data of all films.
	 * @return Film object
	 */
	private Film getNextFilm(ResultSet rs){
    	Film thisFilm = null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	/**
	 * GET method which retrieves all Films from the database using a prepared statement and populates a string with the
	 * results concatenated. The DatabaseConnectionSingleton class is used to establish a connection to the database.
	 * If an SQLException occurs, it will be caught and printed to the console. Finally, the prepared statement and connection
	 * are closed.
	 * @return toString string of all films
	 */
	public String getFilmsAll() {
		conn = DatabaseConnectionSingleton.getInstance();
		String allFilms = "";
		try {
			String sql = "SELECT * FROM films";
			prepStmt = conn.createPreparedStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			while(rs1.next()){
				oneFilm = getNextFilm(rs1);
				allFilms += oneFilm.toString();
			}
		} catch(SQLException se) { 
			System.out.println(se);
		} finally {
			if(prepStmt != null) {
				try {
					prepStmt.close();
					conn.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return allFilms;
	}
	/**
	 * GET method which retrieves a single Film using the id from the database using a prepared statement and populates the filmById
	 * Film object with the result, then returns the toString string representation of the film. The DatabaseConnectionSingleton class
	 * is used to establish a connection to the database. If an SQLException occurs, it will be caught and printed to the console.
	 * Finally, the prepared statement and connection are closed.
	 * @param id of Film to search on database
	 * @return toString string of Film
	 */
	public String getFilmById(int id) {
		conn = DatabaseConnectionSingleton.getInstance();
		Film filmById = new Film();
		try {
			String sql = "SELECT * FROM films WHERE id = ?";
			prepStmt = conn.createPreparedStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet rs1 = prepStmt.executeQuery();
			while(rs1.next()){
				filmById = getNextFilm(rs1);
			}
		} catch(SQLException se) { 
			System.out.println(se);
		} finally {
			if(prepStmt != null) {
				try {
					prepStmt.close();
					conn.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filmById.toString();
	}
	/**
	 * POST method which inserts a film into the database using a prepared statement, using a populated Film object. The
	 * DatabaseConnectionSingleton class is used to establish a connection to the database. If an SQLException occurs, it
	 * will be thrown and the message "Film Not Added" will be printed. Finally, the prepared statement and connection are closed.
	 * @param f Film class to be inserted into the database.
	 * @return b Boolean response
	 * @throws SQLException throws if an SQL error occurs
	 */
	public boolean addFilm(Film f) throws SQLException {
		boolean b = false;
		conn = DatabaseConnectionSingleton.getInstance();
	  	try {
	  		String sql = "INSERT INTO films (title, year, director, stars, review) VALUES (?, ?, ?, ?, ?);";
	  		prepStmt = conn.createPreparedStatement(sql);
	  		prepStmt.setString(1, f.getTitle().toUpperCase());
	  		prepStmt.setInt(2, f.getYear());
	  		prepStmt.setString(3, f.getDirector().toUpperCase());
	  		prepStmt.setString(4, f.getStars().toUpperCase());
	  		prepStmt.setString(5, f.getReview());
	  		prepStmt.executeUpdate();
	  		System.out.println("Film Added");
	  		b = true;
	  	} catch (SQLException se) {
	  		throw new SQLException("Film Not Added");
	  	} finally {
	  		if(prepStmt != null) {
	  			try {
	  				prepStmt.close();
	  				conn.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	   return b;
	}
	/**
	 * PUT method which updates a film on the database using a prepared statement, using a populated Film object. The
	 * DatabaseConnectionSingleton class is used to establish a connection to the database. If an SQLException occurs,
	 * it will be thrown and the message "Film Not Edited" will be printed. Finally, the prepared statement and connection
	 * are closed.
	 * @param f Film class to be updated on the database.
	 * @return b Boolean response
	 * @throws SQLException throws if an SQL error occurs
	 */
	public boolean editFilm(Film f) throws SQLException {
		boolean b = false;
		conn = DatabaseConnectionSingleton.getInstance();
		try {
			String sql = "UPDATE films SET title = ?, year = ?, director = ?, stars = ?, review = ? WHERE id = ?;";
			prepStmt = conn.createPreparedStatement(sql);
			prepStmt.setString(1, f.getTitle().toUpperCase());
			prepStmt.setInt(2, f.getYear());
			prepStmt.setString(3, f.getDirector().toUpperCase());
			prepStmt.setString(4, f.getStars().toUpperCase());
			prepStmt.setString(5, f.getReview());
			prepStmt.setInt(6, f.getId());
			prepStmt.executeUpdate();
			System.out.println("Film Updated");
			b = true;
		} catch (SQLException se) {
			throw new SQLException("Film Not Edited");
		} finally {
			if(prepStmt != null) {
				try {
					prepStmt.close();
					conn.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	   return b;
	}
	/**
	 * DELETE method which deletes a film from the database using a prepared statement, using a populated Film object. The
	 * DatabaseConnectionSingleton class is used to establish a connection to the database. If an SQLException occurs, it will
	 * be thrown and the message "Film Not Deleted" will be printed. Finally, the prepared statement and connection are closed.
	 * @param f Film class to be deleted from the database.
	 * @return b Boolean response
	 * @throws SQLException throws if an SQL error occurs
	 */
	public boolean deleteFilm(int id) throws SQLException {
		boolean b = false;
		conn = DatabaseConnectionSingleton.getInstance();
		try {
			String sql = "DELETE FROM films WHERE id = ?;";
			prepStmt = conn.createPreparedStatement(sql);
		   
			prepStmt.setInt(1, id);

			prepStmt.executeUpdate();
			System.out.println("Film Deleted");
			b = true;
		} catch (SQLException se) {
			throw new SQLException("Film Not Deleted");
		} finally {
			if(prepStmt != null) {
				try {
					prepStmt.close();
					conn.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	   return b;
	}
}
