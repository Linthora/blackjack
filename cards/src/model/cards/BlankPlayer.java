package model.cards;

import util.AbstractObservableModel;

import java.util.LinkedList;

/**
 * A worthless implementation of the Player abstract class.
 * Used for demo purposes.
 */
public class BlankPlayer extends AbstractObservableModel implements Player {

    /**
     * The player's name.
     */
    protected String name;

    /**
     * The player's hand.
     */
    protected Deck hand;

    /**
     * The player's Strategy.
     */
    protected Strategy strategy;

    /**
     * Is this player currently playing?
     */
    protected boolean isPlaying;

    /**
     * Information about the player.
     */
    protected String info;


    /**
     * Creates a new player with the given name and strategy.
     * @param name The player's name.
     * @param hand The player's hand.
     * @param strategy The player's strategy.
     */
    public BlankPlayer(String name, Deck hand, Strategy strategy) {
        super(new LinkedList<>());
        this.name = name;
        this.hand = hand;
        this.strategy = strategy;
        this.isPlaying = false;
        this.info = "";
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Can't change the name of a blank player");
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
        this.fireChangement();
        this.strategy.play(board, this);
        this.isPlaying = false;
        this.fireChangement();
    }

    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public Strategy getStrategy() {
        return this.strategy;
    }

}
