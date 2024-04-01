package dk.via.chatsystem.view;

import dk.via.chatsystem.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class LoginViewController {
    @FXML public TextField usernameField;
    @FXML public Button loginButton;

    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;
    private Region root;

    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.loginViewModel = loginViewModel;
        this.root = root;

        loginViewModel.bindUsername(usernameField.textProperty());

        this.loginButton.setOnAction(actionEvent -> {
            loginViewModel.login(viewHandler);
        });
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
