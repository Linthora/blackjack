package test;

import model.cards.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/**
 * Tests for Deck.
 */
public class DeckTest {

    @Test 
    public void testDeckBuilderAndSomeDeck() {
        DeckBuilder builder = new DeckBuilder();
        RealDeck deck = (RealDeck)builder.build();
        assertEquals(0, deck.size());
        assertEquals(300, deck.getMaxCards());
        assertFalse(deck.isShown());
        assertEquals(0, deck.getListeners().size());
        assertTrue(deck.isEmpty());
        assertFalse(deck.isFull());
        
        assertEquals(new RandomShuffler(deck.getRand()), deck.getShuffler());
        LinkedList<Card> cards = new LinkedList<>();
        cards.add(new CardBuilder().withName("a").withColor("r").withValue(1).build());
        cards.add(new CardBuilder().withName("b").withColor("r").withValue(2).build());
        cards.add(new CardBuilder().withName("c").withColor("r").withValue(3).build());
        cards.add(new CardBuilder().withName("d").withColor("r").withValue(4).build());
        cards.add(new CardBuilder().withName("e").withColor("r").withValue(5).build());
        for(Card c : cards) {
            deck.insertRandom(c);
        }
        assertEquals(5, deck.size());
        assertEquals(300, deck.getMaxCards());
        assertFalse(deck.isShown());
        assertEquals(0, deck.getListeners().size());
        assertFalse(deck.isEmpty());
        assertFalse(deck.isFull());
        deck.sort();
        assertEquals(cards, deck.getCards());
        assertEquals("Deck [deck=" + cards + ", isShown=" + false + ", maxCards=" + 300 + "]" , deck.toString());

        cards = new LinkedList<>();
        cards.add(new CardBuilder().withName("d").withColor("r").build());
        cards.add(new CardBuilder().withName("e").withColor("r").build());
        cards.add(new CardBuilder().withName("a").withColor("r").withValue(1).build());
        cards.add(new CardBuilder().withName("b").withColor("b").withValue(2).build());
        cards.add(new CardBuilder().withName("b").withColor("r").withValue(2).build());
        cards.add(new CardBuilder().withName("b").withColor("r").withValue(2).build());
        cards.add(new CardBuilder().withName("b").withColor("z").withValue(2).build());
        cards.add(new CardBuilder().withName("c").withColor("r").withValue(3).build());
        deck.getCards().clear();
        for(Card c : cards) {
            deck.insertRandom(c);
        }
        assertEquals(cards.size(), deck.size());
        deck.sort();
        assertEquals(cards, deck.getCards());

        assertEquals("Deck [deck=" + cards + ", isShown=" + false + ", maxCards=" + 300 + "]" , deck.toString());
    }

    @Test
    public void testRealDeck() {
        // note: we don't need to test shuffle random behavior because we use a method from java.util package
        // same goes for sort behavior

        LinkedList<Card> deck = new LinkedList<>();
        deck.add(new CardBuilder().withName("as").withColor("pique").withValue(11).build());
        deck.add(new CardBuilder().withName("roi").withColor("coeur").build());

        Deck d = new DeckBuilder().withDeck(deck).withShuffler(new Shuffler() {
            @Override
            public LinkedList<Card> shuffle(LinkedList<Card> deck) {
                return deck;
            }
        }).withRandom(new Random() {
            @Override
            public int nextInt(int bound) {
                return 0;
            }
        }).withComparator(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return 0;
            }
        }).withMaxCards(52).withIsShown(true).build();

        assertEquals(2, d.size());
        assertEquals(52, d.getMaxCards());
        assertFalse(d.isFull());
        assertTrue(d.isShown());
        assertFalse(d.isEmpty());
        assertEquals(deck, d.getCards());
        assertEquals(deck.get(0), d.draw());
        assertEquals(deck.get(1), d.draw());
        assertTrue(d.isEmpty());
        d.insertFirst(deck.get(0));
        d.insertFirst(deck.get(1));
        assertFalse(d.isEmpty());
        assertEquals(deck.get(0), d.drawLast());
        assertEquals(deck.get(1), d.drawLast());
        assertTrue(d.isEmpty());
        d.insertLast(deck.get(0));
        d.insertLast(deck.get(1));
        d.insertRandom(new CardBuilder().withName("dame").withColor("trefle").withValue(10).build());
        assertEquals(3, d.size());
        d.sort();
        deck.addFirst(new CardBuilder().withName("dame").withColor("trefle").withValue(10).build());
        assertEquals(deck, d.getCards());
        d.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return 0;
            }
        });
        assertEquals(deck, d.getCards());
        d.cut(1);
        deck.addFirst(deck.removeLast());
        assertEquals(deck, d.getCards());
        d.cut();
        deck.add(deck.removeFirst());
        assertEquals(deck, d.getCards());
        d.shuffle();
        assertEquals(deck, d.getCards());
        d.setIsShown(false);
        assertFalse(d.isShown());

        // test cut with more than 3 cards  
        deck.add(new CardBuilder().withName("valet").withColor("trefle").withValue(9).build());
        d.insertLast(deck.getLast());
        deck.add(new CardBuilder().withName("dame").withColor("pique").withValue(10).build());
        d.insertLast(deck.getLast());
        assertEquals(5, d.size());
        d.cut(3);
        assertNotEquals(deck, d.getCards());
        deck.add(deck.removeFirst());
        deck.add(deck.removeFirst());
        deck.add(deck.removeFirst());
        deck.add(deck.removeFirst());        
        assertEquals(deck, d.getCards());

        // now testing where throws should occur
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            d.insertFirst(null);
        });
        assertEquals("Can't insert a null card", e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> {
            d.insertLast(null);
        });
        e = assertThrows(IllegalArgumentException.class, () -> {
            d.insert(0, null);
        });
        assertEquals("Can't insert a null card", e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> {
            d.insertRandom(null);
        });
        assertEquals("Can't insert a null card", e.getMessage());
        
        e = assertThrows(IllegalArgumentException.class, () -> {
            d.insert(-1, new CardBuilder().withName("dame").withColor("pique").build());
        });
        assertEquals("Can't insert a card out of the deck bound.", e.getMessage());
        Deck d2 = new DeckBuilder().withMaxCards(0).build();
        assertTrue(d2.isEmpty());
        assertTrue(d2.isFull());
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.insert(0, new CardBuilder().withName("dame").withColor("pique").build());
        });
        assertEquals("Can't insert a card in a full deck", e.getMessage());
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.insert(1, new CardBuilder().withName("dame").withColor("pique").build());
        });
        assertEquals("Can't insert a card in a full deck", e.getMessage());
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.insertFirst(new CardBuilder().withName("dame").withColor("pique").build());
        });
        assertEquals("Can't insert a card in a full deck", e.getMessage());
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.insertLast(new CardBuilder().withName("dame").withColor("pique").build());
        });
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.insertRandom(new CardBuilder().withName("dame").withColor("pique").build());
        });
        assertEquals("Can't insert a card in a full deck", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            d2.cut(-1);
        });
        assertEquals("Index out of bounds", e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> {
            d2.cut(1);
        });
        assertEquals("Index out of bounds", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            d2.sort(null);
        });
        assertEquals("Can't sort with a null comparator", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            d2.shuffle(null);
        });
        assertEquals("Can't shuffle with a null shuffler", e.getMessage());

        e = assertThrows(IllegalCallerException.class, () -> {
            d2.draw();
        });
        assertEquals("Can't draw from an empty deck", e.getMessage());
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.drawLast();
        });
        assertEquals("Can't draw from an empty deck", e.getMessage());
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.drawRandom();
        });
        assertEquals("Can't draw from an empty deck", e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> {
            d.draw(-1);
        });
        assertEquals("Can't draw a card out of the deck bound.", e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> {
            d.draw(15);
        });
        assertEquals("Can't draw a card out of the deck bound.", e.getMessage());
        
        e = assertThrows(IllegalCallerException.class, () -> {
            d2.setMaxCards(123);
        });
        assertEquals("Can't change the max cards of a deck", e.getMessage());
    }
    
}
