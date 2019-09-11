/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import gewinnverteiler.SceneChanger;
import gewinnverteiler.ValueHolder;
import java.net.URL;
import java.text.DecimalFormat;
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

    // Variablen
    private double neuererfolgvortag;
    private double bilanzerfolg;
    private double zwischenresultat;
    private double erforderlicheReserve;
    private double erfolg;
    private double aktienkapital;
    private double partizipationskapital;
    private double gesReserven;
    private double erfolgvortrag;
    private double gewuenschteDividendeProzent;
    private double dividende;
    private double zweiteGesReservenZuweisung;
    private double superdividende;
    private double zuweisungGesReserven;
    private int emptyCounter = 0;
    private boolean dividendenAusschuettung = true;
    private boolean superdividendeAusschuettung = false;
    private boolean validInput = false;
    private boolean reservenDeckung = false;

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

        // form validatierung
        if (Double.valueOf(aktienkapitalTxt.getText()) < 10000) {
            errorLbl.setText("Aktienkapital muss über 10'000 CHF liegen.");
            aktienkapitalTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (Double.valueOf(partizipationskapitalTxt.getText()) < 0) {
            errorLbl.setText("Partizipationskapital kann nicht unter Null sein.");
            partizipationskapitalTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (Double.valueOf(partizipationskapitalTxt.getText()) < 0) {
            errorLbl.setText("Gesetzliche Reserve kann nicht weniger als Null betragen..");
            gesReservenTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (Double.valueOf(zielDividendeTxt.getText()) < 0 || Double.valueOf(zielDividendeTxt.getText()) > 100) {
            errorLbl.setText("Dividende muss zwischen 0 und 100 % liegen.");
            zielDividendeTxt.setStyle("-fx-border-color: red");
            emptyCounter++;
        }

        if (emptyCounter > 1) {
            errorLbl.setText("Bitte die roten Felder ausfüllen oder korrigieren");
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
            gewuenschteDividendeProzent = Double.valueOf(zielDividendeTxt.getText());
            System.out.println("Gewünschte Dividende: " + gewuenschteDividendeProzent + " %");

            // verrechnung erfolg mit vortrag
            bilanzerfolg = erfolg + erfolgvortrag;

            erforderlicheReserve = ((aktienkapital + partizipationskapital) / 100) * 20;
            System.out.println("Bilanzerfolg: " + bilanzerfolg);

            // verlustvortrag
            if (bilanzerfolg < 0) {
                // deckbar mit reserven
                if (gesReserven >= (bilanzerfolg * -1)) {
                    gesReserven += bilanzerfolg;
                    //bilanzerfolg = 0;
                    reservenDeckung = true;
                    ValueHolder.getInstance().setGesReservendeckung(bilanzerfolg * -1);
                } else {
                    bilanzerfolg += gesReserven;
                    //gesReserven = 0;
                    reservenDeckung = true;
                    ValueHolder.getInstance().setGesReservendeckung(gesReserven * -1);
                }
                // keine dividende bei bilanzverlust
                dividende = 0;
                dividendenAusschuettung = false;
            }

            // ist eine dividende gewünscht
            if (gewuenschteDividendeProzent <= 0) {
                dividende = 0;
                dividendenAusschuettung = false;
            }

            ValueHolder.getInstance().setBilanzerfolg(bilanzerfolg);

            // zuweisung erste ges. reserve
            if (gesReserven < erforderlicheReserve && bilanzerfolg > 1) {
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
                System.out.println("Zuweisung 1. gesetzliche Reserven: " + zuweisungGesReserven);

                // erstes zwischentotal nach 1. reservenzuweisung
                zwischenresultat = bilanzerfolg;
                ValueHolder.getInstance().setZwischenresultat(zwischenresultat);
                System.out.println("Zwischentotal nach erster Reservenzuweisung: " + zwischenresultat);

                if (dividendenAusschuettung) {

                    // gewuenschte dividendenbeträge festlegen
                    // möchte man eine superdividende?
                    if (gewuenschteDividendeProzent > 5) { // superdividende gewünscht
                        // gewünschte grunddividende ermitteln
                        double gewuenschteDividende = ((aktienkapital + partizipationskapital) / 100) * 5;
                        if (gewuenschteDividende < zwischenresultat) { // genügend erfolg für 5% grunddividende?
                            zwischenresultat -= gewuenschteDividende;
                            dividende = gewuenschteDividende;

                            // gewünschte superdividende
                            superdividendeAusschuettung = true;
                            double gewuenschteSuperdividendeProzent = gewuenschteDividendeProzent - 5;
                            DecimalFormat formatter = new DecimalFormat("#.#");
                            formatter.format(gewuenschteSuperdividendeProzent); // in Prozenten auf 0.1 gerundet
                            System.out.println("Gewünschte Superdividende in % " + gewuenschteSuperdividendeProzent);
                            double gewuenschteSuperdividende = ((aktienkapital + partizipationskapital) / 100) * gewuenschteSuperdividendeProzent; // in Franken
                            System.out.println("Gewünschte Superdividende in CHF " + gewuenschteSuperdividende);

                            // mögliche superdividende
                            double moeglicheSuperdividende = Math.floor(zwischenresultat / ((aktienkapital + partizipationskapital) * 0.011)); // in Prozenten
                            System.out.println("Mögliche Superdividende in %: " + moeglicheSuperdividende);
                            moeglicheSuperdividende = ((aktienkapital + partizipationskapital) / 100) * moeglicheSuperdividende; // in Franken
                            System.out.println("Mögliche Superdividende in CHF: " + moeglicheSuperdividende);

                            if (moeglicheSuperdividende > gewuenschteSuperdividende) { // gewünschte superdividende möglich?
                                zwischenresultat -= gewuenschteSuperdividende;
                                superdividende = gewuenschteSuperdividende;
                                zweiteGesReservenZuweisung = (superdividende / 100) * 10;
                                zwischenresultat -= zuweisungGesReserven;
                            } else {
                                superdividende = moeglicheSuperdividende;
                                zwischenresultat -= moeglicheSuperdividende;
                                zweiteGesReservenZuweisung = (superdividende / 100) * 10;
                                zwischenresultat -= zuweisungGesReserven;
                            }
                        } else {
                            superdividendeAusschuettung = false;
                            superdividende = 0;
                            dividende = zwischenresultat;
                            zwischenresultat = 0;
                        }
                    } else { // keine superdividende
                        // gewünschte grunddividende ermitteln
                        double gewuenschteDividende = ((aktienkapital + partizipationskapital) / 100) * 5;
                        //  5% überhaupt möglich?
                        if (gewuenschteDividende < zwischenresultat) { // hets platz für dividende
                            zwischenresultat -= gewuenschteDividende;
                            dividende = gewuenschteDividende;
                        } else {
                            superdividende = 0;
                            dividende = zwischenresultat;
                            zwischenresultat = 0;
                        }
                    }
                }
            } else {
                System.out.println("Keine Dividende, Superdividende oder Reservenzuweisung, da ein Bilanzverlust vorhanden ist.");
                dividende = 0;
                dividendenAusschuettung = false;
                superdividende = 0;
                superdividendeAusschuettung = false;
                if (zuweisungGesReserven > 0) {
                    System.out.println("Bilanzerfolg test " + bilanzerfolg);
                    System.out.println("Zuweisungs test" + zuweisungGesReserven);
                    zwischenresultat = bilanzerfolg + (zuweisungGesReserven * -1);
                } else {
                    //zwischenresultat = bilanzerfolg;
                }
            }

            System.out.println("Dividende: " + dividende);
            System.out.println("Zwischentotal nach Superdividende: " + zwischenresultat);
            System.out.println("Superdividende: " + superdividende);

            neuererfolgvortag = zwischenresultat;
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
        ValueHolder.getInstance().setNeuererfolgvortag(neuererfolgvortag);
        ValueHolder.getInstance().setReservenzuweisung(zuweisungGesReserven);
        ValueHolder.getInstance().setNeu1Reserve(gesReserven);
        ValueHolder.getInstance().setNeu2Reserve(zweiteGesReservenZuweisung);
        ValueHolder.getInstance().setGewuenschteDividende(gewuenschteDividendeProzent);
        ValueHolder.getInstance().setSuperdividendeAusschuettung(superdividendeAusschuettung);
        ValueHolder.getInstance().setDividendenAusschuettung(dividendenAusschuettung);
        ValueHolder.getInstance().setReservendeckung(reservenDeckung);
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
