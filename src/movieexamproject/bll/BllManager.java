/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

import java.util.ArrayList;
import java.util.List;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;
import movieexamproject.dal.CategoryDBDAO;
import movieexamproject.dal.MovieDBDAO;

/**
 *
 * @author Martin
 */
public class BllManager implements Interface{
     private MovieDBDAO moviedao = new MovieDBDAO();
     private CategoryDBDAO categorydbdao = new CategoryDBDAO();

     

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
    public void updateMovie(int id, String name, float rating, String filepath) {
        moviedao.updateMovie(id, name, rating, filepath);
    }

    @Override
    public void playedMovie(int id) {
        moviedao.playedMovie(id);
    }

    @Override
    public ArrayList<Category> getCategoriesByMovie(int movieid) {
      return categorydbdao.getCategoriesByMovie(movieid);
    }
        
     
     
}
