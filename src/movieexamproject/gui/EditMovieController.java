/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class EditMovieController implements Initializable {

    @FXML
    private TextField editMovietxt;
    @FXML
    private ChoiceBox<?> choicebox;
    @FXML
    private ListView<?> ListView;
    @FXML
    private Button RemoveCategoryBtn;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button ConfirmBtn;
    @FXML
    private Button CancelBtn;

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
    
}
