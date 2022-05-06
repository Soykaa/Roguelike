package ru.hse.roguelike.controller.comands.macro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.comands.Command;
import ru.hse.roguelike.controller.comands.micro.MainMenuDownCommand;
import ru.hse.roguelike.controller.comands.micro.MainMenuEmptyCommand;
import ru.hse.roguelike.controller.comands.micro.MainMenuUpCommand;
import ru.hse.roguelike.controller.comands.micro.MicroCommand;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

import java.util.Map;

public class MainMenuMacroCommand extends MacroCommand {

    Map<InputCommand, Command> commands;

    public MainMenuMacroCommand(MainScreenView mainScreenView, Game game, AbstractViewFactory factory, GameRulesScreenView gameRulesView) {
        mainScreenView.showMainScreen();

        ItemHolder itemHolder = new ItemHolder();
        commands = Map.of(InputCommand.UP, new MainMenuUpCommand(itemHolder, mainScreenView),
                InputCommand.DOWN, new MainMenuDownCommand(itemHolder, mainScreenView),
                InputCommand.ENTER, new MainMenuEnterMacroCommand(game, factory, itemHolder, gameRulesView),
                InputCommand.BACKSLASH, new MainMenuEmptyCommand(),
                InputCommand.LEFT, new MainMenuEmptyCommand(),
                InputCommand.RIGHT, new MainMenuEmptyCommand(),
                InputCommand.SPACE, new MainMenuEmptyCommand(),
                InputCommand.ESCAPE, new MainMenuEmptyCommand(),
                InputCommand.UNKNOWN_COMMAND, new MainMenuEmptyCommand());
    }

    @Override
    public Screen execute(InputCommand inputCommand) {
        if (inputCommand == InputCommand.ENTER) {
            return ((MacroCommand)commands.get(inputCommand)).execute(inputCommand);
        }

        return ((MicroCommand)commands.get(inputCommand)).execute();
    }

}
