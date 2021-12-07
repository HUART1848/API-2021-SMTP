package ch.coolsmtp.mail;

import java.util.Arrays;

public class Group {
    private int no;

    private Person sender;
    private Person[] receiver;

    public Group(int no) {
        this.no = no;
    }

    public Group(int no, Person sender, Person... receiver) {
        this.no = no;
        this.sender = sender;
        this.receiver = Arrays.copyOf(receiver, receiver.length);
    }

    public boolean parseGroupFromFile() {
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("=== GROUP %d ===\n", no));

        if (sender == null || receiver == null) {
            sb.append("Empty group...");
            return sb.toString();
        }

        sb.append(String.format("SENDER\n\t%s\n", sender));

        sb.append("RECEIVERS : \n");
        for (Person p : receiver)
            sb.append(String.format("\t%s\n", p));

        return sb.toString();
    }
}
