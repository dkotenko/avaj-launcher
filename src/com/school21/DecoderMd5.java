package com.school21;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DecoderMd5 {

    private static final int MIN = 1;
    private static final int MAX = 10000;

    private static String getHash(MessageDigest md, String strToHash)
    {
        byte[] digest;

        md.update(strToHash.getBytes(StandardCharsets.UTF_8));
        digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    private static String getDecodedInt(MessageDigest md, String toDecode)
    {
        for (int i = MIN; i < MAX; i++)
        {
            if (getHash(md, String.valueOf(i)).equals(toDecode))
                return String.valueOf(i);
        }
        return null;
    }

    private static String getDecodedType(MessageDigest md, String toDecode)
    {
        for (AircraftTypes type : AircraftTypes.values())
        {
            if (getHash(md, type.toString()).equals(toDecode))
                return type.toString();
        }
        return null;
    }

    private static String getDecodedName(MessageDigest md, String toDecode)
    {
        String currentIdHash;

        for (int i = MIN; i < MAX; i++)
        {
            for (AircraftTypes type : AircraftTypes.values())
            {
                currentIdHash = getHash(md, type.toString() + String.valueOf(i));
                if (currentIdHash.equals(toDecode))
                    return currentIdHash;
            }
        }
        return toDecode;
    }

    public static LinkedList<String> decode(File file) throws IOException, NoSuchAlgorithmException, AvajException
    {
        String timesNumber;
        LinkedList<String> rows = new LinkedList<String>();
        String algoName = "MD5";

        MessageDigest md = MessageDigest.getInstance(algoName);

        Scanner scanner = new Scanner(file);
        /*
            first row
         */
        if (!scanner.hasNextLine())
            throw new ScenarioValidationException("Scenario file is empty");
       timesNumber = getDecodedInt(md, scanner.nextLine());
        if (timesNumber == null)
            throw new ScenarioValidationException("Invalid simulations times number");
        rows.add(timesNumber);
        /*
            aircraft rows
         */
        while (scanner.hasNextLine())
        {
            String[] splitted = scanner.nextLine().split(" ");
            if (splitted.length != 5)
                throw new ScenarioValidationException("Invalid number of aircraft params");
            splitted[0] = getDecodedType(md, splitted[0]);
            splitted[1] = getDecodedName(md, splitted[1]);
            splitted[2] = getDecodedInt(md, splitted[2]);
            splitted[3] = getDecodedInt(md, splitted[3]);
            splitted[4] = getDecodedInt(md, splitted[4]);
            for (int i = 1; i < 5; i++)
            {
                splitted[0] += " " + splitted[i];
            }
            rows.add(splitted[0]);
        }
        return (rows);
    }
}
