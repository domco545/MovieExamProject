/*
 * This window is for adding a cattegory to our category list by end-user.
 */
package movieexamproject.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author Gentlemen
 */
public class AddCategoryController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField nameText;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;

    Interface in = new BllManager();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCategory(ActionEvent event) {
        if(nameText.getText() == null || nameText.getText().trim().isEmpty()){
            errorLabel.setText("Please set the name");
        }else{
            in.addCategory(nameText.getText());
            cancel(event);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
