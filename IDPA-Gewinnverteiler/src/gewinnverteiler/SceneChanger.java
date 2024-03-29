/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package gewinnverteiler;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Nick Flückiger
 */
public class SceneChanger {

    private SceneChanger() {

    }
    private static SceneChanger instance;

    public static SceneChanger getInstance() {
        if (instance == null) {
            instance = new SceneChanger();
        }
        return instance;
    }

    public void loadFXML(String name, AnchorPane rootpane) {
        try {
            Stage stage = new Stage();
            Stage oldstage = (Stage) rootpane.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource(name));
            stage.setScene(new Scene(root));
            stage.setTitle(name);
            stage.setResizable(false);
            stage.getIcons().add(new Image("Resources/calculator.png"));
            oldstage.close();
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load new window: " + name + " because of:");
            e.printStackTrace();
        }
    }

    public void loadWebPage(String name, WebView view) {
        WebEngine webEngine = view.getEngine();
        URL location = this.getClass().getResource(name);
        webEngine.load(location.toString());
    }
}
