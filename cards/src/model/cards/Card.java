package model.cards;

/**
 * A class which represents a card. Can be extended if needed.
 */
public class Card {

        /**
         * The name and color of the card.
         */
        protected String name, color; 

        /**
         * The value of the card.
         */
        protected Integer value;
        
        /**
         * Creates a new card with the given name, color and value.
         * @param name The name of the card.
         * @param color The color of the card.
         * @param value The value of the card.
         */
        public Card(String name, String color, Integer value) {
                this.value = value;
                this.color = color;
                this.name = name;
        }

        /**
         * Creates a new card with the given name and color. The value is set to null.
         * @param name The name of the card.
         * @param color The color of the card.
         */
        public Card(String name, String color) {
                this(name, color, null);
        }

        /**
         * Returns the value of the card.
         * @return The value of the card.
         */
        public Integer getValue() {
                return this.value;
        }

        /**
         * Returns the color of the card.
         * @return The color of the card.
         */
        public String getColor() {
                return this.color;
        }
        
        /**
         * Returns the name of the card.
         * @return The name of the card.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Tells whether or not the card has a value.
         * @return True if the card has a value, false otherwise.
         */
        public boolean hasValue() {
                return this.value != null;
        }

        /**
         * Sets the value of the card.
         * @param value The new value of the card.
         */
        public void setValue(Integer value) {
                this.value = value;
        }

        @Override
        public String toString() {
                return "Card: (" + this.name + ", " + this.color + ", " + this.value + ")";
        }

        @Override
        public boolean equals(Object o) {
                if(o instanceof Card) {
                        Card c = (Card) o;
                        return this.value == c.getValue() && this.color.equals(c.getColor()) && this.name.equals(c.getName());
                }
                return false;
        }

}