package ru.hse.roguelike.model.character.mob.decorator;

import ru.hse.roguelike.model.character.mob.Mob;

/**
 * Mob decorator.
 **/
public abstract class MobDecorator extends Mob {
    protected final Mob mob;

    /**
     * Creates new MobDecorator instance.
     * Calls parent constructor.
     * Initialises mob with the given value.
     *
     * @param mob mob
     **/
    public MobDecorator(Mob mob) {
        super(mob.getCharacterType(), mob.getColor(), mob.getAttackStrength(), mob.getStrategy());
        this.mob = mob;
    }
}
