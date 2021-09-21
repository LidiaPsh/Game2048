import Model.GameMap;
import Model.Tile;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GameMapTest {

    @org.junit.Test
    public void clickW() {//тест на нажатие клавишы
        GameMap gameMap = new GameMap();
        gameMap.clickW();
        Tile[][] tiles = gameMap.getTiles();
        int howNums = 0;
        for (int i = 0; i < 4; i++) {
            if (tiles[i][0].getValue() != 0) {
                howNums++;
            }
        }
        boolean chek = true;
        if (howNums > 2 || howNums == 0) {//может быть 1(когда рандомнуло в одной линии и они привратились или в 4( по 2 двойки)
            // или в 8(и по 2 четверки соответсвенно)) или 2, когда зарандомило числа в разных линиях, проверям только верхнюю линию
            chek = false;
        }
        assertTrue(chek);
    }

    @org.junit.Test
    public void clickS() {// допилить
        GameMap gameMap = new GameMap();
        gameMap.clickS();
        Tile[][] tiles = gameMap.getTiles();
        int howNums = 0;
        for (int i = 0; i < 4; i++) {
            if (tiles[i][3].getValue() != 0) {
                howNums++;
            }
        }
        boolean chek = true;
        if (howNums > 2 || howNums == 0) {//может быть 1(когда рандомнуло в одной линии и они привратились или в 4( по 2 двойки)
            // или в 8(и по 2 четверки соответсвенно)) или 2, когда зарандомило числа в разных линиях, проверям только нижнию линию
            chek = false;
        }
        assertTrue(chek);
    }


    @org.junit.Test
    public void chekWin() {
        GameMap gameMap = new GameMap();
        assertFalse(gameMap.chekWin());//проверка на победу когда нет плитки 2048
        gameMap.getTiles()[0][0] = new Tile(2048);
        assertTrue(gameMap.chekWin());//проверка на победу когда есть плитка 2048
    }

    @org.junit.Test
    public void lossChek() {
        GameMap gameMap = new GameMap();
        assertFalse(gameMap.lossChek());//проверка на проигрышь когда его нет
        gameMap.getTiles()[0][0] = new Tile(2048);
        gameMap.getTiles()[0][1] = new Tile(512);
        gameMap.getTiles()[0][2] = new Tile(4);
        gameMap.getTiles()[0][3] = new Tile(8);
        gameMap.getTiles()[1][0] = new Tile(2);
        gameMap.getTiles()[1][1] = new Tile(2048);
        gameMap.getTiles()[1][2] = new Tile(64);
        gameMap.getTiles()[1][3] = new Tile(8);
        gameMap.getTiles()[2][0] = new Tile(32);
        gameMap.getTiles()[2][1] = new Tile(2);
        gameMap.getTiles()[2][2] = new Tile(2048);
        gameMap.getTiles()[2][3] = new Tile(1024);
        gameMap.getTiles()[3][0] = new Tile(8);
        gameMap.getTiles()[3][1] = new Tile(16);
        gameMap.getTiles()[3][2] = new Tile(4);
        gameMap.getTiles()[3][3] = new Tile(2048);
        assertFalse(gameMap.lossChek());//проверка на проигрышь когда есть сложения
        gameMap.getTiles()[0][3] = new Tile(16);
        assertTrue(gameMap.lossChek());//проверка на проигрышь когда он есть
    }

    @org.junit.Test
    public void clickA() {
        GameMap gameMap = new GameMap();
        gameMap.clickA();
        Tile[][] tiles = gameMap.ColumnToRow();
        int howNums = 0;
        for (int i = 0; i < 4; i++) {
            if (tiles[i][0].getValue() != 0) {
                howNums++;
            }
        }
        boolean chek = true;
        if (howNums > 2 || howNums == 0) {//может быть 1(когда рандомнуло в одной линии и они привратились или в 4( по 2 двойки)
            // или в 8(и по 2 четверки соответсвенно)) или 2, когда зарандомило числа в разных линиях, проверям только верхнюю линию
            // так как когда мы повернули в право, то лево стало верхом
            chek = false;
        }
        assertTrue(chek);
    }

    @org.junit.Test
    public void clickD() {
        GameMap gameMap = new GameMap();
        gameMap.clickD();
        Tile[][] tiles = gameMap.ColumnToRow();
        int howNums = 0;
        for (int i = 0; i < 4; i++) {
            if (tiles[i][3].getValue() != 0) {
                howNums++;
            }
        }
        boolean chek = true;
        if (howNums > 2 || howNums == 0) {//может быть 1(когда рандомнуло в одной линии и они привратились или в 4( по 2 двойки)
            // или в 8(и по 2 четверки соответсвенно)) или 2, когда зарандомило числа в разных линиях, проверям только нижнию линию,
            // так как у меня все на поворотах и когда мы поворачиваем в права оно становиться низом
            chek = false;
        }
        assertTrue(chek);
    }

    @org.junit.Test
    public void scoreM(){
        GameMap gameMap = new GameMap();
        assertEquals(0,gameMap.getScoreGame());//в начале игры
        gameMap.scoreM(new Tile(2),new Tile(2));
        assertEquals(4,gameMap.getScoreGame());// после сложения 2 плиток со значеним 2
        gameMap.scoreM(new Tile(4),new Tile(4));
        assertEquals(12,gameMap.getScoreGame());// после сложения 2 плиток со значеним 4 и в результате уже есть 4
    }

}