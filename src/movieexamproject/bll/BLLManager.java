/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.bll;

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

    @Override
    public List<Category> getAllCatergories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
