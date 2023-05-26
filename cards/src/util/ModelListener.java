package util;

/**
 * Abstract Object containing the required elements in a Listener in a MVC configuration.
 */
public interface ModelListener{

    /**
     * Method called when an update of the listened model occurs.
     * @param source The source of the update.
     */
    void onModelUpdate(Object source);
}