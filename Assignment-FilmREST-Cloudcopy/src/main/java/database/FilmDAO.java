package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import models.Film;

/**
 * <h1>Film Data Access Object (DAO) class</h1>
 * <p>The FilmDAO class is responsible for all database operations related to the Film model. It uses the
 * DatabaseConnectionSingleton to establish a connection to the database.</p>
 */
public class FilmDAO {
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
	public FilmDAO() {}
	/**
	 * Helper method used to iterate through a ResultSet and store each Film object into an ArrayList
	 * @param rs ResultSet containing data of all films.
	 * @return Film object
	 */
	private Film getNextFilm(ResultSet rs){
		Film thisFilm = null;
		try {
			thisFilm = new Film (
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
	 * GET method which retrieves all Films from the database using a prepared statement and populates an ArrayList with the
	 * results as separate Film objects. The DatabaseConnectionSingleton class is used to establish a connection to the database.
	 * If an SQLException occurs, it will be caught and printed to the console. Finally, the prepared statement and connection
	 * are closed.
	 * @return ArrayList of Film objects
	 */
	public ArrayList<Film> getAllFilms(){
		conn = DatabaseConnectionSingleton.getInstance();
		ArrayList<Film> allFilms = new ArrayList<Film>();
				
		try {
			String sql = "SELECT * FROM films";
			prepStmt = conn.createPreparedStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			while(rs1.next()){
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
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
	 * POST method which inserts a film into the database using a prepared statement, using a populated Film object. The
	 * DatabaseConnectionSingleton class is used to establish a connection to the database. If an SQLException occurs, it
	 * will be thrown and the message "Film Not Added" will be printed. Finally, the prepared statement and connection are closed.
	 * @param f Film class to be inserted into the database.
	 * @return b Boolean response
	 * @throws SQLException throws if an SQL error occurs
	 */
	public boolean insertFilm(Film f) throws SQLException {
		boolean b = false;
		conn = DatabaseConnectionSingleton.getInstance();
		
		try {
			String sql = "INSERT INTO films (title, year, director, stars, review) VALUES (?, ?, ?, ?, ?);";
			prepStmt = conn.createPreparedStatement(sql);
			prepStmt.setString(1, f.getTitle().toUpperCase());
			prepStmt.setInt(2, f.getYear());
			prepStmt.setString(3, f.getDirector());
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
	public boolean updateFilm(Film f) throws SQLException {
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
	public boolean deleteFilm(Film f) throws SQLException {
		boolean b = false;
		conn = DatabaseConnectionSingleton.getInstance();
	   	   
		try {
			String sql = "DELETE FROM films WHERE id = ?;";
			prepStmt = conn.createPreparedStatement(sql);
		   
			prepStmt.setInt(1, f.getId());

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
