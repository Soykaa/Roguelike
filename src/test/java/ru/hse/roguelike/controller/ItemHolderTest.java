package ru.hse.roguelike.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.controller.input.InputCommand;

public class ItemHolderTest {
    ItemHolder itemHolder;

    @BeforeEach
    public void setup() {
        itemHolder = new ItemHolder();
    }

    @Test
    public void testItemHolder() {
        Assertions.assertSame(itemHolder.getCurrentItem(), SelectedItem.START_GAME);
        itemHolder.setSelectedItem(InputCommand.UP);
        Assertions.assertSame(itemHolder.getCurrentItem(), SelectedItem.EXIT);
        itemHolder.setSelectedItem(InputCommand.DOWN);
        Assertions.assertSame(itemHolder.getCurrentItem(), SelectedItem.START_GAME);
    }
}
