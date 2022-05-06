package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.mob.Mob;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.Characters.mob.strategy.AggressiveMobStrategy;
import ru.hse.roguelike.view.console_view.GameScreenViewConsole;

public class ReplicationTest {
    @Test
    public void simpleReplicationTest() throws IOException {
        var board = new GameCharacter[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Empty();
            }
        }
        var mob = new Mob(CharacterType.MOB_AGGRESSIVE, "color", 2,
                new AggressiveMobStrategy(2, 2, new Coordinates(1, 0)), 1);
        board[1][1] = mob;
        var player = new Player(1, 0);
        board[6][1] = player;
        var mobMap = new HashMap<Mob, Coordinates>();
        mobMap.put(mob, new Coordinates(1, 1));
        var level = new Level(board, player, mobMap, CharacterType.SHELTER_LAVENDER, 5);
        level.setGameView(new GameScreenViewConsole(new DefaultVirtualTerminal()));
        level.moveCharacters(5, 1);
        Assertions.assertEquals(CharacterType.MOB_AGGRESSIVE, board[2][1].getCharacterType());
        Assertions.assertEquals(GameState.DEFEAT, level.moveCharacters(4, 1));
    }
}
