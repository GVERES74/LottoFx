/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author VERESG
 */
public class LottoFX extends Application {
    
    @Override
     public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View2.fxml"));
        
       
        Scene scene = new Scene(root);
        
       
        stage.setScene(scene);
        
        stage.setResizable(false);
        stage.setTitle("Lottósorsolás - Copyright 2018 - Gabor Veres");
        stage.show();
        
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
    
}
