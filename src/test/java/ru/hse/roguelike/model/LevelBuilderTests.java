package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.character.Player;
import ru.hse.roguelike.model.level_characteristic.*;
import ru.hse.roguelike.model.level_builder.FromFileLevelBuilder;
import ru.hse.roguelike.model.level_builder.LevelBuilder;
import ru.hse.roguelike.model.level_builder.RandomLevelBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

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

    @Test
    public void testFromFileLevelBuilder() throws IOException {
        String directory = System.getProperty("user.dir") + File.separator + "tmp";
        Files.createDirectories(Paths.get(directory));
        Files.createFile(Paths.get(directory + File.separator + "level0"));
        Files.createFile(Paths.get(directory + File.separator + "level1"));
        String level = "{\"victoryPoints\": 5, " +
                "\"realShelterType\": \"SHELTER_LAVENDER\", " +
                "\"board\":\n" +
                    "[[{\"numberOfPoints\":8,\"characterType\":\"POINTS\"}, " +
                        "{\"characterType\":\"EMPTY\"}, " +
                        "{\"destroyBonus\":1,\"characterType\":\"OBSTACLE\"}, " +
                        "{\"lives\":4,\"currentCoordinates\":{\"x\":0,\"y\":3},\"characterType\":\"PLAYER\"}, " +
                        "{\"characterType\":\"SHELTER_PINK\"}, " +
                        "{\"characterType\":\"SHELTER_YELLOW\"}, " +
                        "{\"characterType\":\"SHELTER_LAVENDER\"}, " +
                        "{\"type\":\"DESTROY\",\"characterType\":\"INVENTORY\"}, " +
                        "{\"type\":\"PROTECTION\",\"characterType\":\"INVENTORY\"}]]}";

        Files.writeString(Paths.get(directory + File.separator + "level0"), level);
        Files.writeString(Paths.get(directory + File.separator + "level1"), level);

        LevelBuilder levelBuilder = new FromFileLevelBuilder(File.separator + "tmp");
        Player player = new Player(10, 0);
        var l1 = levelBuilder.build(player);
        Assertions.assertNotNull(l1);
        var l2 = levelBuilder.build(player);
        Assertions.assertNotNull(l2);
        var l3 = levelBuilder.build(player);
        Assertions.assertNull(l3);
        Files.walk(Paths.get(directory))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
