package ru.hse.roguelike.view.console_view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.controller.SelectedItem;

import java.io.IOException;
import java.util.Map;

import ru.hse.roguelike.view.abstract_view.MainScreenView;

/**
 * Main screen view.
 **/
public class MainScreenViewConsole implements MainScreenView {
    private final Terminal terminal;
    private final TextGraphics textGraphics;
    private final Map<SelectedItem, GraphicItem> itemToGraphics;
    private SelectedItem currentItem;

    private static class GraphicItem {
        int rowNumber;
        String text;

        GraphicItem(int rowNumber, String text) {
            this.rowNumber = rowNumber;
            this.text = text;
        }
    }

    /**
     * Creates new MainScreenViewConsole instance.
     *
     * @param terminal terminal
     * @throws IOException in case of view error
     **/
    public MainScreenViewConsole(Terminal terminal) throws IOException {
        this.terminal = terminal;
        this.textGraphics = terminal.newTextGraphics();
        itemToGraphics = Map.of(SelectedItem.START_GAME, new GraphicItem(3, "Start game"),
                SelectedItem.START_GAME_FROM_FILE, new GraphicItem(5, "Start game from file"),
                SelectedItem.SHOW_RULES, new GraphicItem(7, "Show rules"),
                SelectedItem.EXIT, new GraphicItem(9, "Exit"));
        currentItem = SelectedItem.START_GAME;
    }

    /**
     * Shows main screen.
     **/
    @Override
    public void showMainScreen() {
        try {
            terminal.clearScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.CYAN);
        textGraphics.putString(2, 1, "Welcome to ESCAPE from ******", SGR.BOLD);
        for (var graphicItem : itemToGraphics.values()) {
            textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(5, graphicItem.rowNumber, graphicItem.text, SGR.BOLD);
        }
        textGraphics.setBackgroundColor(TextColor.ANSI.RED_BRIGHT);
        textGraphics.putString(5, itemToGraphics.get(currentItem).rowNumber,
                itemToGraphics.get(currentItem).text, SGR.BOLD);
        try {
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets selected menu item.
     *
     * @param item selected item
     **/
    @Override
    public void setSelectedItem(SelectedItem item) {
        try {
            textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(5, itemToGraphics.get(currentItem).rowNumber,
                    itemToGraphics.get(currentItem).text, SGR.BOLD);
            currentItem = item;
            textGraphics.setBackgroundColor(TextColor.ANSI.RED_BRIGHT);
            textGraphics.putString(5, itemToGraphics.get(currentItem).rowNumber,
                    itemToGraphics.get(currentItem).text, SGR.BOLD);
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
