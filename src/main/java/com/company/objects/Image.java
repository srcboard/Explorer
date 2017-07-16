package com.company.objects;


import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Image extends VBox {

    private ImageView imageView;

    public Image(File file, int width, int length) {

        setAlignment(Pos.CENTER);

        javafx.scene.image.Image image = null;
        try {
            image = new javafx.scene.image.Image(new FileInputStream(file), width, length, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView = new ImageView(image);

        getChildren().addAll(imageView);

    }

    public static boolean thisImage(File file) {

        return file.getName().lastIndexOf(".jpg") != -1
                || file.getName().lastIndexOf(".png") != -1;

    }

}
