package com.school21;

abstract public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private long idCounter;

    public Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
    }

    private long nextId()
    {
        return ++idCounter;
    }
}
