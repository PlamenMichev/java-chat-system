package dk.via.chatsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.Console;


public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        System.out.println("Test");
        FXMLLoader loader = new FXMLLoader();
        var test = getClass();
        var resource = getClass().getResource("LoginView.fxml");
        loader.setLocation(getClass().getResource("view/LoginView.fxml"));
        Region root = loader.load();

        // Set the FXML file as the root of the scene
        Scene scene = new Scene(root, 300, 150);

        // Set the scene to the stage and show the stage
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}