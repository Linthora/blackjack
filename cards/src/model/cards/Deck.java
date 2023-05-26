package model.cards;

import java.util.*;
import util.ObservableModel;

/**
 * An interface representing a Deck of cards.
 * Implements {@link util.ObservableModel}
 */
public interface Deck extends ObservableModel {

    /**
     * Shuffles the deck.
     */
    public void shuffle();

    /**
     * Shuffles the deck with given shuffler.
     * @param shuffler The shuffler to use.
     */
    public void shuffle(Shuffler shuffler);

    /**
     * Cut the deck at the given index.
     * @param index The index to cut at.
     */
    public void cut(int index);

    /**
     * Cut the deck at random index.
     */
    public void cut();

    /**
     * Draw a card from the top of the deck.
     * @return The card drawn.
     */
    public Card draw();

    /**
     * Draw a card from given index.
     * @param index The index to draw from.
     * @return The card drawn.
     */
    public Card draw(int index);

    /**
     * Draw a random card from the deck.
     * @return The card drawn.
     */
    public Card drawRandom();

    /**
     * Draw a card from the bottom of the deck.
     * @return The card drawn.
     */
    public Card drawLast();

    /**
     * Insert a card at the bottom of the deck.
     * @param card The card to insert.
     */
    public void insertLast(Card card);

    /**
     * Insert a card at the top of the deck.
     * @param card The card to insert.
     */
    public void insertFirst(Card card);

    /**
     * Insert a card at given index.
     * @param index The index to insert at.
     * @param card The card to insert.
     */
    public void insert(int index, Card card);

    /**
     * Insert a card at a random index.
     * @param card The card to insert.
     */
    public void insertRandom(Card card);

    /**
     * Sort the deck.
     */
    public void sort();

    /**
     * Sort the deck with given comparator.
     * @param comparator The comparator to use.
     */
    public void sort(Comparator<Card> comparator);

    /**
     * Set the visibility of the deck.
     * @param isShown True if the deck is visible, false otherwise.
     */
    public void setIsShown(boolean isShown);

    /**
     * Get the visibility of the deck.
     * @return True if the deck is visible, false otherwise.
     */
    public boolean isShown();

    /**
     * Get the number of cards in the deck.
     * @return The number of cards in the deck.
     */
    public int size();

    /**
     * Tells if the deck is empty.
     * @return True if the deck is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Tells if the deck is full.
     * @return True if the deck is full, false otherwise.
     */
    public boolean isFull();

    /**
     * Returns the max capacity of the deck.
     * @return The max capacity of the deck.
     */
    public int getMaxCards();

    /**
     * Sets the max capacity of the deck.
     * @param maxCards The new max capacity of the deck.
     */
    public void setMaxCards(int maxCards);

    /**
     * Returns an iterator over the cards in the deck.
     * @return An iterator over the cards in the deck.
     */
    public Iterator<Card> iterator();

    /**
     * Returns a list of the cards in the deck.
     * @return A list of the cards in the deck.
     */
    public List<Card> getCards();
    


}
