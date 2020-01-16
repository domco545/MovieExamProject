/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;
import org.json.simple.parser.ParseException;

/**
 *
 * @author narma
 */
public interface Interface {
     public List<Category> getAllCatergories();  
     public void deleteCategory(int id);
      public void addCategory(String name);
      public void addMovie(String name, String path, ArrayList<Category> selectedCategory);
      public void deleteMovie(int id);
      public void updateMovie(int id, String name, String filepath,ArrayList<Category> categories);
      public void playedMovie(int id);
      public ArrayList<Category> getCategoriesByMovie(int movieid);
      public void fetchAllFromOmdb(ArrayList<Movie> allMovies);
      public void fetchOneFromOmdb(Movie m)throws IOException, ParseException;
      public void addRating(int movieid, float rating);
      public ArrayList<Movie> getMoviesByTilteAndRatings(String query);
      public ArrayList<Movie> getMoviesByTilteAndRatingsOnCategory(String query, int categoryId);
      public ArrayList<Movie> getMoviesToDelete();

    public void deleteAllMovies(List<Movie> obsSelected);
}
