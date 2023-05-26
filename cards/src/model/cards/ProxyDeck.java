package model.cards;

import java.util.*;

import util.*;

/**
 * A class representing a deck defined as a classic deck that you can't see through.
 * implements {@link model.cards.Deck}
 */
public class ProxyDeck implements Deck {

    /**
     * The deck to proxy.
     */
    protected Deck deck;

    /**
     * Creates a new ProxyDeck.
     * @param deck The deck to proxy.
     */
    public ProxyDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void shuffle() {
        this.deck.shuffle();
    }

    @Override
    public void shuffle(Shuffler shuffler) {
        this.deck.shuffle(shuffler);
    }

    @Override
    public void cut(int index) {
        this.deck.cut(index);
    }

    @Override
    public void cut() {
        this.deck.cut();
    }

    @Override
    public Card draw() {
        return this.deck.draw();
    }

    @Override
    public Card draw(int index) {
        return this.deck.draw(index);
    }

    @Override
    public Card drawRandom() {
        return this.deck.drawRandom();
    }

    @Override
    public Card drawLast() {
        return this.deck.drawLast();
    }

    @Override
    public void insertLast(Card card) {
        this.deck.insertLast(card);
    }

    @Override
    public void insertFirst(Card card) {
        this.deck.insertFirst(card);
    }

    @Override
    public void insert(int index, Card card) {
        this.deck.insert(index, card);
    }

    @Override
    public void insertRandom(Card card) {
        this.deck.insertRandom(card);
    }

    @Override
    public void sort() {
        this.deck.sort();
    }

    @Override
    public void sort(Comparator<Card> comparator) {
        this.deck.sort(comparator);
    }

    @Override
    public void setIsShown(boolean isShown) {
        this.deck.setIsShown(isShown);
    }

    @Override
    public boolean isShown() {
        return this.deck.isShown();
    }

    @Override
    public int size() {
        return this.deck.size();
    }

    @Override
    public boolean isEmpty() {
        return this.deck.isEmpty();
    }

    @Override
    public boolean isFull() {
        return this.deck.isFull();
    }

    @Override
    public int getMaxCards() {
        return this.deck.getMaxCards();
    }

    @Override
    public void setMaxCards(int maxCards) {
        this.deck.setMaxCards(maxCards);
    }

    @Override
    public Iterator<Card> iterator() {
        if(this.deck.isShown()) {
            return this.deck.iterator();
        }
        throw new UnsupportedOperationException("Deck is not shown");
    }

    @Override
    public List<Card> getCards() {
        if(this.deck.isShown()) {
            return this.deck.getCards();
        }
        throw new UnsupportedOperationException("Deck is not shown");
    }

    @Override
    public void addListener(ModelListener listener) {
        this.deck.addListener(listener);
    }

    @Override
    public void removeListener(ModelListener listener) {
        this.deck.removeListener(listener);
    }

    @Override
    public String toString() {
        return "ProxyDeck{Deck [" + (this.deck.isShown() ? ("deck="+this.deck.getCards()+", ") : "" ) + "isShown=" + this.deck.isShown() + ", maxCards=" + this.deck.getMaxCards() + "]}";
    }

    @Override 
    public void fireChangement() {
        this.deck.fireChangement();
    }
}
