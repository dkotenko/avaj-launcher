package com.school21;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class    MessageWriter
{
    private static PrintWriter	printWriter;
    private static final String	file = "simulation.txt";

    public static void create()
    {
        try
        {
            printWriter = new PrintWriter(new FileWriter(file));
        }
        catch (IOException exception)
        {
            throw new AvajException("Can't create file " + file);
        }
    }

    public static void close()
    {
        printWriter.close();
    }

    public static void writeLine(String line)
    {
        if (printWriter == null)
            throw new AvajException("Can't write to output file");
        printWriter.println(line);
    }
}