package test;

import model.cards.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Card.
 */
public class CardTest {

    @Test
    public void testCard() {
        System.out.println("Test Card");
        Card c = new Card("as", "pique", 11);
        assertEquals("as", c.getName());
        assertEquals("pique", c.getColor());
        assertEquals(11, c.getValue().intValue());
        assertEquals("Card: (as, pique, 11)", c.toString());
        assertTrue(c.hasValue());

        Card c2 = new Card("roi", "coeur");
        assertEquals("roi", c2.getName());
        assertEquals("coeur", c2.getColor());
        assertNull(c2.getValue());
        assertFalse(c2.hasValue());
        assertEquals("Card: (roi, coeur, null)", c2.toString());
        c2.setValue(10);
        assertTrue(c2.hasValue());
        assertEquals(10, c2.getValue().intValue());

        assertFalse(c.equals(c2));
        assertFalse(c2.equals(c));
        assertTrue(c2.equals(new Card("roi", "coeur", 10)));
        assertTrue(new Card("roi", "coeur", 10).equals(c2));

        assertFalse(c.equals(new Card("roi", "coeur", 10)));
        assertFalse(new Card("roi", "coeur", 10).equals(c));
    }

    @Test
    public void testCardBuilder() {
        System.out.println("Test CardBuilder");
        Card c = new CardBuilder().withName("as").withColor("pique").withValue(11).build();
        assertEquals("as", c.getName());
        assertEquals("pique", c.getColor());
        assertEquals(11, c.getValue().intValue());
        assertEquals("Card: (as, pique, 11)", c.toString());
        assertTrue(c.hasValue());

        Card c2 = new CardBuilder().withName("roi").withColor("coeur").build();
        assertEquals("roi", c2.getName());
        assertEquals("coeur", c2.getColor());
        assertNull(c2.getValue());
        assertFalse(c2.hasValue());
        assertEquals("Card: (roi, coeur, null)", c2.toString());
        c2.setValue(10);
        assertTrue(c2.hasValue());
        assertEquals(10, c2.getValue().intValue());

        assertFalse(c.equals(c2));
        assertFalse(c2.equals(c));

        Exception e = assertThrows(IllegalStateException.class, () -> {new CardBuilder().build();});
        assertEquals("Name and color must be set", e.getMessage());
        e = assertThrows(IllegalStateException.class, () -> {new CardBuilder().withName("as").build();});
        assertEquals("Name and color must be set", e.getMessage());
        e = assertThrows(IllegalStateException.class, () -> {new CardBuilder().withColor("pique").build();});
        assertEquals("Name and color must be set", e.getMessage());
        e = assertThrows(IllegalStateException.class, () -> {new CardBuilder().withName("as").withValue(2).build();});
        assertEquals("Name and color must be set", e.getMessage());
    }
    
}