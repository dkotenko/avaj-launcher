package com.school21;

public class AlreadyRegisteredException extends AvajException{
    public AlreadyRegisteredException() {
        super("Flyable already registered");
    }
}
