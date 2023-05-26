package model.blackjack;

import java.util.*;

import model.cards.*;
import util.AbstractObservableModel;
//import util.ModelListener;

/**
 * A class which represents a player in a blackjack game.
 * Implements {@link model.cards.Player}.
 */
public class BlackjackPlayer extends AbstractObservableModel implements Player {

    /**
     * The player's name.
     */
    protected String name;

    /**
     * The player's hand.
     */
    protected Deck hand;

    /**
     * The player's score for the current round.
     */
    protected int scoreRound;

    /**
     * The player's score for the current game.
     */
    protected int scoreGame;

    /**
     * Is this the player's turn?
     */
    protected boolean isPlaying;

    /**
     * The player's strategy. Or null if the player is a human and we get its action from the view.
     */
    protected Strategy strategy;

    /**
     * Creates a new player with given name and strategy.
     * @param name The player's name.
     * @param strategy The player's strategy. Or null if the player is a human and we get its action from the view.
     */
    public BlackjackPlayer(String name, Strategy strategy) {
        super(new LinkedList<>());
        this.name = name;
        this.strategy = strategy;
        this.hand = new ProxyHandBuilder().withIsShown(true).build();
        this.scoreRound = 0;
        this.scoreGame = 0;
        this.isPlaying = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Deck getHand() {
        return this.hand;
    }

    @Override
    public void setHand(Deck hand) {
        this.hand = hand;
    }

    @Override
    public void play(Board board) {
        this.isPlaying = true;
        fireChangement();
        
        this.strategy.play((Blackjack) board, this);

        this.scoreRound = getScoreRound();

        this.isPlaying = false;
        fireChangement();
    }

    /**
     * Action used to make the player draw a card from the given deck.
     * @param deck the deck from which the player draws a card.
     */
    public void drawCard(Deck deck) {
        Card card = deck.draw();
        if(card.getValue() == null || card.getValue() > 10) {
            card.setValue(10);
        }
        if(card.getName().equals("as")) {
            card.setValue( (getScoreRound() + 11) > 21 ? 1 : 11);
        }
        
        this.hand.insertLast(card);
        fireChangement();
    }

    /**
     * Returns the player's score for the current round.
     * @return the player's score for the current round.
     */
    public int getScoreRound() {
        int score = 0;
        for(Card card : this.hand.getCards()) {
            Integer value = card.getValue();
            if(value == null)
                throw new IllegalStateException("A card in the hand of a player doesn't have a value");
            score += value;
        }
        return score;
    }

    /**
     * Returns the player's score for the current game.
     * @return the player's score for the current game.
     */
    public int getScoreGame() {
        return this.scoreGame;
    }

    /**
     * Increments the player's score for the current game.
     */
    public void incrementGameScore() {
        this.scoreGame++;
    }

    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }

    @Override
    public String getInfo() {
        return "Score: " + this.scoreGame+"  ";
    }

    @Override
    public Strategy getStrategy() {
        return this.strategy;
    }

    /**
     * Sets if the player is playing or not.
     * @param isPlaying true if the player is playing, false otherwise.
     */
    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(!(obj instanceof BlackjackPlayer))
            return false;
        BlackjackPlayer other = (BlackjackPlayer) obj;
        return this.name.equals(other.name) && (this.strategy == other.strategy || this.strategy.equals(other.strategy));
    }

    @Override
    public String toString() {
        return "Player: [name=" + this.name + ", strategy=" + this.strategy + ", hand=" + this.hand + "]";
    }
}
