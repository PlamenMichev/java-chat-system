package dk.via.chatsystem.view;

import dk.via.chatsystem.viewmodel.ChatRoomViewModel;
import javafx.scene.layout.Region;

public class ChatRoomViewController {

    private ChatRoomViewModel chatRoomViewModel;
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, ChatRoomViewModel chatRoomViewModel, Region root) {
        this.chatRoomViewModel = chatRoomViewModel;
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
