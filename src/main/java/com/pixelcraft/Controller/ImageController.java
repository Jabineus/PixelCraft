package com.pixelcraft.Controller;

import com.pixelcraft.Model.ImageModel;
import com.pixelcraft.View.MainView;
import com.pixelcraft.*;

public class ImageController {
    private MainView view;
    private ImageModel model;

    public ImageController(MainView view, ImageModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        // Add event handlers for GUI elements
        view.getApplyEffectButton().setOnAction(e -> applyEffect());
        view.getSaveButton().setOnAction(e -> save());
    }

    private void applyEffect() {
        // Logic to apply the selected effect from the model
        // Example: model.applyEffect("grayscale");
        String selectedConverter = view.getSelectedConverter();
        if (selectedConverter != null) {
            model.applyConverter(ConverterFactory.getConverter(selectedConverter));
        }
    }
    private void save(){
        try {
            view.saveImage(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

