package ru.hse.roguelike.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemHolderTest {
    ItemHolder itemHolder;

    @BeforeEach
    public void setup() {
        itemHolder = new ItemHolder();
    }

    @Test
    public void testItemHolder() {
        Assertions.assertSame(itemHolder.getCurrentItem(), SelectedItem.START_GAME);
        itemHolder.moveUp();
        Assertions.assertSame(itemHolder.getCurrentItem(), SelectedItem.EXIT);
        itemHolder.moveDown();
        Assertions.assertSame(itemHolder.getCurrentItem(), SelectedItem.START_GAME);
    }
}
