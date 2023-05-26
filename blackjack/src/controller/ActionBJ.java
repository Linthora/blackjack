package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A implementation of the ActionListener interface that allows us to use buttons to listen to the player's decision.
 * Extends java.awtActionListener.
 */
public class ActionBJ implements ActionListener {
    
    /**
     * A reference to the controller to notify it of the player's decision.
     */
    private BlackjackController controller;

    /**
     * The shared storage to store the player's decision before notifying the controller.
     */
    private SharedStorageForBJ storage;

    /**
     * A boolean indicating if this button is for drawing or passing.
     */
    private boolean isDraw;
    
    /**
     * Constructor of the class.
     * @param controller the controller to notify of the player's decision.
     * @param storage the shared storage to store the player's decision before notifying the controller.
     * @param isDraw a boolean indicating if this button is for drawing or passing.
     */
    public ActionBJ(BlackjackController controller, SharedStorageForBJ storage, boolean isDraw) {
        this.controller = controller;
        this.storage = storage;
        this.isDraw = isDraw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        storage.setIsDraw(this.isDraw);
        controller.notifyPlayerDecision();
    }
    
}