/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieexamproject.be.Category;
import java.sql.*;
import movieexamproject.be.Movie;

/**
 *
 * @author narma
 */
public class MovieDBDAO {
    
    private SQLServerDataSource ds;
    
    public MovieDBDAO(){     
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MovieExamProject");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
    
    public void addMovie(String name, String path, ArrayList<Category> selectedCategory){
        int movieID = -1;
        try(Connection con = ds.getConnection()){
            String sql = "INSERT INTO Movie (name,filepath) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, path);
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
               movieID = rs.getInt(1);
            }
            for (Category category : selectedCategory) {
                int categoryID = category.getId();
                
                String sql2 = "INSERT INTO MoviesOnCategories (CategoryId,MovieId) VALUES (?,?)";
                PreparedStatement pstmt2 = con.prepareStatement(sql2);
                pstmt2.setInt(1, categoryID);
                pstmt2.setInt(2, movieID);
                pstmt2.executeUpdate();
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteMovie(int id){
        try(Connection con = ds.getConnection()){
            String sql = "DELETE FROM Movie WHERE id = ?;"
                       + "DELETE FROM MoviesOnCategories WHERE CategoryId = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateMovie(int id, String name, String filepath,ArrayList<Category> categories){
        try(Connection con = ds.getConnection()){
            String sql = "UPDATE Movie SET name = ?,  filepath = ?"
                       + "DELETE FROM MoviesOnCategories WHERE MovieId=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
           
            pstmt.setString(2, filepath);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            
            for (Category category : categories) {
                int categoryID = category.getId();
                
                String sql2 = "INSERT INTO MoviesOnCategories (CategoryId,MovieId) VALUES (?,?)";
                PreparedStatement pstmt2 = con.prepareStatement(sql2);
                pstmt2.setInt(1, categoryID);
                pstmt2.setInt(2, id);
                pstmt2.executeUpdate();
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playedMovie(int id){
        try(Connection con = ds.getConnection()){
            String sql = "UPDATE Movie SET lastview = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addRating(int movieid, float rating)
    {
        try(Connection con = ds.getConnection()) {
            String sql="UPDATE Movie SET rating = ? WHERE id=? ";
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.setFloat(1, rating);
             pstmt.setInt(2, movieid);
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public ArrayList<Movie> getMoviesByTilteAndRatings(String title, int rating){
        try {
            Connection con = ds.getConnection();
            String sql="SELECT * FROM Movie"
                    + "WHERE movie.name LIKE '%?%' AND moive.rating >=? AND movie.rating < (?+1);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setFloat(2, rating);
            pstmt.setFloat(3, rating);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<Movie> movies = new ArrayList();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float rate = rs.getFloat("rating");
                String filePath = rs.getString("filePath");
                Date lastView = rs.getDate("lastView");
                Movie m = new Movie(id, name, rate, filePath, lastView);
                movies.add(m);
            }
            return movies;
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
