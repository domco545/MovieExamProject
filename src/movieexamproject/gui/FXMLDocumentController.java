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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author Martin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<Movie> tableView;
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
    private Button removeCatBtn;
    @FXML
    private Button removeMovieBtn;
    @FXML
    private Button addMovieBtn;
    @FXML
    private ListView<Category> categoryList;
     
    Interface in = new BllManager();
    private ObservableList<Category> obsCategories = FXCollections.observableArrayList(in.getAllCatergories());
    @FXML
    private Button editMovie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       categoryList.setItems(obsCategories);
    }    
    


    @FXML
    private void openAddMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/addMovie.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        AddMovieController amc = loader.getController();
        amc.acceptCategories(obsCategories);
                    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rateBtnAction(ActionEvent event) {
    }

    @FXML
    private void playBtnAction(ActionEvent event) {
    }

    @FXML
    private void searchBtnAction(ActionEvent event) {
    }

    @FXML
    private void addCategory(ActionEvent event) {
    }

    @FXML
    private void removeCategory(ActionEvent event) {
        int id = categoryList.getSelectionModel().getSelectedItem().getId();
        in.deleteCategory(id);
    }

    @FXML
    private void removeMovie(ActionEvent event) {
        int id = tableView.getSelectionModel().getSelectedItem().getId();
        in.deleteMovie(id);
    }

    @FXML
    private void editMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/EditMovie.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        /*AddMovieController amc = loader.getController();
        amc.acceptCategories(obsCategories);*/
                    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    }
    

