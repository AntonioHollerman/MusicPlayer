module gui.graphics {
    requires javafx.controls;
    requires javafx.fxml;
    requires music;

    opens gui.graphics.add to javafx.fxml;
    opens gui.graphics.content to javafx.fxml;
    opens gui.graphics.edit to javafx.fxml;
    opens gui.graphics.master to javafx.fxml;
    opens gui.graphics.player to javafx.fxml;
    opens gui.graphics.test to javafx.fxml;

    exports gui.graphics.test;
    exports gui.graphics.master;
}