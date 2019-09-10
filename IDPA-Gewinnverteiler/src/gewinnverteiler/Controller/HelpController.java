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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Janis Tejero
 */
public class HelpController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Menu menuRechner;
    @FXML
    private WebView siteWebView;
    @FXML
    private Menu menuResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SceneChanger.getInstance().loadWebPage("/Resources/Hilfe.html", siteWebView);
    }

    @FXML
    private void goToRechner(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Calculator.fxml", rootpane);
    }

    @FXML
    private void goToResultat(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Result.fxml", rootpane);
    }

}
