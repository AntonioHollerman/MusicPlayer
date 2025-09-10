module gui.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui.demo to javafx.fxml;
    exports gui.demo;
}