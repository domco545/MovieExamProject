/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;
import movieexamproject.be.WebData;
import movieexamproject.dal.CategoryDBDAO;
import movieexamproject.dal.MovieDBDAO;
import movieexamproject.dal.OmdbAPI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Martin
 */
public class BllManager implements Interface{
     private MovieDBDAO moviedao = new MovieDBDAO();
     private CategoryDBDAO categorydbdao = new CategoryDBDAO();
     private OmdbAPI omdbapi = new OmdbAPI();
     JSONParser parser = new JSONParser();

     

    public BllManager() {
        
    }
     
     
    @Override
    public List<Category> getAllCatergories() {
       return categorydbdao.getAllCatergories();
       
    }

    @Override
    public void deleteCategory(int id) {
      categorydbdao.deleteCategory(id);
    }

    @Override
    public void addCategory(String name) {
     categorydbdao.addCategory(name);
    }

    @Override
    public void addMovie(String name, String path, ArrayList<Category> selectedCategory) {
     moviedao.addMovie(name, path, selectedCategory);
    }

    @Override
    public void deleteMovie(int id) {
     moviedao.deleteMovie(id);
    }

    @Override
    public void updateMovie(int id, String name, String filepath, ArrayList<Category> categories) {
        moviedao.updateMovie(id, name, filepath,categories);
    }

    @Override
    public void playedMovie(int id) {
        moviedao.playedMovie(id);
    }

    @Override
    public ArrayList<Category> getCategoriesByMovie(int movieid) {
      return categorydbdao.getCategoriesByMovie(movieid);
    }

    @Override
    public void fetchAllFromOmdb(ArrayList<Movie> allMovies) {
        ArrayList<WebData> webdata = new ArrayList();
        for (Movie movie : allMovies) {
            try {
                String s = omdbapi.sendGET(movie.getName());
                JSONObject json = (JSONObject) parser.parse(s);
                
                int imdbRating = (Integer) json.get("imdbRating");
                String imdb = (String) json.get("imdbID");
                String imdbLink = "https://www.imdb.com/title/"+imdb;
                
                WebData data = new WebData(movie.getId(),imdbRating,imdbLink);
                webdata.add(data);
                
            } catch (IOException ex) {
                Logger.getLogger(BllManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(BllManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //request to dal layer
    }
    
    public void fetchOneFromOmdb(Movie m) throws IOException, ParseException{
        String s = omdbapi.sendGET(m.getName());
        JSONObject json = (JSONObject) parser.parse(s);

        int imdbRating = (Integer) json.get("imdbRating");
        String imdb = (String) json.get("imdbID");
        String imdbLink = "https://www.imdb.com/title/"+imdb;
        WebData data = new WebData(m.getId(),imdbRating,imdbLink);
        
        //request to dal layer
    }

    @Override
    public void addRating(int movieid, float rating) {
        moviedao.addRating(movieid, rating);
    }
        
     
     
}
