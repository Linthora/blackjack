package model.cards;

import java.util.*;
import util.AbstractObservableModel;
import util.ModelListener;

/**
 * An abstract class representing a board in a card game.
 */
public abstract class Board extends AbstractObservableModel {

    /**
     * The decks belonging to the board.
     */
    protected Map<String, Deck> decks;

    /**
     * The hands belonging to the board.
     */
    protected Map<String, Deck> hands;

    /**
     * The players playing on the board.
     */
    protected Map<String, Player> players;

    /**
     * The list of player playing on the board, order by when they were added.
     */
    protected List<Player> playerList;

    /**
     * An int representing which player is playing next.
     */
    protected int nextPlayer;

    /**
     * Creates a new board.
     */
    public Board() {
        super(new LinkedList<>());
        this.decks = new HashMap<>();
        this.hands = new HashMap<>();
        this.players = new HashMap<>();
        this.playerList = new LinkedList<>();
        this.nextPlayer = 0;
    }

    /**
     * Add a deck to the board with the given name.
     * @param name The name of the deck.
     * @param deck The deck to add.
     */
    public void addDeck(String name, Deck deck) {
        if(name == null || deck == null)
            throw new IllegalArgumentException("Can't add a null deck or a null name");
        if(this.decks.containsKey(name))
            throw new IllegalArgumentException("A deck with this name already exists");
        this.decks.put(name, deck);
        this.fireChangement();
    }

    /**
     * Add a hand to the board with the given name.
     * @param name The name of the hand.
     * @param hand The hand to add.
     */
    public void addHand(String name, Deck hand) {
        if(name == null || hand == null)
            throw new IllegalArgumentException("Can't add a null hand or a null name");
        if(this.hands.containsKey(name))
            throw new IllegalArgumentException("A hand with this name already exists");
        this.hands.put(name, hand);
        this.fireChangement();
    }

    /**
     * Add a given player to the board.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        if(player == null || player.getName() == null)
            throw new IllegalArgumentException("Can't add a null player or a null name");
        String name = player.getName();
        if(this.players.containsKey(name))
            throw new IllegalArgumentException("A player with this name already exists");
        this.players.put(name, player);
        this.playerList.add(player);
        this.fireChangement();
    }

    /**
     * Removes a player from the board from its name.
     * @param name The name of the player to remove.
     */
    public void removePlayer(String name) {
        if(name == null)
            throw new IllegalArgumentException("Can't remove a null player");
        if(!this.players.containsKey(name))
            throw new IllegalArgumentException("A player with this name doesn't exist");
        this.playerList.remove(this.players.get(name));
        this.players.remove(name);
        this.fireChangement();
    }

    /**
     * Removes a deck from its name.
     * @param name The name of the deck to remove.
     */
    public void removeDeck(String name) {
        if(name == null)
            throw new IllegalArgumentException("Can't remove a null deck");
        if(!this.decks.containsKey(name))
            throw new IllegalArgumentException("No deck with this name");
        this.decks.remove(name);
        this.fireChangement();
    }

    /**
     * Removes a hand from its name.
     * @param name The name of the hand to remove.
     */
    public void removeHand(String name) {
        if(name == null)
            throw new IllegalArgumentException("Can't remove a null hand");
        if(!this.hands.containsKey(name))
            throw new IllegalArgumentException("No hand with this name");
        this.hands.remove(name);
        this.fireChangement();
    }

    /**
     * Returns the deck with the given name.
     * @param name The name of the deck to return.
     * @return The deck with the given name.
     */
    public Deck getDeck(String name) {
        if(name == null)
            throw new IllegalArgumentException("Can't get a null deck");
        if(!this.decks.containsKey(name))
            throw new IllegalArgumentException("No deck with this name");
        return this.decks.get(name);
    }

    /**
     * Returns the hand with the given name.
     * @param name The name of the hand to return.
     * @return The hand with the given name.
     */
    public Deck getHand(String name) {
        if(name == null)
            throw new IllegalArgumentException("Can't get a null hand");
        if(!this.hands.containsKey(name))
            throw new IllegalArgumentException("No hand with this name");
        return this.hands.get(name);
    }

    /**
     * Returns the player with the given name.
     * @param name The name of the player to return.
     * @return The player with the given name.
     */
    public Player getPlayer(String name) {
        if(name == null)
            throw new IllegalArgumentException("Can't get a null player");
        if(!this.players.containsKey(name))
            throw new IllegalArgumentException("No player with this name");
        return this.players.get(name);
    }

    /**
     * Returns the map containing the decks.
     * @return The map containing the decks.
     */
    public Map<String, Deck> getDecks() {
        return this.decks;
    }

    /**
     * Returns the map containing the hands.
     * @return The map containing the hands.
     */
    public Map<String, Deck> getHands() {
        return this.hands;
    }

    /**
     * Returns the map containing the players.
     * @return The map containing the players.
     */
    public Map<String, Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the list of players.
     * @return The list of players.
     */
    public List<Player> getPlayerList() {
        return this.playerList;
    }

    /**
     * Return the number of decks on the board.
     * @return The number of decks on the board.
     */
    public int getNbDecks() {
        return this.decks.size();
    }

    /**
     * Return the number of hands on the board.
     * @return The number of hands on the board.
     */
    public int getNbHands() {
        return this.hands.size();
    }

    /**
     * Return the number of players on the board.
     * @return The number of players on the board.
     */
    public int getNbPlayers() {
        return this.players.size();
    }

    /**
     * Abstract method to initialize the board.
     */
    public abstract void init();

    /**
     * Abstract method to end the game with.
     */
    public abstract void end();

    /**
     * Abstract method used to tell if the game is over.
     * @return True if the game is over, false otherwise.
     */
    public abstract boolean isOver();

    /**
     * Method used to play a full tour of the game (each player plays one card).
     */
    public void playNextTour() {
        if(this.nextPlayer >= this.playerList.size())
            this.nextPlayer = 0;
        Player player = this.playerList.get(this.nextPlayer);
        player.play(this);
        this.nextPlayer++;
    }

    /**
     * Method used to play a full game.
     */
    public void play() {
        this.init();
        while(!this.isOver()) {
            for(Player player : this.playerList) {
                player.play(this);
            }
            fireChangement();
        }
        this.end();
    }

    @Override
    public String toString() {
        return "Board{" +
                "decks=" + decks +
                ", hands=" + hands +
                ", players=" + players +
                ", playerList=" + playerList +
                '}';
    }

    @Override
    public void addListener(ModelListener o) {
        super.addListener(o);
        for(Deck deck : this.decks.values()) {
            deck.addListener(o);
        }
        for(Deck hand : this.hands.values()) {
            hand.addListener(o);
        }
        for(Player player : this.players.values()) {
            player.addListener(o);
        }
    }

    @Override
    public void removeListener(ModelListener o) {
        super.removeListener(o);
        for(Deck deck : this.decks.values()) {
            deck.removeListener(o);
        }
        for(Deck hand : this.hands.values()) {
            hand.removeListener(o);
        }
        for(Player player : this.players.values()) {
            player.removeListener(o);
        }
    }

    
}
