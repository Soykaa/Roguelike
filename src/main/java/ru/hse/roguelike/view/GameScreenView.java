package ru.hse.roguelike.view;

import java.io.IOException;
import ru.hse.roguelike.model.GameCharacter;

public interface GameScreenView {
    void showBoard(GameCharacter[][] board) throws IOException, InterruptedException;
}
