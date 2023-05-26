package model.cards;

/**
 * An interface representing a strategy for a player.
 */
public interface Strategy {
    /**
     * What should the player do?
     * @param board The board to play on.
     * @param player The player to play.
     */
    public void play(Board board, Player player);
}
