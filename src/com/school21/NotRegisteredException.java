package com.school21;

public class NotRegisteredException extends AvajException{
    public NotRegisteredException() {
        super("Invalid Aircraft type");
    }
}
