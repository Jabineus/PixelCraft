module com.pixelcraft {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.desktop;

    opens com.pixelcraft to javafx.fxml;
    exports com.pixelcraft;
}
