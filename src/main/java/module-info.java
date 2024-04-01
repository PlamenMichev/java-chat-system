module dk.via {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens dk.via.chatsystem.view to javafx.fxml;
    opens dk.via.chatsystem to javafx.fxml;

    exports dk.via.chatsystem;
}