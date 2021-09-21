package game.game2048;

import Controller.Controller;
import javafx.scene.control.Alert;

public class InfAlert {

    private static final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public static void alertWin() {
        alert.setTitle("Win!!!!!");
        alert.setHeaderText(null);
        alert.setContentText("YOU WIN!");
        alert.show();
    }

    public static void alertLoss() {
        alert.setTitle("Game over!!!!!");
        alert.setHeaderText(null);
        alert.setContentText("GAME OVER!");
        alert.show();
    }
}
