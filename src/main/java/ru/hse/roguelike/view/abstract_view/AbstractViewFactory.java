package ru.hse.roguelike.view.abstract_view;

import java.io.IOException;

/**
 * Different game views.
 **/
abstract public class AbstractViewFactory {
    /**
     * Main screen view.
     *
     * @throws IOException in case of view error
     **/
    abstract public MainScreenView createMainScreenView() throws IOException;

    /**
     * Game screen view.
     *
     * @throws IOException in case of view error
     **/
    abstract public GameScreenView createGameScreenView() throws IOException;

    /**
     * Game rules screen view.
     *
     * @throws IOException in case of view error
     **/
    abstract public GameRulesScreenView createGameRulesScreenView() throws IOException;
}
