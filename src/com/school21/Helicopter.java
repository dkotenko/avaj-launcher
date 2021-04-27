package com.school21;

public class Helicopter extends Aircraft implements Flyable
{
    private WeatherTower weatherTower;

    protected Helicopter (String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather)
        {
            case "SUN":
                coordinates.
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
    }
}

