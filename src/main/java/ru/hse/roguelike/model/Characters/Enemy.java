package ru.hse.roguelike.model.Characters;

import java.util.Random;
import ru.hse.roguelike.model.Characters.strategies.MobStrategy;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents enemies.
 **/
public class Enemy extends GameCharacter implements EnemyPrototype {
    private final MobStrategy strategy;
    private final float replicationProbability;

    private final String color;

    private final int attackStrength;

    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     * Initialises strategy with the given value.
     *
     * @param characterType enemy type
     * @param strategy      enemy strategy
     **/
    public Enemy(CharacterType characterType, String color, int attackStrength, MobStrategy strategy) {
        super(characterType);
        this.strategy = strategy;
        this.color = color;
        this.attackStrength = attackStrength;
        this.replicationProbability = new Random().nextFloat() * 0.01f;
    }

    public String getColor() {
        return color;
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
    public Enemy(CharacterType characterType, String color, int attackStrength, MobStrategy strategy, float replicationProbability) {
        super(characterType);
        this.strategy = strategy;
        this.color = color;
        this.attackStrength = attackStrength;
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
            player.decreaseLives(attackStrength);
        }
        if (player.getBackpack().getActiveItem().getType() == InventoryItem.PROTECTION) {
            player.decreaseLives((float) (attackStrength / 2.0));
        }
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    /**
     * Creates a copy of enemy object.
     **/
    @Override
    public Enemy cloneEnemy() {
        return new Enemy(this.getCharacterType(), color, attackStrength, strategy, replicationProbability);
    }

    /**
     * @return probability that enemy will replicate at each shift
     **/
    public float getReplicationProbability() {
        return replicationProbability;
    }

    public static void main(String[] args) {
        float a = 2;
        System.out.println(a / 3);
    }
}
