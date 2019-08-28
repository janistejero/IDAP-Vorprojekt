/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
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
    private Label errorLbl;
    @FXML
    private Menu menuHilfe;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Menu menuResultat;
    @FXML
    private TextField erfolgTxt;
    @FXML
    private TextField aktienkapitalTxt;
    @FXML
    private TextField partizipationskapitalTxt;
    @FXML
    private TextField gesReservenTxt;
    @FXML
    private TextField erfolgvortragTxt;
    @FXML
    private TextField zielDividendeTxt;

    // variablen
    private double reservenzuweisung;
    private double neuererfolgvortag;
    private double bilanzerfolg;
    private double zwischenresultat;
    private double erforderlicheReserve;
    private double erfolg;
    private double aktienkapital;
    private double partizipationskapital;
    private double gesReserven;
    private double erfolgvortrag;
    private double gewuenschteDividende;
    private double dividende;
    private boolean dividendenAusschuettung = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //errorLbl.setVisible(false);

        /*
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
         */
        erfolgTxt.setText("10000");
        aktienkapitalTxt.setText("100000");
        partizipationskapitalTxt.setText("10000");
        gesReservenTxt.setText("10000");
        erfolgvortragTxt.setText("10000");
        zielDividendeTxt.setText("5");
    }

    @FXML
    private void berechnen(ActionEvent event) {

        /*
         while (erfolgTxt.getText().equals("") || aktienkapitalTxt.getText().equals("") || partizipationskapitalTxt.getText().equals("") || gesReservenTxt.getText().equals("") || erfolgvortragTxt.getText().equals("") || zielDividendeTxt.getText().equals("")) {
            errorLbl.setText("Value must be a number");
        }*/
        
        // lesen der Benutzereingaben
        erfolg = Double.valueOf(erfolgTxt.getText());
        aktienkapital = Double.valueOf(aktienkapitalTxt.getText());
        partizipationskapital = Double.valueOf(partizipationskapitalTxt.getText());
        gesReserven = Double.valueOf(gesReservenTxt.getText());
        erfolgvortrag = Double.valueOf(erfolgvortragTxt.getText());
        gewuenschteDividende = Double.valueOf(zielDividendeTxt.getText());

        if (aktienkapital < 0 || aktienkapital == 0) {
        }

        // verrechnung erfolg mit vortrag
        bilanzerfolg = erfolg + erfolgvortrag;

        erforderlicheReserve = ((aktienkapital + partizipationskapital) / 100) * 20;
        System.out.println("Bilanzerfolg: " + bilanzerfolg);

        // ausgleich mit reserven
        if (bilanzerfolg < 0 && gesReserven > bilanzerfolg) {
            gesReserven += bilanzerfolg;
            bilanzerfolg = 0;
            dividende = 0;
            dividendenAusschuettung = false;
        }

        if (dividendenAusschuettung) {
            if (gesReserven < erforderlicheReserve) {
                double zuweisungGesReserven = erfolg / 100 * 5;
                if (zuweisungGesReserven < 1) {
                    zuweisungGesReserven = 0;
                }
                bilanzerfolg -= zuweisungGesReserven;
                gesReserven += zuweisungGesReserven;
                System.out.println("1. gesetzliche Reserven: " + zuweisungGesReserven);
            }
        }

        System.out.println("Gesetzliche Reserven jetzt: " + gesReserven);

        double volleGrundDividende = partizipationskapital + aktienkapital / 100 * 5;

        if (volleGrundDividende < bilanzerfolg) {
            bilanzerfolg -= volleGrundDividende;
            dividende = volleGrundDividende;
            zwischenresultat = bilanzerfolg;
        } else if (volleGrundDividende > bilanzerfolg) {
            dividende = Math.floor(bilanzerfolg / (aktienkapital + partizipationskapital) * 100);
            dividende = ((aktienkapital + partizipationskapital) / 100) * dividende;
            bilanzerfolg -= dividende;
            zwischenresultat = bilanzerfolg;

        }
        System.out.println("Dividende: " + dividende);
        System.out.println("Zwischentotal: " + zwischenresultat);

        double superdividende = Math.floor(bilanzerfolg / (aktienkapital + partizipationskapital) * 0.011);
    }

    @FXML
    private void goToResultat(ActionEvent event) {
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
