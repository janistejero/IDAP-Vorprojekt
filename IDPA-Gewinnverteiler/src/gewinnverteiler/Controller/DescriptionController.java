/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import gewinnverteiler.SceneChanger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Janis Tejero
 */
public class DescriptionController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Menu menuRechner;
    @FXML
    private Menu menuResultat;
    @FXML
    private Menu menuHilfe;
    @FXML
    private WebView siteWebView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SceneChanger.getInstance().loadWebPage("/Resources/Beschreibung.html", siteWebView);
    }    

    
    @FXML
    private void goToRechner(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Calculator.fxml", rootpane);
    }

    @FXML
    private void goToResultat(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Result.fxml", rootpane);
    }
    
    
    @FXML
    private void goToHilfe(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Help.fxml", rootpane);
    }
    
}
