package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;

/**
 * Interface for commands with simple logic, connected with screen displaying.
 **/
public interface MicroCommand {
    /**
     * Executes command.
     *
     * @return screen
     **/
    Screen execute();
}
