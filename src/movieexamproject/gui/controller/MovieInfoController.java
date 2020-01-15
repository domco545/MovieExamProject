/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author domin
 */
public class MovieInfoController implements Initializable {

    @FXML
    private WebView webView;
    
    private WebEngine engine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = webView.getEngine();
    }  
    
    public void getImdbLink(String link){
        if(link.isEmpty()){
            engine.loadContent("<html><body><h1>Data not fetched or movie name does not corespond to any movie in imdb</h1></body></html>");
        }else{
            engine.load(link);
        }
    }
}
