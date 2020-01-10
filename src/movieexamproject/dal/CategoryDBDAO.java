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

    public List<Category> getAllCatergories()
    {
        try(Connection con=ds.getConnection()){
            String sql="SELECT * FROM Category";
                Statement s= con.createStatement();
                ArrayList<Category> categories = new ArrayList();
                ResultSet r = s.executeQuery(sql);
            while(r.next())
            {
                int id =r.getInt("id");
                String name=r.getString("name");
                Category category=new Category(id,name);
                categories.add(category);
            }
            
                for (Category category : categories) {
                    int catId = category.getId();
                    ArrayList<Movie> movies = new ArrayList();

                    if (catId==1) {
                        String sqlAll = "SELECT * FROM Movie";
                        Statement st = con.createStatement();
                        ResultSet rsAll = st.executeQuery(sqlAll);
                        while(rsAll.next()){
                            int id =rsAll.getInt("id");
                            String name=rsAll.getString("name");
                            float rating=rsAll.getFloat("rating");
                            String filelink = rsAll.getString("filepath");
                            Date lastview = (rsAll.getDate("lastview"));
                            Movie movie = new Movie(id,name,rating,filelink,lastview);
                            movies.add(movie);
                        }
                        
                    }else{

                    String sql2 = "SELECT Movie.id,Movie.name,Movie.filepath,Movie.rating,Movie.lastview,MoviesOnCategories.CategoryId,MoviesOnCategories.MovieId\n" +
                                  "FROM MoviesOnCategories\n" +
                                  "LEFT JOIN Movie ON MoviesOnCategories.MovieId = Movie.id\n" +
                                  "WHERE MoviesOnCategories.CategoryId=?";
                    PreparedStatement pstmt= con.prepareStatement(sql2);
                    pstmt.setInt(1, catId);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next())
                    {
                        int id =rs.getInt("id");
                        String name=rs.getString("name");
                        float rating=rs.getFloat("rating");
                        String filelink = rs.getString("filepath");
                        Date lastview = (rs.getDate("lastview"));
                        Movie movie = new Movie(id,name,rating,filelink,lastview);
                        movies.add(movie);
                    } 
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
    
    public void deleteCategory(int id){
        try(Connection con=ds.getConnection()){
            String sql = "DELETE FROM Category WHERE id = ?;"
                       + "DELETE FROM MoviesOnCategories WHERE CategoryId = ?";
            PreparedStatement pstmt= con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();
        }   
        catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCategory(String name){
        try(Connection con = ds.getConnection()){
            String sql = "INSERT INTO Category (name) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Category> getCategoriesByMovie(int movieid)
    {
        try(Connection con = ds.getConnection()) {
            String sql="SELECT Category.id, Category.name, MoviesOnCategories.CategoryId FROM MoviesOnCategories  "
                    + "LEFT JOIN Category ON MoviesOnCategories.CategoryId=Category.id"
                    + "WHERE MoviesOnCategories.MovieId =?";
            ArrayList<Category> categories = new ArrayList();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, movieid);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                int id =rs.getInt("id");
                String name = rs.getString("name");
                Category category=new Category(id,name);
                categories.add(category);
            }
            return categories;
            
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
