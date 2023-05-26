package util;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstact Object containing the elements required by an Observable Model in a MVC configuration.
 */
public abstract class AbstractObservableModel implements ObservableModel {
    
    /**
     * List of {@link ModelListener} listening to the current model.
     */
    protected List<ModelListener> listeners;
    
    /**
     * Creates an Observable Model containing the given List of listeners.
     * @param listeners The List of listeners.
     */
    public AbstractObservableModel(List<ModelListener> listeners) {
        if(listeners==null)
            this.listeners = new LinkedList<>();
        else 
            this.listeners = listeners;
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(ModelListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Empty the List of the listeners.
     */
    public void clearListener() {
        this.listeners.clear();
    }

    @Override
    public void fireChangement() {
        for(ModelListener listener : this.listeners)
            listener.onModelUpdate(this);
    }

    /**
     * Get the List of listeners.
     * @return The List of listeners.
     */
    public List<ModelListener> getListeners() {
        return listeners;
    }
}