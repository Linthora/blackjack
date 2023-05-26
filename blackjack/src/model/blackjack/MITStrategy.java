package model.blackjack;

import java.util.List;

import model.cards.Board;
import model.cards.Card;
import model.cards.Player;
import model.cards.Strategy;

/**
 * A strategy which plays according to the MIT blackjack strategy.
 */
public class MITStrategy implements Strategy {
    
    /**
     * Character corresponding to the "stand" action.
     */
    protected final char RESTER = 'R'; 

    /**
     * Character corresponding to the "draw" action.
     */
    protected final char PIOCHER = 'P'; 

    /**
     * The array actions corresponding to the strategy to do when only one as is in the player hand.
     * The column index of the array corresponds to the value of the dealer's card - 2 (2 to 11).
     * The row index of the array corresponds to the value of the player's other card - 2 (2 to 10).
     */
    protected char[][] asStrategy;

    /**
     * The array actions corresponding to the strategy to do when the player has no as in his hand.
     * The column index of the array corresponds to the value of the dealer's card - 2 (2 to 11).
     * The row index of the array corresponds to the value of the player's hand - 4 (4 to 16).
     * If it is upper that 16, the action is always to stand.
     */
    protected char[][] valueStrategy;

    /**
     * Construct a new MITStrategy.
     */
    public MITStrategy() {

        this.asStrategy = new char[][] {
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'P', 'P', 'P', 'P', 'P', 'R', 'R', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'}
        };

        this.valueStrategy = new char[][] {
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'R', 'R', 'R', 'R', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'R', 'R', 'R', 'R', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'R', 'R', 'R', 'R', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'R', 'R', 'R', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'}
        };
    }

    /**
     * Get the good action to do given the player's other card value and the dealer's hand value if the player has an as in his hand.
     * @param valueOtherCard The value of the player's other card.
     * @param valueDealer The value of the dealer's card.
     * @return The action to do.
     */
    protected char getActionAs(int valueOtherCard, int valueDealer) {
        if(valueOtherCard < 2 || valueOtherCard > 10 || valueDealer < 2 || valueDealer > 11)
            throw new IllegalArgumentException("Value out of bounds");

        return this.asStrategy[valueOtherCard-2][valueDealer-2];
    }

    /**
     * Get the good action to do given the player's hand value and the dealer's hand value if the player has no as or two as in his hand.
     * @param value The value of the player's hand.
     * @param valueDealer The value of the dealer's card.
     * @return The action to do.
     */
    protected char getActionValue(int value, int valueDealer) {
        if(value < 4 || valueDealer < 2 || valueDealer > 11)
            throw new IllegalArgumentException("Value out of bounds");

        if(value > 16)
            return this.RESTER;

        return this.valueStrategy[value-4][valueDealer-2];
    }

    @Override
    public void play(Board board, Player player) {
        char action;
        boolean play = true;

        do {
            //instance de BlackjackPlayer
            BlackjackPlayer bp = (BlackjackPlayer) player;

            //Main du joueur
            List<Card> hand = player.getHand().getCards();

            //Value du croupier
            int valueDealer = board.getPlayer("dealer").getHand().getCards().get(0).getValue();
            
            //Tableau des as
            if(hand.size() == 2 && (hand.get(0).getName().equals("as") || hand.get(1).getName().equals("as"))) {
                //Value de l'autre carte
                int valueOtherCard;
                if(hand.get(0).getName() == "as")
                    valueOtherCard = hand.get(1).getValue();
                else
                    valueOtherCard = hand.get(0).getValue();

                //Action
                action = this.getActionAs(valueOtherCard, valueDealer);
            } 
            else { //Tableau des values
                //valeur de la main
                int value = 0;
                for(Card card : hand)
                    value += card.getValue();

                //Action
                action = this.getActionValue(value, valueDealer);
            }

            if(action == this.PIOCHER)
                bp.drawCard(board.getDeck("deck"));
            else
                play = false;

        } while(play);
    }

    @Override
    public String toString() {
        return "MITStrategy";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MITStrategy;
    }
    
}
