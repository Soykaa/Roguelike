package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.SelectedItem;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;

import java.util.Map;

public class MainMenuEnterCommand implements MicroCommand {

    private final ItemHolder itemHolder;

    Map<SelectedItem, MicroCommand> command;


    public MainMenuEnterCommand(Game game, AbstractViewFactory factory, ItemHolder itemHolder, GameRulesScreenView gameRulesView) {
        this.itemHolder = itemHolder;

        command = Map.of(SelectedItem.EXIT, new MainMenuExitCommand(),
                SelectedItem.SHOW_RULES, new MainMenuShowRulesCommand(gameRulesView),
                SelectedItem.START_GAME, new MainMenuStartGameCommand(game, factory),
                SelectedItem.START_GAME_FROM_FILE, new MainMenuStartGameFromFileCommand(game, factory));

    }


    @Override
    public Screen execute() {
        return command.get(itemHolder.getCurrentItem()).execute();
    }

}
