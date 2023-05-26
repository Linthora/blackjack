package model.cards;

import java.util.LinkedList;

/**
 * An interface representing a method to shuffle a deck.
 */
public interface Shuffler {

    /**
     * Shuffles the given deck.
     * @param deck The deck to shuffle.
     * @return The shuffled deck.
     */
    public LinkedList<Card> shuffle(LinkedList<Card> deck);
}
