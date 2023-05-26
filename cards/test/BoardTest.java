package test;

import model.cards.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class containing the tests for the Board class.
 */
public class BoardTest {

    @Test
    public void testBoard() {
        Board board = new BlankBoard();
        assertEquals(0, board.getPlayers().size());
        assertEquals(0, board.getDecks().size());
        assertEquals(0, board.getHands().size());
        assertEquals(0, board.getPlayerList().size());

        // Test addPlayer
        Player p1 = new BlankPlayer("p1", null,null);
        board.addPlayer(p1);
        assertEquals(1, board.getPlayers().size());
        assertEquals(p1, board.getPlayers().get("p1"));
        assertEquals(p1, board.getPlayer("p1"));
        assertEquals(p1, board.getPlayerList().get(0));
        assertEquals(1, board.getPlayerList().size());

        // Test addDeck
        Deck d1 = new DeckBuilder().build();
        board.addDeck("1", d1);
        assertEquals(1, board.getDecks().size());
        assertEquals(d1, board.getDecks().get("1"));
        assertEquals(d1, board.getDeck("1"));

        // Test addHand
        Deck h1 = new DeckBuilder().build();
        board.addHand("1", h1);
        assertEquals(1, board.getHands().size());
        assertEquals(h1, board.getHands().get("1"));
        assertEquals(h1, board.getHand("1"));
        
        // Test removePlayer
        
        board.removePlayer("p1");
        assertEquals(0, board.getPlayers().size());
        assertEquals(0, board.getPlayerList().size());

        // Test removeDeck
        board.removeDeck("1");
        assertEquals(0, board.getDecks().size());
        
        // Test removeHand
        board.removeHand("1");
        assertEquals(0, board.getHands().size());

        // Test addPlayer with a null player
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            board.addPlayer(null);
        });
        assertEquals("Can't add a null player or a null name", e.getMessage());
        assertEquals(0, board.getPlayers().size());

        // Test addPlayer with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addPlayer(new BlankPlayer(null, null, null));
        });
        assertEquals("Can't add a null player or a null name", e.getMessage());
        assertEquals(0, board.getPlayers().size());

        // test adding players with the same name
        board.addPlayer(new BlankPlayer("p1", null, null));
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addPlayer(new BlankPlayer("p1", null, null));
        });
        assertEquals("A player with this name already exists", e.getMessage());
        assertEquals(1, board.getPlayers().size());

        // Test addDeck with a null deck
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addDeck("1", null);
        });
        assertEquals("Can't add a null deck or a null name", e.getMessage());
        assertEquals(0, board.getDecks().size());

        // Test addDeck with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addDeck(null, new DeckBuilder().build());
        });
        assertEquals("Can't add a null deck or a null name", e.getMessage());
        assertEquals(0, board.getDecks().size());

        // Test addDeck that already exists
        board.addDeck("1", new DeckBuilder().build());
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addDeck("1", new DeckBuilder().build());
        });
        assertEquals("A deck with this name already exists", e.getMessage());
        assertEquals(1, board.getDecks().size());

        // Test addHand with a null hand
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addHand("1", null);
        });
        assertEquals("Can't add a null hand or a null name", e.getMessage());
        assertEquals(0, board.getHands().size());

        // Test addHand with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addHand(null, new DeckBuilder().build());
        });
        assertEquals("Can't add a null hand or a null name", e.getMessage());

        // Test addHand that already exists
        board.addHand("1", new DeckBuilder().build());
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.addHand("1",new DeckBuilder().build());
        });
        assertEquals("A hand with this name already exists", e.getMessage());
        assertEquals(1, board.getHands().size());

        // test removePlayer with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.removePlayer(null);
        });
        assertEquals("Can't remove a null player", e.getMessage());

        // test removePlayer with a player that doesn't exist
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.removePlayer("2");
        });
        assertEquals("A player with this name doesn't exist", e.getMessage());

        // test removeDeck with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.removeDeck(null);
        });
        assertEquals("Can't remove a null deck", e.getMessage());

        // test removeDeck with a deck that doesn't exist
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.removeDeck("2");
        });
        assertEquals("No deck with this name", e.getMessage());

        // test removeHand with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.removeHand(null);
        });
        assertEquals("Can't remove a null hand", e.getMessage());

        // test removeHand with a hand that doesn't exist
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.removeHand("2");
        });
        assertEquals("No hand with this name", e.getMessage());


        // test getPlayer with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.getPlayer(null);
        });
        assertEquals("Can't get a null player", e.getMessage());

        // test getPlayer with a player that doesn't exist
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.getPlayer("2");
        });
        assertEquals("No player with this name", e.getMessage());

        // test getDeck with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.getDeck(null);
        });
        assertEquals("Can't get a null deck", e.getMessage());

        // test getDeck with a deck that doesn't exist
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.getDeck("2");
        });
        assertEquals("No deck with this name", e.getMessage());

        // test getHand with a null name
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.getHand(null);
        });
        assertEquals("Can't get a null hand", e.getMessage());

        // test getHand with a hand that doesn't exist
        e = assertThrows(IllegalArgumentException.class, () -> {
            board.getHand("2");
        });
        assertEquals("No hand with this name", e.getMessage());
    }
    
}
