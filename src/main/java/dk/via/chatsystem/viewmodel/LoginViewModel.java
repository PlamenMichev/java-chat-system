package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.model.Model;
import dk.via.chatsystem.model.User;
import dk.via.chatsystem.view.ViewFactory;
import dk.via.chatsystem.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.transform.Result;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class LoginViewModel implements PropertyChangeListener {
    private final Model model;
    private StringProperty username;

    public LoginViewModel(Model model) {
        this.model = model;
        this.username = new SimpleStringProperty("");
        model.addPropertyChangeListener(this);
    }

    public void login(ViewHandler viewHandler) {
        try {
            model.addUser(this.username.get());
            model.setCurrentUser(new User(this.username.get()));
            viewHandler.openView(ViewFactory.CHAT_ROOM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bindUsername(StringProperty property) {
        property.bindBidirectional(username);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
