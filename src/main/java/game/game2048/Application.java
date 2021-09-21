package game.game2048;

import Controller.Controller;
import Model.GameMap;
import Model.Tile;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 810);
        stage.setTitle("Game 2048");
        stage.getIcons().add(new Image("https://play-lh.googleusercontent.com/YMjBRDCr5a9p_bdWnMTWKrC3FNi40WsR4jXt1UXlNMZfRhoXQL3eAkIwXAk5wwQZ_g"));
        stage.setScene(scene);
        Controller controller = fxmlLoader.getController();
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCharacter()) {
                    case "w", "ц", "W", "Ц" -> {
                        controller.clickW();
                        winOrLoss(controller);
                    }
                    case "a", "ф", "A", "Ф" -> {
                        controller.clickA();
                        winOrLoss(controller);
                    }
                    case "s", "ы", "S", "Ы" -> {
                        controller.clickS();
                        winOrLoss(controller);
                    }
                    case "d", "в", "D", "В" -> {
                        controller.clickD();
                        winOrLoss(controller);
                    }
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void winOrLoss(Controller controller) {
        GameMap gameMap=controller.getGameMap();
        if (gameMap.chekWin()) {
            InfAlert.alertWin();
            gameMap.setChekChange(true);
            controller.setGameMap(new GameMap());
            controller.print();
        }
        if (gameMap.lossChek()) {
            InfAlert.alertLoss();
            gameMap.setChekChange(true);
            controller.setGameMap(new GameMap());
            controller.print();
        }
    }
}