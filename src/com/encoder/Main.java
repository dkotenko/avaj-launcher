package com.encoder;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    private static String getHash(MessageDigest md, String strToHash)
    {
        byte[] digest;

        md.update(strToHash.getBytes(StandardCharsets.UTF_8));
        digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    private static void exitErrorProgram(String message)
    {
        System.out.println("Error: " + message);
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length != 1)
        {
            System.out.println("Usage: path-to-file-to-encode");
            System.exit(0);
        }

        Scanner scanner = null;
        String algoName = "MD5";
        MessageDigest md = null;

        try
        {
            md = MessageDigest.getInstance(algoName);
        }
        catch (NoSuchAlgorithmException ee)
        {
            exitErrorProgram("Internal error: no MD5 algorithm provided");
        }

        File inputFile = null;
        File outputFile = null;
        PrintWriter writer = null;
        try
        {
            inputFile = new File(args[0]);
            outputFile = new File("encoded_" + inputFile.getName());
            scanner = new Scanner(inputFile);
            writer = new PrintWriter(outputFile);
        }
        catch (FileNotFoundException e)
        {
            exitErrorProgram(String.format("can't find file '%s'", args[0]));
        }


        if (!scanner.hasNextLine())
            exitErrorProgram(String.format("empty file '%s'", args[0]));
        String toWrite = getHash(md, scanner.nextLine());
        writer.println(toWrite);
        while (scanner.hasNextLine())
        {
            toWrite = scanner.nextLine();
            for (String token: toWrite.split(" "))
            {
                writer.print(getHash(md, token));
                writer.print(' ');
            }
            writer.print('\n');
        }
        writer.flush();
        writer.close();
        System.out.println(String.format("%s ENCODED", outputFile.getName()));
    }

}
