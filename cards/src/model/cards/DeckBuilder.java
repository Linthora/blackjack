package model.cards;

import java.util.*;

import util.ModelListener;

/**
 * A builder for a Deck.
 */
public class DeckBuilder {

    /**
     * The cards to build the deck with.
     */
    protected LinkedList<Card> deck;

    /**
     * The shuffler to use in the deck.
     */
    protected Shuffler shuffler;

    /**
     * The Random to use in the deck.
     */
    protected Random rand;

    /**
     * The comparator to use in the deck.
     */
    protected Comparator<Card> comparator;

    /**
     * The maximum capacity of the deck.
     */
    protected Integer maxCards;

    /**
     * Is the deck visible?
     */
    protected Boolean visible;

    /**
     * The listeners of the deck.
     */
    protected List<ModelListener> listeners;

    /**
     * Creates a new DeckBuilder.
     */
    public DeckBuilder() {
        this.deck = new LinkedList<>();
        this.rand = new Random();
        this.shuffler = new RandomShuffler(rand);
        this.comparator =  (card1, card2) -> {
                if(card1.hasValue() && card2.hasValue()) {
                    int tmp = card1.getValue().compareTo(card2.getValue());
                    if(tmp == 0) {
                        return card1.toString().compareTo(card2.toString());
                    }
                    return tmp;
                } else if(card1.hasValue()) {
                    return 1;
                } else if(card2.hasValue()) {
                    return -1;
                } else {
                    return card1.toString().compareTo(card2.toString());
                }
            };
        this.maxCards = 300;
        this.visible = false;
    }

    /**
     * Replace the cards in the deck with the given cards.
     * @param deck The cards to use.
     * @return The DeckBuilder.
     */
    public DeckBuilder withDeck(LinkedList<Card> deck) {
        this.deck = deck;
        return this;
    }

    /**
     * Add a card to the deck.
     * @param card The card to add.
     * @return The DeckBuilder.
     */
    public DeckBuilder addCard(Card card) {
        this.deck.add(card);
        return this;
    }

    /**
     * Add all the cards in the given deck.
     * @param cards The cards to add.
     * @return The DeckBuilder.
     */
    public DeckBuilder addCards(List<Card> cards) {
        this.deck.addAll(cards);
        return this;
    }

    /**
     * Replace the shuffler in the deck with the given shuffler.
     * @param shuffler The shuffler to use.
     * @return The DeckBuilder.
     */
    public DeckBuilder withShuffler(Shuffler shuffler) {
        this.shuffler = shuffler;
        return this;
    }

    /**
     * Replace the Random in the deck with the given Random.
     * @param rand The Random to use.
     * @return The DeckBuilder.
     */
    public DeckBuilder withRandom(Random rand) {
        this.rand = rand;
        return this;
    }

    /**
     * Replace the comparator in the deck with the given comparator.
     * @param comparator The comparator to use.
     * @return The DeckBuilder.
     */
    public DeckBuilder withComparator(Comparator<Card> comparator) {
        this.comparator = comparator;
        return this;
    }

    /**
     * Replace the visibility of the deck with the given visibility.
     * @param visible The visibility to use.
     * @return The DeckBuilder.
     */
    public DeckBuilder withIsShown(boolean visible) {
        this.visible = visible;
        return this;
    }

    /**
     * Replace the maximum capacity of the deck with the given maximum capacity.
     * @param maxCards The maximum capacity to use.
     * @return The DeckBuilder.
     */
    public DeckBuilder withMaxCards(int maxCards) {
        this.maxCards = maxCards;
        return this;
    }

    /**
     * Add a listener to the deck.
     * @param listener The listener to add.
     * @return The DeckBuilder.
     */
    public DeckBuilder addListener(ModelListener listener) {
        this.listeners.add(listener);
        return this;
    }

    /**
     * Build the deck with the given parameters.
     * @return The deck built if the parameters are valid.
     * @throws IllegalStateException If one or more parameter are invalid.
     */
    public Deck build() {
        if(this.maxCards < 0)
            throw new IllegalStateException("Max cards must be positive");
        if(this.maxCards < this.deck.size())
            throw new IllegalStateException("Max cards must be greater than deck size");
        if(this.shuffler == null)
            throw new IllegalStateException("Shuffler must be set");
        if(this.rand == null)
            throw new IllegalStateException("Random must be set");
        if(this.comparator == null)
            throw new IllegalStateException("Comparator must be set");
        if(this.visible == null)
            throw new IllegalStateException("Visible must be set");
        if(this.deck == null)
            throw new IllegalStateException("Deck must be set");
        return new RealDeck(this.deck, this.shuffler, this.rand, this.comparator, this.maxCards, this.visible);
    }
}
