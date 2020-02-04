package com.adaptionsoft.games;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game = new Game();
	@Test
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}
    @Before
    public void Setup() {
        game.add("bob");
        game.add("sue");
        game.add("jack");
    }

	@Test
    public void should_get_one_player_when_add_one_player()
    {
        Game game = new Game();
        game.add("bob");
        assertEquals(1, game.howManyPlayers());
        assertEquals("bob", game.getCurrentPlayer().getName());
    }
	@Test
    public void should_get_two_player_when_add_two_player()
    {
        Game game = new Game();
        game.add("bob");
        game.add("sue");
        assertEquals(2, game.howManyPlayers());
    }
	@Test
    public void should_get_the_second_player_when_add_two_player_and_to_next_player()
    {
        Game game = new Game();
        game.add("bob");
        game.add("sue");
        assertEquals("bob", game.getCurrentPlayer().getName());
        game.toNextPlayer();
        assertEquals("sue", game.getCurrentPlayer().getName());
    }

    @Test
    public void should_get_1_place_when_roll_1()
    {
        game.roll(1);
        assertEquals(1, game.getCurrentPlayer().getPalces());
    }
    @Test
    public void should_get_0_place_when_roll_12()
    {
        game.roll(12);
        assertEquals(0, game.getCurrentPlayer().getPalces());
    }

    @Test
    public void should_get_1_coins_when_answer_correctly()
    {
        game.wasCorrectlyAnswered();
//        assertEquals(1, game.getCurrentPlayer().getGoldCoins());
    }

}
