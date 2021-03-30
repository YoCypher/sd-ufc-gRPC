package com.sd.grpc.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Navigator {
    private static Stage stage;

    public static void setStage(Stage stage) {
        Navigator.stage = stage;
    }

    public static void navigate(Scene scene, String title) {
        stage.close();
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static  <T> T loadView(String name) throws IOException {
        var fxmlLoader = new FXMLLoader();
        String fxmlDocPath = "./src/main/java/com/sd/grpc/javafx/" + name;
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
        return fxmlLoader.load(fxmlStream);
    }
}