package com.school21;

public class NotRegisteredException extends RuntimeException{
    public NotRegisteredException() {
        super("Invalid Aircraft type");
    }
}
