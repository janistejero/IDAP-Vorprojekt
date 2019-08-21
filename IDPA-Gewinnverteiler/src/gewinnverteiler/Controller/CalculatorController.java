/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
        errorLbl.setVisible(false);

        menuResultat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToResultat(event);
            }
        });

        menuHilfe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToHilfe(event);
            }
        });
        
         // Value factory.
        final int initialValue = 1000;
        SpinnerValueFactory<Double> valueFactory1 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100000000, initialValue);
        SpinnerValueFactory<Double> valueFactory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000000, initialValue);
        SpinnerValueFactory<Double> valueFactory3 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100000000, initialValue);
        SpinnerValueFactory<Double> valueFactory4 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000000, initialValue);
        SpinnerValueFactory<Double> valueFactory5 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 10000000, initialValue);
        
        nettogewinnSpn.setValueFactory(valueFactory1);
        aktienanzahlSpn.setValueFactory(valueFactory2);
        partizipationskapitalSpn.setValueFactory(valueFactory3);
        gesReservernSpn.setValueFactory(valueFactory4);
        erfolgvortragSpn.setValueFactory(valueFactory5);
        
        nettogewinnSpn.getStyleClass().clear();
        aktienanzahlSpn.getStyleClass().clear();
        partizipationskapitalSpn.getStyleClass().clear();
        gesReservernSpn.getStyleClass().clear();
        erfolgvortragSpn.getStyleClass().clear();
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
    private void goToResultat(Event event) {
        loadFXML("Result.fxml");
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
        loadFXML("...");
    }

    private void loadFXML(String name) {
        try {
            Stage stage = new Stage();
            Stage oldstage = (Stage) rootpane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(name);
            stage.show();
            oldstage.close();

        } catch (IOException e) {
            System.out.println("Can't load new window:" + name + " because of:");
            e.printStackTrace();
        }
    }

}
