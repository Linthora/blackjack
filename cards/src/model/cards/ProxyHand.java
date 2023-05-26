package model.cards;

import java.util.*;

import util.*;

/**
 * A class representing a hand based on a Deck instance.
 * Implements {@link model.cards.Deck}
 */
public class ProxyHand implements Deck {

    /**
     * The deck to proxy.
     */
    protected Deck hand;

    /**
     * Creates a new ProxyHand.
     * @param hand The hand to proxy.
     */
    public ProxyHand(Deck hand) {
        this.hand = hand;
    }

    @Override
    public void shuffle() {
        throw new UnsupportedOperationException("Can't shuffle a hand");
    }

    @Override
    public void shuffle(Shuffler shuffler) {
        throw new UnsupportedOperationException("Can't shuffle a hand");
    }

    @Override
    public void cut(int index) {
        throw new UnsupportedOperationException("Can't cut a hand");
    }
    
    @Override
    public void cut() {
        throw new UnsupportedOperationException("Can't cut a hand");
    }

    @Override
    public Card draw() {
        return this.hand.draw();
    }

    @Override
    public Card draw(int index) {
        return this.hand.draw(index);
    }

    @Override
    public Card drawRandom() {
        return this.hand.drawRandom();
    }

    @Override
    public Card drawLast() {
        return this.hand.drawLast();
    }

    @Override
    public void insertLast(Card card) {
        this.hand.insertLast(card);
    }

    @Override
    public void insertFirst(Card card) {
        this.hand.insertFirst(card);
    }

    @Override
    public void insert(int index, Card card) {
        this.hand.insert(index, card);
    }

    @Override
    public void insertRandom(Card card) {
        this.hand.insertRandom(card);
    }

    @Override
    public void sort() {
        this.hand.sort();
    }

    @Override
    public void sort(Comparator<Card> comparator) {
        this.hand.sort(comparator);
    }

    @Override
    public void setIsShown(boolean isShown) {
        this.hand.setIsShown(isShown);
    }

    @Override
    public boolean isShown() {
        return this.hand.isShown();
    }

    @Override
    public int size() {
        return this.hand.size();
    }

    @Override
    public boolean isEmpty() {
        return this.hand.isEmpty();
    }

    @Override
    public boolean isFull() {
        return this.hand.isFull();
    }

    @Override
    public int getMaxCards() {
        return this.hand.getMaxCards();
    }

    @Override
    public void setMaxCards(int maxCards) {
        this.hand.setMaxCards(maxCards);
    }

    @Override
    public Iterator<Card> iterator() {
        return this.hand.iterator();
    }

    @Override
    public List<Card> getCards() {
        return this.hand.getCards();
    }

    @Override
    public String toString() {
        return "ProxyHand{" + this.hand + '}';
    }

    @Override
    public void addListener(ModelListener listener) {
        this.hand.addListener(listener);
    }

    @Override
    public void removeListener(ModelListener listener) {
        this.hand.removeListener(listener);
    }

    @Override
    public void fireChangement() {
        this.hand.fireChangement();
    }
}
