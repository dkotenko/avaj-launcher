package com.school21;

abstract public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    private String type;
    private boolean landed;
    protected static final String templateMessage = "%s#%s(%d): ";

    public boolean isLanded() {
        return landed;
    }

    public void setLanded(boolean landed) {
        this.landed = landed;
    }

    public Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
        landed = false;
    }

    public Aircraft(String name, String type, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.type = type;
        id = nextId();
    }

    protected static void sendMessage(String message, Aircraft a)
    {
        MessageWriter.writeLine(String.format(
                templateMessage + message,
                a.getClass().getSimpleName(),
                a.getName(),
                a.getId()
                ));
    }

    private long nextId()
    {
        return ++idCounter;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


}
