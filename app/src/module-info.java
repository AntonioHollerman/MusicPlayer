module app {
    requires javafx.controls;
    requires javafx.fxml;

    requires database;
    requires music;


    opens application to javafx.fxml;
    opens controllers to javafx.fxml;
    exports application;
}