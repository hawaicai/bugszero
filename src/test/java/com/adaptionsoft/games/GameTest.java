package com.adaptionsoft.games;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class GameTest {

	@Test
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}

	@Test
    public void testSome()
    {
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        String str = "bob was added\r\n" +
                "They are player number 1\r\n" +
                "suse was added\r\n" +
                "They are player number 2\r\n" +
                "jack was added\r\n" +
                "They are player number 3\r\n" +
                "Answer was correct!!!!\r\n" +
                "bob now has 1 Gold Coins.\r\n";
        Game game = new Game();
        game.add("bob");
        game.add("suse");
        game.add("jack");
        game.doSomeTemp();
        assertEquals(str, resultStream.toString());
    }
}
