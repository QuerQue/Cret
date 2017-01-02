package ppak.cret.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;

/**
 * Main JavaFX Application class, extends Application.
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cret");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main program method
     * @param args start parameters, not used
     */
    public static void main(String[] args){
        launch(args);
    }

}
