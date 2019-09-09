/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import gewinnverteiler.SceneChanger;
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
    private Label aktuellGrunddividendeLbl;
    @FXML
    private Label aktuellSuperdividendeLbl;
    @FXML
    private Label erfolgLbl;
    @FXML
    private Label erfolgWertLbl;
    @FXML
    private Label erfolgvortragLbl;
    @FXML
    private Label erfolgvortragWertLbl;
    @FXML
    private Label grunddividendeLbl;
    @FXML
    private Label grunddividendeWertLbl;
    @FXML
    private Label superdividendeLbl;
    @FXML
    private Label superdividendeWertLbl1;
    private Label bilanzerfolgLbl;
    @FXML
    private Label bilanzerfolgWertLbl;
    @FXML
    private Label grundivididendeLbl;
    @FXML
    private Label grunddividendeWertLbl2;
    @FXML
    private Label superdividendeWertLbl2;

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

    }

    public void showResults(double erfolg, double vortrag, double bilanzerfolg, double reservenzuweisung, double zwischentotal, double dividende, double superdividende, double zweitereservenzuweisung) {
        erfolgLbl.setText(String.valueOf(erfolg));
        if(vortrag > 0){
            erfolgVortragLbl.setText("Gewinnvortrag");
        } else{
            erfolgVortragLbl.setText("Verlustvortrag");
        }
        erfolgvortragWertLbl.setText(String.valueOf(vortrag));
    }

    @FXML
    private void goToRechner(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Calculator.fxml", rootpane);
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
    }

}
