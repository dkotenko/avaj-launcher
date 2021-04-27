package com.school21;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException() {
        super("Flyable already registered");
    }
}
