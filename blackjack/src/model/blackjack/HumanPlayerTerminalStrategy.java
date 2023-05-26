package model.blackjack;

import java.util.*;

import model.cards.*;

/**
 * A strategy implementation used to make a player play using user input using the terminal.
 * Extends {@link model.cards.Strategy}.
 */
public class HumanPlayerTerminalStrategy implements Strategy {

    @Override
    public void play(Board board, Player player) {
        BlackjackPlayer p = (BlackjackPlayer) player;

        Scanner sc = new Scanner(System.in);
        String input = "";

        System.out.println("Playing " + p.getName() + ":");

        while(!input.equals("stop") && !input.equals("no") && p.getScoreRound() < 21) {
            System.out.println("Your score: " + p.getScoreRound());
            System.out.println("Do you want to draw a card ? (yes/no/stop)");
            input = sc.nextLine();
            if(input.equals("yes")) {
                p.drawCard(board.getDeck("deck"));
            }
        }
        System.out.println("End of turn");
    }
    
}
