package com.school21;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    protected JetPlane (String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        switch (weatherTower.getWeather(coordinates))
        {
            case "SUN":
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
        }
        if (coordinates.getHeight() == 0)


    }


    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;;
    }
}
