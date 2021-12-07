package ch.coolsmtp.mail;

import ch.coolsmtp.util.Parser;

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
        for (String bruh : messages) {
            System.out.printf("'%s'\n", bruh);
        }

        String tmp = messages[new Random().nextInt(messages.length)];

        if (!tmp.startsWith("Subject:"))
            return false;

        message = tmp;
        return true;
    }

    public String getMessage() {
        return message;
    }
}
