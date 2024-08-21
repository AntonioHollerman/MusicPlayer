module gui.graphics {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui.graphics to javafx.fxml;
    exports gui.graphics;
}