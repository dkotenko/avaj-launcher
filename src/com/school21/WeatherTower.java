package com.school21;

public class WeatherTower extends Tower {
    private int timesNumber;

    public String getWeather(Coordinates coordinates)
    {
        String weather = WeatherProvider
                .getProvider()
                .getCurrentWeather(coordinates);
        return weather;
    }

    void changeWeather()
    {
        conditionsChanged();
    }

    void changeWeatherTimes()
    {
        for (int i = 0; i < timesNumber; i++)
        {
            changeWeather();
        }
    }

    public int getTimesNumber()
    {
        return timesNumber;
    }

    public void setTimesNumber(int timesNumber) {
        this.timesNumber = timesNumber;
    }
}
