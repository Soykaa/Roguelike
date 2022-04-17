package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Characters.strategies.AggressiveMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.CowardMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.MobStrategy;
import ru.hse.roguelike.model.Characters.strategies.PassiveMobStrategy;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

import javax.swing.*;

/**
 * Represents enemies.
 **/
public class Enemy extends GameCharacter {
    public MobStrategy getStrategy() {
        return strategy;
    }

    private final MobStrategy strategy;


    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     *
     * @param characterType enemy type
     **/
    public Enemy(CharacterType characterType, int visibility, int maxSteps, Coordinates shift) {
        super(characterType);
        switch (characterType) {
            case ENEMY_AGGRESSIVE:
                strategy = new AggressiveMobStrategy(visibility, maxSteps, shift);
                break;
            case ENEMY_COWARD:
                strategy = new CowardMobStrategy(visibility, maxSteps, shift);
                break;
            default:
                strategy = new PassiveMobStrategy();
        }
    }

    public Enemy(CharacterType characterType, MobStrategy strategy) {
        super(characterType);
        this.strategy = strategy;
    }


    /**
     * Makes next move.
     *
     * @return new coordinates
     **/
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return strategy.makeNextMove(mobCoordinates, playerCoordinates);
    };

    /**
     * Attacks player.
     *
     * @param player player
     **/
    public void attack(Player player) {
        if (player.getBackpack().getActiveItem().getType() != InventoryItem.PROTECTION) {
            player.decreaseLives(2);
        }
        if (player.getBackpack().getActiveItem().getType() == InventoryItem.PROTECTION) {
            player.decreaseLives(1);
        }
    }
}
