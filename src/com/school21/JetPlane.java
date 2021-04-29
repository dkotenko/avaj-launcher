package com.school21;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane (String name, Coordinates coordinates)
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
                sendMessage("Good weather to fly away.", this);
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                sendMessage("It's raining. Better watch out for lightings.", this);
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                sendMessage("I am the Hedgehog in the fog!", this);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
                sendMessage("OMG! Winter is coming!", this);
                break;
        }
        if (coordinates.getHeight() == 0)
            weatherTower.unregister(this);
    }


    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;;
    }
}
