package com.school21;

public class Helicopter extends Aircraft implements Flyable
{
    private WeatherTower weatherTower;

    Helicopter (String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather)
        {
            case "SUN":
                coordinates.setHeight(coordinates.getHeight() + 2);
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                sendMessage("This is hot.", this);
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                sendMessage("No water can stop the attack helicopter", this);
                break;
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                sendMessage("Who is smoking in here?", this);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                sendMessage("My rotor is going to freeze!", this);
                break;
        }
        if (coordinates.getHeight() == 0)
            weatherTower.unregister(this);
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
    }
}

