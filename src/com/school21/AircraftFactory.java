package com.school21;

public class AircraftFactory {

    public Flyable AircraftFactory(String type, String name, int longitude, int latitude, int height) {
        switch (type)
        {
            case "Ballon":
                return new Baloon(name, new Coordinates(longitude, latitude, height));
            case "Helicopter":
                return new Helicopter(name, new Coordinates(longitude, latitude, height));
            case "JetPlane":
                return new JetPlane(name, new Coordinates(longitude, latitude, height));
            default:
                throw new InvalidAircraftTypeException(type);
        }
    }


}
