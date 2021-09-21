module game.game2048 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;
    exports Model;


    opens game.game2048 to javafx.fxml;
    exports game.game2048;
    exports Controller;
    opens Controller to javafx.fxml;
}