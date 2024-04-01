package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.transform.Result;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener {
    private final Model model;
    private StringProperty username;

    public LoginViewModel(Model model) {
        this.model = model;
        this.username = new SimpleStringProperty("");
        model.addPropertyChangeListener(this);
    }

    public void login() {
        model.addUser(this.username.get());
    }

    public void bindUsername(StringProperty property) {
        property.bindBidirectional(username);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
