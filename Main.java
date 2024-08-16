package pixelcraft;

import javafx.application.Application;
import javafx.stage.Stage;

public class PixelCraftGUI extends Application {

    @Override
    public void start(Stage stage) {

        Model model = new Model();
        View view = new View(stage);
        model.addObserver(view);

        Controller controller = new Controller(model, view);
        controller.installControllers();
    }

    public static void main(String[] args) {
        launch();
    }
}
