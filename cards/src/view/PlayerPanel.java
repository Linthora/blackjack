package view;

import model.cards.*;

import javax.swing.border.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
/**
 * Class representing Player panel
 * Extends {@link CustomPanel}
 */
public class PlayerPanel extends CustomPanel {

    /**
     * A hand panel to manipulate
     */
    protected HandPanel hand;

    /**
     * The name of the player
     */
    protected JLabel nameLabel;

    /**
     * A label to display some text at the bottom of the panel
     */
    protected JLabel infoLabel;

    /**
     * Constructor of the PlayerPanel
     * 
     * @param name The name of the player
     * @param xPos The x position of the panel, note that the reference point is the center of the panel
     * @param yPos The y position of the panel
     */
    public PlayerPanel(String name, int xPos, int yPos) {
        super("player", name, xPos, yPos, 5);
        this.hand = new HandPanel(name, xPos, yPos+10);
        this.setLayout(null);
        this.add(hand);
        this.setOpaque(false);
        this.nameLabel = new JLabel();
        this.add(this.nameLabel);
        this.infoLabel = new JLabel();
        this.add(this.infoLabel);
    }

    @Override
    public void make(Object o) {
        if(!(o instanceof Player)) {
            throw new IllegalArgumentException("Can't make a player panel with a non-player object");
        }
        Player player = (Player) o;

        if (player.isPlaying()) {
            this.setBorder(new LineBorder(Color.GREEN, 3));
        } else {
            this.setBorder(null);
        }

        this.nameLabel.setText(player.getName());
        this.nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel.setBounds(0,0 , 100,20);
        nameLabel.setForeground(Color.WHITE);

        this.infoLabel.setText(player.getInfo());
        this.infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoLabel.setBounds(0,(int)(this.getBounds().getHeight())-20 , 100,20);
        infoLabel.setForeground(Color.WHITE);
        
        this.hand.make(player.getHand());
        int width = (int) hand.getBounds().getWidth();
        int height = (int) hand.getBounds().getHeight();
        this.setBounds(this.xPos-(width/2), this.yPos, width, height);
        this.hand.repaint();
    }
}
