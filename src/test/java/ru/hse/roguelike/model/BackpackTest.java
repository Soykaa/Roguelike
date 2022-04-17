package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.Inventory;

public class BackpackTest {
    @Test
    public void SimpleBackpackTest() {
        var backpack = new Backpack();
        Assertions.assertEquals(1, backpack.getAllItems().size());
        backpack.putItem(new Inventory(InventoryItem.DESTROY));
        Assertions.assertEquals(2, backpack.getAllItems().size());
        Assertions.assertEquals("DEFAULT", backpack.getActiveItem().getType().toString());
        backpack.setNextActiveItem();
        Assertions.assertEquals("DESTROY", backpack.getActiveItem().getType().toString());
        backpack.clear();
    }
}
