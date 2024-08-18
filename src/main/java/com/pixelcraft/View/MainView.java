package com.pixelcraft.View;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import com.pixelcraft.Model.ImageModel;
import com.pixelcraft.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;

public class MainView implements Observer{
    private Stage stage;

    private Button applyEffectButton;
    private Button saveButton;
    private Button loadImageButton;
    private ComboBox<String> converterOptions;

    public MainView(Stage stage) {
        this.stage = stage;
        createUI();
    }

    private void createUI() {
        this.applyEffectButton = new Button("Apply Effect");
        this.saveButton = new Button("Save Image");
        this.loadImageButton = new Button("Upload Image");
        this.converterOptions = new ComboBox<>();

        VBox layout = new VBox(10);
        layout.getChildren().add(loadImageButton);
        layout.getChildren().add(converterOptions);
        layout.getChildren().add(applyEffectButton);
        layout.getChildren().add(saveButton);
        

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();

        converterOptions.getItems().addAll("GrayScale", "Rotate", "Blur", "Vertical Flip", "Pop Art", "Swirl", "Circle Crop", "Frame", "Pixel", "Diagonal Flip");
    
    }
    public Button getApplyEffectButton() {
        return applyEffectButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadImageButton() {return loadImageButton;}

    public String getSelectedConverter() {
        return converterOptions.getValue();
    }
    public void loadImage(ImageModel model) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            BufferedImage image = ImageIO.read(file);
            model.loadImage(image);
        }
    }

    public void saveImage(ImageModel model) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PNG Files", "*.png")
        );
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            model.saveImage(model.getModifiedImage(), file.getPath());
        }
    }
    @Override
    public void update() {
        // Update the original and modified images in the ImageView components
        
    }
}

