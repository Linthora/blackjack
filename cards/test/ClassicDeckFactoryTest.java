package test;

import model.cards.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for classic card factory.
 */
public class ClassicDeckFactoryTest {
    
    @Test
    public void testClassicDeckFactory() {
        System.out.println("Test ClassicDeckFactory");

        Deck d32 = ClassicCardGameFactory.create32Deck();
        assertEquals(32, d32.size());
        assertFalse(d32.isEmpty());
        assertFalse(d32.isShown());
        assertTrue(d32.isFull());
        assertEquals("ProxyDeck{Deck [isShown=" + false + ", maxCards=" + 32 + "]}" , d32.toString());
        assertEquals("Card: (as, spade, 1)", d32.draw().toString());
        assertEquals("Card: (7, spade, 7)", d32.draw().toString());
        assertEquals("Card: (king, club, 13)", d32.drawLast().toString());

        Deck d52 = ClassicCardGameFactory.create52Deck();
        assertEquals(52, d52.size());
        assertFalse(d52.isEmpty());
        assertFalse(d52.isShown());
        assertTrue(d52.isFull());
        assertEquals("ProxyDeck{Deck [isShown=" + false + ", maxCards=" + 52 + "]}" , d52.toString());
        assertEquals("Card: (as, spade, 1)", d52.draw().toString());
        assertEquals("Card: (2, spade, 2)", d52.draw().toString());
        assertEquals("Card: (king, club, 13)", d52.drawLast().toString());
        
    }
}
