/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import gewinnverteiler.SceneChanger;
import gewinnverteiler.ValueHolder;
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
    private double zweiteGesReservenZuweisung;
    private double superdividende;
    private double zuweisungGesReserven;
    private int emptyCounter = 0;
    private boolean dividendenAusschuettung = true;
    private boolean superdividendeAusschuettung = false;
    private boolean validInput = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLbl.setVisible(false);
        erfolgTxt.setText("10000");
        aktienkapitalTxt.setText("100000");
        partizipationskapitalTxt.setText("0");
        gesReservenTxt.setText("10000");
        erfolgvortragTxt.setText("10000");
        zielDividendeTxt.setText("5");
    }

    @FXML
    private void berechnen(ActionEvent event) {
        
        emptyCounter = 0;
        
        // form validation
        if (erfolgTxt.getText().isEmpty()) {
            errorLbl.setText("Feld Erfolg kann nicht leer sein.");
            erfolgTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }
        if (aktienkapitalTxt.getText().isEmpty()) {
            errorLbl.setText("Feld Aktienkapital kann nicht leer sein.");
            aktienkapitalTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (partizipationskapitalTxt.getText().isEmpty()) {
            errorLbl.setText("Feld Partizipationskapital kann nicht leer sein.");
            partizipationskapitalTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (gesReservenTxt.getText().isEmpty()) {
            errorLbl.setText("Feld Gesetzliche Reserve kann nicht leer sein.");
            gesReservenTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (erfolgvortragTxt.getText().isEmpty()) {
            errorLbl.setText("Feld Erfolgvortrag kann nicht leer sein.");
            erfolgvortragTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (zielDividendeTxt.getText().isEmpty()) {
            errorLbl.setText("Feld Ziel Dividende kann nicht leer sein.");
            zielDividendeTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (emptyCounter > 1) {
            errorLbl.setText("Bitte die leeren Felder ausfüllen");
        }

        if (emptyCounter > 0) {
            validInput = false;
            errorLbl.setVisible(true);
        } else {
            validInput = true;
        }

        // Berechnung nur starten wenn Eingaben valide sind
        if (validInput) {
            // lesen der Benutzereingaben
            erfolg = Double.valueOf(erfolgTxt.getText());
            aktienkapital = Double.valueOf(aktienkapitalTxt.getText());
            partizipationskapital = Double.valueOf(partizipationskapitalTxt.getText());
            gesReserven = Double.valueOf(gesReservenTxt.getText());
            erfolgvortrag = Double.valueOf(erfolgvortragTxt.getText());
            gewuenschteDividende = Double.valueOf(zielDividendeTxt.getText());

            // verrechnung erfolg mit vortrag
            bilanzerfolg = erfolg + erfolgvortrag;

            erforderlicheReserve = ((aktienkapital + partizipationskapital) / 100) * 20;
            System.out.println("Bilanzerfolg: " + bilanzerfolg);

            // verlustvortrag
            if (bilanzerfolg < 0) {
                // deckbar mit reserven
                if (gesReserven >= (bilanzerfolg * -1)) {
                    gesReserven += bilanzerfolg;
                    bilanzerfolg = 0;
                } else {
                    bilanzerfolg += gesReserven;
                    gesReserven = 0;
                }
                dividende = 0;
                dividendenAusschuettung = false;
            }

            ValueHolder.getInstance().setBilanzerfolg(bilanzerfolg);

            if (dividendenAusschuettung) {
                if (gesReserven < erforderlicheReserve) {
                    double differenz = erforderlicheReserve - gesReserven;
                    zuweisungGesReserven = erfolg / 100 * 5;
                    if (differenz < zuweisungGesReserven) {
                        zuweisungGesReserven = differenz;
                    }

                    if (zuweisungGesReserven < 1) {
                        zuweisungGesReserven = 0;
                    }
                    bilanzerfolg -= zuweisungGesReserven;
                    gesReserven += zuweisungGesReserven;
                    System.out.println("An 1. gesetzliche Reserven: " + zuweisungGesReserven);
                }
            } else {
                System.out.println("Keine Dividende, Superdividende oder Reservenzuweisung, da ein Bilanzverlust vorhanden ist.");
            }


            double volleGrundDividende = (partizipationskapital + aktienkapital) / 100 * 5;

            // 5% grösser als der bilanzerfolg *nach verrechnung* der gesetzlichen zuweisung
            System.out.println("Bilanzerfolg: " + bilanzerfolg);
            if (volleGrundDividende < bilanzerfolg) {
                bilanzerfolg -= volleGrundDividende;
                dividende = volleGrundDividende;
                zwischenresultat = bilanzerfolg;
                superdividendeAusschuettung = true;
            } else if (volleGrundDividende > bilanzerfolg) {
                dividende = Math.floor(bilanzerfolg / (aktienkapital + partizipationskapital) * 100); // in Prozenten
                dividende = ((aktienkapital + partizipationskapital) / 100) * dividende; // in Franken
                bilanzerfolg -= dividende;
                zwischenresultat = bilanzerfolg;
            }

            if (superdividendeAusschuettung) {
                superdividende = Math.floor(zwischenresultat / ((aktienkapital + partizipationskapital) * 0.011)); // in Prozenten
                superdividende = ((aktienkapital + partizipationskapital) / 100) * superdividende;
                zweiteGesReservenZuweisung = (superdividende / 100) * 10;
                ValueHolder.getInstance().setZwischenresultat(zwischenresultat + dividende);
                zwischenresultat -= superdividende;
                zwischenresultat -= zweiteGesReservenZuweisung;
                System.out.println("Zweite Gesetzliche Reserve: " + zweiteGesReservenZuweisung);
            }

            System.out.println("Dividende: " + dividende);
            System.out.println("Zwischentotal: " + zwischenresultat);
            System.out.println("Superdividende: " + superdividende);

            // Zum ValueHolder schreiben
            updateValues();

            // weiterleitung zur Resultatseite
            SceneChanger.getInstance().loadFXML("View/Result.fxml", rootpane);
        }
    }

    private void updateValues() {
        ValueHolder.getInstance().setAktienkapital(aktienkapital);
        ValueHolder.getInstance().setErfolg(erfolg);
        ValueHolder.getInstance().setPartizipationskapital(partizipationskapital);
        ValueHolder.getInstance().setAktuell1Reserve(Double.valueOf(gesReservenTxt.getText()));
        ValueHolder.getInstance().setErfolgvortrag(erfolgvortrag);
        ValueHolder.getInstance().setGrunddividende(dividende);
        ValueHolder.getInstance().setSuperdividende(superdividende);
        ValueHolder.getInstance().setAktuell2Reserve(0);
        ValueHolder.getInstance().setZiel1Reserve(((aktienkapital + partizipationskapital) / 100) * 20);
        ValueHolder.getInstance().setErforderlicheReserve(((aktienkapital + partizipationskapital) / 100) * 20);
        ValueHolder.getInstance().setNeuererfolgvortag(zwischenresultat);
        ValueHolder.getInstance().setReservenzuweisung(zuweisungGesReserven);
        ValueHolder.getInstance().setNeu1Reserve(gesReserven);
        ValueHolder.getInstance().setNeu2Reserve(zweiteGesReservenZuweisung);
        ValueHolder.getInstance().setGewuenschteDividende(gewuenschteDividende);
        ValueHolder.getInstance().setSuperdividendeAusschuettung(superdividendeAusschuettung);
        ValueHolder.getInstance().setDividendenAusschuettung(dividendenAusschuettung);
    }

    @FXML
    private void goToResultat(ActionEvent event) {
        berechnen(event);
        SceneChanger.getInstance().loadFXML("View/Result.fxml", rootpane);
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Help.fxml", rootpane);
    }

}
