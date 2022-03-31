package ru.hse.roguelike.view;

import static java.lang.Thread.sleep;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import ru.hse.roguelike.model.GameCharacter;

public class GameScreenViewConsole implements GameScreenView {
    private final Terminal terminal;
    private TextGraphics textGraphics;

    public GameScreenViewConsole(Terminal terminal, TextGraphics textGraphics) {
        this.terminal = terminal;
        this.textGraphics = textGraphics;
    }

    @Override
    public void showBoard(GameCharacter[][] board) throws IOException, InterruptedException {
        terminal.clearScreen();
        if (board.length == 0) {
            throw new RuntimeException("Board cannot be empty");
        }
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        textGraphics = screen.newTextGraphics();
        System.out.println("Size " + textGraphics.getSize().toString());
        System.out.println("Terminal " + terminal.getTerminalSize().toString());

        var textImage = new BasicTextImage(board.length, board[0].length);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                TextCharacter character;
                System.out.println("Setting " + i + " " + j + " " + board[i][j]);
                switch (board[i][j]) {
                    case EnemyWeak -> character = TextCharacter.fromCharacter('o', ANSI.RED_BRIGHT, ANSI.BLACK_BRIGHT)[0];
                    case EnemyStrong -> character = TextCharacter.fromCharacter('o', ANSI.RED, ANSI.BLACK_BRIGHT)[0];
                    case Obstacle -> character = TextCharacter.fromCharacter(' ', ANSI.BLACK, ANSI.BLACK)[0];
                    case Empty -> character = TextCharacter.fromCharacter(' ', ANSI.BLACK_BRIGHT, ANSI.BLACK_BRIGHT)[0];
                    case ShelterGreen -> character = TextCharacter.fromCharacter(' ', ANSI.GREEN, ANSI.GREEN)[0];
                    case ShelterBlue -> character = TextCharacter.fromCharacter(' ', ANSI.BLUE, ANSI.BLUE)[0];
                    case ShelterYellow -> character = TextCharacter.fromCharacter(' ', ANSI.YELLOW, ANSI.YELLOW)[0];
                    case Points -> character = TextCharacter.fromCharacter('1', ANSI.BLACK, ANSI.BLACK_BRIGHT)[0];
                    case InventoryAttack, InventoryProtect -> character = TextCharacter.fromCharacter('?', ANSI.BLACK, ANSI.BLACK_BRIGHT)[0];
                    case Player -> character = TextCharacter.fromCharacter('*', ANSI.BLACK, ANSI.BLACK_BRIGHT)[0];
                    default -> throw new IllegalStateException("Unexpected value: " + board[i][j]);
                }
                textImage.setCharacterAt(i, j, character);
            }
        }
        textGraphics.drawImage(TerminalPosition.OFFSET_1x1, textImage);
        screen.refresh();
        //terminal.flush();
//        sleep(3000);
//        textImage.setCharacterAt(5, 5, new TextCharacter("x", ANSI.GREEN, ANSI.YELLOW, EnumSet.noneOf(SGR.class)));
//        System.out.println("Changed");
////        textGraphics.drawImage(TerminalPosition.OFFSET_1x1, textImage);
//        terminal.flush();
//        textGraphics.putString(10, 1, "GAME RULES", SGR.BOLD);
//        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
//        textGraphics.putString(10, 3, "Print ESC to return to main menu", SGR.BOLD);
//        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
//        textGraphics.putString(5, 5, "Use arrows to move", SGR.BOLD);
//        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
//        textGraphics.putString(5, 7, "Use '/' to change inventory", SGR.BOLD);
//        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
//        textGraphics.putString(5, 9, "Use space to beat your enemies", SGR.BOLD);
//        try {
//            terminal.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
