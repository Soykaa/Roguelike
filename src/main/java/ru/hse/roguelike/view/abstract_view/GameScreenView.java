package ru.hse.roguelike.view.abstract_view;

import java.io.IOException;

import ru.hse.roguelike.model.Backpack;
import ru.hse.roguelike.model.Characters.GameCharacter;

public interface GameScreenView {
    void showBoard(GameCharacter[][] board) throws IOException;

    void moveCharacter(int xFrom, int yFrom, int xTo, int yTo) throws IOException;

    void setMessage(String message) throws IOException;

    void removeMessage() throws IOException;

    void removeCharacter(int x, int y) throws IOException;

    void placeCharacter(GameCharacter character, int x, int y) throws IOException;

    void showPoints(int currentPoints, int totalPoints) throws IOException;

    void showLives(int lives) throws IOException;

    void showBackpack(Backpack selectedItem) throws IOException;
}
