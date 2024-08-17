package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
    private Stage stage;
    private Button applyEffectButton;

    public MainView(Stage stage) {
        this.stage = stage;
        this.applyEffectButton = new Button("Apply Effect");

        VBox layout = new VBox(10);
        layout.getChildren().add(applyEffectButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    public Button getApplyEffectButton() {
        return applyEffectButton;
    }

    public void show() {
        stage.show();
    }
}

