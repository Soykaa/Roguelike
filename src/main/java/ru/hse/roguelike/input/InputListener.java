package ru.hse.roguelike.input;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.controller.InteractionManager;

import java.io.IOException;

public class InputListener {

    private InputCommand getCommand(KeyStroke keyStroke) {
        var key = keyStroke.getKeyType();
        switch (key) {
            case ArrowDown:
                return InputCommand.DOWN;
            case ArrowUp:
                return InputCommand.UP;
            case ArrowLeft:
                return InputCommand.LEFT;
            case ArrowRight:
                return InputCommand.RIGHT;
            case Enter:
                return InputCommand.ENTER;
            case Escape:
                return InputCommand.ESCAPE;
            case Character:
                if (keyStroke.getCharacter().equals(' ')) {
                    return InputCommand.SPACE;
                }
                if (keyStroke.getCharacter().equals('/')) {
                    return InputCommand.BACK_SLASH;
                }
                break;
        }
        return InputCommand.UNKNOWN_COMMAND;
    }

    public void start() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        try {
            terminal = defaultTerminalFactory.createTerminal();
            terminal.enterPrivateMode();
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            final TextGraphics textGraphics = terminal.newTextGraphics();
            InteractionManager interactionManager = new InteractionManager(terminal, textGraphics);
            KeyStroke keyStroke = terminal.readInput();
            while(true) {
                var command = getCommand(keyStroke);
                if (command != InputCommand.UNKNOWN_COMMAND)
                    interactionManager.processCommand(command);
                if (!interactionManager.isRunning) {
                    break;
                }
                keyStroke = terminal.readInput();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if(terminal != null) {
                try {
                    terminal.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        var input = new InputListener();
        input.start();
    }
}
