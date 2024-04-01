package dk.via.chatsystem.view;

import dk.via.chatsystem.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {
    public static final String LOGIN = "LOGIN";
    public static final String CHAT_ROOM = "CHAT_ROOM";

    private final ViewHandler viewHandler;
    private final ViewModelFactory viewModelFactory;
    private LoginViewController loginViewController;
    private ChatRoomViewController chatRoomViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.loginViewController = null;
        this.chatRoomViewController = null;
    }

    public Region loadLoginView() {
        if (loginViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginView.fxml"));
            try {
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(viewHandler, viewModelFactory.getConvertViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }

        loginViewController.reset();
        return loginViewController.getRoot();
    }

    public Region loadChatRoomView() {
        if (chatRoomViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatRoomView.fxml"));
            try {
                Region root = loader.load();
                chatRoomViewController = loader.getController();
                chatRoomViewController.init(viewHandler, viewModelFactory.getChatRoomViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }

        chatRoomViewController.reset();
        return chatRoomViewController.getRoot();
    }

    public Region load(String id) {
        return switch(id) {
            case LOGIN -> loadLoginView();
            case CHAT_ROOM -> loadChatRoomView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
    }
}
