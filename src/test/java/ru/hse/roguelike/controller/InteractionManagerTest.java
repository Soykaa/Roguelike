package ru.hse.roguelike.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.GameState;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;
import ru.hse.roguelike.view.console_view.ConsoleViewFactory;
import ru.hse.roguelike.view.console_view.GameRulesScreenViewConsole;
import ru.hse.roguelike.view.abstract_view.MainScreenView;
import ru.hse.roguelike.view.console_view.MainScreenViewConsole;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InteractionManagerTest {
    MainScreenView mainScreenView;
    GameRulesScreenView gameRulesScreenView;
    ConsoleViewFactory factory;

    @BeforeEach
    public void setup() throws IOException {
        factory = Mockito.mock(ConsoleViewFactory.class);
        mainScreenView = Mockito.mock(MainScreenViewConsole.class);
        gameRulesScreenView = Mockito.mock(GameRulesScreenViewConsole.class);
        when(factory.createGameRulesScreenView()).thenReturn(gameRulesScreenView);
        when(factory.createMainScreenView()).thenReturn(mainScreenView);
    }

    @Test
    public void testMainMenuScreenDown() throws IOException {
        InteractionManager interactionManager = new InteractionManager("", factory);
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
        InteractionManager interactionManager = new InteractionManager("", factory);
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
        InteractionManager interactionManager = new InteractionManager("", factory);
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
        when(game.startGame(false, factory)).thenReturn(GameState.IS_RUNNING);
        InteractionManager interactionManager = new InteractionManager(factory, game);
        interactionManager.processCommand(InputCommand.ENTER);
        verify(game, times(1)).startGame(false, factory);
        Assertions.assertSame(interactionManager.getScreen(), Screen.GAME);
    }

    @Test
    public void testEndGame() throws IOException {
        Game game = Mockito.mock(Game.class);
        when(game.startGame(false, factory)).thenReturn(GameState.IS_RUNNING);
        doReturn(GameState.VICTORY).when(game).manageGame(any(Action.class));
        InteractionManager interactionManager = new InteractionManager(factory, game);
        interactionManager.processCommand(InputCommand.ENTER);
        interactionManager.processCommand(InputCommand.UP);
        verify(mainScreenView, times(3)).showMainScreen();
        Assertions.assertSame(interactionManager.getScreen(), Screen.MAIN_MENU);
    }
}
