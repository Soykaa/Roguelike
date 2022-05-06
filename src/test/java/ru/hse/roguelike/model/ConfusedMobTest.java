package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.characters.CharacterType;
import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.characters.mob.decorator.ConfusedMobDecorator;
import ru.hse.roguelike.model.characters.mob.strategy.AggressiveMobStrategy;

public class ConfusedMobTest {
    private Coordinates addCoordinates(Coordinates coordinates, Coordinates shift) {
        return new Coordinates(coordinates.getX() + shift.getX(), coordinates.getY() + shift.getY());
    }

    @Test
    public void testConfusedMobPlayerReturnsToStrategyAfterConfusionIsOver() {
        Mob aggressiveMob = new Mob(CharacterType.MOB_AGGRESSIVE, "color", 2,
                new AggressiveMobStrategy(3, 10, new Coordinates(1, 0)));
        ConfusedMobDecorator confusedAggressiveMob = new ConfusedMobDecorator(aggressiveMob, 1);
        Coordinates mobCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(5, 5);
        mobCoordinates = addCoordinates(mobCoordinates, confusedAggressiveMob.makeNextMove(mobCoordinates, playerCoordinates));
        Coordinates newMobCoordinates = addCoordinates(mobCoordinates, confusedAggressiveMob.makeNextMove(mobCoordinates, playerCoordinates));
        Assertions.assertTrue(newMobCoordinates.getX() - mobCoordinates.getX() == 1 && newMobCoordinates.getY() - mobCoordinates.getY() == 0);
        mobCoordinates = newMobCoordinates;
        newMobCoordinates = addCoordinates(mobCoordinates, confusedAggressiveMob.makeNextMove(mobCoordinates, playerCoordinates));
        Assertions.assertTrue(newMobCoordinates.getX() - mobCoordinates.getX() == 1 && newMobCoordinates.getY() - mobCoordinates.getY() == 0);
    }
}
