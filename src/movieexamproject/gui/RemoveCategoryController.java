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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import movieexamproject.be.Category;
import movieexamproject.bll.BllManager;
import movieexamproject.bll.Interface;

/**
 * FXML Controller class
 *
 * @author saraf
 */
public class RemoveCategoryController implements Initializable {

    @FXML
    private Button deleteBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label nameLbl;
    @FXML
    private Label errorLbl;
    
    Interface in = new BllManager();
    Category category;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void delete(ActionEvent event) {
        if(category.getId() == 1){
            errorLbl.setText("You cannot remove All category");
        }else{
        in.deleteCategory(category.getId());
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    public void acceptCategory(Category category){
        this.category = category;
        nameLbl.setText(this.category.getName());
    }
    
}
