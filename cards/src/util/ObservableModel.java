package util;

/**
 * An interface allowing an MVC model to be observable.
 */
public interface ObservableModel {

    /**
     * Adds a listener to the model.
     *
     * @param e a {@link ModelListener} that will be notified of model changes.
     */
    void addListener(ModelListener e);

    /**
     * Removes a listener from the model.
     *
     * @param e a {@link ModelListener} to remove from the model listeners list.
     */
    void removeListener(ModelListener e);

    /**
     * Notifies all listeners that the model has changed.
     */
    public void fireChangement();
}