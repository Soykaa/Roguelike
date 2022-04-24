package ru.hse.roguelike.model.Characters.decorator;

import ru.hse.roguelike.model.Characters.Enemy;

/**
 * Enemy decorator.
 **/
public abstract class EnemyDecorator extends Enemy {
    protected final Enemy enemy;

    /**
     * Creates new EnemyDecorator instance.
     * Calls parent constructor.
     * Initialises enemy with the given value.
     *
     * @param enemy enemy
     **/
    public EnemyDecorator(Enemy enemy) {
        super(enemy.getCharacterType(), enemy.getColor(), enemy.getAttackStrength(), enemy.getStrategy());
        this.enemy = enemy;
    }
}
