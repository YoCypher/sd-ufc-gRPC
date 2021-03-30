package com.sd.grpc.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Navigator.setStage(stage);
        Pane root = Navigator.loadView("page.fxml");
        Scene scene = new Scene(root);
        Navigator.navigate(scene, "Cars");
    }
}
