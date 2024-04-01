package dk.via.chatsystem;

import dk.via.chatsystem.client.ChatClient;
import dk.via.chatsystem.client.ChatClientImplementation;
import dk.via.chatsystem.model.Model;
import dk.via.chatsystem.model.ModelManager;
import dk.via.chatsystem.view.ViewHandler;
import dk.via.chatsystem.viewmodel.ViewModelFactory;
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
        ChatClient client = new ChatClientImplementation("localhost", 8080);
        Model model = new ModelManager(client);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Perform cleanup or save data before closing the application
            if (model.getCurrentUser() != null) {
                model.removeUser(model.getCurrentUser().getUsername());
            }
        }));
    }

    public static void main(String[] args) {
        launch(args);
    }
}