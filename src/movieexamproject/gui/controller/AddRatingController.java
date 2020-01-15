/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import movieexamproject.be.Movie;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class AddRatingController implements Initializable {

    @FXML
    private Label currentMovieLbl;
    @FXML
    private TextField ratingField;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;
    Movie movie;
    @FXML
    private Label errorlbl;
    /**
     * Initializes the controller class.
     */
    Interface in = new BllManager();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void acceptData(Movie movie)
    {
        this.movie=movie;
        currentMovieLbl.setText(movie.getName());
        ratingField.setText(Float.toString(movie.getRating()));
    }
    @FXML
    private void addRating(ActionEvent event) {
        float rating = 0;
        
        //check if input can be converted to float
        //maybe change this to textfromatter in future
        try{
             rating = Float.parseFloat( ratingField.getText());
             rating = (float) (Math.round(rating*10)/10.0);
        }catch(Exception e){
            errorlbl.setText("invalid input");
            return;
        }
        
        if (rating <1 || rating >10)
        {
            errorlbl.setText("Invalid rating. Please rate between 1-10.");
        }
        else 
        {   
            in.addRating(movie.getId(),rating);
            cancelEvent(event);
        }
    }

    @FXML
    private void cancelEvent(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
