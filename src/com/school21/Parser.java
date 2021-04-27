package com.school21;

import java.io.File;
import java.io.FileWriter;
import java.security.MessageDigest;
public class Parser {



    public static void parse(File file, boolean md5mode)
    {
        if (md5mode)
        {
            file = DecoderMd5.decode(file);
            if (file == null)
                return ;

        }


    }
}
