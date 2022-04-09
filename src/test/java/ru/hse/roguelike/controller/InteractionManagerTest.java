package ru.hse.roguelike.controller;

import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.Result;
import ru.hse.roguelike.view.GameRulesScreenView;
import ru.hse.roguelike.view.GameRulesScreenViewConsole;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.MainScreenViewConsole;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class InteractionManagerTest {
    MainScreenView mainScreenView;
    GameRulesScreenView gameRulesScreenView;

    @BeforeEach
    public void setup() {
        mainScreenView = Mockito.mock(MainScreenViewConsole.class);
        gameRulesScreenView = Mockito.mock(GameRulesScreenViewConsole.class);
    }

    @Test
    public void testMainMenuScreenDown() throws IOException {
        InteractionManager interactionManager = new InteractionManager("", mainScreenView, null, null);
        verify(mainScreenView, times(1)).showMainScreen();
        Assertions.assertSame(interactionManager.getScreen(), Screen.MAIN_MENU);
        interactionManager.processCommand(InputCommand.DOWN);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.START_GAME_FROM_FILE);
        interactionManager.processCommand(InputCommand.DOWN);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.SHOW_RULES);
        interactionManager.processCommand(InputCommand.DOWN);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.EXIT);
        interactionManager.processCommand(InputCommand.DOWN);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.START_GAME);
    }

    @Test
    public void testMainMenuScreenUp() throws IOException {
        InteractionManager interactionManager = new InteractionManager("", mainScreenView, null, null);
        verify(mainScreenView, times(1)).showMainScreen();
        Assertions.assertSame(interactionManager.getScreen(), Screen.MAIN_MENU);
        interactionManager.processCommand(InputCommand.UP);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.EXIT);
        interactionManager.processCommand(InputCommand.UP);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.SHOW_RULES);
        interactionManager.processCommand(InputCommand.UP);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.START_GAME_FROM_FILE);
        interactionManager.processCommand(InputCommand.UP);
        verify(mainScreenView, times(1)).setSelectedItem(SelectedItem.START_GAME);
    }

    @Test
    public void testMainMenuAndGameRulesMenu() throws IOException {
        InteractionManager interactionManager = new InteractionManager("", mainScreenView, gameRulesScreenView, null);
        interactionManager.processCommand(InputCommand.DOWN);
        interactionManager.processCommand(InputCommand.DOWN);
        interactionManager.processCommand(InputCommand.ENTER);
        verify(gameRulesScreenView, times(1)).showGameRules();
        Assertions.assertSame(interactionManager.getScreen(), Screen.GAME_RULES);
        interactionManager.processCommand(InputCommand.ESCAPE);
        verify(mainScreenView, times(2)).showMainScreen();
        Assertions.assertSame(interactionManager.getScreen(), Screen.MAIN_MENU);
    }

    @Test
    public void testMainMenuAndGame() throws IOException {
        Game game = Mockito.mock(Game.class);
        InteractionManager interactionManager = new InteractionManager(mainScreenView, gameRulesScreenView, null, game);
        interactionManager.processCommand(InputCommand.ENTER);
        verify(game, times(1)).startGame(false, null);
        Assertions.assertSame(interactionManager.getScreen(), Screen.GAME);
    }

    @Test
    public void testEndGame() throws IOException {
        Game game = Mockito.mock(Game.class);
        doReturn(Result.VICTORY).when(game).manageGame(any(Action.class));
        InteractionManager interactionManager = new InteractionManager(mainScreenView, gameRulesScreenView, null, game);
        interactionManager.processCommand(InputCommand.ENTER);
        interactionManager.processCommand(InputCommand.UP);
        verify(mainScreenView, times(2)).showMainScreen();
        Assertions.assertSame(interactionManager.getScreen(), Screen.MAIN_MENU);
    }

}
