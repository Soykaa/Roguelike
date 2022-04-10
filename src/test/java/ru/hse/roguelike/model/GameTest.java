package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import ru.hse.roguelike.view.console_view.ConsoleViewFactory;

public class GameTest {
    @Test
    public void simpleGameTest() throws IOException {
        var game = new Game("");
        game.startGame(false, new ConsoleViewFactory( new DefaultVirtualTerminal()));
        Result result = game.makeAction(Action.CHANGE_EQUIPTION);
        Assertions.assertEquals(result.toString(), "IS_RUNNING");
    }
}
