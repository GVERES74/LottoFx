/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiniGames;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author VERESG
 */
public class MiniGamesMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Fuck it!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 500, 350);
        Image banner = new Image("https://docs.oracle.com/javafx/javafx/images/fx_boxback_logo.jpg");
        ImageView imgviewer = new ImageView(banner);
        root.getChildren().add(imgviewer);
        root.setAlignment(Pos.CENTER);
        imgviewer.fitWidthProperty().bind(root.widthProperty());
        imgviewer.fitHeightProperty().bind(root.heightProperty());
        
        primaryStage.setTitle("ImageViewer");
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
