package com.school21;

public class InvalidAircraftTypeException extends AvajException
{
    public InvalidAircraftTypeException(String typeName) {
        super("Invalid Aircraft type: " + typeName);
    }
}
