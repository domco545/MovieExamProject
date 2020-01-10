/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

import java.util.ArrayList;
import java.util.List;
import movieexamproject.be.Category;

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
     
}
