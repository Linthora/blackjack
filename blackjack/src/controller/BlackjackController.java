package controller;

import model.blackjack.*;

import model.cards.*;

import view.*;

import java.util.*;

/**
 * Controller for our blackjack game.
 */
public class BlackjackController {

    /**
     * Creates a BlackjackController with player with given strategies and a number of rounds and plays the game.
     * @param strategies the strategies of the players
     * @param nbRound the number of rounds to play.
     */
    public BlackjackController(List<Strategy> strategies, int nbRound) {
        this(strategies, strategies.size(), nbRound);
    }

    /**
     * Creates a new BlackjackController with human players and starts the game.
     * @param nbPlayers the number of players in the game.
     * @param nbRound the number of rounds in the game.
     */
    public BlackjackController(int nbPlayers, int nbRound) {
        this(null, nbPlayers, nbRound);
    }

    /**
     * Private constructor for the BlackjackController to create a new game based on the given parameters in the 2 public constructors.
     * @param strategies the strategies of the players.
     * @param nbPlayers the number of players in the game.
     * @param nbRound the number of rounds in the game.
     */
    private BlackjackController(List<Strategy> strategies, int nbPlayers, int nbRound) {
        if(nbPlayers < 1 || nbPlayers > 4) 
            throw new IllegalArgumentException("Number of players must be between 1 and 4");
        if(nbRound < 1)
            throw new IllegalArgumentException("Number of rounds must be more than 1");
        
        if(strategies == null) {
            strategies = new ArrayList<Strategy>();
            for(int i = 0; i < nbPlayers; i++) {
                strategies.add(null);
            }
        }

        Blackjack blackjack = new Blackjack(strategies, nbRound);
        MainFrame mainFrame = new MainFrame(blackjack);
        mainFrame.setTitle("Blackjack");

        SharedStorageForBJ sharedStorage = new SharedStorageForBJ();
        mainFrame.addButton("Draw", new ActionBJ(this, sharedStorage, true));
        mainFrame.addButton("Pass", new ActionBJ(this, sharedStorage, false));
        mainFrame.setHeader("Welcome to this new game of Blackjack!");

        blackjack.addListener(mainFrame);
        
        blackjack.init();

        while(!blackjack.isOver()) {
            blackjack.newRoundInit();
            while(!blackjack.isRoundOver()) {
                int turn = blackjack.getTurn();
                if(turn > blackjack.getNbPlayers())
                    turn = 0;
                BlackjackPlayer p = (BlackjackPlayer) blackjack.getPlayerList().get(turn);
                if(p.getStrategy() == null) {
                    sharedStorage.setIsDraw(true);
                    p.setIsPlaying(true);
                    p.fireChangement();

                    while(sharedStorage.getIsDraw() == true && p.getScoreRound() < 21) {
                        
                        synchronized(this) {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(sharedStorage.getIsDraw() == true)
                            p.drawCard(blackjack.getDeck("deck"));
                    }
                    p.setIsPlaying(false);
                    p.fireChangement();
                } else {
                    p.play(blackjack);
                    // sleep for 2 seconds
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blackjack.nextTurn();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            mainFrame.setHeader("Last round winner is " + blackjack.getWinnerRound() + "!");
        }
        
        mainFrame.setHeader("Game over! Winner is " + blackjack.getWinnerGame() + "!");
        
        mainFrame.setTitle("Game over! Winner is " + blackjack.getWinnerGame() + "!");

        System.out.println("Game over, winner: " + blackjack.getWinnerGame());
    }

    /**
     * Method used to notify the controller that the player has given an action (draw or pass) via the GUI.
     */
    public void notifyPlayerDecision() {
        synchronized(this) {
            this.notify();
        }
    }

    /**
     * Main method of the controller.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(2, 6);
    }
}
