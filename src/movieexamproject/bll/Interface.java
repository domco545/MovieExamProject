/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

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
      public void addMovie(String name, String path);
      public void deleteMovie(int id);
      public void updateMovie(int id, String name, float rating, String filepath);
      public void playedMovie(int id);
     
}
