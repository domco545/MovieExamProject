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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import movieexamproject.be.Category;
import movieexamproject.be.Movie;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author domin
 */
public class AdvancedSearchController implements Initializable {

    @FXML
    private TableView<Movie> movieTableview;
    @FXML
    private TextField searchTxt;
    @FXML
    private Button SearchBtn;
    @FXML
    private ListView<Category> selectedCategoryLst;
    @FXML
    private ChoiceBox<Category> categoryChoicebox;
    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Label errorLbl;
    @FXML
    private TableColumn<Movie, String> collumTitle;
    @FXML
    private TableColumn<Movie, Float> collumRating;
    @FXML
    private TableColumn<Movie, Date> collumLastviewed;
    @FXML
    private TableColumn<Movie, Float> collumImdbRating;
    
    private Interface in = new BllManager();
    private boolean inSearch;
    private ObservableList<Category> obsChoicebox = FXCollections.observableArrayList();
    private ObservableList<Category> obsSelected = FXCollections.observableArrayList();
    private ObservableList<Movie> obsMovies = FXCollections.observableArrayList();


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       collumTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
       collumRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
       collumLastviewed.setCellValueFactory(new PropertyValueFactory<>("lastview"));
       collumImdbRating.setCellValueFactory(new PropertyValueFactory<>("ImdbRating"));
    }    

    @FXML
    private void search(ActionEvent event) {
        if(obsSelected.isEmpty()){
            errorLbl.setText("Please select at least one category");
        }else{
            errorLbl.setText("");
            ArrayList<Category> selectedCat = new ArrayList<>(obsSelected);
            obsMovies = FXCollections.observableArrayList(in.advancedSearch(searchTxt.getText(), selectedCat));
            movieTableview.setItems(obsMovies);

            SearchBtn.setText("Cancel");
        }
    }

    @FXML
    private void addCategory(ActionEvent event) {
        Category temp = categoryChoicebox.getSelectionModel().getSelectedItem();
        obsSelected.add(temp);
        obsChoicebox.remove(temp);
        selectedCategoryLst.setItems(obsSelected);
        categoryChoicebox.getItems().clear();
        categoryChoicebox.getItems().addAll(obsChoicebox);
        categoryChoicebox.getSelectionModel().selectFirst();
        
        if (obsChoicebox.isEmpty()) {
            addBtn.setDisable(true);
        }
    }

    @FXML
    private void removeCategory(ActionEvent event) {
        Category temp = selectedCategoryLst.getSelectionModel().getSelectedItem();
        obsSelected.remove(temp);
        selectedCategoryLst.setItems(obsSelected);
        obsChoicebox.add(temp);
        categoryChoicebox.getItems().clear();
        categoryChoicebox.getItems().addAll(obsChoicebox);
        categoryChoicebox.getSelectionModel().selectFirst();
    }
    
    public void acceptData(ObservableList<Category> categories){
        ArrayList<Category> temp = new ArrayList<Category>(categories);
        temp.remove(0); //remove category All
        obsChoicebox = FXCollections.observableArrayList(temp);
        categoryChoicebox.setItems(obsChoicebox);
        categoryChoicebox.getSelectionModel().selectFirst();
    }

    @FXML
    private void playMovie(ActionEvent event) throws IOException {
                
       String path = movieTableview.getSelectionModel().getSelectedItem().getFilelink();

        
        File file = new File(path);
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }
}
