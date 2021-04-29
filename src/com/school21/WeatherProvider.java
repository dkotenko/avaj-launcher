package com.school21;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather (Coordinates coordinates) {
        boolean precipitation;
        String weather;

        precipitation =  (Math.random() < 0.5);
        if (!precipitation)
            weather = Weather.SUN.toString();
        else if (coordinates.getLatitude() > 150)
            weather = Weather.SNOW.toString();
        else if (coordinates.getHeight() < 200)
            weather = Weather.FOG.toString();
        else
            weather = Weather.RAIN.toString();
        return weather;
    }
}
