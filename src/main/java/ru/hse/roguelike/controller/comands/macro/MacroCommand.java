package ru.hse.roguelike.controller.comands.macro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.comands.Command;
import ru.hse.roguelike.controller.input.InputCommand;

public abstract class MacroCommand implements Command {
    public abstract Screen execute(InputCommand inputCommand);
}
