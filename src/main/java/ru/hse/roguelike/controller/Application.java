package ru.hse.roguelike.controller;

public class Application {
    private boolean isRunning = false;

    public static void main(String[] args) {
        System.out.println("-----Start game -----");
        var application = new Application();
        application.start(args[0]);
    }

    private void start(String pathToFile) {

    }
}
