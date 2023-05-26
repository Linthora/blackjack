package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;


import org.w3c.dom.events.MouseEvent;

import util.ModelListener;

import model.cards.*;

import java.util.*;

/**
 * Class representing a Deck
 * Extends {@link CustomPanel}
 */
public class DeckPanel extends CustomPanel {
    
    /**
     * The number of cards in the deck
     */
    protected JLabel cardsValueLabel;

    /**
     * The composant of the deck containing N cards (N being the size of the deck)
     */
    protected List<CardPanel> cards;

    /**
     * The height of a card
     */
    protected int height;

    /**
     * The width of a card
     */
    protected int width;
    
    /**
     * Constructor of the DeckPanel
     * 
     * @param name The name of the deck
     * @param xPos The x position of the deck
     * @param yPos The y position of the deck
     */
    public DeckPanel(String name, int xPos, int yPos) {
        super("deck", name, xPos, yPos, 2);

        this.cardsValueLabel = new JLabel();
        this.cards = new LinkedList<>();      
        
        CardPanel cardReference = new CardPanel("back", 0, 0);
        this.height = cardReference.getHeight();
        this.width = cardReference.getWidth();

        setOpaque(false);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void make(Object o) {
        if(!(o instanceof Deck)) {
            throw new IllegalArgumentException("Can't make a deck panel with a non-deck object");
        }
        Deck deck = (Deck) o;

        Integer numberOfCards = deck.size();
        
        this.removeAll();

        int numberOfCardsToShow = numberOfCards;

        if (numberOfCards>40) {
            numberOfCardsToShow = 40;
        }
        
        setBounds(xPos, yPos, width+spacing*numberOfCardsToShow, height+spacing*numberOfCardsToShow+10);

        
        JLabel moreCardHint = new JLabel(numberOfCards.toString());
        moreCardHint.setForeground(Color.WHITE);
        moreCardHint.setFont(new Font("Dialog", Font.PLAIN, 40));
        moreCardHint.setBounds((spacing*numberOfCardsToShow)+(int)(width/4), (spacing*numberOfCardsToShow)+(int)(height/4), 50, 50);
        this.add(moreCardHint);

        for (int i = 0; i < numberOfCardsToShow; i++) {
            CardPanel card = new CardPanel("back", spacing*numberOfCardsToShow-i*spacing, spacing*numberOfCardsToShow-i*spacing);
            this.add(card);
        }
    }
}
