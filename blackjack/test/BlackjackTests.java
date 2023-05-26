package test;

import model.cards.*;
import model.blackjack.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/**
 * A test class for the Blackjack class and for the DealerStrategy class (which the functionnement is evaluated implicitly here).
 */
public class BlackjackTests {
    
    @Test
    public void test() {
        Blackjack blackjack = new Blackjack(2, 1);
        assertEquals(2, blackjack.getNbPlayers());
        assertEquals(1, blackjack.getNbMaxRound());
        assertEquals(0, blackjack.getNbRound());
        assertEquals(0, blackjack.getTurn());
        assertEquals(0, blackjack.getPlayers().size());
        assertEquals(0, blackjack.getListeners().size());
        assertEquals(null, blackjack.getWinnerGame());
        assertEquals(null, blackjack.getWinnerRound());

        blackjack.init();
        assertEquals(3, blackjack.getPlayers().size());
        assertEquals(0, blackjack.getListeners().size());
        assertEquals(0, blackjack.getTurn());
        assertEquals(0, blackjack.getNbRound());
        assertEquals(1, blackjack.getNbMaxRound());

        List<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(null);
        strategies.add(null);
        assertEquals(strategies, blackjack.getStrategies());

        assertEquals(null, blackjack.getWinnerRound());
        assertEquals(null, blackjack.getWinnerGame());

        assertEquals(3, blackjack.getPlayers().size());
        assertEquals(new BlackjackPlayer("dealer", new DealerStrategy()), blackjack.getPlayerList().get(0));
        assertEquals(new BlackjackPlayer("dealer", new DealerStrategy()), blackjack.getPlayer("dealer"));
        assertEquals(0, blackjack.getPlayer("dealer").getHand().size());
        assertEquals(0, ((BlackjackPlayer)blackjack.getPlayer("dealer")).getScoreRound());
        assertEquals(0, ((BlackjackPlayer)blackjack.getPlayer("dealer")).getScoreGame());

        assertEquals(new BlackjackPlayer("player 0", null), blackjack.getPlayerList().get(1));
        assertEquals(new BlackjackPlayer("player 0", null), blackjack.getPlayer("player 0"));
        assertEquals(0, blackjack.getPlayer("player 0").getHand().size());
        assertEquals(0, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreRound());
        assertEquals(0, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreGame());

        assertEquals(new BlackjackPlayer("player 1", null), blackjack.getPlayerList().get(2));
        assertEquals(new BlackjackPlayer("player 1", null), blackjack.getPlayer("player 1"));
        assertEquals(0, blackjack.getPlayer("player 1").getHand().size());
        assertEquals(0, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreRound());
        assertEquals(0, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreGame());

        // more test after to test increment and some other stuff
        blackjack.nextTurn();
        assertEquals(1, blackjack.getTurn());
        assertEquals(0, blackjack.getNbRound());
        assertEquals(3, blackjack.getPlayers().size());

        assertFalse(blackjack.isRoundOver());
        assertFalse(blackjack.isOver());
        for(Player player : blackjack.getPlayerList()) {
            assertEquals(0, ((BlackjackPlayer) player).getScoreRound());
            assertEquals(0, ((BlackjackPlayer) player).getScoreGame());
        }


        blackjack.nextTurn();
        assertEquals(2, blackjack.getTurn());
        assertFalse(blackjack.isRoundOver());
        for(Player player : blackjack.getPlayerList()) {
            assertEquals(0, ((BlackjackPlayer) player).getScoreRound());
            assertEquals(0, ((BlackjackPlayer) player).getScoreGame());
        }

        
        blackjack.nextTurn();
        assertEquals(3, blackjack.getTurn());
        assertFalse(blackjack.isRoundOver());
        for(Player player : blackjack.getPlayerList()) {
            assertEquals(0, ((BlackjackPlayer) player).getScoreRound());
            assertEquals(0, ((BlackjackPlayer) player).getScoreGame());
        }

        
        blackjack.nextTurn();
        assertEquals(4, blackjack.getTurn());
        assertTrue(blackjack.isRoundOver());
        for(Player player : blackjack.getPlayerList()) {
            assertEquals(0, ((BlackjackPlayer) player).getScoreRound());
            assertEquals(0, ((BlackjackPlayer) player).getScoreGame());
        }


        assertTrue(blackjack.isOver());

        
        // end without any winner
        blackjack.end();
        assertEquals("Draw", blackjack.getWinnerGame());
        
        // test newRoundInit() and serve as test for dealer strategy
        blackjack = new Blackjack(2, 1);
        blackjack.init();
        blackjack.newRoundInit();
        assertEquals(1, blackjack.getTurn());
        assertEquals(0, blackjack.getNbRound());
        
        assertNotEquals(null, blackjack.getDeck("deck"));
        assertEquals(47, blackjack.getDeck("deck").size());

        assertEquals(1, blackjack.getPlayer("dealer").getHand().size());
        assertEquals(2, blackjack.getPlayer("player 0").getHand().size());
        assertEquals(2, blackjack.getPlayer("player 1").getHand().size());

        assertNotEquals(blackjack.getPlayer("player 0").getHand(), blackjack.getPlayer("player 1").getHand());
        assertNotEquals(blackjack.getPlayer("player 0").getHand(), blackjack.getPlayer("dealer").getHand());
        assertNotEquals(blackjack.getPlayer("player 1").getHand(), blackjack.getPlayer("dealer").getHand());

        assertEquals(null, blackjack.getWinnerRound());
        assertEquals(null, blackjack.getWinnerGame());
    }

    @Test
    public void test2() {
        List<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(null);
        strategies.add(new MITStrategy());
        strategies.add(new MITStrategy());

        Blackjack blackjack = new Blackjack(strategies, 1);
        assertEquals(3, blackjack.getNbPlayers());
        assertEquals(1, blackjack.getNbMaxRound());
        assertEquals(0, blackjack.getPlayers().size());


        
        blackjack.init();
        assertEquals(3, blackjack.getNbPlayers());
        assertEquals(1, blackjack.getNbMaxRound());
        assertEquals(0, blackjack.getNbRound());
        assertEquals(0, blackjack.getTurn());
        assertEquals(4, blackjack.getPlayers().size());
        assertEquals(0, blackjack.getListeners().size());
        assertEquals(null, blackjack.getWinnerGame());
        assertEquals(null, blackjack.getWinnerGame());


        assertEquals(strategies, blackjack.getStrategies());
        
        assertEquals(new BlackjackPlayer("dealer", new DealerStrategy()), blackjack.getPlayerList().get(0));

        assertEquals(new BlackjackPlayer("player 0", null), blackjack.getPlayerList().get(1));
        assertEquals(new BlackjackPlayer("player 1", new MITStrategy()), blackjack.getPlayerList().get(2));
        assertEquals(new BlackjackPlayer("player 2", new MITStrategy()), blackjack.getPlayerList().get(3));

        // set dealer hand to 21
        // make the hand first
        Deck deck = new ProxyHandBuilder()
                .addCard(new CardBuilder().withColor("spade").withName("10").withValue(10).build())
                .addCard(new CardBuilder().withColor("spade").withName("as").withValue(11).build())
            .build();
        blackjack.getPlayer("dealer").setHand(deck);
        assertEquals(21, ((BlackjackPlayer) blackjack.getPlayer("dealer")).getScoreRound());

        
        assertTrue(blackjack.isRoundOver());
        
        assertEquals("dealer", blackjack.getWinnerRound());
        assertEquals(1, ((BlackjackPlayer) blackjack.getPlayer("dealer")).getScoreGame());

        blackjack.end();
        assertEquals("dealer", blackjack.getWinnerGame());

        // set dealer hand to 19 and player 0 to 20 and player 1 to 18
        
        blackjack.getPlayer("dealer").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("9").withColor("spade").withValue(9).build()).build());
        blackjack.getPlayer("player 0").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).build());
        blackjack.getPlayer("player 1").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("8").withColor("spade").withValue(8).build()).build());

        assertEquals(19, ((BlackjackPlayer) blackjack.getPlayer("dealer")).getScoreRound());
        assertEquals(20, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreRound());
        assertEquals(18, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreRound());

        assertFalse(blackjack.isRoundOver());
        blackjack.nextTurn();
        blackjack.nextTurn();
        blackjack.nextTurn();
        blackjack.nextTurn();
        blackjack.nextTurn();

        assertTrue(blackjack.isRoundOver());


        assertEquals("player 0", blackjack.getWinnerRound());

        assertEquals(1, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreGame());
        assertEquals(0, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreGame());
        assertEquals(1, ((BlackjackPlayer) blackjack.getPlayer("dealer")).getScoreGame());

        blackjack.end();
        assertEquals("Draw", blackjack.getWinnerGame()); 

        
        // set player 0 hand to 20 and player 1 to 20
        blackjack.getPlayer("player 0").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).build());
        blackjack.getPlayer("player 1").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).build());

        assertEquals(20, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreRound());
        assertEquals(20, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreRound());

        assertTrue(blackjack.isRoundOver());

        assertEquals(null, blackjack.getWinnerRound());

        assertEquals(1, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreGame());
        assertEquals(0, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreGame());
        assertEquals(1, ((BlackjackPlayer) blackjack.getPlayer("dealer")).getScoreGame());

        // set all hands to 21
        blackjack.getPlayer("dealer").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("as").withColor("spade").withValue(11).build()).build());
        blackjack.getPlayer("player 0").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("as").withColor("spade").withValue(11).build()).build());
        blackjack.getPlayer("player 1").setHand(new ProxyHandBuilder().addCard(new CardBuilder().withName("10").withColor("spade").withValue(10).build()).addCard(new CardBuilder().withName("as").withColor("spade").withValue(11).build()).build());

        assertEquals(21, ((BlackjackPlayer) blackjack.getPlayer("dealer")).getScoreRound());
        assertEquals(21, ((BlackjackPlayer) blackjack.getPlayer("player 0")).getScoreRound());
        assertEquals(21, ((BlackjackPlayer) blackjack.getPlayer("player 1")).getScoreRound());

        assertTrue(blackjack.isRoundOver());

        assertEquals(null, blackjack.getWinnerRound());
    }
}
