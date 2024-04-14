package dk.via.chatsystem;

import dk.via.chatsystem.model.Model;
import dk.via.chatsystem.model.ModelManager;
import dk.via.chatsystem.shared.Chat;
import dk.via.chatsystem.view.ViewHandler;
import dk.via.chatsystem.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.Console;
import java.rmi.registry.LocateRegistry;


public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var registry = LocateRegistry.getRegistry(1099);
        var chat = (Chat) registry.lookup("chat");
        var model = new ModelManager(chat);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Perform cleanup or save data before closing the application
            if (model.getCurrentUser() != null) {
                try
                {
                    model.removeUser(model.getCurrentUser().getUsername());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }));
    }

    public static void main(String[] args) {
        launch(args);
    }
}