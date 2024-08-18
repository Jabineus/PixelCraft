module com.pixelcraft {
    requires  transitive javafx.controls;
    requires  transitive javafx.fxml;


    opens com.pixelcraft to javafx.fxml;
    exports com.pixelcraft;
}
