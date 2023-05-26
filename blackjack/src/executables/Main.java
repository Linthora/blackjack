package executables;

import controller.*;
import model.cards.*;
import model.blackjack.*;

import java.util.*;

/**
 * The main class of this Application.
 * Feel free to modify this class if you want other configurations.
 */
public class Main {

    /**
     * The main method of this Application.
     * @param args The command line arguments. 1st being the configuation to use and 2nd how many rounds to play.
     */
    public static void main(String[] args) {
        int nbMaxTurn = 4;
        int config = 3;

        if (args.length > 0) {
            int configArg = Integer.parseInt(args[0]);
            if(configArg > 0 && configArg <= 6) {
                config = configArg;
            }
            if(args.length > 1) {
                int nbMaxTurnArg = Integer.parseInt(args[1]);
                if(nbMaxTurnArg > 0) {
                    nbMaxTurn = nbMaxTurnArg;
                }
            }
        }

        switch(config) {
            case 1:
                Main.configuration1(nbMaxTurn);
                break;
            case 2:
                Main.configuration2(nbMaxTurn);
                break;
            case 3:
                Main.configuration3(nbMaxTurn);
                break;
            case 5:
                Main.configuration5(nbMaxTurn);
                break;
            case 6:
                Main.configuration6(nbMaxTurn);
                break;
            default:
                Main.configuration4(nbMaxTurn);
                break;
        }
        
    }

    /**
     * Creates a new blackjack game with configuration 1.
     * With 2 human players.
     */
    public static void configuration1(Integer nbMaxRound) {
        BlackjackController controller = new BlackjackController(2, nbMaxRound);
    }

    /**
     * Creates a new blackjack game with configuration 2.
     * With 1 human player.
     */
    public static void configuration2(Integer nbMaxRound) {
        BlackjackController controller = new BlackjackController(1, nbMaxRound);
    }

    /**
     * Creates a new blackjack game with configuration 3.
     * With 1 human player against 1 computer player. (+ 1 dealer) (MIT)
     */
    public static void configuration3(Integer nbMaxRound) {
        List<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(null);
        strategies.add(new MITStrategy());
        BlackjackController controller = new BlackjackController(strategies, nbMaxRound);
    }

    /**
     * Creates a new blackjack game with configuration 4.
     * With 1 human player against 2 computer players. (+ 1 dealer) (MIT + Random)
     */
    public static void configuration4(Integer nbMaxRound) {
        List<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(null);
        strategies.add(new MITStrategy());
        strategies.add(new RandomStrategy());
        BlackjackController controller = new BlackjackController(strategies, nbMaxRound);
    }

    /**
     * Creates a new blackjack game with configuration 5.
     * With 1 human player against 2 computer players. (+ 1 dealer) (Random + Proportionnal)
     */
    public static void configuration5(Integer nbMaxRound) {
        List<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(null);
        strategies.add(new RandomStrategy());
        strategies.add(new ProportionStrategy());
        BlackjackController controller = new BlackjackController(strategies, nbMaxRound);
    }

    /**
     * Creates a new blackjack game with configuration 6.
     * With 3 human players.
     */
    public static void configuration6(Integer nbMaxRound) {
        BlackjackController controller = new BlackjackController(3, nbMaxRound);
    }
}
