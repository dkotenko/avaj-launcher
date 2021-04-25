package com.school21;

abstract public class Aircraft {
    long id;
    String name;
    Coordinates coordinates;
    long idCounter;

    public Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public long nextId()
    {
        return ++idCounter;
    }
}
