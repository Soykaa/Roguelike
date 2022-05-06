package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;

/**
 * Interface for commands, connected with screen displaying, with simple logic.
 **/
public interface MicroCommand {
    /**
     * Executes command.
     *
     * @return screen
     **/
    Screen execute();
}
