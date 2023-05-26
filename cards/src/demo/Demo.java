package demo;

import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.cards.*;
import view.*;

/**
 * This class is an example on how to create a controller for the game.
 * It is not mandatory to use this class, you can create your own controller.
 */
public class Demo {

    /***
     * This main function will go through everything you need to know in order to 
     * build a frame.
     * @param args not used here.
     */
    public static void main(String[] args) {
        
        // First, we need to create a board, this will be where our card game informations will be handled.
        Board tableDeJeu = new BlankBoard();

        // then, we need to create a frame, this is the main window of our application
        // and we will pass our previously made Board.
        MainFrame ui = new MainFrame(tableDeJeu);
        ui.setTitle("Demo");
        
        // We can add a deck to our board, this can be a deck for instance.
        // let's create ourselves a deck of 52 cards.
        Deck db = ClassicCardGameFactory.createDeck(true);
        // then make it visible.
        db.setIsShown(true);
        // and finally add it to our board.
        tableDeJeu.addDeck("pioche", db);

        // Now let's create some players.
        // First we're going to make two cards so they have something to see at least...
        LinkedList<Card> cards = new LinkedList<Card>();
        cards.add(new Card("as", "heart", 1));
        cards.add(new Card("king", "heart", 13));

        // Then we're going to create four players, all of them having the cards we created earlier.
        // this is a little silly tho it's for demonstration purposes.
        // Note: you cannot add more than 4 players on a board
        db = new ProxyHandBuilder().withDeck(cards).build();
        tableDeJeu.addPlayer(new BlankPlayer("dealer", db, null));
        tableDeJeu.addPlayer(new BlankPlayer("antoine", db, null));
        tableDeJeu.addPlayer(new BlankPlayer("alix", db, null));
        tableDeJeu.addPlayer(new BlankPlayer("kenzo", db, null));

        // We can add buttons buy giving them a name and an action listener.
        // Note: you cannot add more than 3 buttons on a board
        ui.addButton("boutton 1", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("you just pressed \"boutton 1\"");
            }
        });
        ui.addButton("boutton 2", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("you just pressed \"boutton 2\"");
            }
        });

        // And finally we can display something on the top of our frame
        ui.setHeader("this is a dummy header !");
    }
}