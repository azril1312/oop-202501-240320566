package com.upb.agripos;

import com.upb.agripos.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new LoginView(stage), 400, 300));
        stage.setTitle("AgriPOS - Sistem Penjualan Pertanian");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}