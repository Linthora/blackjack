package model.cards;

import java.util.LinkedList;

/**
 * A factory to create a classic card game with a deck of 52 or 32 cards.
 * Implements {@link model.cards.DeckFactory}.
 */
public class ClassicCardGameFactory implements DeckFactory{

    /**
     * Boolean stating if the deck should be 52 or 32 cards.
     */
    protected boolean large;

    /**
     * Constant list of the available colors.
     */
    public static final String[] colors = {"spade", "heart", "diamond", "club"};

    /**
     * Constant list of the available names.
     */
    public static final String[] names = {"as", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * Creates a new ClassicCardGameFactory.
     * @param large Boolean stating if the deck should be 52 or 32 cards.
     */
    public ClassicCardGameFactory(boolean large) {
        this.large = large;
    }

    /**
     * Create a new ClassicCardGameFactory for 52 cards deck.
     */
    public ClassicCardGameFactory() {
        this.large = true;
    }

    @Override
    public Deck createDeck() {
        if(this.large)
            return create52();

        return create32();
    }

    /**
     * Create a new deck of 52 cards.
     * @return The new deck.
     */
    public Deck create52() {
        LinkedList<Card> cards = new LinkedList<>();
        
        for(String color : ClassicCardGameFactory.colors) {
            int value = 1;
            for(String name : ClassicCardGameFactory.names) {
                cards.add(new CardBuilder().withColor(color).withName(name).withValue(value++).build());
            }
        }

        return new DeckBuilder().withDeck(cards).withMaxCards(52).build();
    }

    /**
     * Create a new deck of 32 cards.
     * @return The new deck.
     */
    public Deck create32() {
        LinkedList<Card> cards = new LinkedList<>();

        for(String color : ClassicCardGameFactory.colors) {
            cards.add(new CardBuilder().withName(ClassicCardGameFactory.names[0]).withColor(color).withValue(1).build());
            int value = 7;
            for(int i=6; i < ClassicCardGameFactory.names.length; ++i) {
                cards.add(new CardBuilder().withName(ClassicCardGameFactory.names[i]).withColor(color).withValue(value++).build());
            }
        }

        return new DeckBuilder().withDeck(cards).withMaxCards(32).build();
    }

    /**
     * Static method to create a new 52 or 32 card deck.
     * @param large Boolean stating if the deck should be 52 or 32 cards.
     * @return The new deck.
     */
    public static Deck createDeck(boolean large) {
        return new ClassicCardGameFactory(large).createDeck();
    }

    /**
     * Static method to create a new 32 card deck.
     * @return The new deck.
     */
    public static Deck create32Deck() {
        return new ProxyDeck(new ClassicCardGameFactory(false).createDeck());
    }

    /**
     * Static method to create a new 52 card deck.
     * @return The new deck.
     */
    public static Deck create52Deck() {
        return new ProxyDeck(new ClassicCardGameFactory(true).createDeck());
    }
}
