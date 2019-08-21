/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Janis Tejero
 */
public class Main extends Application {

    
    @Override
       public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("View/Calculator.fxml"));
        Scene scene = new Scene(root, 487, 587);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("Resources/calculator.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

}