package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Characters.strategies.AggressiveMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.CowardMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.MobStrategy;
import ru.hse.roguelike.model.Characters.strategies.PassiveMobStrategy;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents enemies.
 **/
public class Enemy extends GameCharacter {
    private final MobStrategy strategy;


    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     * Creates strategy according to enemy type.
     *
     * @param characterType enemy type
     * @param visibility    enemy visibility
     * @param maxSteps      maximum number of steps in shift direction
     * @param shift         shift direction
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

    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     * Initialises strategy with the given value.
     *
     * @param characterType enemy type
     * @param strategy      enemy strategy
     **/
    public Enemy(CharacterType characterType, MobStrategy strategy) {
        super(characterType);
        this.strategy = strategy;
    }

    /**
     * Returns enemy strategy.
     *
     * @return enemy strategy
     **/
    public MobStrategy getStrategy() {
        return strategy;
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    enemy coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return strategy.makeNextMove(mobCoordinates, playerCoordinates);
    }

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
