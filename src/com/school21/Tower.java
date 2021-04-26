package com.school21;

import java.util.LinkedList;

public abstract class Tower {
    private LinkedList<Flyable> observers;

    public void register(Flyable flyable)
    {
        if (observers.contains(flyable))
            throw new AlreadyRegisteredException();
        observers.add(flyable);
    }

    public void unregister(Flyable flyable)
    {
        if (!observers.contains(flyable))
            throw new NotRegisteredException();
        observers.remove(flyable);
    }

    private Flyable getObserverByInstance(Flyable flyable)
    {

    }

    protected void conditionsChanged()
    {

    }
}
