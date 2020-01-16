/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import movieexamproject.be.Movie;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author XMdag
 */
public class OldMoviePopUpController implements Initializable {

    @FXML
    private Button deleteBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private ListView<Movie> oldMovies;
    @FXML
    private ListView<Movie> moviesToDelete;
    @FXML
    private Button AddMovieBtn;
    @FXML
    private Button RemoveMovieBtn;
    private ArrayList temp;
    private ObservableList<Movie> obsSelectedToRemove = FXCollections.observableArrayList();
    private ObservableList<Movie> obsSelected = FXCollections.observableArrayList();
     Movie movie;
     Interface in = new BllManager();
    @FXML
    private Label errorLbl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    public void acceptData(ObservableList<Movie> moviestodelete)
    {
        oldMovies.setItems(obsSelected);
        obsSelected.addAll(moviestodelete);
    }
    
    @FXML
    private void deleteBtn(ActionEvent event) {
        
        
        if(obsSelectedToRemove.isEmpty())
        {
            errorLbl.setText("Please select a movie or press cancel");
        }
        else
        {
            in.deleteAllMovies(obsSelectedToRemove);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        }
       
    }

    @FXML
    private void cancelBtn(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    private void addMovieAction(ActionEvent event) {
       obsSelectedToRemove.add(oldMovies.getSelectionModel().getSelectedItem());
       moviesToDelete.setItems(obsSelectedToRemove);
       obsSelected.remove(oldMovies.getSelectionModel().getSelectedItem());
       oldMovies.setItems(obsSelected);
    }

    @FXML
    private void removeMovieBtn(ActionEvent event) {
       //oldMovies.add(obsSelected.getSelectionModel().getSelectedItem());
       obsSelected.add(moviesToDelete.getSelectionModel().getSelectedItem());
       obsSelectedToRemove.remove(moviesToDelete.getSelectionModel().getSelectedItem());
       moviesToDelete.setItems(obsSelectedToRemove);
       
       oldMovies.setItems(obsSelected);
       
    }
    
    
}
