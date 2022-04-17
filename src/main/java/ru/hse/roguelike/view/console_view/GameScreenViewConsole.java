package ru.hse.roguelike.view.console_view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import ru.hse.roguelike.model.Backpack;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Points;
import ru.hse.roguelike.view.abstract_view.GameScreenView;

/**
 * Game screen view.
 **/
public class GameScreenViewConsole implements GameScreenView {
    private final Terminal terminal;
    private final TextGraphics textGraphics;
    private GameCharacter[][] board;

    private final RGB chineseWhite = new RGB(215, 231, 230);
    private final RGB lightYellow = new RGB(246, 225, 207);
    private final RGB lavender = new RGB(227, 211, 240);
    private final RGB white = new RGB(255, 255, 255);

    /**
     * Creates new GameScreenViewConsole instance.
     *
     * @param terminal terminal
     * @throws IOException in case of view error
     **/
    public GameScreenViewConsole(Terminal terminal) throws IOException {
        this.terminal = terminal;
        textGraphics = terminal.newTextGraphics();
    }

    private TerminalPosition getAbsolutePositionOfBoardCellLeftUpperCorner(int relativeX, int relativeY) {
        return new TerminalPosition(3 * relativeX + 1, 2 * relativeY + 1);
    }

    private TerminalSize getAbsoluteBoardSize(int relativeWidth, int relativeHeight) {
        return new TerminalSize(3 * relativeWidth + 1, 2 * relativeHeight + 1);
    }

    private void drawCharacter(GameCharacter character, TerminalPosition position) {
        textGraphics.setForegroundColor(ANSI.WHITE);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        switch (character.getCharacterType()) {
            case ENEMY_WEAK:
            case ENEMY_STRONG:
                textGraphics.setForegroundColor(ANSI.RED_BRIGHT);
                textGraphics.putString(position, "\uC6C3");
                break;
            case OBSTACLE:
                textGraphics.setBackgroundColor(ANSI.BLACK_BRIGHT);
                textGraphics.setForegroundColor(ANSI.BLACK);
                textGraphics.putString(position, "xx");
                break;
            case EMPTY:
                textGraphics.setBackgroundColor(ANSI.BLACK);
                textGraphics.putString(position, "  ");
                break;
            case SHELTER_LAVENDER:
                textGraphics.setBackgroundColor(lavender);
                textGraphics.putString(position, "  ");
                break;
            case SHELTER_PINK:
                textGraphics.setBackgroundColor(chineseWhite);
                textGraphics.putString(position, "  ");
                break;
            case SHELTER_YELLOW:
                textGraphics.setBackgroundColor(lightYellow);
                textGraphics.putString(position, "  ");
                break;
            case POINTS:
                textGraphics.setForegroundColor(ANSI.GREEN_BRIGHT);
                textGraphics.putString(position, "+" + ((Points) character).getNumberOfPoints());
                break;
            case INVENTORY:
                textGraphics.setForegroundColor(ANSI.WHITE);
                textGraphics.putString(position, "??");
                break;
            case PLAYER:
                textGraphics.setForegroundColor(white);
                textGraphics.putString(position, "\uC6C3");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + character);
        }
    }

    private void drawBoardImage(GameCharacter[][] board) {
        int boardWidth = board.length;
        int boardHeight = board[0].length;

        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < boardHeight; row++) {
                TerminalPosition currentPosition = getAbsolutePositionOfBoardCellLeftUpperCorner(col, row);
                drawCharacter(board[col][row], currentPosition);
            }
        }
    }

    private void drawBorders(TerminalSize boardSize) {
        int boardHeight = boardSize.getRows();
        int boardWidth = boardSize.getColumns();
        textGraphics.setForegroundColor(ANSI.WHITE);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        for (int i = 0; i <= boardHeight; i += 2) {
            textGraphics.drawLine(1, i, boardWidth - 2, i, '-');
        }
        for (int i = 0; i <= boardWidth; i += 3) {
            textGraphics.drawLine(i, 1, i, boardHeight - 2, '|');
        }
        textGraphics.setCharacter(0, 0, '\u250C');
        textGraphics.setCharacter(boardWidth - 1, boardHeight - 1, '\u2518');
        textGraphics.setCharacter(0, boardHeight - 1, '\u2514');
        textGraphics.setCharacter(boardWidth - 1, 0, '\u2510');
    }

    /**
     * Shows game board.
     *
     * @param board board to show
     * @throws IOException in case of view error
     **/
    @Override
    public void showBoard(GameCharacter[][] board) throws IOException {
        this.board = board;
        terminal.clearScreen();
        if (board.length == 0) {
            throw new RuntimeException("Board cannot be empty");
        }
        drawBoardImage(board);
        drawBorders(getAbsoluteBoardSize(board.length, board[0].length));
        terminal.flush();
    }

    /**
     * Moves game character.
     *
     * @param xFrom old coordinate on axis X
     * @param yFrom old coordinate on axis Y
     * @param xTo   new coordinate on axis X
     * @param yTo   new coordinate on axis Y
     * @throws IOException in case of view error
     **/
    @Override
    public void moveCharacter(int xFrom, int yFrom, int xTo, int yTo) throws IOException {
        GameCharacter character = board[xFrom][yFrom];
        removeCharacter(xFrom, yFrom);
        placeCharacter(character, xTo, yTo);
    }

    /**
     * Sets message to show.
     *
     * @param message message to show
     * @throws IOException in case of view error
     **/
    @Override
    public void setMessage(String message) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.putString(1, boardSize.getRows() + 1, message);
        terminal.flush();
    }

    /**
     * Removes shown message.
     *
     * @throws IOException in case of view error
     **/
    @Override
    public void removeMessage() throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.putString(1, boardSize.getRows() + 1, String.format("%-20s", " "));
        terminal.flush();
    }

    /**
     * Removes character from cell with passed coordinates.
     *
     * @param x coordinate on axis X
     * @param y coordinate on axis Y
     * @throws IOException in case of view error
     **/
    @Override
    public void removeCharacter(int x, int y) throws IOException {
        TerminalPosition positionFrom = getAbsolutePositionOfBoardCellLeftUpperCorner(x, y);
        drawCharacter(new Empty(), positionFrom);
        terminal.flush();
    }

    /**
     * Places character from cell with passed coordinates on the game board.
     *
     * @param character character to place
     * @param x         coordinate on axis X
     * @param y         coordinate on axis Y
     * @throws IOException in case of view error
     **/
    @Override
    public void placeCharacter(GameCharacter character, int x, int y) throws IOException {
        TerminalPosition positionTo = getAbsolutePositionOfBoardCellLeftUpperCorner(x, y);
        drawCharacter(character, positionTo);
        terminal.flush();
    }

    /**
     * Shows points.
     *
     * @param currentPoints current number of points
     * @param totalPoints   total number of points
     * @throws IOException in case of view error
     **/
    @Override
    public void showPoints(int currentPoints, int totalPoints) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.putString(boardSize.getColumns() + 3, 1, "Points: " + currentPoints + " / " + totalPoints);
        terminal.flush();
    }

    /**
     * Shows lives.
     *
     * @param lives number of lives
     * @throws IOException in case of view error
     **/
    @Override
    public void showLives(int lives) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.putString(boardSize.getColumns() + 3, 3, "Lives: " + lives + " \u2665");
        terminal.flush();
    }

    /**
     * Shows backpack with current active item.
     *
     * @param backpack backpack
     * @throws IOException in case of view error
     **/
    @Override
    public void showBackpack(Backpack backpack) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setForegroundColor(ANSI.WHITE);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.putString(boardSize.getColumns() + 3, 7, "Backpack:");
        int row = 9;
        for (var backpackItem : backpack.getAllItems()) {
            if (backpackItem.getType() == backpack.getActiveItem().getType()) {
                textGraphics.setBackgroundColor(ANSI.CYAN);
            } else {
                textGraphics.setBackgroundColor(ANSI.BLACK);
            }
            textGraphics.setForegroundColor(ANSI.WHITE);
            textGraphics.putString(boardSize.getColumns() + 3, row, backpackItem.getType().name());
            row += 1;
        }
        terminal.flush();
    }
}