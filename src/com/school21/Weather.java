package com.school21;

public enum Weather {
    SUN("SUN"),
    FOG("FOG"),
    RAIN("RAIN"),
    SNOW("SNOW");

    private final String text;

    Weather(final String text) {
         this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
