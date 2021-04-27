package com.school21;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
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

    private static int getDecodedInt(MessageDigest md, String toDecode)
    {
        for (int i = MIN; i < MAX; i++)
        {
            if (getHash(md, String.valueOf(i)).equals(toDecode))
                return i;
        }
        return 0;
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

    private static String getDecodedId(MessageDigest md, String toDecode)
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
        return null;
    }

    private static void decode(File file) throws IOException, NoSuchAlgorithmException
    {
        int timesNumber;
        File decoded = new File("decoded_" + file.getName());
        FileWriter writer = null;
        String algoName = "MD5";

        MessageDigest md = MessageDigest.getInstance("MD5");
        decoded.createNewFile();

        Scanner scanner = new Scanner(file);
        writer = new FileWriter(decoded);
        /*
            first row
         */
        if (!scanner.hasNextLine())
            throw ScenarioFileIsEmpty();
       timesNumber = getDecodedInt(md, scanner.nextLine());
        if (timesNumber == 0)
            throw InvalidSimulationTimesNumber();
        /*
            aircraft rows
         */
        while (scanner.hasNextLine())
        {
            String[] splitted = scanner.nextLine().split(" ");

            createAircraft(splitted);
        }



        writer.write("Hello user3821496\n"); //just an example how you can write a String to it
        writer.flush();
        writer.close();
    }
}
