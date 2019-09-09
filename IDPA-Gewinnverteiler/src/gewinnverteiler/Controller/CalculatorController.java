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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    
    private ResultController resultController;

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
        erfolgTxt.setText("10000");
        aktienkapitalTxt.setText("100000");
        partizipationskapitalTxt.setText("10000");
        gesReservenTxt.setText("10000");
        erfolgvortragTxt.setText("10000");
        zielDividendeTxt.setText("5");
    }

    @FXML
    private void berechnen(ActionEvent event) {

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
        } else {
            System.out.println("Keine Dividende, Superdividende oder Reservenzuweisung, da ein Bilanzverlust vorhanden ist.");
        }

        System.out.println("Gesetzliche Reserven jetzt: " + gesReserven);

        double volleGrundDividende = (partizipationskapital + aktienkapital) / 100 * 5;

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
        System.out.println("Superdividende: " + superdividende);
        SceneChanger.getInstance().loadFXML("View/Result.fxml", rootpane);
    }

    @FXML
    private void goToResultat(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Result.fxml", rootpane);
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
        
    }

    
}
