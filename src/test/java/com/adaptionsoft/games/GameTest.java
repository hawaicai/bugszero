package com.adaptionsoft.games;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        assertEquals(1, game.getCurrentPlayer().getGoldCoins());
    }

    @Test
    public void should_be_winner_when_answer_correctly_6()
    {
        for (int i = 0; i < 5; i++)
        {
            game.wasCorrectlyAnswered();
            assertTrue(game.didPlayerNotWin());
        }
        game.wasCorrectlyAnswered();
        assertFalse(game.didPlayerNotWin());
    }

    @Test
    public void should_in_penalty_box_when_answer_wrong()
    {
        game.wasWrongAnswer();
        assertTrue(game.getCurrentPlayer().isInpenaltyBox());
    }

    @Test
    public void should__stady_in_penalty_box_when_roll_2_given_in_penalty_box()
    {
        game.wasWrongAnswer();
        assertTrue(game.getCurrentPlayer().isInpenaltyBox());
        game.roll(2);
        assertTrue(game.getCurrentPlayer().isInpenaltyBox());
    }

    @Test
    public void should__getting_out_of_penalty_box_when_roll_1_given_in_penalty_box()
    {
        game.wasWrongAnswer();
        assertTrue(game.getCurrentPlayer().isInpenaltyBox());
        game.roll(1);
        assertFalse(game.getCurrentPlayer().isInpenaltyBox());
    }

    @Test
    public void test_askquestion()
    {
        String str = "The category is Pop\r\n" +
                "Pop Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.askQuestion();
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void test_get_category_when_input_places()
    {
        DecksManager decksManager = new DecksManager();
        assertEquals("Pop",decksManager.currentCategory(0));
        assertEquals("Science",decksManager.currentCategory(1));
        assertEquals("Sports",decksManager.currentCategory(2));
        assertEquals("Rock",decksManager.currentCategory(3));
    }

    @Test
    public void test_get_question_when_input_category()
    {
        DecksManager decksManager = new DecksManager();
        assertEquals("Pop Question 0",decksManager.askQuestion("Pop"));
        assertEquals("Science Question 0",decksManager.askQuestion("Science"));
        assertEquals("Sports Question 0",decksManager.askQuestion("Sports"));
        assertEquals("Rock Question 0",decksManager.askQuestion("Rock"));
        assertEquals("Pop Question 1",decksManager.askQuestion("Pop"));
    }
    @Test
    public void test_get_question_when_input_invalid_category()
    {
        DecksManager decksManager = new DecksManager();
        assertNull(decksManager.askQuestion("Pop1"));
    }

    @Test
    public void test_get_question_when_input_category_51th()
    {
        DecksManager decksManager = new DecksManager();
        for (int i = 0; i <  50; i++)
        {
            decksManager.askQuestion("Pop");
        }
        assertEquals("Pop Question 0",decksManager.askQuestion("Pop"));
    }
}
