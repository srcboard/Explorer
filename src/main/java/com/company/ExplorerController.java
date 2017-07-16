package com.company;

import com.company.objects.Image;
import com.company.objects.VBoxСontainer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.TilePane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ExplorerController implements Initializable {

    public TreeView explorer;
    public ScrollPane content;

    File tempFile;

    public void initialize(URL location, ResourceBundle resources) {

        TreeItem treeItem = new TreeItem("Explorer");
        explorer.setRoot(treeItem);

        File[] roots = File.listRoots();
        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();
        for (File childFile : roots) {
            children.add(new TreeItem(childFile));
        }
        treeItem.getChildren().setAll(children);

        addSubfoldersForGroup(treeItem, 2);

    }

    private void addSubfoldersForGroup(TreeItem treeItem, int level) {
        if (level <= 0) return;

        level--;

        for (Object subgroup : treeItem.getChildren()) {

            TreeItem itemSubgroup = (TreeItem) subgroup;

            File subgroupValue = (File) itemSubgroup.getValue();
            if (subgroupValue != null && subgroupValue.isDirectory()) {

                File[] subgroupFiles = subgroupValue.listFiles();
                if (subgroupFiles != null) {

                    ObservableList<TreeItem<File>> subgroupChildren = FXCollections.observableArrayList();
                    for (File childFile : subgroupFiles) {
                        subgroupChildren.add(new TreeItem(childFile));
                    }
                    itemSubgroup.getChildren().setAll(subgroupChildren);

                    addSubfoldersForGroup(itemSubgroup, level);

                }

            }

        }

    }

    public void explorerOnMousePressed(Event event) {

        TreeItem treeItem = (TreeItem) explorer.getSelectionModel().getSelectedItem();

        tempFile = (File) treeItem.getValue();

        if (Image.thisImage(tempFile)) {

            TilePane tilePane = new TilePane();
            Image image = new Image(tempFile, 500, 500);
            tilePane.getChildren().add(image);
            content.setContent(tilePane);

            return;
        }

        Thread thread = new Thread(new Runnable() {

            public void run() {

                final TilePane tilePane = new TilePane();

                File[] listFiles = tempFile.listFiles();
                for (final File file : listFiles) {

                    if (file.getName().lastIndexOf(".exe") != -1) {
                        continue;
                    }

                    Platform.runLater(new Runnable() {

                        public void run() {

                            content.setContent(tilePane);
                            if (Image.thisImage(file)) {

                                Image image = new Image(file, 200, 200);
                                tilePane.getChildren().add(image);
                                content.setContent(tilePane);

                            } else {

                                if (file.isDirectory()) {
                                    VBoxСontainer vBox = new VBoxСontainer("/icons/folder.png", file.getName());
                                    tilePane.getChildren().add(vBox);
                                    content.setContent(tilePane);
                                } else {
                                    VBoxСontainer vBox = new VBoxСontainer("/icons/missing-sketch.png", file.getName());
                                    tilePane.getChildren().add(vBox);
                                    content.setContent(tilePane);
                                }

                            }

                        }

                    });
                }
            }

        });

        thread.start();

    }

}
