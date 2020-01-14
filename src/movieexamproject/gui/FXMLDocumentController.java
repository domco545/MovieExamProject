/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    @FXML
    private Button editMovie;
    @FXML
    private TableColumn<Movie, String> collumTitle;
    @FXML
    private TableColumn<Movie, Float> collumRating;
    @FXML
    private TableColumn<Movie, Date> collumLastViewed;
     
    Interface in = new BllManager();
    private ObservableList<Category> obsCategories = FXCollections.observableArrayList(in.getAllCatergories());
    private ObservableList<Movie> obsMovie = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       categoryList.setItems(obsCategories);
       
       collumTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
       collumRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
       collumLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastview"));
       
        categoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
        @Override
        public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
             obsMovie = FXCollections.observableArrayList(categoryList.getSelectionModel().getSelectedItem().getAllMovies());
             tableView.setItems(obsMovie);
            }
        });
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
        
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });
    }

    @FXML
    private void rateBtnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/AddRating.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        AddRatingController arc = loader.getController();
        arc.acceptData(tableView.getSelectionModel().getSelectedItem());
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });   
    }

    @FXML
    private void playBtnAction(ActionEvent event) throws IOException {
        
       String path = tableView.getSelectionModel().getSelectedItem().getFilelink();

        
        File file = new File(path);
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }

    

    @FXML
    private void searchBtnAction(ActionEvent event) {
    }

    @FXML
    private void addCategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/AddCategory.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });
    }

    @FXML
    private void removeCategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/RemoveCategory.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        RemoveCategoryController rcc = loader.getController();
        rcc.acceptCategory(categoryList.getSelectionModel().getSelectedItem());
                    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });
    }

    @FXML
    private void removeMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/RemoveMovie.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        RemoveMovieController rmc = loader.getController();
        rmc.acceptMovie(tableView.getSelectionModel().getSelectedItem());
                    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });
    }

    @FXML
    private void editMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/EditMovie.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        EditMovieController emc = loader.getController();
        emc.acceptData(tableView.getSelectionModel().getSelectedItem(), obsCategories);
                    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });
    }
    
    public void init(){
        obsCategories = FXCollections.observableArrayList(in.getAllCatergories());
        categoryList.setItems(obsCategories);
        categoryList.getSelectionModel().select(0);
        obsMovie = FXCollections.observableArrayList(categoryList.getSelectionModel().getSelectedItem().getAllMovies());
        tableView.setItems(obsMovie);
    }
}
    

