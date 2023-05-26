package view;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.SwingConstants;


import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import model.cards.Deck;
import model.cards.Card;

import java.util.*;


/**
 * Class representing a Hand panel
 * Extends {@link CustomPanel}
 */
public class HandPanel extends CustomPanel {

    /**
     * The total value of the hand
     */
    protected JLabel cardsValueLabel;

    /**
     * The list of cards in the hand
     */
    protected List<CardPanel> cards;

    /**
     * Will display a hand of cards, the x given value will represent where x center of the deck will be placed.
     * 
     * @param name The name of the hand
     * @param xPos The x coordinate of the hand (top left corner)
     * @param yPos The y coordinate of the hand (top left corner)
     */
    public HandPanel(String name,int xPos, int yPos) {
        super("hand", name, xPos, yPos, 18);
        this.cardsValueLabel = new JLabel();
        this.add(this.cardsValueLabel);
        this.cards = new LinkedList<>();

        setOpaque(false);
        setLayout(null);
    }

    @Override
    public void make(Object o) {
        if(!(o instanceof Deck)) {
            throw new IllegalArgumentException("Can't make a hand panel with a non-hand object");
        }
        
        Deck hand = (Deck) o;
        Integer totalValue = getValueHand(hand);
        LinkedList<String> arr = new LinkedList<>();
        for(Card c: hand.getCards()) {
            arr.add(c.getName() + c.getColor());
        }

        int length = arr.size()-1;

        CardPanel cardReference = new CardPanel("back", 0, 0);
        int height = cardReference.getHeight();
        int width = cardReference.getWidth();

        setBounds(0, 20, width+length*spacing, height+65);
    
        this.cardsValueLabel.setText("Total: " + totalValue.toString());
        this.cardsValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.cardsValueLabel.setBounds((int)((length-1)*spacing/2),height+2 , 70,20);
        this.cardsValueLabel.setForeground(Color.YELLOW);
        
        for(CardPanel card: this.cards) {
            this.remove(card);
        }

        this.cards = new LinkedList<>();

        for(int i = 0; i < arr.size(); i++) {
            CardPanel card = new CardPanel(arr.get(i), i*spacing, 0);
            this.cards.add(card);
            this.add(card);
        }
    }

    /**
     * Get the value of a hand
     * 
     * @param hand The hand to get the value of
     * @return The value of the hand
     */
    public int getValueHand(Deck hand) {
        int totalValue = 0;
        for (Card c : hand.getCards()) {
            totalValue += c.hasValue() ? c.getValue() : 0;
        }
        return totalValue;
    }
}
