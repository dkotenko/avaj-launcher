package com.school21;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Scanner;

public class DecoderMd5 {

    private static final int MIN = 1;
    private static final int MAX = 10000;

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public static String toHex(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars);
    }

    private static String getHash(MessageDigest md, String strToHash)
    {
        md.update(strToHash.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        return  toHex(digest);
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
                String currentId = type.toString().charAt(0) + String.valueOf(i);
                currentIdHash = getHash(md, currentId);
                //System.out.println(currentIdHash + ' ' + currentId);
                if (currentIdHash.equals(toDecode))
                    return currentId;
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
