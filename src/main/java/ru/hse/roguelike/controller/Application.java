package ru.hse.roguelike.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.view.MainScreenViewConsole;
import ru.hse.roguelike.view.GameRulesScreenViewConsole;

public class Application {
    private final String filesPath;

    public Application() {
        this.filesPath = "";
    }

    public Application(String filesPath) {
        this.filesPath = filesPath;
    }

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

    public void start() throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(200, 200));
        try (Terminal terminal = defaultTerminalFactory.createTerminal()) {
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            final TextGraphics textGraphics = terminal.newTextGraphics();
            var mainScreenView = new MainScreenViewConsole(terminal, textGraphics);
            var gameRulesView = new GameRulesScreenViewConsole(terminal, textGraphics);
            InteractionManager interactionManager = new InteractionManager(filesPath, mainScreenView, gameRulesView, terminal);
            KeyStroke keyStroke = terminal.readInput();
            while (true) {
                var command = getCommand(keyStroke);
                if (command != InputCommand.UNKNOWN_COMMAND)
                    interactionManager.processCommand(command);
                if (!interactionManager.isRunning) {
                    break;
                }
                keyStroke = terminal.readInput();
            }
        }
    }

    public static void main(String[] args) {
        Application application;
        if (args.length > 0) {
            application = new Application(args[0]);
        } else {
            application = new Application();
        }
        try {
            application.start();
        } catch (IOException e) {
            System.out.println("Problem while running application: " + e.getMessage());
        }
    }
}
