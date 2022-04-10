package ru.hse.roguelike.view.abstract_view;

import java.io.IOException;

abstract public class AbstractViewFactory {
    abstract public MainScreenView createMainScreenView() throws IOException;
    abstract public GameScreenView createGameScreenView() throws IOException;
    abstract public GameRulesScreenView createGameRulesScreenView() throws IOException;
}
