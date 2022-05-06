package ru.hse.roguelike.controller.comands.macro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.SelectedItem;
import ru.hse.roguelike.controller.comands.micro.MainMenuExitCommand;
import ru.hse.roguelike.controller.comands.micro.MainMenuShowRulesCommand;
import ru.hse.roguelike.controller.comands.micro.MainMenuStartGameCommand;
import ru.hse.roguelike.controller.comands.micro.MainMenuStartGameFromFileCommand;
import ru.hse.roguelike.controller.comands.micro.MicroCommand;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;

import java.util.Map;

public class MainMenuEnterMacroCommand extends MacroCommand {

    private final ItemHolder itemHolder;

    Map<SelectedItem, MicroCommand> command;


    public MainMenuEnterMacroCommand(Game game, AbstractViewFactory factory, ItemHolder itemHolder, GameRulesScreenView gameRulesView) {
        this.itemHolder = itemHolder;

        command = Map.of(SelectedItem.EXIT, new MainMenuExitCommand(),
                SelectedItem.SHOW_RULES, new MainMenuShowRulesCommand(gameRulesView),
                SelectedItem.START_GAME, new MainMenuStartGameCommand(game, factory),
                SelectedItem.START_GAME_FROM_FILE, new MainMenuStartGameFromFileCommand(game, factory));

    }


    @Override
    public Screen execute(InputCommand inputCommand) {
        return command.get(itemHolder.getCurrentItem()).execute();
    }

}
