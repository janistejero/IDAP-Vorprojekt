/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Janis Tejero
 */
public class CalculatorController implements Initializable {

    @FXML
    private Button berechnenBtn;
    @FXML
    private Spinner<Double> nettogewinnSpn;
    @FXML
    private Spinner<Double> aktienanzahlSpn;
    @FXML
    private Spinner<Double> partizipationskapitalSpn;
    @FXML
    private Spinner<Double> gesReservernSpn;
    @FXML
    private Spinner<Double> erfolgvortragSpn;
    @FXML
    private Label errorLbl;
    @FXML
    private Menu menuRechner;
    @FXML
    private Menu menuHilfe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLbl.setVisible(false);

        berechnenBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("src\\gewinnverteiler\\View\\Result.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CalculatorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void berechnen(ActionEvent event) {
        prüfeInput(nettogewinnSpn.getValue(), aktienanzahlSpn.getValue(), partizipationskapitalSpn.getValue(), gesReservernSpn.getValue(), erfolgvortragSpn.getValue());
    }

    private boolean prüfeInput(double nettogewinn, double aktienanzahl, double partizipationskapital, double gesReserven, double erfolgvortrag) {
        boolean valid = false;
        if (aktienanzahl < 0 || aktienanzahl == 0) {
            errorLbl.setText("Die Aktienanzahl kann als AG nicht unter 0 sein.");
            errorLbl.setVisible(true);
        }

        return true;
    }

    @FXML
    private void goToRechner(ActionEvent event) {

    }

    @FXML
    private void goToResultat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Result.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
    }

}
