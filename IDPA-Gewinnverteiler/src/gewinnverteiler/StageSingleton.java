/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gewinnverteiler;

import javafx.stage.Stage;

/**
 *
 * @author Janis Tejero
 */
public class StageSingleton {
    
    private Stage stage;
    
    private StageSingleton() {
        
    }
    
    public static StageSingleton getInstance() {
        return StageSingletiHolder.INSTANCE;
    }
    
    private static class StageSingletiHolder {

        private static final StageSingleton INSTANCE = new StageSingleton();
    }
    
    public Stage getStage(){
        return this.stage;
    }
    
    public void setStage(Stage primaryStage){
        this.stage = primaryStage;
    }
}
