package view;

import javax.swing.JPanel;
import java.util.*;

/**
 * Abstract class meant to represent a pannel for our frame
 * Extends {@link JPanel}
 */
public abstract class CustomPanel extends JPanel {
    
    /**
     * The type of the panel
     */
    protected String type;

    /**
     * The name of the panel
     */
    protected String name;
    
    /**
     * The x position of the panel
     */
    protected int xPos;

    /**
     * The y position of the panel
     */
    protected int yPos;

    /**
     * A spacing constant
     */
    protected final int spacing;

    /**
     * The types of the panel
     */
    public final String[] types = { "deck", "player", "hand", "input" };

    /**
     * Constructor of the CustomPanel
     * 
     * @param type The type of the panel
     * @param name The name of the panel
     * @param xPos The x position of the panel
     * @param yPos The y position of the panel
     * @param spacing The spacing constant
     */
    public CustomPanel(String type, String name, int xPos, int yPos, int spacing) {
        super();
        if(type == null)
            throw new IllegalArgumentException("Can't create a panel with a null type");
        if(!Arrays.asList(types).contains(type))
            throw new IllegalArgumentException("Can't create a panel with a type that is not in the list of types");
        if(name == null)
            throw new IllegalArgumentException("Can't create a panel with a null name");
        this.type = type;
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.spacing = spacing;
    }

    /**
     * Getter for the type of the panel
     * @return The type of the panel
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter for the name of the panel
     * @return The name of the panel
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the x position of the panel
     * @return The x position of the panel
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * Getter for the y position of the panel
     * @return The y position of the panel
     */
    public int getYPos() {
        return this.yPos;
    }
    
    /**
     * Method to treat a pannel by it's components.
     * THis method typically read an object such as a player, a deck, a hand, etc, in order to graphically represent it.
     * 
     * @param o The object to read on.
     */
    public abstract void make(Object o);
}
