package com.school21;

public enum AircraftTypes {
    BALLON("Ballon"),
    HELICOPTER("Helicopter"),
    JETPLANE("JetPlane");

    private final String text;

    AircraftTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
