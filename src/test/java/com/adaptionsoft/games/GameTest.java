package com.adaptionsoft.games;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game = new Game();
    @Before
    public void setup()
    {
        game.add("bob");
        game.add("sue");
        game.add("jack");
    }/*
	@Test
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}*/

	@Test
    public void testListOverMemory()
    {
        Questions questions = new Questions();
        questions.init();
        for (int i = 0; i < 10000; i++)
        {
            questions.getQuestionsByPlace(i);
        }
    }

    @Test
    public void should_be_2_when_add_2_players()
    {
        assertEquals(3, game.howManyPlayers());
        assertTrue(game.isPlayable());
    }

    @Test
    public void test_roll_1_place_1()
    {
        String str = "bob is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "bob's new location is 1\r\n" +
                "The category is Science\r\n" +
                "Science Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(1);
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void test_roll_1_place_1_and_correctanswer()
    {
        game.roll(1);
        String str = "Answer was correct!!!!\r\n" +
                "bob now has 1 Gold Coins.\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.wasCorrectlyAnswered();
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void test_roll_1_place_1_and_wronganswer()
    {
        game.roll(1);
        String str = "Question was incorrectly answered\r\n" +
                "bob was sent to the penalty box\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.wrongAnswer();
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void should_do_nothing_when_input_roll_2_given_inpenalty_box()
    {
        game.roll(1);
        game.wrongAnswer();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        String str = "bob is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "bob is not getting out of the penalty box\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(2);
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void should_do_nothing_when_input_roll_1_given_inpenalty_box()
    {
        game.roll(1);
        game.wrongAnswer();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        String str = "bob is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "bob is getting out of the penalty box\r\n" +
                "bob's new location is 2\r\n" +
                "The category is Sports\r\n" +
                "Sports Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(1);
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void test_roll_1_place_2()
    {
        String str = "bob is the current player\r\n" +
                "They have rolled a 2\r\n" +
                "bob's new location is 2\r\n" +
                "The category is Sports\r\n" +
                "Sports Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(2);
        assertEquals(str, resultStream.toString());
    }
    @Test
    public void test_roll_1_place_3()
    {
        String str = "bob is the current player\r\n" +
                "They have rolled a 3\r\n" +
                "bob's new location is 3\r\n" +
                "The category is Rock\r\n" +
                "Rock Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(3);
        assertEquals(str, resultStream.toString());
    }
    @Test
    public void test_roll_1_place_4()
    {
        String str = "bob is the current player\r\n" +
                "They have rolled a 4\r\n" +
                "bob's new location is 4\r\n" +
                "The category is Pop\r\n" +
                "Pop Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(4);
        assertEquals(str, resultStream.toString());
    }

    @Test
    public void test_roll_2_place_4()
    {
        game.roll(1);
        game.wrongAnswer();
        String str = "sue is the current player\r\n" +
                "They have rolled a 4\r\n" +
                "sue's new location is 4\r\n" +
                "The category is Pop\r\n" +
                "Pop Question 0\r\n";
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        game.roll(4);
        assertEquals(str, resultStream.toString());
    }

}
