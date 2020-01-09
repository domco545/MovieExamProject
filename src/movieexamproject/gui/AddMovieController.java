/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import movieexamproject.be.Category;

/**
 * FXML Controller class
 *
 * @author XMdag
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
    
    private boolean readyToSave;
    @FXML
    private Button chooseBtn;
    @FXML
    private ChoiceBox<?> choicebox;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private ListView<Category> categoryView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addSong(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void getPath(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        
        if(file.getAbsolutePath().endsWith(".mp4") || file.getAbsolutePath().endsWith(".mpeg4")){
            
            moviePath.setText(file.getAbsolutePath());
            
            readyToSave = true;

            
        }else{
            readyToSave = false;
             moviePath.setText("Please select an mp4 or mpeg4 file");
            
        }
            
    }

    @FXML
    private void addToList(ActionEvent event) {
    }
    
}
