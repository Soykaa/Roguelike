package ru.hse.roguelike.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.input.InputCommand;
import ru.hse.roguelike.view.ApplicationView;
import ru.hse.roguelike.view.ApplicationViewConsole;
import ru.hse.roguelike.view.GameRulesView;
import ru.hse.roguelike.view.GameRulesViewConsole;

public class InteractionManager {
    private State state = State.MAIN_MENU;
    private final ApplicationView applicationView;
    private final ItemHolder itemHolder = new ItemHolder();
    private final GameRulesView gameRulesView;
    public boolean isRunning = true;

    public InteractionManager(Terminal terminal, TextGraphics textGraphics) {
        applicationView = new ApplicationViewConsole(terminal, textGraphics);
        gameRulesView = new GameRulesViewConsole(terminal, textGraphics);
        applicationView.showInitialScreen();
    }

    public void processCommand(InputCommand command) {
        switch (state) {
            case MAIN_MENU -> processCommandMainMenu(command);
            case GAME_RULES -> processCommandRules(command);
        }
    }

    private void processCommandRules(InputCommand command) {
        if (command == InputCommand.ESCAPE) {
            applicationView.showInitialScreen();
            state = State.MAIN_MENU;
        }
    }

    private void processCommandMainMenu(InputCommand command) {
        if (command != InputCommand.ENTER) {
            applicationView.setSelectedItem(itemHolder.setSelectedItem(command));
            return;
        }
        if (itemHolder.getCurrentItem() == SelectedItem.EXIT) {
            isRunning = false;
            return;
        }
        if (itemHolder.getCurrentItem() == SelectedItem.SHOW_RULES) {
            state = State.GAME_RULES;
            gameRulesView.showGameRules();
            return;
        }
        if (itemHolder.getCurrentItem() == SelectedItem.START_GAME) {
            // TODO
        }
    }

}
