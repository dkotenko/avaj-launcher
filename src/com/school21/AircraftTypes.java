package com.school21;

public enum AircraftTypes {
    BALOON("Baloon"),
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
