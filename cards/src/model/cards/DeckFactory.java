package model.cards;

/**
 * Interface representing a factory for creating decks.
 */
public interface DeckFactory {

    /**
     * Creates a new deck.
     * @return The new deck.
     */
    public Deck createDeck();
}
