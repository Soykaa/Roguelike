package ru.hse.roguelike.model.Characters;

import java.util.Random;
import ru.hse.roguelike.model.Characters.strategies.AggressiveMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.CowardMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.MobStrategy;
import ru.hse.roguelike.model.Characters.strategies.PassiveMobStrategy;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents enemies.
 **/
public class Enemy extends GameCharacter implements EnemyPrototype {
    private final MobStrategy strategy;
    private final float replicationProbability;


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
        this.replicationProbability = new Random().nextFloat() * 0.01f;
    }

    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     * Creates strategy according to enemy type.
     *
     * @param characterType          enemy type
     * @param visibility             enemy visibility
     * @param maxSteps               maximum number of steps in shift direction
     * @param shift                  shift direction
     * @param replicationProbability probability that the enemy will replicate at each shift
     **/
    public Enemy(CharacterType characterType, int visibility, int maxSteps, Coordinates shift, float replicationProbability) {
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
        this.replicationProbability = replicationProbability;
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
        this.replicationProbability = new Random().nextFloat() * 0.01f;
    }

    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     * Initialises strategy with the given value.
     *
     * @param characterType          enemy type
     * @param strategy               enemy strategy
     * @param replicationProbability probability that the enemy will replicate at each shift
     **/
    public Enemy(CharacterType characterType, MobStrategy strategy, float replicationProbability) {
        super(characterType);
        this.strategy = strategy;
        this.replicationProbability = replicationProbability;
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

    /**
     * Creates a copy of enemy object.
     **/
    @Override
    public Enemy cloneEnemy() {
        return new Enemy(this.getCharacterType(), strategy, replicationProbability);
    }

    /**
     * @return probability that enemy will replicate at each shift
     **/
    public float getReplicationProbability() {
        return replicationProbability;
    }
}
