/*
 * Thsi window is for adding movie to the list by name and filepath.
 */
package movieexamproject.gui.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @Gentlemen
 */
public class AddMovieController implements Initializable {

    @FXML
    private TextField movieName;
    @FXML
    private TextField moviePath;
    @FXML
    private Button addMovie;
    @FXML
    private Button cancel;
    @FXML
    private Button chooseBtn;
    @FXML
    private ChoiceBox<Category> choicebox;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private ListView<Category> categoryView;
    @FXML
    private Label errorLabel;
    
    private ObservableList<Category> obsSelected = FXCollections.observableArrayList();
    private ObservableList<Category> obsChoicebox = FXCollections.observableArrayList();
    Interface in = new BllManager();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addMovie(ActionEvent event) {
        if (chceckIfReady()) {
            String name = movieName.getText();
            String path = moviePath.getText();
            ArrayList<Category> selected = new ArrayList<Category>(obsSelected);
            in.addMovie(name, path, selected);
            cancel(event);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void getPath(ActionEvent event) {
        //getting file path and chechking if extensions match requirements
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        
        if(file.getAbsolutePath().endsWith(".mp4") || file.getAbsolutePath().endsWith(".mpeg4")){
            
            moviePath.setText(file.getAbsolutePath()); 
        }else{
             errorLabel.setText("Please select an mp4 or mpeg4 file");    
        }    
    }

    @FXML
    private void addToList(ActionEvent event) {
        //adding from choice box to selected list
        Category temp = choicebox.getSelectionModel().getSelectedItem();
        obsSelected.add(temp);
        obsChoicebox.remove(temp);
        categoryView.setItems(obsSelected);
        choicebox.getItems().clear();
        choicebox.getItems().addAll(obsChoicebox);
        choicebox.getSelectionModel().selectFirst();
        if (obsChoicebox.isEmpty()) {
            addCategoryBtn.setDisable(true);
        }
    }
    
    private boolean chceckIfReady(){
        //checking if every field is filled
        if(moviePath.getText() == null || moviePath.getText().trim().isEmpty()){
            errorLabel.setText("Please choose the file path");
            return false;
        }else 
        if(movieName.getText() == null || movieName.getText().trim().isEmpty()){
            errorLabel.setText("Please enter the movie name");
            return false;
       }else
        if(obsSelected.isEmpty()){
            errorLabel.setText("Please select at least one category");
            return false;
        }
        return true;
    }
    
    public void acceptCategories(ObservableList<Category> obsCategory){
        ArrayList<Category> temp = new ArrayList<Category>(obsCategory); //had to do that becasue observable list were somehow connected 
        temp.remove(0); //removes all category from selection
        obsChoicebox = FXCollections.observableArrayList(temp);
        choicebox.getItems().addAll(obsChoicebox);
        choicebox.getSelectionModel().selectFirst();
    }
}
