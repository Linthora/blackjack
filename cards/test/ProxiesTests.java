package test;

import model.cards.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/**
 * Tests for the proxies.
 */
public class ProxiesTests {
    
    @Test
    public void testProxyHand() {
        Deck h = new ProxyHandBuilder().build();
        assertTrue(h instanceof ProxyHand);
    
        Exception e = assertThrows(UnsupportedOperationException.class, () -> h.shuffle());
        assertEquals("Can't shuffle a hand", e.getMessage());
        e = assertThrows(UnsupportedOperationException.class, () -> h.shuffle(new RandomShuffler(new Random())));
        assertEquals("Can't shuffle a hand", e.getMessage());
        e = assertThrows(UnsupportedOperationException.class, () -> h.cut(0));
        assertEquals("Can't cut a hand", e.getMessage());
        e = assertThrows(UnsupportedOperationException.class, () -> h.cut());
        assertEquals("Can't cut a hand", e.getMessage());
        assertEquals("ProxyHand{Deck [deck=" + new LinkedList<>() + ", isShown=" + false + ", maxCards=" + 300 + "]}" , h.toString());

        h.insertFirst(new CardBuilder().withName("a").withColor("r").withValue(1).build());
        h.insertFirst(new CardBuilder().withName("b").withColor("r").withValue(2).build());
        h.insertLast(new CardBuilder().withName("c").withColor("r").withValue(3).build());
        h.insert(1, new CardBuilder().withName("d").withColor("r").withValue(4).build());

        assertEquals(4, h.size());
        assertEquals(300, h.getMaxCards());
        assertFalse(h.isShown());
        assertFalse(h.isEmpty());
        assertFalse(h.isFull());
        assertEquals("ProxyHand{Deck [deck=" + h.getCards() + ", isShown=" + false + ", maxCards=" + 300 + "]}" , h.toString());
        h.sort();
        h.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        h.setIsShown(true);
        assertTrue(h.isShown());
        assertTrue(h.iterator().getClass().getName().equals("java.util.LinkedList$ListItr"));
    }

    @Test
    public void testProxyDeck() {
        Deck d = new ProxyDeckBuilder().build();
        assertTrue(d instanceof ProxyDeck);
        assertFalse(d.isShown());

        Exception e = assertThrows(UnsupportedOperationException.class, () -> d.getCards());
        assertEquals("Deck is not shown", e.getMessage());
        e = assertThrows(UnsupportedOperationException.class, () -> d.iterator());
        assertEquals("Deck is not shown", e.getMessage());
        assertEquals("ProxyDeck{Deck [isShown=" + false + ", maxCards=" + 300 + "]}" , d.toString());
        d.setIsShown(true);
        assertTrue(d.isShown());
        assertEquals("ProxyDeck{Deck [deck=" + d.getCards() + ", isShown=" + true + ", maxCards=" + 300 + "]}" , d.toString());

        d.insertFirst(new CardBuilder().withName("a").withColor("r").withValue(1).build());
        d.insertFirst(new CardBuilder().withName("b").withColor("r").withValue(2).build());
        d.insertLast(new CardBuilder().withName("c").withColor("r").withValue(3).build());
        d.insert(1, new CardBuilder().withName("d").withColor("r").withValue(4).build());

        assertEquals(4, d.size());
        assertEquals(300, d.getMaxCards());
        assertTrue(d.isShown());
        assertFalse(d.isEmpty());
        assertFalse(d.isFull());
        assertEquals("ProxyDeck{Deck [deck=" + d.getCards() + ", isShown=" + true + ", maxCards=" + 300 + "]}" , d.toString());
        d.sort();
        d.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertTrue(d.iterator().getClass().getName().equals("java.util.LinkedList$ListItr"));
    }
}
