/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.be;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author narma
 */
public class Movie {
    
    private int id;
    private String name;
    private float rating;
    private String filelink;
    private Date lastview;

    public Movie(int id, String name, float rating, String filelink, Date lastview)
    {
      this.id=id;
      this.name= name;
      this.rating=rating;
      this.filelink=filelink;
      this.lastview=lastview;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public Date getLastview() {
        return lastview;
    }

    public void setLastview(Date lastview) {
        this.lastview = lastview;
    }

    @Override
    public String toString() {
        return name+" "+lastview; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
