package com.school21;

public class ScenarioValidationException extends AvajException{
    private static final String text = "Scenario validation error";

    public ScenarioValidationException() {
        super(text);
    }
    public ScenarioValidationException(String message)
    {
        super(message);
    }

    public String message() {
        return text;
    }
}
