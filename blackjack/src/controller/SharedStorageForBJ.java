package controller;

/**
 * A class that allow us to use buttons to listen to the player's decision.
 */
public class SharedStorageForBJ {
    
    /**
     * Indicates if the player wants to draw or pass.
     */
    protected Boolean isDraw;

    /**
     * Constructor of the class.
     */
    public SharedStorageForBJ() {
        this.isDraw = null;
    }

    /**
     * Getter for isDraw.
     * @return the value of the attribute isDraw.
     */
    public Boolean getIsDraw() {
        return this.isDraw;
    }

    /**
     * Setter for isDraw.
     * @param isDraw the new value of the attribute isDraw.
     */
    public void setIsDraw(Boolean isDraw) {
        this.isDraw = isDraw;
    }
}
