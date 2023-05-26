package model.cards;

/**
 * A builder for a Deck defined by ProxyDeck.
 */
public class ProxyDeckBuilder extends DeckBuilder {

    /**
     * Constructs a new ProxyDeckBuilder.
     */
    public ProxyDeckBuilder() {
        super();
    }

    @Override
    public Deck build() {
        return new ProxyDeck(super.build());
    }
    
    
}
