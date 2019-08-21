/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import gewinnverteiler.StageSingleton;
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
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Menu menuResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLbl.setVisible(true);

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

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("View/Result.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            // Stage s = StageSingleton.getInstance().getStage();
            Stage s = (Stage) rootpane.getScene().getWindow();
            s.setScene(scene);

            /*
            AnchorPane pane = FXMLLoader.load(getClass().getResource("View/Result.fxml"));
            rootpane.getChildren().setAll(pane);*/
        } catch (IOException e) {
            System.out.println("Can't load new window");
        }
    }

    @FXML
    private void goToHilfe(ActionEvent event) {

    }

}
