package ru.hse.roguelike.controller.command.macro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.input.InputCommand;

/**
 * Interface for commands, connected with screen displaying, with complicated logic.
 **/
public interface MacroCommand {
    /**
     * Executes passed command.
     *
     * @param inputCommand passed command
     * @return corresponding screen
     **/
    Screen execute(InputCommand inputCommand);
}
