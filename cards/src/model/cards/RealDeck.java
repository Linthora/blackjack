package model.cards;

import java.util.*;

import util.AbstractObservableModel;

/**
 * A class representing a deck.
 * Implements {@link model.cards.Deck}
 */
public class RealDeck extends AbstractObservableModel implements Deck {

    /**
     * The cards in the deck.
     */
    protected LinkedList<Card> deck;

    /**
     * The shuffler used to shuffle the deck by default.
     */
    protected Shuffler shuffler;

    /**
     * The Random used in the deck.
     */
    protected Random rand;

    /**
     * The comparator used to sort the deck by default.
     */
    protected Comparator<Card> comparator;

    /**
     * The max capacity of the deck.
     */
    protected final int maxCards;

    /**
     * Is the deck visible?
     */
    protected boolean isShown;

    /**
     * Creates a new RealDeck with given attributes.
     * @param deck The cards in the deck.
     * @param shuffler The shuffler used to shuffle the deck by default.
     * @param rand The Random used in the deck
     * @param comparator The comparator used to sort the deck by default.
     * @param maxCards The max capacity of the deck.
     * @param isShown Is the deck visible?
     */
    public RealDeck(List<Card> deck, Shuffler shuffler, Random rand, Comparator<Card> comparator, int maxCards, boolean isShown) {
        super(new LinkedList<>());
        this.deck = new LinkedList<>(deck);
        this.shuffler = shuffler;
        this.rand = rand;
        this.comparator = comparator;
        this.isShown = isShown;
        this.maxCards = maxCards;
    }

    @Override
    public void shuffle() {
        shuffle(this.shuffler);
    }

    @Override
    public void shuffle(Shuffler shuffler) {
        if(shuffler == null) {
            throw new IllegalArgumentException("Can't shuffle with a null shuffler");
        }
        this.deck = shuffler.shuffle(this.deck);
        this.fireChangement();
    }

    @Override
    public void cut(int index) {
        if(index < 0 || index >= this.deck.size()-1) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        
        if(this.deck.size() < 2)
            return;

        LinkedList<Card> bis = new LinkedList<>();

        for(int i=0; i <= index; ++i) {
            bis.add(this.deck.poll());
        }
        this.deck.addAll(bis);
        this.fireChangement();
    }

    public void cut() {
        int cutIndex = 0;
        if(this.deck.size() > 2)   
            cutIndex = this.rand.nextInt(this.deck.size()-2);
        this.cut(cutIndex);
    }

    @Override
    public Card draw() {
        if(this.deck.isEmpty())
            throw new IllegalCallerException("Can't draw from an empty deck");
        Card res = this.deck.poll();
        fireChangement();
        return res;
    }

    @Override
    public Card draw(int index) {
        if(this.deck.isEmpty())
            throw new IllegalCallerException("Can't draw from an empty deck");
            
        if(index < 0 || index >= this.deck.size())
            throw new IllegalArgumentException("Can't draw a card out of the deck bound.");
        
        Card res = this.deck.remove(index);
        fireChangement();
        return res;
    }

    @Override
    public Card drawRandom() {
        if(this.deck.isEmpty())
            throw new IllegalCallerException("Can't draw from an empty deck");
        int index = this.rand.nextInt(this.deck.size());
        Card res = this.deck.remove(index);
        fireChangement();
        return res;
    }

    @Override
    public Card drawLast() {
        if(this.deck.isEmpty())
            throw new IllegalCallerException("Can't draw from an empty deck");
        Card res = this.deck.removeLast();
        fireChangement();
        return res;
    }

    @Override
    public void insertLast(Card card) {
        if(card == null)
            throw new IllegalArgumentException("Can't insert a null card");
        if(this.deck.size() >= this.maxCards)
            throw new IllegalCallerException("Can't insert a card in a full deck");
        this.deck.add(card);
        fireChangement();
    }

    @Override
    public void insertFirst(Card card) {
        if(card == null)
            throw new IllegalArgumentException("Can't insert a null card");
        if(this.deck.size() >= this.maxCards)
            throw new IllegalCallerException("Can't insert a card in a full deck");
        this.deck.addFirst(card);
        fireChangement();
    }

    @Override
    public void insert(int index, Card card) {
        if(card == null)
            throw new IllegalArgumentException("Can't insert a null card");
        if(this.deck.size() >= this.maxCards)
            throw new IllegalCallerException("Can't insert a card in a full deck");
        if(index < 0)
            throw new IllegalArgumentException("Can't insert a card out of the deck bound.");
        if(index >= this.deck.size())
            this.deck.add(card);
        else
            this.deck.add(index, card);
        fireChangement();
    }

    @Override
    public void insertRandom(Card card) {
        int index = isEmpty() ? 0 : this.rand.nextInt(this.deck.size());
        insert(index, card);
    }

    @Override
    public void sort() {
        sort(this.comparator);
    }

    @Override
    public void sort(Comparator<Card> comparator) {
        if(comparator == null)
            throw new IllegalArgumentException("Can't sort with a null comparator");
        this.deck.sort(comparator);
        fireChangement();
    }

    @Override
    public void setIsShown(boolean isShown) {
        this.isShown = isShown;
        fireChangement();
    }

    @Override
    public boolean isShown() {
        return this.isShown;
    }

    @Override
    public int getMaxCards() {
        return this.maxCards;
    }

    @Override
    public void setMaxCards(int maxCards) {
        throw new IllegalCallerException("Can't change the max cards of a deck");
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
        return this.deck.size() >= this.maxCards;
    }

    @Override
    public Iterator<Card> iterator() {
        return this.deck.iterator();
    }

    @Override
    public List<Card> getCards() {
        return this.deck;
    }

    @Override
    public String toString() {
        return "Deck [deck=" + this.deck + ", isShown=" + this.isShown + ", maxCards=" + this.maxCards + "]";
    }

    /**
     * Returns the Random object used by the deck
     * @return the Random object used by the deck
     */
    public Random getRand() {
        return rand;
    }

    /**
     * Returns the comparator used by the deck
     * @return the comparator used by the deck
     */
    public Comparator<Card> getComparator() {
        return comparator;
    }

    /**
     * Returns the shuffler used by the deck
     * @return the shuffler used by the deck
     */
    public Shuffler getShuffler() {
        return shuffler;
    }
}
