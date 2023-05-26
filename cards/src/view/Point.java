package view;

/**
 * Simple class representing a point
 */
public class Point {

    /**
     * The x coordinate of the point
     */
    protected int x;
    
    /**
     * The y coordinate of the point
     */
    protected int y;

    /**
     * Constructor of the Point
     * 
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x coordinate of the point
     * @return The x coordinate of the point
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y coordinate of the point
     * @return The y coordinate of the point
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for the x coordinate of the point
     * @param x The new x coordinate of the point
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Setter for the y coordinate of the point
     * @param y The new y coordinate of the point
     */
    public void setY(int y) {
        this.y = y;
    }
}
