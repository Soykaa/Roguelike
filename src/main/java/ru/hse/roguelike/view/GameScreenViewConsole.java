package ru.hse.roguelike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.model.Backpack;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Points;

import java.io.IOException;

public class GameScreenViewConsole implements GameScreenView {
    private final Terminal terminal;
    private final Screen screen;
    private final TextGraphics textGraphics;
    private GameCharacter[][] board;

    private final RGB ChineseWhite = new RGB(215, 231, 230);
    private final RGB LightYellow = new RGB(246, 225, 207);
    private final RGB Lavender = new RGB(227, 211, 240);
    private final RGB White = new RGB(255, 255, 255);

    public GameScreenViewConsole(Terminal terminal) throws IOException {
        this.terminal = terminal;
        this.screen = new TerminalScreen(terminal);
        textGraphics = screen.newTextGraphics();
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
                textGraphics.setBackgroundColor(Lavender);
                textGraphics.putString(position, "  ");
                break;
            case SHELTER_PINK:
                textGraphics.setBackgroundColor(ChineseWhite);
                textGraphics.putString(position, "  ");
                break;
            case SHELTER_YELLOW:
                textGraphics.setBackgroundColor(LightYellow);
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
                textGraphics.setForegroundColor(White);
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

    @Override
    public void showBoard(GameCharacter[][] board) throws IOException {
        this.board = board;
        terminal.clearScreen();
        if (board.length == 0) {
            throw new RuntimeException("Board cannot be empty");
        }
        screen.startScreen();
        drawBoardImage(board);
        drawBorders(getAbsoluteBoardSize(board.length, board[0].length));
        screen.refresh();
    }

    @Override
    public void moveCharacter(int xFrom, int yFrom, int xTo, int yTo) throws IOException {
        GameCharacter character = board[xFrom][yFrom];
        removeCharacter(xFrom, yFrom);
        placeCharacter(character, xTo, yTo);
    }

    @Override
    public void setMessage(String message) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.putString(1, boardSize.getRows() + 1, message);
        screen.refresh();
    }

    @Override
    public void removeMessage() throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.putString(1, boardSize.getRows() + 1, String.format("%-20s", " "));
        screen.refresh();
    }

    @Override
    public void removeCharacter(int x, int y) throws IOException {
        TerminalPosition positionFrom = getAbsolutePositionOfBoardCellLeftUpperCorner(x, y);
        drawCharacter(new Empty(), positionFrom);
        screen.refresh();
    }

    @Override
    public void placeCharacter(GameCharacter character, int x, int y) throws IOException {
        TerminalPosition positionTo = getAbsolutePositionOfBoardCellLeftUpperCorner(x, y);
        drawCharacter(character, positionTo);
        screen.refresh();
    }

    @Override
    public void showPoints(int currentPoints, int totalPoints) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.putString(boardSize.getColumns() + 3, 1, "Points: " + currentPoints + " / " + totalPoints);
        screen.refresh();
    }

    @Override
    public void showLives(int lives) throws IOException {
        TerminalSize boardSize = getAbsoluteBoardSize(board.length, board[0].length);
        textGraphics.setBackgroundColor(ANSI.BLACK);
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.putString(boardSize.getColumns() + 3, 3, "Lives: " + lives + " \u2665");
        screen.refresh();
    }

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
        screen.refresh();
    }
}
