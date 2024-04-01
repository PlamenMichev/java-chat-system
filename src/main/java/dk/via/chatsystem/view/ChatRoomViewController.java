package dk.via.chatsystem.view;

import dk.via.chatsystem.model.User;
import dk.via.chatsystem.viewmodel.ChatRoomViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.control.ListView;

public class ChatRoomViewController {

    @FXML public ListView<User> connectedUsersListView;

    private ChatRoomViewModel chatRoomViewModel;
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, ChatRoomViewModel chatRoomViewModel, Region root) {
        this.chatRoomViewModel = chatRoomViewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        connectedUsersListView.setItems(chatRoomViewModel.getUsers());
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
