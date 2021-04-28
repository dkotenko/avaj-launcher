package com.school21;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.Scanner;

public class Parser {
    private static final int MIN = 1;
    private static final int MAX = 10000;

    public static void parse(File file, WeatherTower weatherTower)
            throws NumberFormatException, FileNotFoundException, ScenarioValidationException
    {
        int timesNumber;
        String template = "File %s: Line %d: ";

        Scanner scanner = new Scanner(file);
        /*
            first row
         */
        if (!scanner.hasNextLine())
            throw new ScenarioValidationException("Scenario file is empty");
        try
        {
            timesNumber = Integer.parseInt(scanner.nextLine());
            if (timesNumber < 1)
                throw new ScenarioValidationException("Simulation times number is not positive");
            weatherTower.setTimesNumber(timesNumber);
        }
        catch (NumberFormatException eee)
        {
            throw new  ScenarioValidationException("Invalid number at row 1");
        }
        if (!scanner.hasNextLine())
            throw new ScenarioValidationException("No aircrafts in scenario file");
        /*
            aircraft rows
         */

        int i = 2;
        while (scanner.hasNextLine())
        {
            String[] splitted = scanner.nextLine().split(" ");
            if (splitted.length != 5)
            {
                String error = String.format(
                        template + "invalid number of aircraft parameters",
                        file.getName(), i);
                throw new ScenarioValidationException(error);
            }
            try
            {
                String type = getType(splitted[0]);
                String name = splitted[1];
                int longitude = getInt(splitted[2]);
                int latitude = getInt(splitted[3]);
                int height = getInt(splitted[4]);
                Aircraft a = (Aircraft)AircraftFactory.NewAircraft(
                        type, name, longitude, latitude, height);
                weatherTower.register((Flyable)a);
                registerAircraft(a, type, weatherTower);
            }
            catch (ScenarioValidationException e)
            {
                String error = String.format(
                        template + e.getMessage(),
                        file.getName(), i);
                throw new ScenarioValidationException(error);
            }
            catch (NumberFormatException ee)
            {
                throw new ScenarioValidationException("Invalid number at row " + String.valueOf(i));
            }
            i++;
        }
    }

    private static void registerAircraft(Aircraft a ,String type, WeatherTower weatherTower)
    {
        if (type.equals(AircraftTypes.BALOON.toString()))
        {
            ((Baloon)a).registerTower(weatherTower);
        }
        else if (type.equals(AircraftTypes.HELICOPTER.toString()))
        {
            ((Helicopter)a).registerTower(weatherTower);
        }
        else if (type.equals(AircraftTypes.JETPLANE.toString()))
        {
            ((JetPlane)a).registerTower(weatherTower);
        }
    }



    private static int getInt(String s) throws ScenarioValidationException
    {
        int num = 0;

        try
        {
            num = Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            throw new ScenarioValidationException("Can't read integer value");
        }
        if (num < MIN || num > MAX)
        {
            String template = "The number %s must be between %d and %d";
            throw new ScenarioValidationException(String.format(template, s, MIN, MAX));
        }
        return num;
    }

    private static String getType(String s) throws ScenarioValidationException
    {
        for (AircraftTypes type : AircraftTypes.values())
        {
            if (type.toString().equals(s))
                return s;
        }
        throw new ScenarioValidationException("Invalid aircraft type");
    }
}
