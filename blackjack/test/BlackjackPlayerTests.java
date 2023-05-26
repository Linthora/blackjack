package test;

import model.cards.*;
import model.blackjack.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/**
 * A test class for the BlackjackPlayer class.
 */
public class BlackjackPlayerTests {
    
    @Test 
    public void testBlackjackPlayer() {
        BlackjackPlayer player = new BlackjackPlayer("Player", null);
        assertEquals("Player", player.getName());
        assertEquals(0, player.getScoreRound());
        assertEquals(0, player.getScoreGame());
        assertFalse(player.isPlaying());
        assertNull(player.getStrategy());
        assertEquals(0, player.getHand().size());

        assertEquals("Score: 0  ", player.getInfo());

        player = new BlackjackPlayer("Dealer", new DealerStrategy());
        assertEquals("Dealer", player.getName());
        assertEquals(0, player.getScoreRound());
        assertEquals(0, player.getScoreGame());
        assertFalse(player.isPlaying());
        assertEquals(new DealerStrategy(), player.getStrategy());
        assertEquals(0, player.getHand().size());

        player = new BlackjackPlayer("Player mit", new MITStrategy());
        assertEquals("Player mit", player.getName());
        assertEquals(0, player.getScoreRound());
        assertEquals(0, player.getScoreGame());
        assertFalse(player.isPlaying());
        assertEquals(new MITStrategy(), player.getStrategy());
        assertEquals(0, player.getHand().size());

        player.incrementGameScore();
        assertEquals(1, player.getScoreGame());
        player.incrementGameScore();
        assertEquals(2, player.getScoreGame());

        player.setIsPlaying(true);
        assertTrue(player.isPlaying());
        player.setIsPlaying(false);
        assertFalse(player.isPlaying());

        Deck deck = new ProxyHandBuilder()
                .addCard(new CardBuilder().withColor("spade").withName("10").withValue(10).build())
                .addCard(new CardBuilder().withColor("spade").withName("as").withValue(11).build())
            .build();
        player.setHand(deck);
        assertEquals(deck, player.getHand());
        assertEquals(21, player.getScoreRound());

        Deck deck2 = new ProxyHandBuilder()
                .addCard(new CardBuilder().withColor("spade").withName("2").withValue(2).build())
                .addCard(new CardBuilder().withColor("spade").withName("as").withValue(11).build())
            .build();
        player.setHand(deck2);
        assertEquals(deck2, player.getHand());
        assertEquals(13, player.getScoreRound());

        assertEquals("Score: 2  ", player.getInfo());

        assertFalse(player.equals(deck2));
        assertTrue(player.equals(player));
        assertFalse(player.equals(null));
        assertFalse(player.equals(new Object()));
        assertFalse(player.equals(new BlackjackPlayer("Player", null)));
        assertFalse(player.equals(new BlackjackPlayer("Dealer", new DealerStrategy())));
        assertTrue(player.equals(new BlackjackPlayer("Player mit", new MITStrategy())));
        

    }
}
