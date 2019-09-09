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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Janis Tejero
 */
public class ResultController implements Initializable {

    @FXML
    private Menu menuRechner;
    @FXML
    private Menu menuHilfe;
    @FXML
    private Label aktuell1ReserveLbl;
    @FXML
    private Label aktuell2ReserveLbl;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Label ziel1ReserveLbl;
    @FXML
    private Label ziel2ReserveLbl;
    @FXML
    private Label neu1ReserveLbl;
    @FXML
    private Label neu2ReserveLbl;
    @FXML
    private PieChart reserve1Chart;
    @FXML
    private PieChart reserve2Chart;
    @FXML
    private Label erfolgLbl;
    @FXML
    private Label erfolgWertLbl;
    @FXML
    private Label erfolgvortragLbl;
    @FXML
    private Label erfolgvortragWertLbl;
    @FXML
    private Label grunddividendeWertLbl;
    @FXML
    private Label superdividendeLbl;
    @FXML
    private Label bilanzerfolgLbl;
    @FXML
    private Label bilanzerfolgWertLbl;
    @FXML
    private Label grundivididendeLbl;
    @FXML
    private Label grunddividendeWertLbl2;
    @FXML
    private Label superdividendeWertLbl2;
    @FXML
    private Label superdividendeWertLbl;
    @FXML
    private Label verwendungsplanLbl;
    @FXML
    private HBox grunddividendeHBox;
    @FXML
    private HBox superdividendeHBox;
    @FXML
    private HBox erstesZwischentotalHBox;
    @FXML
    private HBox zweiteGesReservenHBox;
    @FXML
    private HBox zweitesZwischentotalHBox;
    @FXML
    private VBox verwendungsplanVBox;
    @FXML
    private Label neuerErfolgvortragLbl;
    @FXML
    private Label neuerErfolgvortragWertLbl;
    @FXML
    private Label ersteGesReserveLbl;
    @FXML
    private Label neu1ReserveWertLbl;
    @FXML
    private Label zwischentotalWertLbl1;
    @FXML
    private Label zwischentotalWertLbl2;
    @FXML
    private Label zweiteGesReserveLbl;
    @FXML
    private Label neu2ReserveWertLbl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        menuRechner.setOnAction((ActionEvent event) -> {
            goToRechner(event);
        });

        menuHilfe.setOnAction((ActionEvent event) -> {
            goToHilfe(event);
        });

        ObservableList<PieChart.Data> reserve1Data
                = FXCollections.observableArrayList(
                        new PieChart.Data("Aktuell", 10000),
                        new PieChart.Data("Ziel", 20000));
        reserve1Chart.getData().addAll(reserve1Data);

        ObservableList<PieChart.Data> reserve2Data
                = FXCollections.observableArrayList(
                        new PieChart.Data("Aktuell", 5000),
                        new PieChart.Data("Ziel", 1000));
        reserve2Chart.getData().addAll(reserve2Data);

        showResults();
    }

    public void showResults() {

        // erste Reserve
        aktuell1ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getAktuell1Reserve()));
        ziel1ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getZiel1Reserve()));
        neu1ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getNeu1Reserve()));

        // zweite Reserve und Superdividende
        if (ValueHolder.getInstance().getSuperdividendeAusschuettung()) {
            aktuell2ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getAktuell2Reserve()));
            ziel2ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getSuperdividende()));
            superdividendeWertLbl.setText(String.valueOf(ValueHolder.getInstance().getSuperdividende()));
            superdividendeWertLbl2.setText(String.valueOf(ValueHolder.getInstance().getSuperdividende()));
        } else {
            aktuell2ReserveLbl.setText("---");
            ziel2ReserveLbl.setText("---");
            neu2ReserveLbl.setText("---");
            superdividendeWertLbl.setText("---");
            verwendungsplanVBox.getChildren().remove(superdividendeHBox);
        }

        // Grunddividende
        if (ValueHolder.getInstance().getDividendenAusschuettung()) {
            grunddividendeWertLbl.setText(String.valueOf(ValueHolder.getInstance().getGrunddividende()));
            grunddividendeWertLbl2.setText(String.valueOf(ValueHolder.getInstance().getGrunddividende()));
        } else {
            grunddividendeWertLbl.setText("---");
            verwendungsplanVBox.getChildren().remove(grunddividendeHBox);
        }

        // Verwendungsplan
        if (ValueHolder.getInstance().getNeuererfolgvortag() > 0) {
            verwendungsplanLbl.setText("Gewinnverwendungsplan");
        } else {
            verwendungsplanLbl.setText("Verlustverrechnungsplan");
        }

        // erfolg
        if (ValueHolder.getInstance().getErfolg() > 0) {
            erfolgLbl.setText("Jahresgewinn");
        } else {
            erfolgLbl.setText("Jahresverlust");
        }

        // erfolgvortrag
        if (ValueHolder.getInstance().getErfolgvortrag() > 0) {
            erfolgvortragLbl.setText("Gewinnvortrag");
        } else {
            erfolgvortragLbl.setText("Verlustvortrag");
        }
        erfolgvortragWertLbl.setText(String.valueOf(ValueHolder.getInstance().getErfolgvortrag()));

        // bilanzerfolg
        if (ValueHolder.getInstance().getBilanzerfolg() > 0) {
            bilanzerfolgLbl.setText("Bilanzgewinn");
        } else {
            bilanzerfolgLbl.setText("Bilanzerfolg");
        }

        // prüfe auf Bilanzverlust
        if (ValueHolder.getInstance().getBilanzerfolg() < 0) {
            verwendungsplanVBox.getChildren().remove(erstesZwischentotalHBox);
            verwendungsplanVBox.getChildren().remove(grunddividendeHBox);
            verwendungsplanVBox.getChildren().remove(zweitesZwischentotalHBox);
            verwendungsplanVBox.getChildren().remove(superdividendeHBox);
            verwendungsplanVBox.getChildren().remove(zweiteGesReservenHBox);
        }

        // erste gesetzliche Reserve
        
    }

    @FXML
    private void goToRechner(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Calculator.fxml", rootpane);
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
    }

}
