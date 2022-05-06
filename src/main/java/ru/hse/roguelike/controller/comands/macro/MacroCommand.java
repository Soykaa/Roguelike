package ru.hse.roguelike.controller.comands.macro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.input.InputCommand;

public interface MacroCommand {
    Screen execute(InputCommand inputCommand);
}
