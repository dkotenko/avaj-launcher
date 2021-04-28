package com.school21;

import java.util.LinkedList;
import java.util.logging.Logger;

public abstract class Tower {
    private static LinkedList<Flyable> observers = new LinkedList<Flyable>();
    private String msg = "Tower says: %s#%s(%d) ";

    public void register(Flyable flyable)
    {
        if (observers.contains(flyable))
           throw new SimulationException("Aircraft already registered");
        observers.add(flyable);
        String template = msg + "registered to weather tower.";
        Aircraft a = (Aircraft)flyable;

        MessageWriter.writeLine(
                String.format(template, a.getClass().getSimpleName(), a.getName(), a.getId())
        );
    }

    public void unregister(Flyable flyable)
    {
        if (!observers.contains(flyable))
            throw new SimulationException("Aircraft is not registered");
        String template = msg + "unregistered from weather tower.";
        Aircraft a = (Aircraft)flyable;
        a.setLanded(true);
        MessageWriter.writeLine(
                String.format(msg + "landing." , a.getClass().getSimpleName(), a.getName(), a.getId()));
        MessageWriter.writeLine(
                String.format(template, a.getClass().getSimpleName(), a.getName(), a.getId()));
        //observers.remove(flyable);
    }

    private Flyable getObserverByInstance(Flyable flyable)
    {
        return flyable;
    }

    protected void conditionsChanged()
    {
        for (Flyable observer : observers)
        {
            if (!((Aircraft)observer).isLanded())
                observer.updateConditions();
        }


    }
}
