/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

import java.util.ArrayList;
import java.util.List;
import movieexamproject.be.Category;
import movieexamproject.dal.CategoryDBDAO;
import movieexamproject.dal.MovieDBDAO;

/**
 *
 * @author XMdag
 */
public class BLLManager implements Interface{
    
    CategoryDBDAO cat= new CategoryDBDAO();
    MovieDBDAO mov= new MovieDBDAO();
    List<Category> categorylist = new ArrayList();

    public BLLManager() {
        init();
    }
    

    @Override
    public List<Category> getAllCatergories() {
        return categorylist;
    }
    public void init ()
    {   
        categorylist = cat.getAllCatergories();
    }
    
}
