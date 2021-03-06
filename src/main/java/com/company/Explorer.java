package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Explorer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/forms/explorer.fxml"));
        primaryStage.setTitle("Explorer");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

    }

}
