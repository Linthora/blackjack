package model.blackjack;

import java.util.*;

import model.cards.*;
import util.ModelListener;

/**
 * A class which represents a player in a blackjack game.
 * Extends {@link model.cards.Board}.
 */
public class Blackjack extends Board {

    /**
     * The number of players in the game.
     */
    protected int nbPlayers;

    /**
     * How many round have been played yet.
     */
    protected int nbRound;

    /**
     * The maximum number of rounds to be played.
     */
    protected int nbMaxRound;

    /**
     * The current turn of this round.
     */
    protected int turn;

    /**
     * The winner of the last round.
     * Or null if the last round resulted in a draw.
     */
    protected String winnerRound;

    /**
     * The winner of this game.
     */
    protected String winnerGame;

    /**
     * The strategies of the players.
     */
    protected List<Strategy> strategies;

    /**
     * Creates a new blackjack game with given number of players and maximum number of rounds.
     * @param nbPlayers the number of players in the game.
     * @param nbMaxRound the maximum number of rounds to be played.
     */
    public Blackjack(int nbPlayers, int nbMaxRound) {
        super();
        if(nbPlayers < 1 || nbPlayers > 4) 
            throw new IllegalArgumentException("Number of players must be between 1 and 4");        
        if(nbMaxRound < 1) 
            throw new IllegalArgumentException("Number of rounds must be more than 1");
        this.nbPlayers = nbPlayers;
        this.nbRound = 0;
        this.nbMaxRound = nbMaxRound;
        this.winnerRound = null;
        this.winnerGame = null;
        this.strategies = new ArrayList<>();
        this.turn = 0;
        for(int i = 0; i < nbPlayers; i++) {
            this.strategies.add(null);
        }
    }

    /**
     * Creates a new blackjack game with players and strategies given in the given list.
     * And a maximum number of rounds given.
     * @param players the strategies of the players.
     * @param nbMaxRound the maximum number of rounds to be played.
     */
    public Blackjack(List<Strategy> players, int nbMaxRound) {
        this(players.size(), nbMaxRound);
        for(int i = 0; i < nbPlayers; i++) {
            if(players.get(i) != null && players.get(i).toString() == "DealerStrategy for Blackjack") {
                throw new IllegalArgumentException("DealerStrategy is not allowed to be used by a player in Blackjack");
            }
            this.strategies.set(i, players.get(i));
        }
    }

    @Override
    public void init() {
        this.addPlayer(new BlackjackPlayer("dealer", new DealerStrategy()));

        for(int i = 0; i < this.nbPlayers; i++) {
            this.addPlayer(new BlackjackPlayer("player "+i, this.strategies.get(i)));
        }

        List<ModelListener> listeners = this.getListeners();

        for(Player player : this.playerList) {
            for(ModelListener listener : listeners) {
                player.addListener(listener);
            }
        }

        this.nbRound = 0;
    }

    /**
     * Initializes a new round.
     */
    public void newRoundInit() {

        if(this.decks.get("deck") != null) {
            this.decks.remove("deck");
        }

        this.decks.put("deck", ClassicCardGameFactory.create52Deck());
        this.getDeck("deck").shuffle();

        for(Player p : this.getPlayerList()) {
            p.setHand(new ProxyHandBuilder().build());
        }

        this.getPlayer("dealer").play(this);
        fireChangement();
        this.turn = 1;

        this.winnerRound = null;
    }

    @Override
    public void end() {
    
        int maxScore = 0;
        for(Player player : this.playerList) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            if(p.getScoreGame() > maxScore) {
                maxScore = p.getScoreGame();
                this.winnerGame = p.getName();
            } else if (p.getScoreGame() == maxScore) {
                this.winnerGame = null;
            }
        }
        if(this.winnerGame == null) {
            this.winnerGame = "Draw";
        }
    }

    @Override
    public boolean isOver() {
        boolean isOver = this.nbRound++ == this.nbMaxRound;
        if(isOver) {
            this.end();
        }
        return isOver;
    }

    /**
     * Tells if the current round is over.
     * And if so, updates the score of the players.
     * @return true if the current round is over, false otherwise.
     */
    public boolean isRoundOver() {
        boolean isOver = this.turn > this.nbPlayers + 1;
        int max21 = 0;
        String winner = null;
        for(Player player: this.playerList) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            if(!isOver && p.getScoreRound() == 21) {
                winner = player.getName();
                isOver = true;
                max21 = 21;
            } else if (isOver && p.getScoreRound() >= max21 && p.getScoreRound() <= 21) {
                if(p.getScoreRound() == max21) {
                    winner = null;
                } else {
                    max21 = p.getScoreRound();
                    winner = p.getName();
                }
            }
        }
        if(isOver) {
            this.winnerRound = winner;
            if(winner != null)
                ((BlackjackPlayer) this.players.get(winner)).incrementGameScore();
        }
        return isOver;
    }

    /**
     * Returns the number of players in this game, without the dealer.
     * @return the number of players in this game.
     */
    public int getNbPlayers() {
        return this.nbPlayers;
    }

    /**
     * Returns the number of rounds played yet.
     * @return the number of rounds played yet.
     */
    public int getNbRound() {
        return this.nbRound;
    }

    /**
     * Returns the maximum number of rounds to be played.
     * @return the maximum number of rounds to be played.
     */
    public int getNbMaxRound() {
        return this.nbMaxRound;
    }

    /**
     * Returns the current turn of this round.
     * @return the current turn of this round.
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Returns the winner of the last round.
     * @return the winner of the last round.
     */
    public String getWinnerRound() {
        return this.winnerRound;
    }

    /**
     * Returns the winner of this game.
     * @return the winner of this game.
     */
    public String getWinnerGame() {
        return this.winnerGame;
    }

    /**
     * Increments the current turn of this round.
     */
    public void nextTurn() {
        this.turn++;
    }

    /**
     * Returns the strategies of the players.
     * @return the strategies of the players.
     */
    public List<Strategy> getStrategies() {
        return this.strategies;
    }
}
