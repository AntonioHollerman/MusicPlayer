module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires music;


    opens test to javafx.fxml;
    opens ui.add to javafx.fxml;
    opens ui.content to javafx.fxml;
    opens ui.edit to javafx.fxml;
    opens ui.player to javafx.fxml;
    opens ui.master to javafx.fxml;

    exports ui.add;
    exports ui.content;
}