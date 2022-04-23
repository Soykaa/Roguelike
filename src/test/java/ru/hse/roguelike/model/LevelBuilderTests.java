package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.LevelCharacteristics.FifthLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.FirstLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.FourthLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.LevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.SecondLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.ThirdLevelCharacteristic;
import ru.hse.roguelike.model.levelbuilder.LevelBuilder;
import ru.hse.roguelike.model.levelbuilder.RandomLevelBuilder;

public class LevelBuilderTests {
    @Test
    public void testRandomLevelBuilder() {
        LevelBuilder builder = new RandomLevelBuilder();
        Player player = new Player(10, 0);
        var l1 = builder.build(player);
        Assertions.assertNotNull(l1);
        LevelCharacteristic levelCharacteristic1 = new FirstLevelCharacteristic();
        Assertions.assertEquals(l1.getBoard().length, levelCharacteristic1.getX());
        Assertions.assertEquals(l1.getBoard()[0].length, levelCharacteristic1.getY());
        var l2 = builder.build(player);
        Assertions.assertNotNull(l2);
        LevelCharacteristic levelCharacteristic2 = new SecondLevelCharacteristic();
        Assertions.assertEquals(l2.getBoard().length, levelCharacteristic2.getX());
        Assertions.assertEquals(l2.getBoard()[0].length, levelCharacteristic2.getY());
        var l3 = builder.build(player);
        Assertions.assertNotNull(l3);
        LevelCharacteristic levelCharacteristic3 = new ThirdLevelCharacteristic();
        Assertions.assertEquals(l3.getBoard().length, levelCharacteristic3.getX());
        Assertions.assertEquals(l3.getBoard()[0].length, levelCharacteristic3.getY());
        var l4 = builder.build(player);
        Assertions.assertNotNull(l4);
        LevelCharacteristic levelCharacteristic4 = new FourthLevelCharacteristic();
        Assertions.assertEquals(l4.getBoard().length, levelCharacteristic4.getX());
        Assertions.assertEquals(l4.getBoard()[0].length, levelCharacteristic4.getY());
        var l5 = builder.build(player);
        Assertions.assertNotNull(l5);
        LevelCharacteristic levelCharacteristic5 = new FifthLevelCharacteristic();
        Assertions.assertEquals(l5.getBoard().length, levelCharacteristic5.getX());
        Assertions.assertEquals(l5.getBoard()[0].length, levelCharacteristic5.getY());
        var l6 = builder.build(player);
        Assertions.assertNull(l6);
        var l7 = builder.build(player);
        Assertions.assertNull(l7);
    }
}
