package ru.hse.roguelike.controller.command.macro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.command.micro.MainMenuDownCommand;
import ru.hse.roguelike.controller.command.micro.MainMenuEmptyCommand;
import ru.hse.roguelike.controller.command.micro.MainMenuEnterCommand;
import ru.hse.roguelike.controller.command.micro.MainMenuUpCommand;
import ru.hse.roguelike.controller.command.micro.MicroCommand;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

import java.util.Map;

/**
 * Represents commands, connected with game menu screen displaying, with complicated logic.
 **/
public class MainMenuMacroCommand implements MacroCommand {
    private final Map<InputCommand, MicroCommand> commands;

    /**
     * Creates new MainMenuMacroCommand instance.
     *
     * @param game           game itself
     * @param mainScreenView main screen view
     * @param factory        abstract view factory
     * @param gameRulesView  game rules view
     **/
    public MainMenuMacroCommand(MainScreenView mainScreenView, Game game, AbstractViewFactory factory, GameRulesScreenView gameRulesView) {
        mainScreenView.showMainScreen();

        ItemHolder itemHolder = new ItemHolder();
        commands = Map.of(InputCommand.UP, new MainMenuUpCommand(itemHolder, mainScreenView),
                InputCommand.DOWN, new MainMenuDownCommand(itemHolder, mainScreenView),
                InputCommand.ENTER, new MainMenuEnterCommand(game, factory, itemHolder, gameRulesView),
                InputCommand.BACKSLASH, new MainMenuEmptyCommand(),
                InputCommand.LEFT, new MainMenuEmptyCommand(),
                InputCommand.RIGHT, new MainMenuEmptyCommand(),
                InputCommand.SPACE, new MainMenuEmptyCommand(),
                InputCommand.ESCAPE, new MainMenuEmptyCommand(),
                InputCommand.UNKNOWN_COMMAND, new MainMenuEmptyCommand());
    }

    /**
     * Executes passed command.
     *
     * @param inputCommand passed command
     * @return corresponding screen
     **/
    @Override
    public Screen execute(InputCommand inputCommand) {
        return commands.get(inputCommand).execute();
    }
}
