package com.school21;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException(Aircraft aircraft) {
        super("Flyable already registered");
    }
}
