/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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
    @FXML
    private TextField filepathTxt;
    @FXML
    private Button filepathBtn;
    @FXML
    private Label errorLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void removeCategory(ActionEvent event) {
        Category temp =   ListView.getSelectionModel().getSelectedItem();
        obsSelected.remove(temp);
        ListView.setItems(obsSelected);
        obsChoicebox.add(temp);
        choicebox.getItems().addAll(obsChoicebox);
        
    }

    @FXML
    private void addCategory(ActionEvent event) {
      Category temp =    choicebox.getSelectionModel().getSelectedItem();
      obsSelected.add(temp);
      obsChoicebox.remove(temp);
      ListView.setItems(obsSelected);
      choicebox.getItems().clear();
      choicebox.getItems().addAll(obsChoicebox);
      if (obsChoicebox.isEmpty()) {
            addCategoryBtn.setDisable(true);
        }
      
    }

    @FXML
    private void confirmAndSave(ActionEvent event) {
        String name = editMovietxt.getText();
            String filepath = filepathTxt.getText();
            ArrayList<Category> selected = new ArrayList<Category>(obsSelected);
            in.updateMovie(movie.getId(),name,filepath ,selected);
            cancel(event);
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        
        if(file.getAbsolutePath().endsWith(".mp4") || file.getAbsolutePath().endsWith(".mpeg4")){
            
            filepathTxt.setText(file.getAbsolutePath()); 
        }else{
             errorLabel.setText("Please select an mp4 or mpeg4 file");    
        }    
    }
    
  
    
}
