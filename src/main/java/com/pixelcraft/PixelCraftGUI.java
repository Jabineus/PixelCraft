package com.pixelcraft;

import javafx.application.Application;
import javafx.stage.Stage;

import com.pixelcraft.Controller.ImageController;
import com.pixelcraft.Model.ImageModel;
import com.pixelcraft.View.MainView;


public class PixelCraftGUI extends Application {

    @Override
    public void start(Stage stage) {

        ImageModel model = new ImageModel();
        MainView view = new MainView(stage);
        model.addObserver(view);

        ImageController controller = new ImageController(view, model);
    }

    public static void main(String[] args) {
        launch();
    }
}