package com.company.objects;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VBoxСontainer extends VBox {

    private ImageView imageView;
    private Label label;

    {
        setPrefHeight(210);
        setPrefWidth(210);
    }

    public VBoxСontainer(String path, String title) {

        setAlignment(Pos.CENTER);

        Image image = new Image(path, 200, 200, true, true);

        imageView = new ImageView(image);
        label = new Label(title);

        getChildren().addAll(imageView, label);

    }

    public VBoxСontainer(File file) {

        setAlignment(Pos.CENTER);

        Image image = null;
        try {
            image = new Image(new FileInputStream(file), 200, 200, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView = new ImageView(image);
        label = new Label(file.getName().substring(0, file.getName().length() <= 10 ? file.getName().length() : 10));

        getChildren().addAll(imageView, label);

    }

}
