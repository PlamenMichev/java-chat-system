package dk.via.chatsystem.view;

import dk.via.chatsystem.model.User;
import dk.via.chatsystem.viewmodel.ChatRoomViewModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
        this.sendButton.setOnAction(actionEvent -> sendMessage());
        // Event handler for Enter key press on the root node
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    // Call sendMessage method when Enter key is pressed
                    sendMessage();
                }
            }
        });

        // Make sure the root node is focus traversable to capture key events
        root.setFocusTraversable(true);

        // We make the current user in the list view with different color
        connectedUsersListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<User> call(ListView<User> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);
                        if (user != null) {
                            setText(user.getUsername());
                            // Check if the user is the current user
                            if (user.getUsername().equals(chatRoomViewModel.getCurrentUser())) {
                                setStyle("-fx-background-color: lightblue;");
                            } else {
                                setStyle(null);
                            }
                        } else {
                            setText(null);
                            setStyle(null);
                        }
                    }
                };
            }
        });
    }

    private void sendMessage() {
        if (!messageTextField.getText().isEmpty())
        {
            chatRoomViewModel.sendMessage();
            messageTextField.clear();
        }
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
