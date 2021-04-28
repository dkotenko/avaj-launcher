package com.school21;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon (String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather)
        {
            case "SUN":
                coordinates.setHeight(coordinates.getHeight() + 4);
                coordinates.setLongitude(coordinates.getLongitude() + 2);
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() + 5);
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
        }
        if (coordinates.getHeight() == 0)
            weatherTower.unregister(this);
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
    }
}
