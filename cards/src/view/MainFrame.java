package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.ModelListener;

import java.awt.FlowLayout;  
import java.awt.Container;  
import java.awt.Color;
import java.awt.event.ActionListener;

import model.cards.Board;
import model.cards.*;
import model.cards.CardBuilder;
import model.cards.ProxyDeck;
import model.cards.ProxyDeckBuilder;

import java.util.*;

/**
 * Class representing the main frame of our application
 * Extends {@link JFrame}
 * Implements {@link ModelListener}
 */
public class MainFrame extends JFrame implements ModelListener {

    /**
     * THe background image of the frame
     */
    protected final String backgroundPath = "./assets/table_texture.jpg";

    /**
     * The dimensions of the frame
     */
    protected int[] winSize = {1060,618};

    /**
     * a header on the frame where we can put informations
     */
    protected JLabel informationLabel;

    /**
     * The board of our game
     */
    protected Board board;

    /**
     * A map containing some panels of our frame
     */
    protected Map<String, CustomPanel> panels;

    /**
     * Usefull to place our players on the frame
     */
    protected int iPlayer = 0;

    /**
     * Usefull to place our decks on the frame
     */
    protected int iDeck = 0;

    /**
     * A list of point where we can place a player
     */
    protected final Point[] posPlayer = {
        new Point(winSize[0]/2, 50),
        new Point(winSize[0]/2, winSize[1]-220),
        new Point((winSize[0]/2)-(winSize[0]/2)/2, winSize[1]-250),
        new Point((winSize[0]/2)+(winSize[0]/2)/2, winSize[1]-250),
    };

    /**
     * A list of point where we can place a deck
     */
    protected final Point[] posDeck = {
        new Point(30, 30),
        new Point(30, winSize[1]-200),
        new Point(900, 30),
        new Point(900, winSize[1]-200),
    };

    /**
     * Constant size of a button
     */
    protected final int[] buttonSize = { 150, 75 };

    /**
     * A list of points where we can place a button
     */
    protected final Point[] posButton = {
        new Point(winSize[0]/2-(winSize[0]/2)/3, winSize[1]/2),
        new Point(winSize[0]/2+(winSize[0]/2)/3, winSize[1]/2),
        new Point(winSize[0]/2, winSize[1]/2),
        
    };

    /**
     * Usefull to place our buttons on the frame
     */
    protected int nButton = 0;


    /**
     * Constructor of the MainFrame
     * 
     * @param board The board of our game
     */
    public MainFrame(Board board) {
        if(board == null) 
            throw new IllegalArgumentException("Board cannot be null");
        if(board.getNbDecks() > 4)
            throw new IllegalArgumentException("This View is a teapot. It cannot handle more than 4 decks");
        if(board.getNbPlayers() > 4)
            throw new IllegalArgumentException("This View is a teapot. It cannot handle more than 5 players");
        if(board.getNbHands() > 0 )
            throw new IllegalArgumentException("This View is a teapot. It cannot handle hands except for the player's one");

        this.board = board;
        this.panels = new HashMap<String, CustomPanel>();
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(backgroundPath)))));
        } catch (IOException ex) {
            System.out.println("error while importing image file \"" + backgroundPath + "\"");
        }

        this.board.addListener(this);

        JLabel headerTextLabel = new JLabel("Something interesting to say");
        headerTextLabel.setForeground(Color.WHITE);
        headerTextLabel.setBounds(0, 0, winSize[0], 30);
        headerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerTextLabel.setOpaque(false);

        this.informationLabel = headerTextLabel;

        this.getContentPane().add(headerTextLabel);
        
        //this.setTitle("El famoso black jack");

        this.setSize(winSize[0], winSize[1]);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        make();
    }

    /**
     * A make function to update the frame
     */
    public void make() {
        
        for(String name: this.board.getDecks().keySet()) {
            if(this.panels.containsKey(name)) {
                this.panels.get(name).make(this.board.getDecks().get(name));
            }
            else {
                this.panels.put(name, new DeckPanel(name, posDeck[iDeck].getX(), posDeck[iDeck].getY()));
                this.getContentPane().add(this.panels.get(name));
                this.panels.get(name).make(this.board.getDecks().get(name));
                iDeck++;
            }
            
        }
        
        for(String name: this.board.getPlayers().keySet()) {
            if(this.panels.containsKey(name)) {
                this.panels.get(name).make(this.board.getPlayers().get(name));
            }
            else {
                this.panels.put(name, new PlayerPanel(name, posPlayer[iPlayer].getX(), posPlayer[iPlayer].getY()));
                this.getContentPane().add(this.panels.get(name));
                this.panels.get(name).make(this.board.getPlayers().get(name));
                iPlayer++;
            }
            
        }

        repaint();

    }

    @Override 
    public void onModelUpdate(Object source) {
        make();
    }

    /**
     * A function to add a button to the frame
     * 
     * @param name The name of the button
     * @param func The action to do when the button is clicked
     */
    public void addButton(String name, ActionListener func) {
        if (nButton > 2) {
            throw new IllegalArgumentException("This View cannot handle more than 3 buttons");
        }
        JButton button = new JButton(name);
        button.setBounds(posButton[nButton].getX()-buttonSize[0]/2,posButton[nButton].getY()-buttonSize[1]/2,buttonSize[0],buttonSize[1]);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setVisible(true);
        button.setForeground(Color.WHITE);
        button.addActionListener(func);
        this.getContentPane().add(button);
        nButton++;
        make();
    }

    /**
     * A function to remove a button from the frame
     * 
     * @param s The name of the button
     */
    public void setHeader(String s){
        this.informationLabel.setText(s);
        make();
    }
}
