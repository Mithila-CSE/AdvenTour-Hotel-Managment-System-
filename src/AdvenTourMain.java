/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java 
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdvenTourMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(scene);
           // primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdvenTourMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
