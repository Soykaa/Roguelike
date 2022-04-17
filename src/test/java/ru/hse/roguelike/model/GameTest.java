package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import ru.hse.roguelike.view.console_view.ConsoleViewFactory;

public class GameTest {
    @Test
    public void simpleGameTest() throws IOException {
        var game = new Game("");
        game.startGame(false, new ConsoleViewFactory(new DefaultVirtualTerminal()));
        GameState gameState = game.makeAction(Action.CHANGE_EQUIPTION);
        Assertions.assertEquals(gameState.toString(), "IS_RUNNING");
    }
}
