/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class EditMovieController implements Initializable {

    @FXML
    private TextField editMovietxt;
    @FXML
    private ChoiceBox<Category> choicebox;
    @FXML
    private ListView<Category> ListView;
    @FXML
    private Button RemoveCategoryBtn;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button ConfirmBtn;
    @FXML
    private Button CancelBtn;
    Category category;
    Movie movie;
     private ObservableList<Category> obsSelected = FXCollections.observableArrayList();
    private ObservableList<Category> obsChoicebox = FXCollections.observableArrayList();
    private ArrayList<Category> temp;
    Interface in = new BllManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void removeCategory(ActionEvent event) {
    }

    @FXML
    private void addCategory(ActionEvent event) {
    }

    @FXML
    private void confirmAndSave(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
  
    public void acceptData(Movie movie, ObservableList<Category> allCategories)
    {
        this.movie= movie;
        editMovietxt.setText(movie.getName());
        
        temp = new ArrayList<Category>(allCategories);
        filterChoicebox();
    }
    public void filterChoicebox()
    {
        
        ArrayList<Category> currentCategories = in.getCategoriesByMovie(movie.getId());
        obsSelected = FXCollections.observableArrayList(currentCategories);
        ListView.setItems(obsSelected);
        
        for (Category currentCategory : currentCategories) {
            for (Category cat : temp) {
                if(currentCategory.getId()==cat.getId())
                    temp.remove(cat);
            }
        }
        obsChoicebox = FXCollections.observableArrayList(temp);
        choicebox.getItems().addAll(obsChoicebox);
        
    }
}
