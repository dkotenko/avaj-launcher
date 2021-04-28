package com.school21;

public class SimulationException extends AvajException{
    private static final String text = "Simulation error";
    public SimulationException() {
        super("Simulation error");
    }
    public SimulationException(String message)
    {
        super(text + ": " + message);
    }

    public String message() {
        return text;
    }
}
