package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.character.Inventory;

public class BackpackTest {
    @Test
    public void SimpleBackpackTest() {
        var backpack = new Backpack();
        Assertions.assertEquals(2, backpack.getAllItems().size());
        backpack.putItem(new Inventory(InventoryItem.DESTROY));
        Assertions.assertEquals(3, backpack.getAllItems().size());
        Assertions.assertEquals("DEFAULT", backpack.getActiveItem().getType().toString());
        backpack.setNextActiveItem();
        Assertions.assertEquals("CONFUSION", backpack.getActiveItem().getType().toString());
        backpack.setNextActiveItem();
        Assertions.assertEquals("DESTROY", backpack.getActiveItem().getType().toString());
        backpack.clear();
    }
}
