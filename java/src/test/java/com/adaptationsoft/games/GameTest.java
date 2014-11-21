package com.adaptationsoft.games;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;
import static org.junit.Assert.*;

class TestableGame extends Game {

    public void WriteOutput(String output) {

    }
}

public class GameTest {

    @Test
    public void ItIsNotReadyToPlayWithOnePlayer() {
        Game game = new TestableGame();

        game.add("PlayerOne");

        assertFalse(game.isPlayable());
    }

    @Test
    public void ItIsReadyToPlayWithTwoPlayers() {
        Game game = new TestableGame();

        game.add("PlayerOne");
        game.add("PlayerTwo");

        assertTrue(game.isPlayable());
    }
}
