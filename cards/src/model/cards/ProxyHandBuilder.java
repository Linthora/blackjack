package model.cards;

/**
 * A builder for a hand defined by ProxyHand.
 */
public class ProxyHandBuilder extends DeckBuilder {

    /**
     * Constructs a new ProxyHandBuilder.
     */
    public ProxyHandBuilder() {
        super();
    }

    @Override
    public Deck build() {
        return new ProxyHand(super.build());
    }
    
}
