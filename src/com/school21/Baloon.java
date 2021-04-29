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
                sendMessage("Let's enjoy the good weather and take some pics.", this);
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                sendMessage("Damn you rain! You messed up my baloon.", this);
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                sendMessage("Damn you fog! I can't see anything.", this);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
                sendMessage("It's snowing. We're gonna crash.", this);
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
