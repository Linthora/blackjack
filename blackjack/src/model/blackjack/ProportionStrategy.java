package model.blackjack;

import java.util.List;
import java.util.Random;

import model.cards.Board;
import model.cards.Card;
import model.cards.Player;
import model.cards.Strategy;

/**
 * A strategy which plays depending of the ratio between the player's hand value and the max value (21).
 */
public class ProportionStrategy implements Strategy {

    @Override
    public void play(Board board, Player player) {
        boolean play = true;

        do {
            //instance de BlackjackPlayer
            BlackjackPlayer bp = (BlackjackPlayer) player;

            //Main du joueur
            List<Card> hand = player.getHand().getCards();
            
            //Valeur de la main
            int value = 0;
            for(Card c : hand)
                value += c.getValue();

            double proportion = (double) value / 21;
            boolean action = new Random().nextDouble() > proportion;

            if(value < 21 && action)
                bp.drawCard(board.getDeck("deck"));
            else
                play = false;

        } while(play);
    }

    @Override
    public String toString() {
        return "ProportionStrategy";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ProportionStrategy;
    }

}
