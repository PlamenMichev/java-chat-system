module dk.via {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.rmi;
    requires remoteobserver;

    opens dk.via.chatsystem.view to javafx.fxml;
    opens dk.via.chatsystem to javafx.fxml;

    exports dk.via.chatsystem;
    exports dk.via.chatsystem.server;
    exports dk.via.chatsystem.shared;
}