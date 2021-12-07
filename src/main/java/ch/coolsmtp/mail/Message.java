package ch.coolsmtp.mail;

import ch.coolsmtp.util.Parser;
import jdk.swing.interop.SwingInterOpUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class Message {
    private String message;

    public Message() {

    }

    public boolean setRandomMessageFromFile(String filename) {
        String content = Parser.getContentFromFile(filename);
        if (content == null)
            return false;

        String[] messages = content.split("===\n");

        String tmp = messages[new Random().nextInt(messages.length)];

        if (!tmp.startsWith("Subject:"))
            return false;

        String newSubject = tmp.split("\n")[0].split(": ")[1];
        newSubject = String.format("Subject: =?utf-8?Q?%s?=", newSubject);

        message = tmp.replaceFirst(".*?\r\n", newSubject);

        return true;
    }

    public String getMessage() {
        return message;
    }
}
