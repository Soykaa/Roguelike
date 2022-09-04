package ru.hse.roguelike.model.character.mob;

/**
 * Prototype for mobs.
 **/
public interface MobPrototype {
    /**
     * Creates a copy of mob object.
     *
     * @return enemy mob
     **/
    Mob cloneMob();
}
