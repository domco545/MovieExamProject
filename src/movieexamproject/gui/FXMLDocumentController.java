/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movieexamproject.be.Category;
import movieexamproject.bll.BLLManager;

/**
 * FXML Controller class
 *
 * @author Martin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<?> tableView;
    @FXML
    private Button ratingBtn;
    @FXML
    private Button playBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addCatBtn;
    @FXML
    private Button editMovieBtn;
    @FXML
    private Button removeCatBtn;
    @FXML
    private Button removeMovieBtn;
    @FXML
    private Button addMovieBtn;

    BLLManager bllm = new BLLManager();
    private ObservableList<Category> obsCategories = FXCollections.observableArrayList(bllm.getAllCatergories());

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    private void openAddMovie(Stage stage) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("addMovie.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    }
    

