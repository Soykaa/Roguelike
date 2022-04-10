package ru.hse.roguelike.view.console_view;

import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;
import ru.hse.roguelike.view.abstract_view.GameScreenView;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

public class ConsoleViewFactory extends AbstractViewFactory {
    private final Terminal terminal;

    public ConsoleViewFactory(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public MainScreenView createMainScreenView() throws IOException {
        return new MainScreenViewConsole(terminal);
    }

    @Override
    public GameScreenView createGameScreenView() throws IOException {
        return new GameScreenViewConsole(terminal);
    }

    @Override
    public GameRulesScreenView createGameRulesScreenView() throws IOException {
        return new GameRulesScreenViewConsole(terminal);
    }
}
