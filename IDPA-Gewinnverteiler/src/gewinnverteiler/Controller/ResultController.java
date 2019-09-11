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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private Label bilanzerfolgLbl;
    @FXML
    private Label bilanzerfolgWertLbl;
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
    private Label neu1ReserveWertLbl;
    @FXML
    private Label zwischentotalWertLbl1;
    @FXML
    private Label zwischentotalWertLbl2;
    @FXML
    private Label neu2ReserveWertLbl;
    @FXML
    private HBox ersteGesReservenHBox;
    @FXML
    private Label ersteGesReserveLbl;
    @FXML
    private Label grundivididendeLbl;
    @FXML
    private Label superdividendeLbl;
    @FXML
    private Label zweiteGesReserveLbl;
    @FXML
    private Button erklaerungBtn;

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

        
     

        reserve1Chart.setLabelLineLength(10);
        reserve1Chart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITESMOKE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : reserve1Chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }
        // -----------------------------------------------------------------------------------------------------
        
        

        showResults();
    }

    public void showResults() {

        // erste Reserve
        aktuell1ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getAktuell1Reserve()));
        ziel1ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getZiel1Reserve()));
        neu1ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getNeu1Reserve()));
        neu1ReserveWertLbl.setText(String.valueOf(ValueHolder.getInstance().getReservenzuweisung()));

        // erfolg
        if (ValueHolder.getInstance().getErfolg() > 1) {
            erfolgLbl.setText("Jahresgewinn");
        } else {
            erfolgLbl.setText("Jahresverlust");
        }

        erfolgWertLbl.setText(String.valueOf(ValueHolder.getInstance().getErfolg()));

        // erfolgvortrag
        if (ValueHolder.getInstance().getErfolgvortrag() > 1) {
            erfolgvortragLbl.setText("Gewinnvortrag");
        } else {
            erfolgvortragLbl.setText("Verlustvortrag");
        }
        erfolgvortragWertLbl.setText(String.valueOf(ValueHolder.getInstance().getErfolgvortrag()));

        // bilanzerfolg
        if (ValueHolder.getInstance().getBilanzerfolg() > 1) {
            bilanzerfolgLbl.setText("Bilanzgewinn");
        } else {
            bilanzerfolgLbl.setText("Bilanzverlust");
        }

        // prüfe auf Bilanzverlust
        if (ValueHolder.getInstance().getBilanzerfolg() < 1) {
            verwendungsplanVBox.getChildren().remove(erstesZwischentotalHBox);
            verwendungsplanVBox.getChildren().remove(grunddividendeHBox);
            verwendungsplanVBox.getChildren().remove(zweitesZwischentotalHBox);
            verwendungsplanVBox.getChildren().remove(superdividendeHBox);
            verwendungsplanVBox.getChildren().remove(zweiteGesReservenHBox);
            verwendungsplanVBox.getChildren().remove(ersteGesReservenHBox);

            aktuell2ReserveLbl.setText("---");
            ziel2ReserveLbl.setText("---");
            neu2ReserveLbl.setText("---");
            superdividendeWertLbl.setText("---");
        } else {
            // zweite Reserve und Superdividende
            System.out.println(ValueHolder.getInstance().getSuperdividendeAusschuettung());
            System.out.println(ValueHolder.getInstance().getSuperdividende());
            if (ValueHolder.getInstance().getSuperdividendeAusschuettung()) {
                // zweite reserve
                aktuell2ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getAktuell2Reserve()));
                ziel2ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getAktuell2Reserve()));
                neu2ReserveWertLbl.setText(String.valueOf(ValueHolder.getInstance().getNeu2Reserve()));
                neu2ReserveLbl.setText(String.valueOf(ValueHolder.getInstance().getNeu2Reserve()));
                
                // superdividende
                superdividendeWertLbl.setText(String.valueOf(ValueHolder.getInstance().getSuperdividende()));
                superdividendeWertLbl2.setText(String.valueOf(ValueHolder.getInstance().getSuperdividende()));
            } else {
                aktuell2ReserveLbl.setText("---");
                ziel2ReserveLbl.setText("---");
                neu2ReserveLbl.setText("---");
                superdividendeWertLbl.setText("---");
                verwendungsplanVBox.getChildren().remove(superdividendeHBox);
                verwendungsplanVBox.getChildren().remove(zweiteGesReservenHBox);
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

            // erstes zwischentotal
            zwischentotalWertLbl1.setText(String.valueOf(ValueHolder.getInstance().getZwischenresultat()));

            // zweites zwischentotal
            double zwischentotalNachDividende = ValueHolder.getInstance().getZwischenresultat() - ValueHolder.getInstance().getGrunddividende();
            zwischentotalWertLbl2.setText(String.valueOf(zwischentotalNachDividende));

        }

        bilanzerfolgWertLbl.setText(String.valueOf(ValueHolder.getInstance().getBilanzerfolg()));

        if (ValueHolder.getInstance().getNeuererfolgvortag() < 0) {
            neuerErfolgvortragLbl.setText("Verlustvortrag neu");
        }

        neuerErfolgvortragWertLbl.setText(String.valueOf((int) ValueHolder.getInstance().getNeuererfolgvortag()));
        
        // Pie Chart
        ObservableList<PieChart.Data> reserve1Data
                = FXCollections.observableArrayList(
                        new PieChart.Data("Aktuell", ValueHolder.getInstance().getAktuell1Reserve()),
                        new PieChart.Data("Ziel", ValueHolder.getInstance().getZiel1Reserve()));
        reserve1Chart.getData().addAll(reserve1Data);
        
    }

    @FXML
    private void goToRechner(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Calculator.fxml", rootpane);
    }

    @FXML
    private void goToHilfe(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Help.fxml", rootpane);
    }

    @FXML
    private void goToErklaerung(ActionEvent event) {
        SceneChanger.getInstance().loadFXML("View/Description.fxml", rootpane);
    }

}
