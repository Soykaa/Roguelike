package ru.hse.roguelike.view.console_view;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;
import ru.hse.roguelike.view.abstract_view.GameScreenView;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

/**
 * Console views.
 **/
public class ConsoleViewFactory extends AbstractViewFactory {
    private final Terminal terminal;

    /**
     * Creates new ConsoleViewFactory instance.
     *
     * @param terminal terminal
     **/
    public ConsoleViewFactory(Terminal terminal) {
        this.terminal = terminal;
    }

    /**
     * Returns new instance of MainScreenViewConsole.
     *
     * @return main menu view
     * @throws IOException in case of view error
     **/
    @Override
    public MainScreenView createMainScreenView() throws IOException {
        return new MainScreenViewConsole(terminal);
    }

    /**
     * Returns new instance of GameScreenViewConsole.
     *
     * @return game screen view
     * @throws IOException in case of view error
     **/
    @Override
    public GameScreenView createGameScreenView() throws IOException {
        return new GameScreenViewConsole(terminal);
    }

    /**
     * Returns new instance of GameRulesScreenViewConsole.
     *
     * @return game rules screen view
     * @throws IOException in case of view error
     **/
    @Override
    public GameRulesScreenView createGameRulesScreenView() throws IOException {
        return new GameRulesScreenViewConsole(terminal);
    }
}
