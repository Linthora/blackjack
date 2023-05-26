package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Class representing a Card as a paintable JPanel component
 * Extends {@link JPanel}
 */
public class CardPanel extends JPanel{

    /**
     * A BufferedImage of the card, if the image isn't instancied correctly, we'll use the textRepresnetation
     */
    private BufferedImage image;

    /**
     * Both x and y coordinates of the card
     */
    int xPos, yPos;

    /**
     * Constructor of the CardPanel
     * 
     * @param fileName The name of the file wich we want to store in our buffer
     * @param xPos The x coordinate of the card
     * @param yPos The y coordinate of the card
     */
    public CardPanel(String fileName, int xPos, int yPos) {
        try {                
            this.image = ImageIO.read(new File("./assets/"+ fileName +".gif"));
        } catch (IOException ex) {
            System.out.println("error while importing image file \"" + fileName + "\"");
        }

        this.xPos = xPos;
        this.yPos = yPos;

        this.setBounds(xPos, yPos, image.getWidth(), image.getHeight()); // set up the size of the panel (so it doesn't take the whole screen)
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

    }

}