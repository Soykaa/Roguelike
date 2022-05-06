package ru.hse.roguelike.controller;

import java.io.IOException;

import ru.hse.roguelike.controller.comands.macro.MacroCommand;
import ru.hse.roguelike.controller.comands.macro.GameMacroCommand;
import ru.hse.roguelike.controller.comands.macro.MainMenuMacroCommand;
import ru.hse.roguelike.controller.comands.macro.RulesMacroCommand;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

/**
 * Responsible for all user actions outside the gameplay itself,
 * such as actions on the initial screen (request to start the game / display the rules / exit the application),
 * as well as on the screen with the rules of the game.
 **/
public class InteractionManager {
    private Screen screen = Screen.MAIN_MENU;

    private final MacroCommand mainMenuCommand;
    private final MacroCommand gameRulesCommand;
    private final MacroCommand gameCommand;

    /**
     * Creates new InteractionManager instance.
     *
     * @param filesPath config files path
     * @param factory   AbstractViewFactory Implementation
     * @throws IOException in case of view error
     **/
    public InteractionManager(String filesPath, AbstractViewFactory factory) throws IOException {
        var game = new Game(filesPath);
        var mainScreenView = factory.createMainScreenView();

        gameCommand = new GameMacroCommand(game, mainScreenView);

        gameRulesCommand = new RulesMacroCommand(mainScreenView);

        mainMenuCommand = new MainMenuMacroCommand(mainScreenView, game, factory, factory.createGameRulesScreenView());

    }

    /**
     * Creates new InteractionManager instance.
     * Used only for tests to provide opportunity to mock class Game.
     *
     * @param factory AbstractViewFactory Implementation
     * @param game    Game object
     * @throws IOException in case of view error
     */
    public InteractionManager(AbstractViewFactory factory, Game game) throws IOException {
        var mainScreenView = factory.createMainScreenView();

        gameCommand = new GameMacroCommand(game, mainScreenView);
        mainScreenView.showMainScreen();

        gameRulesCommand = new RulesMacroCommand(mainScreenView);

        mainMenuCommand = new MainMenuMacroCommand(mainScreenView, game, factory, factory.createGameRulesScreenView());
    }

    /**
     * Returns current screen.
     *
     * @return screen
     **/
    public Screen getScreen() {
        return screen;
    }

    /**
     * Handles a keystroke on initial / rules / game screen.
     *
     * @param command keystroke to handle
     **/
    public void processCommand(InputCommand command) {
        switch (screen) {
            case MAIN_MENU:
                screen = mainMenuCommand.execute(command);
                break;
            case GAME_RULES:
                screen = gameRulesCommand.execute(command);
                break;
            case GAME:
                screen = gameCommand.execute(command);
                break;
        }
    }
}
