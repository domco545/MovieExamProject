/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.json.simple.parser.ParseException;

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
    @FXML
    private Button detailBtn;
    
    Interface in = new BllManager();
    private ObservableList<Category> obsCategories = FXCollections.observableArrayList(in.getAllCatergories());
    private ObservableList<Movie> obsMovie = FXCollections.observableArrayList();
    private ObservableList<Movie> searchResults = FXCollections.observableArrayList();
    private ObservableList<Movie> obsMoviesToDelete = FXCollections.observableArrayList(in.getMoviesToDelete());
    private boolean searching;

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
        categoryList.getSelectionModel().selectFirst();
        oldMoviePopUp();
    }    
    


    @FXML
    private void openAddMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/addMovie.fxml"));
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
        if(tableView.getSelectionModel().getSelectedItem()==null)
        {
            label.setText("Please select a movie");
            init();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/AddRating.fxml"));
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
        if(searching == false){        
            if(categoryList.getSelectionModel().getSelectedItem().getId()==1){
                searchResults=FXCollections.observableArrayList(in.getMoviesByTilteAndRatings(searchField.getText())) ;
                tableView.setItems(searchResults);
            }
            else{
                searchResults = FXCollections.observableArrayList(in.getMoviesByTilteAndRatingsOnCategory(searchField.getText(),categoryList.getSelectionModel().getSelectedItem().getId()));
                tableView.setItems(searchResults);
            }
            searchBtn.setText("Cancel");
            searching = true;
        }else{
            tableView.setItems(obsMovie);
            searchBtn.setText("Search");
            searchField.clear();
            searching = false;
        }
    }

    @FXML
    private void addCategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/AddCategory.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/RemoveCategory.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/RemoveMovie.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/EditMovie.fxml"));
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
    public void oldMoviePopUp()
    {
      if(in.getMoviesToDelete().equals(null))
        {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/OldMoviePopUp.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        
        OldMoviePopUpController c = loader.getController();
        c.acceptData(obsMoviesToDelete);
                    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                init();
            }
        });
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        } 
    }
    public void init(){
        obsCategories = FXCollections.observableArrayList(in.getAllCatergories());
        categoryList.setItems(obsCategories);
        categoryList.getSelectionModel().select(0);
        obsMovie = FXCollections.observableArrayList(categoryList.getSelectionModel().getSelectedItem().getAllMovies());
        tableView.setItems(obsMovie);
        
        
        
        
    }

    @FXML
    private void openDetail(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movieexamproject/gui/view/MovieInfo.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        MovieInfoController mic = loader.getController();
        mic.getImdbLink(tableView.getSelectionModel().getSelectedItem().getImdbLink());
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fetchOneBtn(ActionEvent event) throws IOException, ParseException {
        Movie m = tableView.getSelectionModel().getSelectedItem();
        
        in.fetchOneFromOmdb(m);
    }

    @FXML
    private void fetchAllBtn(ActionEvent event) {
        in.fetchAllFromOmdb(categoryList.getItems().get(0).getAllMovies());
    }
}
    

