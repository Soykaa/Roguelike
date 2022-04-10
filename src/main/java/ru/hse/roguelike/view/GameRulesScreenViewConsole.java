package ru.hse.roguelike.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GameRulesScreenViewConsole implements GameRulesScreenView {
    private final Terminal terminal;
    private final TextGraphics textGraphics;

    public GameRulesScreenViewConsole(Terminal terminal, TextGraphics textGraphics) {
        this.terminal = terminal;
        this.textGraphics = textGraphics;
    }

    @Override
    public void showGameRules() {
        try {
            terminal.clearScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.CYAN);
        textGraphics.putString(10, 1, "GAME RULES", SGR.BOLD);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(10, 3, "Print ESC to return to main menu", SGR.BOLD);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(5, 5, "Use arrows to move", SGR.BOLD);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(5, 7, "Use '/' to change inventory", SGR.BOLD);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(5, 9, "Use space to beat your enemies", SGR.BOLD);
        try {
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
