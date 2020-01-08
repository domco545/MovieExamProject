/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void addMovie(String name, String path){
        try(Connection con = ds.getConnection()){
            String sql = "INSERT INTO Movie (name,filepath) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, path);
            pstmt.executeUpdate();
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
    
    public void updateMovie(int id, String name, float rating, String filepath){
        try(Connection con = ds.getConnection()){
            String sql = "UPDATE Movie SET name = ?, rating = ?, filepath = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setFloat(2, rating);
            pstmt.setString(3, filepath);
            pstmt.executeUpdate();
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
}
