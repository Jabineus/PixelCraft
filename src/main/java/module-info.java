module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.desktop;

    opens com.pixelcraft to javafx.fxml;
    exports com.pixelcraft;
}
