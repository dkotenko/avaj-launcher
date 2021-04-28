package com.school21;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Locale;


public class Main {

    private static boolean isMd5arg(String arg)
    {
        return arg.toLowerCase(Locale.ROOT).equals("md5");
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
            System.out.println("Cannot create file " + fileName);
        }
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
        boolean md5mode = isMd5Mode(args);
        File file = getFileObj(args, md5mode);
        MessageWriter.create();

        if (md5mode)
        {
            try {
                LinkedList<String> decoded = DecoderMd5.decode(file);
                writeToFile(decoded, "decoded_" + file.getName());
            }
            catch (IOException e)
            {
                System.out.println("Can't open file " + file.getName());
            }
            catch (NoSuchAlgorithmException ee)
            {
                System.out.println("No algorithm MD5 " + file.getName());
            }
            catch (AvajException eee)
            {
                System.out.println(eee.getMessage());
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
            System.out.println("Can'' find file scenario.txt");
        }
        catch (ScenarioValidationException ee)
        {
            System.out.println(ee.getMessage());
        }


        MessageWriter.close();
    }
}
