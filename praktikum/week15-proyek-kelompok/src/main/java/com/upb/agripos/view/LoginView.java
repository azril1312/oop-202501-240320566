package com.upb.agripos.view;

import com.upb.agripos.controller.LoginController;
import com.upb.agripos.model.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends VBox {

    public LoginView(Stage stage) {
        setSpacing(10);
        setPrefWidth(400);
        setPrefHeight(300);

        TextField user = new TextField();
        user.setPromptText("Username");

        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");

        Button login = new Button("Login");
        login.setStyle("-fx-padding: 10; -fx-font-size: 14;");
        LoginController controller = new LoginController();

        login.setOnAction(e -> {
            User loggedInUser = controller.doLogin(user.getText(), pass.getText());
            if (loggedInUser != null) {
                stage.setScene(new Scene(new DashboardView(stage, loggedInUser), 400, 300));
            } else {
                new Alert(Alert.AlertType.ERROR, "Login gagal - Username atau password salah").show();
            }
        });

        getChildren().addAll(
            new Label("LOGIN AGRIPOS"),
            user, pass, login
        );
    }
}