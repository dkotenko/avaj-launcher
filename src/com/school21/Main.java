package com.school21;

import java.io.File;
import java.util.Locale;

public class Main {

    private static boolean isMd5arg(String arg)
    {
        return arg.toLowerCase(Locale.ROOT).equals("md5");
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
        Parser.parse();

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
	// write your code here
    }
}
