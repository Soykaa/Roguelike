package ru.hse.roguelike.model.Characters;

public class Shetter extends Character {
    private String type;

    public Shetter(String type) {
        super("Shetter");
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
