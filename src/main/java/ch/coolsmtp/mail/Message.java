package ch.coolsmtp.mail;

import ch.coolsmtp.util.Parser;

public class Message {
    private String message;

    public Message() {

    }

    public boolean getMessageFromFile(String filename) {
        String tmp = Parser.getContentFromFile(filename);
        if (tmp == null || !tmp.startsWith("Subject:"))
            return false;

        this.message = tmp;
        return true;
    }

    public String getMessage() {
        return message;
    }
}
