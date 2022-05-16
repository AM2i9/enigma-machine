module enigma {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens ml.am2i9.enigma to javafx.fxml;
    exports ml.am2i9.enigma;
}