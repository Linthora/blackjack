package model.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * A class implementing {@link model.cards.Shuffler} that shuffles a deck randomly.
 */
public class RandomShuffler implements Shuffler {

    /**
     * The random to use.
     */
    protected Random rand;

    /**
     * Creates a new RandomShuffler.
     */
    public RandomShuffler() {
        this.rand = new Random();
    }

    /**
     * Creates a new RandomShuffler.
     * @param rand The random to use.
     */
    public RandomShuffler(Random rand) {
        this.rand = rand;
    }

    @Override
    public LinkedList<Card> shuffle(LinkedList<Card> deck) {
        LinkedList<Card> res = new LinkedList<>(deck);
        Collections.shuffle(res, rand);
        return res;
    }

    @Override
    public String toString() {
        return "RandomShuffler [rand=" + rand + "]";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(getClass() != o.getClass()) {
            return false;
        }
        RandomShuffler shuffler = (RandomShuffler) o;
        return this.rand != null 
                && shuffler.getRand() != null
                && this.rand.equals(shuffler.getRand());
    }

    /**
     * Returns the random used.
     * @return The random used.
     */
    public Random getRand() {
        return this.rand;
    }


}
