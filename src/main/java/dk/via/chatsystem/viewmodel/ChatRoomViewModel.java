package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatRoomViewModel implements PropertyChangeListener {
    private final Model client;

    public ChatRoomViewModel(Model client) {
        this.client = client;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
