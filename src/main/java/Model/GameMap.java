package Model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class GameMap {

    private Tile[][] tiles = new Tile[4][4];
    private int scoreGame;
    private boolean chekChange = true;

    public GameMap() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile(0);
            }
        }

        randomNewTile();
        randomNewTile();
    }

    @Override
    public String toString() {
        return "GameMap{" +
                "tiles=" + Arrays.toString(tiles) +
                ", scoreGame=" + scoreGame +
                ", chekChange=" + chekChange +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMap gameMap = (GameMap) o;
        return scoreGame == gameMap.scoreGame && Arrays.equals(tiles, gameMap.tiles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(scoreGame, chekChange);
        result = 31 * result + Arrays.hashCode(tiles);
        return result;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getScoreGame() {
        return scoreGame;
    }

    public void setScoreGame(int scoreGame) {
        this.scoreGame = scoreGame;
    }

    public boolean isChekChange() {
        return chekChange;
    }

    public void setChekChange(boolean chekChange) {
        this.chekChange = chekChange;
    }

    public void randomNewTile() {
        if (chekChange) {
            Random random = new Random();
            int x, y, what;
            while (true) {
                x = random.nextInt(4);
                y = random.nextInt(4);
                Tile tile = new Tile(0);
                if (tiles[x][y].equals(tile)) {
                    what = random.nextInt(10);
                    if (what > 8) {
                        tiles[x][y] = new Tile(4);
                    } else {
                        tiles[x][y] = new Tile(2);
                    }
                    break;
                }
            }
        }
    }

    public boolean lossChek() {
        for (Tile[] it : tiles) {
            Tile buff = new Tile(0);
            for (Tile tile : it) {
                if (tile.getValue() == 0 || tile.equals(buff)) {
                    return false;
                } else {
                    buff = tile;
                }
            }
        }
        tiles = ColumnToRow();
        for (Tile[] it : tiles) {
            Tile buff = new Tile(0);
            for (Tile tile : it) {
                if (tile.getValue() == 0 || tile.getValue() == buff.getValue()) {
                    tiles = RowToColumn();
                    return false;
                } else {
                    buff = tile;
                }
            }
        }
        tiles = RowToColumn();
        return true;
    }

    public void clickW() {
        chekChange = false;
        for (Tile[] it : tiles) {
            Tile buff = new Tile(0);
            boolean chekFirst = true;
            for (Tile tile : it) {
                if (tile.getValue() != 0) {
                    if (chekFirst) {
                        buff = tile;
                        chekFirst = false;
                        continue;
                    }
                    if (tile.getValue() == buff.getValue()) {
                        scoreM(buff, tile);
                        buff.add(tile);
                        tile.setValue(0);
                        buff = new Tile(0);
                        chekChange = true;
                    } else {
                        buff = tile;
                    }
                }
            }
            sortTilesTop(it);
        }
    }

    public void clickS() {
        chekChange = false;
        for (Tile[] it : tiles) {
            Tile buff = new Tile(0);
            boolean chekFirst = true;
            for (int i= it.length-1;i>-1;i--) {
                Tile tile=it[i];
                if (tile.getValue() != 0) {
                    if (chekFirst) {
                        buff = tile;
                        chekFirst = false;
                        continue;
                    }
                    if (tile.getValue() == buff.getValue()) {
                        scoreM(buff, tile);
                        buff.add(tile);
                        tile.setValue(0);
                        buff = new Tile(0);
                        chekChange = true;
                    } else {
                        buff = tile;
                    }
                }
            }
            sortTilesDown(it);
        }
    }

    public void clickA() {
        tiles = ColumnToRow();
        clickW();
        tiles = RowToColumn();
    }

    public void clickD() {
        tiles = ColumnToRow();
        clickS();
        tiles = RowToColumn();
    }

    public boolean chekWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void scoreM(Tile first, Tile second) {
        scoreGame += first.getValue() + second.getValue();
    }


    public void sortTilesTop(Tile[] tiles) {
        for (int i = 1; i < tiles.length; i++) {
            if (tiles[i].getValue() != 0) {
                for (int j = i; j > 0; j--) {
                    if (tiles[j - 1].getValue() == 0) {
                        tiles[j - 1] = tiles[j];
                        tiles[j] = new Tile(0);
                        chekChange = true;
                    }
                }
            }
        }
    }

    public void sortTilesDown(Tile[] tiles) {
        for (int i = tiles.length - 1; i > -1; i--) {
            if (tiles[i].getValue() != 0) {
                for (int j = i; j < tiles.length - 1; j++) {
                    if (tiles[j + 1].getValue() == 0) {
                        tiles[j + 1] = tiles[j];
                        tiles[j] = new Tile(0);
                        chekChange = true;
                    }
                }
            }
        }
    }

    public Tile[][] ColumnToRow() {
        Tile[][] newTile = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newTile[i][j] = tiles[j][i];
            }
        }
        return newTile;
    }

    public Tile[][] RowToColumn() {
        Tile[][] newTile = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newTile[j][i] = tiles[i][j];
            }
        }
        return newTile;
    }
}
