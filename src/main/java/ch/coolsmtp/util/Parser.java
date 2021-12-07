package ch.coolsmtp.util;

import java.io.*;
import java.nio.charset.*;

public class Parser {
    private static final Charset STANDARD_CHARSET = StandardCharsets.UTF_8;

    private static String getContentFromFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), STANDARD_CHARSET));
        } catch (IOException e) {

            return "";
        }
        return "";
    }

    public static String getRandomLineFromFile(String filename) {
        return "";
    }

    public static String parseConfigFromFile(String filename) {

        return "";
    }
}
