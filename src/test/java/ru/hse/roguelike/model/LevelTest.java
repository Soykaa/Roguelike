package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.*;
import ru.hse.roguelike.view.console_view.GameScreenViewConsole;

import java.io.IOException;
import java.util.HashMap;

public class LevelTest {
    @Test
    public void simpleLevelTest() throws IOException {
        var board = new GameCharacter[2][2];
        var player = new Player(9);
        board[0][0] = player;
        board[1][0] = new Points(5);
        board[0][1] = new Inventory(InventoryItem.DESTROY);
        board[1][1] = new Obstacle();
        var level = new Level(board, new GameScreenViewConsole(new DefaultTerminalFactory().createTerminal()), player, new HashMap<>(), CharacterType.SHELTER_LAVENDER, 5);
        level.moveCharacters(1, 0);
        Assertions.assertEquals(5, player.getPoints());
        Assertions.assertEquals(Result.IS_RUNNING, level.destroyObstacle());
        level.moveCharacters(-1, 1);
        Assertions.assertEquals(2, player.getBackpack().getAllItems().size());
        level.changeEquiption();
        Assertions.assertEquals("DESTROY", player.getBackpack().getActiveItem().getType().toString());
        level.moveCharacters(1, 0);
        Assertions.assertEquals(0, player.getCurrentCoordinates().getX());
        Assertions.assertEquals(1, player.getCurrentCoordinates().getY());
        level.destroyObstacle();
        level.moveCharacters(1, 0);
        Assertions.assertEquals(1, player.getCurrentCoordinates().getX());
        Assertions.assertEquals(1, player.getCurrentCoordinates().getY());
    }
}
