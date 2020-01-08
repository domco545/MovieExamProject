/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;

/**
 *
 * @author narma
 */
public class CategoryDBDAO {
    
    private SQLServerDataSource ds;
    
    public CategoryDBDAO(){     
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MovieExamProject");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
    public static void main(String[] args) {
        CategoryDBDAO c=new CategoryDBDAO();
        System.out.println(c.getAllCatergories());
    }
    public List<Category> getAllCatergories()
    {
        try(Connection con=ds.getConnection()){
            String sql="SELECT * FROM Category WHERE id!=1";
                Statement s= con.createStatement();
                List<Category> categories=new ArrayList();
                ResultSet r = s.executeQuery(sql);
                Category categoryAll=new Category(1,"All");
                categories.add(categoryAll);
            while(r.next())
            {
                int id =r.getInt("id");
                String name=r.getString("name");
                Category category=new Category(id,name);
                categories.add(category);
            }
            
                for (Category category : categories) {
                    int catId = category.getId();

                    String sql2 = "SELECT Movie.id,Movie.name,Movie.filelink,Movie.rating,Movie.lastview,MoviesOnCategories.CategoryId,MoviesOnCategories.MovieId\n" +
                                  "FROM MoviesOnCategories\n" +
                                  "LEFT JOIN Movie ON MoviesOnCategories.MovieId = Movie.id\n" +
                                  "WHERE MoviesOnCategories.CategoryId=?";
                    PreparedStatement pstmt= con.prepareStatement(sql2);
                    pstmt.setInt(1, catId);
                    ResultSet rs = pstmt.executeQuery();
                    ArrayList<Movie> movies = new ArrayList();
                    while(rs.next())
                    {
                        int id =rs.getInt("id");
                        String name=rs.getString("name");
                        float rating=rs.getFloat("rating");
                        String filelink = rs.getString("filelink");
                        Date lastview = (rs.getDate("lastview"));
                        Movie movie = new Movie(id,name,rating,filelink,lastview);
                        movies.add(movie);
                    } 
                    category.acceptMovies(movies);
                }
            return categories;
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
}
