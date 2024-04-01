package dk.via.chatsystem.view;

import dk.via.chatsystem.model.User;
import dk.via.chatsystem.viewmodel.ChatRoomViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ChatRoomViewController {

    @FXML public ListView<User> connectedUsersListView;
    @FXML public TextField messageTextField;
    @FXML public TextArea chatArea;
    @FXML public Button sendButton;

    private ChatRoomViewModel chatRoomViewModel;
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, ChatRoomViewModel chatRoomViewModel, Region root) {
        this.chatRoomViewModel = chatRoomViewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        connectedUsersListView.setItems(chatRoomViewModel.getUsers());
        this.chatRoomViewModel.bindMessage(messageTextField.textProperty());
        this.chatRoomViewModel.bindChatBox(chatArea.textProperty());
        this.sendButton.setOnAction(actionEvent -> chatRoomViewModel.sendMessage());
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
