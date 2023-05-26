package model.cards;

/**
 * A worthless implementation of the Board abstract class.
 * Used for testing purposes.
*/
public class BlankBoard extends Board {

    @Override
    public void init() {
        return;
    }

    @Override
    public void end() {
        return;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    
}
