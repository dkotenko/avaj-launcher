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
                coordinates.setHeight(coordinates.getHeight() + 2);
                coordinates.setLongitude(coordinates.getLongitude() + 10);
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
        }
        if (coordinates.getHeight() == 0)
            weatherTower.unregister(this);
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
    }
}

