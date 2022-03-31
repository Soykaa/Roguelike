package ru.hse.roguelike.controller;

import java.io.IOException;
import java.util.Arrays;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.GameCharacter;
import ru.hse.roguelike.view.GameScreenView;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.GameRulesScreenView;

public class InteractionManager {
    private Screen screen = Screen.MAIN_MENU;
    private final MainScreenView mainScreenView;
    private final ItemHolder itemHolder = new ItemHolder();
    private final GameRulesScreenView gameRulesView;
    private final GameScreenView gameView;
    public boolean isRunning = true;

    public InteractionManager(MainScreenView mainScreenView, GameRulesScreenView gameRulesScreenView, GameScreenView gameView) {
        this.mainScreenView = mainScreenView;
        this.gameRulesView = gameRulesScreenView;
        this.gameView = gameView;
        this.mainScreenView.showMainScreen();
    }

    public void processCommand(InputCommand command) throws IOException, InterruptedException {
        switch (screen) {
            case MAIN_MENU -> processCommandMainMenu(command);
            case GAME_RULES -> processCommandRules(command);
            case GAME -> processCommandGame(command);
        }
    }

    private void processCommandRules(InputCommand command) {
        if (command == InputCommand.ESCAPE) {
            mainScreenView.showMainScreen();
            screen = Screen.MAIN_MENU;
        }
    }

    private void processCommandGame(InputCommand command) {
        if (command == InputCommand.ESCAPE) {
            mainScreenView.showMainScreen();
            screen = Screen.MAIN_MENU;
        }
    }

    private void processCommandMainMenu(InputCommand command) throws IOException, InterruptedException {
        switch (command) {
            case UP, DOWN -> mainScreenView.setSelectedItem(itemHolder.setSelectedItem(command));
            case ENTER -> {
                switch (itemHolder.getCurrentItem()) {
                    case EXIT -> isRunning = false;
                    case SHOW_RULES -> {
                        screen = Screen.GAME_RULES;
                        gameRulesView.showGameRules();
                    }
                    case START_GAME, START_GAME_FROM_FILE -> {
                        screen = Screen.GAME;
                        var board = new GameCharacter[30][30];
                        for (GameCharacter[] gameCharacters : board) {
                            Arrays.fill(gameCharacters, GameCharacter.Empty);
                        }
                        board[5][5] = GameCharacter.EnemyWeak;
                        board[16][29] = GameCharacter.EnemyStrong;
                        board[10][10] = GameCharacter.Obstacle;
                        board[20][25] = GameCharacter.ShelterGreen;
                        board[22][7] = GameCharacter.ShelterYellow;
                        board[12][14] = GameCharacter.ShelterBlue;
                        board[13][14] = GameCharacter.ShelterBlue;
                        board[15][28] = GameCharacter.Points;
                        board[10][27] = GameCharacter.InventoryAttack;
                        board[8][22] = GameCharacter.InventoryProtect;
                        board[10][9] = GameCharacter.Player;
                        gameView.showBoard(board);
                    }
                }
            }
        }
    }
}
