package com.school21;

public class InvalidAircraftTypeException extends RuntimeException
{
    public InvalidAircraftTypeException(String typeName) {
        super("Invalid Aircraft type: " + typeName);
    }
}
