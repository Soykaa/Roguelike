package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.comands.Command;

public abstract class MicroCommand implements Command {
    public abstract Screen execute();
}
