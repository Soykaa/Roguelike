package ru.hse.roguelike.view.abstract_view;

import java.io.IOException;

/**
 * Different game views.
 **/
abstract public class AbstractViewFactory {
    /**
     * Main screen view.
     **/
    abstract public MainScreenView createMainScreenView() throws IOException;

    /**
     * Game screen view.
     **/
    abstract public GameScreenView createGameScreenView() throws IOException;

    /**
     * Game rules screen view.
     **/
    abstract public GameRulesScreenView createGameRulesScreenView() throws IOException;
}
