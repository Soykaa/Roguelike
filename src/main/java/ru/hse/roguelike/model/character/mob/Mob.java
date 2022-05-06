package ru.hse.roguelike.model.character.mob;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;
import ru.hse.roguelike.model.character.CharacterType;
import ru.hse.roguelike.model.character.GameCharacter;
import ru.hse.roguelike.model.character.Player;
import ru.hse.roguelike.model.character.mob.state.MobState;
import ru.hse.roguelike.model.character.mob.state.PanicMobState;

import java.util.Random;

/**
 * Represents mobs.
 **/
public class Mob extends GameCharacter implements MobPrototype {
    private final float replicationProbability;
    private final String color;
    private final int attackStrength;

    private int lives = 2;

    private MobState state;

    /**
     * Creates new Mob instance.
     * Calls parent constructor.
     * Initialises strategy with the given value.
     *
     * @param characterType  mob type
     * @param color          mob color
     * @param attackStrength attack strength
     * @param state          mob state
     **/
    public Mob(CharacterType characterType, String color, int attackStrength, MobState state) {
        super(characterType);
        this.state = state;
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
     * @param state                  mob strategy
     * @param replicationProbability probability that the enemy will replicate at each shift
     **/
    public Mob(CharacterType characterType, String color, int attackStrength, MobState state, float replicationProbability) {
        super(characterType);
        this.state = state;
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
     * Returns mob state.
     *
     * @return mob state
     **/
    public MobState getState() {
        return state;
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    mob coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return state.makeNextMove(mobCoordinates, playerCoordinates);
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
        return new Mob(this.getCharacterType(), color, attackStrength, state, replicationProbability);
    }

    /**
     * Returns probability of replication.
     *
     * @return probability that mob will replicate at each shift
     **/
    public float getReplicationProbability() {
        return replicationProbability;
    }


    public int getLives() {
        return lives;
    }

    public void decreaseLives() {
        lives -= 1;
        if (!(state instanceof PanicMobState)) {
            state = new PanicMobState(this);
        }
    }

    public void increaseLives() {
        lives += 1;
    }

    public void changeMobState(MobState newState) {
        state = newState;
    }
}
