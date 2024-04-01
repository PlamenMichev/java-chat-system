package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.model.Model;

public class ViewModelFactory {
    private final LoginViewModel convertViewModel;
    private final ChatRoomViewModel chatRoomViewModel;

    public ViewModelFactory(Model model) {
        this.convertViewModel = new LoginViewModel(model);
        this.chatRoomViewModel = new ChatRoomViewModel(model);
    }

    public LoginViewModel getConvertViewModel() {
        return convertViewModel;
    }

    public ChatRoomViewModel getChatRoomViewModel() {
        return chatRoomViewModel;
    }
}
