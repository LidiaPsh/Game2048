package Controller;

import Model.GameMap;
import Model.Tile;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller {

    private GameMap gameMap = new GameMap();

    @FXML
    private Label score;
    @FXML
    private GridPane gameFields;

    @FXML
    void initialize() {
        print();
    }

    @FXML
    protected void createNewGame() {
        gameMap = new GameMap();
        print();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void print() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Label label = new Label(gameMap.getTiles()[i][j].getValue() + "");
                String s = "";
                switch (gameMap.getTiles()[i][j].getValue()) {
                    case 0 -> s += "-fx-background-color: #E0FFFF;";
                    case 2 -> s += "-fx-background-color: #AFEEEE;";
                    case 4 -> s += "-fx-background-color: #7FFFD4;";
                    case 8 -> s += "-fx-background-color: #40E0D0;";
                    case 16 -> s += "-fx-background-color: #48D1CC;";
                    case 32 -> s += "-fx-background-color: #00CED1;";
                    case 64 -> s += "-fx-background-color: #5F9EA0;";
                    case 128 -> s += "-fx-background-color: #4682B4;";
                    case 256 -> s += "-fx-background-color: #0000FF;";
                    case 512 -> s += "-fx-background-color: #0000CD;";
                    case 1024 -> s += "-fx-background-color: #00008B;";
                    case 2048 -> s += "-fx-background-color: #191970;";
                }
                label.setMaxSize(230.0, 230.0);
                label.setStyle(s + "-fx-border-color: #87CEEB; -fx-border-width: 3; -fx-text-fill: #7B68EE; -fx-font-size: 35;");
                label.setAlignment(Pos.CENTER);
                score.setText(gameMap.getScoreGame() + "");
                gameFields.add(label, i, j);
            }
        }

    }

    public void clickW() {
        gameMap.clickW();
        getGameMap().randomNewTile();
        print();
    }

    public void clickA() {
        gameMap.clickA();
        getGameMap().randomNewTile();
        print();
    }

    public void clickS() {
        gameMap.clickS();
        getGameMap().randomNewTile();
        print();
    }

    public void clickD() {
        gameMap.clickD();
        getGameMap().randomNewTile();
        print();
    }
}