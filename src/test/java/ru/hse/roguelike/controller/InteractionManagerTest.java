package ru.hse.roguelike.controller;

import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.view.GameRulesScreenView;
import ru.hse.roguelike.view.GameRulesScreenViewConsole;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.MainScreenViewConsole;

import java.io.IOException;

public class InteractionManagerTest {
    @Test
    public void test() throws IOException, InterruptedException {
        MainScreenView mainScreenView = Mockito.mock(MainScreenViewConsole.class);
        GameRulesScreenView gameRulesScreenView = Mockito.mock(GameRulesScreenViewConsole.class);
        Terminal terminal = Mockito.mock(Terminal.class);
        ItemHolder itemHolder = Mockito.mock(ItemHolder.class);
        InteractionManager interactionManager = new InteractionManager("", mainScreenView, gameRulesScreenView, terminal, itemHolder);
        interactionManager.processCommand(InputCommand.UP);
    }

}
