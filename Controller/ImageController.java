package Controller;

import Model.ImageModel;
import View.MainView;
import javafx.stage.Stage;

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
        // Add more event handlers as needed
    }

    private void applyEffect() {
        // Logic to apply the selected effect from the model
        // Example: model.applyEffect("grayscale");
    }
}

