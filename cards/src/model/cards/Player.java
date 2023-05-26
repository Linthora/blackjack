package model.cards;

import util.ObservableModel;

/**
 * An interface representing a Player.
 * Implements {@link util.ObservableModel}
 */
public interface Player extends ObservableModel {

    /**
     * Get the name of the player.
     * @return The name of the player.
     */
    public String getName();

    /**
     * Set the name of the player.
     * @param name The name of the player.
     */
    public void setName(String name);

    /**
     * Get the hand of the player.
     * @return The hand of the player.
     */
    public Deck getHand();

    /**
     * Set the hand of the player.
     * @param hand The hand of the player.
     */
    public void setHand(Deck hand);

    /**
     * Is the player currently playing?
     * @return True if the player is playing, false otherwise.
     */
    public boolean isPlaying();

    /**
     * Make the player play.
     * @param board The board to play on.
     */
    public void play(Board board);

    /**
     * Get the player's information.
     * @return The player's information.
     */
    public String getInfo();

    /**
     * Get the strategy of the player.
     * @return The strategy of the player.
     */
    public Strategy getStrategy();
}
