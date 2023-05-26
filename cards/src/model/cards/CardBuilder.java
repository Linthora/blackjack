package model.cards;

/**
 * A builder for a Card.
 */
public class CardBuilder {

    /**
     * The name and color of the card
     */
    protected String name, color;

    /**
     * The value of the card
     */
    protected Integer value;

    /**
     * Creates a new CardBuilder.
     */
    public CardBuilder() {
        this.name = null;
        this.color = null;
        this.value = null;
    }

    /**
     * Sets the name of the card.
     * @param name The name of the card
     * @return The CardBuilder
     */
    public CardBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the color of the card.
     * @param color The color of the card
     * @return The CardBuilder
     */
    public CardBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Sets the value of the card.
     * @param value The value of the card
     * @return The CardBuilder
     */
    public CardBuilder withValue(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * Builds the card.
     * @return The card
     */
    public Card build() {
        if(this.name == null || this.color == null) {
            throw new IllegalStateException("Name and color must be set");
        }
        return new Card(this.name, this.color, this.value);
    }
}
