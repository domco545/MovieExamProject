/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieexamproject.be.Movie;

/**
 *
 * @author narma
 */
public class MovieDBDAO {
    
    private SQLServerDataSource ds;
    
    public MovieDBDAO(){     
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunesCSe19B3");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
   /* public List<Movie> getAllMovies()
    {
         try(Connection con=ds.getConnection()) {
            String sql = "";
             
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return null;
    }*/
}
