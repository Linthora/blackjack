package model.blackjack;

import java.util.*;

import model.cards.*;

/**
 * A class which represents the strategy of a dealer in a blackjack game.
 * Implements {@link model.cards.Strategy}.
 */
public class DealerStrategy implements Strategy {

    @Override
    public void play(Board board, Player player) {
        Blackjack blackjack = (Blackjack) board;
        BlackjackPlayer dealer = (BlackjackPlayer) player;
        
        if(player.getHand().size() == 0) {
            for(Player p : blackjack.getPlayerList()) {
                BlackjackPlayer bp = (BlackjackPlayer) p;
                if(bp != player) {
                    bp.drawCard(board.getDeck("deck"));
                    bp.drawCard(board.getDeck("deck"));
                }
            }
            BlackjackPlayer d = (BlackjackPlayer) player;
            d.drawCard(board.getDeck("deck"));
        } else {
            dealer.drawCard(board.getDeck("deck"));
            int maxScore = 0;
            for(Player p: blackjack.getPlayerList()) {
                if(((BlackjackPlayer) p).getScoreRound() > maxScore && ((BlackjackPlayer) p).getScoreRound() < 21) {
                    maxScore = ((BlackjackPlayer) p).getScoreRound();
                }
            }
            while(dealer.getScoreRound() < maxScore) {
                Card card = blackjack.getDeck("deck").draw();
                if(card.getValue() == null || card.getValue() > 10) {
                    card.setValue(10);
                }
                if(card.getName().equals("as")) {
                    card.setValue( (dealer.getScoreRound() + 11) > 21 ? 1 : 11);
                }
                dealer.getHand().insertLast(card);
            }
        }
        
    }

    @Override
    public String toString() {
        return "DealerStrategy for Blackjack";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DealerStrategy;
    }
    
}
