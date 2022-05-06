package ru.hse.roguelike.model.characters.mob;

import java.util.Random;

import ru.hse.roguelike.model.characters.CharacterType;
import ru.hse.roguelike.model.characters.GameCharacter;
import ru.hse.roguelike.model.characters.Player;
import ru.hse.roguelike.model.characters.mob.strategy.MobStrategy;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents mobs.
 **/
public class Mob extends GameCharacter implements MobPrototype {
    private final MobStrategy strategy;
    private final float replicationProbability;
    private final String color;
    private final int attackStrength;

    /**
     * Creates new Mob instance.
     * Calls parent constructor.
     * Initialises strategy with the given value.
     *
     * @param characterType  mob type
     * @param color          mob color
     * @param attackStrength attack strength
     * @param strategy       mob strategy
     **/
    public Mob(CharacterType characterType, String color, int attackStrength, MobStrategy strategy) {
        super(characterType);
        this.strategy = strategy;
        this.color = color;
        this.attackStrength = attackStrength;
        this.replicationProbability = new Random().nextFloat() * 0.01f;
    }

    /**
     * Creates new Mob instance.
     * Calls parent constructor.
     * Initialises strategy with the given value.
     *
     * @param characterType          mob type
     * @param color                  mob color
     * @param attackStrength         attack strength
     * @param strategy               mob strategy
     * @param replicationProbability probability that the enemy will replicate at each shift
     **/
    public Mob(CharacterType characterType, String color, int attackStrength, MobStrategy strategy, float replicationProbability) {
        super(characterType);
        this.strategy = strategy;
        this.color = color;
        this.attackStrength = attackStrength;
        this.replicationProbability = replicationProbability;
    }

    /**
     * Returns mob color.
     *
     * @return mob color
     **/
    public String getColor() {
        return color;
    }

    /**
     * Returns mob strategy.
     *
     * @return mob strategy
     **/
    public MobStrategy getStrategy() {
        return strategy;
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    mob coordinates
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

    /**
     * Returns attack strength.
     *
     * @return attack strength
     **/
    public int getAttackStrength() {
        return attackStrength;
    }

    /**
     * Creates a copy of mob object.
     *
     * @return enemy mob
     **/
    @Override
    public Mob cloneMob() {
        return new Mob(this.getCharacterType(), color, attackStrength, strategy, replicationProbability);
    }

    /**
     * Returns probability of replication.
     *
     * @return probability that mob will replicate at each shift
     **/
    public float getReplicationProbability() {
        return replicationProbability;
    }
}
