/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieexamproject.be.Movie;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

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
    
//    public List<Movie> getAllMovies()
//    {
//         try(Connection con=ds.getConnection()) {
//            String sql = "SELECT * FROM Movie";
//            List<Movie> movies = new ArrayList();
//            Statement s= con.createStatement();
//            ResultSet r = s.executeQuery(sql);
//            while(r.next())
//            {
//                int id =r.getInt("id");
//                String name=r.getString("name");
//                float rating=r.getFloat("rating");
//                String filelink = r.getString("filelink");
//                Date lastview = (r.getDate("lastview"));
//                Movie movie = new Movie(id,name,rating,filelink,lastview);
//                movies.add(movie);
//                
//            } 
//            return movies;
//            
//        } catch (SQLServerException ex) {
//            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(MovieDBDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//         return null;
//    }
}
