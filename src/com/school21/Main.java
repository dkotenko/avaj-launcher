package com.school21;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Locale;


public class Main {

    private static boolean isMd5arg(String arg)
    {
        return arg.toLowerCase(Locale.ROOT).equals("-md5");
    }

    private static boolean isMd5Mode(String[] args)
    {
        boolean md5mode;

        md5mode = false;
        for (String arg : args)
        {
            if (isMd5arg(arg))
                md5mode = true;
        }
        return md5mode;
    }

    private static void exitErrorProgram(String message)
    {
        System.out.println("Error: " + message);
        System.exit(0);
    }
    
    

    private static void writeToFile(LinkedList<String> rows, String fileName)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName);
            for(String str: rows) {
                writer.write(str + System.lineSeparator());
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            exitErrorProgram("Cannot create file " + fileName);
        }
    }


    private static File getFileObj(String[] args, boolean md5mode)
    {
        File file;

        file = null;
        for (String arg : args)
        {
            if (md5mode && isMd5arg(arg))
                continue ;
            file = new File(arg);
            break ;
        }
        return file;
    }

    public static void main(String[] args) {

        if (args.length == 0 || args.length > 2)
        {
            System.out.println("Usage: path-to-file [-md5]");
            System.exit(0);
        }

        boolean md5mode = isMd5Mode(args);
        if (md5mode && args.length == 1)
            exitErrorProgram("Usage error: path-to-file argument not provided");
        else if (!md5mode && args.length > 1)
            exitErrorProgram("Invalid argument provided");

        File file = getFileObj(args, md5mode);
        MessageWriter.create();
        if (md5mode)
        {
            try {
                LinkedList<String> decoded = DecoderMd5.decode(file);
                String decodedFileName = "decoded_" + file.getName();
                writeToFile(decoded, decodedFileName);
                file = new File(decodedFileName);
            }
            catch (IOException e)
            {
                exitErrorProgram(String.format("Can't open file '%s'", file.getName()));
            }
            catch (NoSuchAlgorithmException ee)
            {
                exitErrorProgram("Internal error: no MD5 algorithm provided");
            }
            catch (AvajException eee)
            {
                exitErrorProgram(eee.getMessage());
            }
        }

        WeatherTower weatherTower = new WeatherTower();

        try
        {
            Parser.parse(file, weatherTower);
            /*
            run
            */
            weatherTower.changeWeatherTimes();
        }
        catch (FileNotFoundException e)
        {
            exitErrorProgram(String.format("Can't find file '%s'", file.getName()));
        }
        catch (ScenarioValidationException ee)
        {
            exitErrorProgram(ee.getMessage());
        }


        MessageWriter.close();
    }
}
