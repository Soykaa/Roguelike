package ru.hse.roguelike.model.Characters;

public class Shelter extends GameCharacter {
    private String type;

    public Shelter(String type) {
        super(CharacterType.SHELTER_LAVENDER);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
