/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
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
*/
    }

    public void showResults(double nettogewinn, double bilanzerfolg) {

    }
    
    

    @FXML
    private void goToRechner(Event event) {
        loadFXML("Calculator.fxml");
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
        }
    }

}
