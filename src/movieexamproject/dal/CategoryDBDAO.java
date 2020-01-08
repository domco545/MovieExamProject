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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieexamproject.be.Category;

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
            while(r.next())
            {
                int id =r.getInt("id");
                String name=r.getString("name");
                Category category=new Category(id,name);
                categories.add(category);
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
